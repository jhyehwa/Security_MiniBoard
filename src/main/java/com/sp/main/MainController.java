package com.sp.main;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mainController")
public class MainController {

	// 사용자 로그인 확인(로그인이 안된 상태에서 게시판을 누르면 로그인후 게시판으로 이동하지만 헤더 가장 윗부분은 로그인 | 회원가입으로 표시된다. 다시 메인으로 이동해야 한다.)
	// 따라서 실제로 사용자 로그인 정보등을 세션에 저장은 authentication-success-handler-ref 에 정의된 객체의 클래스에서 설정한다.
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String method(HttpSession session) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = null;
		boolean isAnonymous = true;
		if(principal instanceof UserDetails) {
			userId = ((UserDetails)principal).getUsername();
			isAnonymous = false;
		} else {
			userId = principal.toString();
			isAnonymous = true;
		}
		
		session.setAttribute("username", userId);
		session.setAttribute("isAnonymous", isAnonymous);
		
		return "main/main";
	}
}
