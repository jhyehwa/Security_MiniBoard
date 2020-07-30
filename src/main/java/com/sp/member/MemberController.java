package com.sp.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("member.memberController")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String loginForm(String login_error, Model model) {
		boolean bLoginError = login_error != null;

		if (bLoginError) {
			String msg = "아이디 또는 비밀번호를 잘못 입력 하셨습니다.";
			model.addAttribute("message", msg);
		}

		return "member/login";
	}

	// 접근 오서라이제이션(Authorization:권한)이 없는 경우
	@RequestMapping(value = "/noAuthorized")
	public String noAuthorized() {

		return "member/noAuthorized";
	}

	@RequestMapping(value = "/member/member", method = RequestMethod.GET)
	public String memberForm(Model model) {
		model.addAttribute("mode", "member");

		return "member/member";
	}

	@RequestMapping(value = "/member/member", method = RequestMethod.POST)
	public String memberSubmit(Member dto, final RedirectAttributes reAttr, Model model) throws Exception {

		String pwd = bcrypt.encode(dto.getUserPwd());
		dto.setUserPwd(pwd);

		try {
			service.insertMember(dto);
		} catch (Exception e) {
			model.addAttribute("message", "회원가입 실패");
			model.addAttribute("mode", "member");
			return "member/member";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dto.getUserName() + " 님의 회원가입이 정상적으로 처리되었습니다.");

		reAttr.addFlashAttribute("message", sb.toString());
		reAttr.addFlashAttribute("title", "회원 가입");

		return "redirect:/member/complete";
	}

	@RequestMapping(value = "/member/complete")
	public String complete(@ModelAttribute("message") String message) throws Exception {
		if (message == null || message.length() == 0) {
			return "redirect:/";
		}

		return "member/complete";
	}

	@RequestMapping(value = "/member/pwd", method = RequestMethod.GET)
	public String pwdForm(String memberOut, Model model, HttpSession session) {

		if (memberOut == null) {
			model.addAttribute("title", "정보수정");
			model.addAttribute("mode", "update");
		} else {
			model.addAttribute("title", "회원탈퇴");
			model.addAttribute("mode", "memberOut");
		}

		return "member/pwd";
	}

	@RequestMapping(value = "/member/pwd", method = RequestMethod.POST)
	public String pwdSubmit(@RequestParam(value = "userPwd") String userPwd, @RequestParam(value = "mode") String mode,
			final RedirectAttributes reAttr, Model model, HttpSession session) {

		SessionInfo info = (SessionInfo) session.getAttribute("member");

		Member dto = service.readMember(info.getUserId());
		if (dto == null) {
			session.invalidate();
			return "redirect:/";
		}

		// 패스워드 검사
		boolean bPwd = bcrypt.matches(userPwd, dto.getUserPwd());

		if (bPwd) {
			if (mode.equals("update")) {
				model.addAttribute("dto", dto);
				model.addAttribute("mode", "update");
				model.addAttribute("title", "회원정보수정");

				return "member/member";
			} else if (mode.equals("memberOut")) {
				// 회원 탈퇴
				try {
					if (!info.getUserId().equals("admin")) {
						service.deleteMember(info.getUserId());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				session.removeAttribute("member");
				session.invalidate();

				StringBuilder sb = new StringBuilder();
				sb.append(dto.getUserName() + "님의 회원 탈퇴 처리가 정상적으로 처리되었습니다.");

				reAttr.addFlashAttribute("title", "회원탈퇴");
				reAttr.addFlashAttribute("message", sb.toString());

				return "redirect:/member/complete";
			}
		}

		model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
		if (mode.equals("update")) {
			model.addAttribute("title", "정보 수정");
			model.addAttribute("mode", "update");
		} else {
			model.addAttribute("title", "회원 탈퇴");
			model.addAttribute("mode", "memberOut");
		}
		return "member/pwd";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updateSubmit(Member dto, final RedirectAttributes reAttr, HttpSession session) throws Exception {
		
		// 패스워드 암호화
		String pwd = bcrypt.encode(dto.getUserPwd());
		dto.setUserPwd(pwd);
		
		try {
			service.updateMember(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dto.getUserName() + "님의 회원정보가 정상적으로 변경되었습니다.");
		
		reAttr.addFlashAttribute("title", "회원정보수정");
		reAttr.addFlashAttribute("message", sb.toString());
		
		return "redirect:/member/complete";
	}

}
