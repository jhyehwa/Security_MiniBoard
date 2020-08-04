package com.sp.bbs;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.common.FileManager;
import com.sp.common.MyUtil;
import com.sp.member.SessionInfo;

@Controller("bbs.bbsController")
@RequestMapping("/bbs/*")
public class BoardController {

	@Autowired
	private BoardService service;

	@Autowired
	private MyUtil myUtil;

	@Autowired
	private FileManager fileManager;

	@RequestMapping(value = "list")
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int current_page,
			HttpServletRequest req,
			Model model) throws Exception {

		String cp = req.getContextPath();

		int rows = 10;
		int total_page = 0;
		int dataCount = 0;

		Map<String, Object> map = new HashMap<String, Object>();

		dataCount = service.dataCount(map);
		if (dataCount != 0) {
			total_page = myUtil.pageCount(rows, dataCount);
		}

		if (total_page < current_page) {
			current_page = total_page;
		}

		int offset = (current_page - 1) * rows;
		if (offset < 0) {
			offset = 0;
		}

		map.put("offset", offset);
		map.put("rows", rows);

		List<Board> list = service.listBoard(map);

		int listNum, n = 0;
		for (Board dto : list) {
			listNum = dataCount - (offset + n);
			dto.setListNum(listNum);
			n++;
		}

		String query = "";
		String listUrl = cp + "/bbs/list";
		String articleUrl = cp + "/bbs/article?page=" + current_page;
		if (query.length() != 0) {
			listUrl = cp + "/bbs/list?" + query;
			articleUrl = cp + "/bbs/article?page=" + current_page + "&" + query;
		}

		String paging = myUtil.paging(current_page, total_page, listUrl);

		model.addAttribute("list", list);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);

		return "bbs/list";
	}

	@RequestMapping(value = "created", method = RequestMethod.GET)
	public String createdForm(Model model) throws Exception {

		model.addAttribute("mode", "created");

		return "bbs/created";
	}

	@RequestMapping(value = "created", method = RequestMethod.POST)
	public String createdSubmit(Board dto, HttpSession session) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String root = session.getServletContext().getRealPath("/");
		String pathName = root + "uploads" + File.separator + "bbs";

		try {
			dto.setUserId(info.getUserId());
			dto.setUserName(info.getUserName());
			service.insertBoard(dto, pathName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "redirect:/bbs/list";
	}

	@RequestMapping("article")
	public String article(@RequestParam int num, @RequestParam String page, Model model) throws Exception {

		String query = "page=" + page;
		service.updateHitCount(num);

		Board dto = service.readBoard(num);
		if (dto == null) {
			return "redirect:.bbs/list?" + query;
		}

		dto.setContent(myUtil.htmlSymbols(dto.getContent()));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);

		Board preReadBoard = service.preReadBoard(map);
		Board nextReadBoard = service.nextReadBoard(map);

		model.addAttribute("dto", dto);
		model.addAttribute("preReadBoard", preReadBoard);
		model.addAttribute("nextReadBoard", nextReadBoard);

		model.addAttribute("page", page);
		model.addAttribute("query", query);

		return "bbs/article";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(
			@RequestParam int num,
			@RequestParam String page,
			HttpSession session,
			Model model) throws Exception {
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		Board dto = service.readBoard(num);
		if (dto == null) {
			return "redirect:/bbs/list?page=" + page;
		}

		if (!info.getUserId().equals(dto.getUserId())) {
			return "redirect:/bbs/list?page=" + page;
		}

		model.addAttribute("dto", dto);
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);

		return "bbs/created";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateSubmit(Board dto, @RequestParam String page, HttpSession session) throws Exception {

		String root = session.getServletContext().getRealPath("/");
		String pathName = root + "uploads" + File.separator + "bbs";

		try {
			service.updateBoard(dto, pathName);
		} catch (Exception e) {
		}

		return "redirect:/bbs/list?page=" + page;
	}

	@RequestMapping(value = "deleteFile")
	public String deleteFile(@RequestParam int num, @RequestParam String page, HttpSession session) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String root = session.getServletContext().getRealPath("/");
		String pathName = root + "uploads" + File.separator + "bbs";

		Board dto = service.readBoard(num);
		if (dto == null) {
			return "redirect:/bbs/list?page=" + page;
		}

		if (!info.getUserId().equals(dto.getUserId())) {
			return "redirect:/bbs/list?page=" + page;
		}

		try {
			if (dto.getSaveFileName() != null) {
				fileManager.doFileDelete(dto.getSaveFileName(), pathName); // 실제파일삭제
				dto.setSaveFileName("");
				dto.setOriginalFileName("");
				service.updateBoard(dto, pathName); // DB 테이블의 파일명 변경(삭제)
			}
		} catch (Exception e) {
		}

		return "redirect:/bbs/update?num=" + num + "&page=" + page;
	}

	@RequestMapping(value = "delete")
	public String delete(
			@RequestParam int num,
			@RequestParam String page,
			HttpSession session) throws Exception {
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String query = "page=" + page;
		String root = session.getServletContext().getRealPath("/");
		String pathName = root + "uploads" + File.separator + "bbs";

		service.deleteBoard(num, pathName, info.getUserId());

		return "redirect:/bbs/list?" + query;
	}

	@RequestMapping(value = "download")
	public void download(
			@RequestParam int num,
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session) throws Exception {

		String root = session.getServletContext().getRealPath("/");
		String pathName = root + "uploads" + File.separator + "bbs";

		Board dto = service.readBoard(num);

		if (dto != null) {
			boolean b = fileManager.doFileDownload(dto.getSaveFileName(), dto.getOriginalFileName(), pathName, resp);
			if (b) {
				return;				
			}
		}

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<script>alert('파일 다운로드가 실패 했습니다.');history.back();</script>");
	}
}
