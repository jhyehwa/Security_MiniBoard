package com.sp.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("member.memberController")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String loginForm(String login_error, Model model) {
		boolean bLoginError = login_error != null;
		
		if(bLoginError) {
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
		if(message == null || message.length() == 0) {
			return "redirect:/";
		}
		
		return "member/complete";
	}
	
}
