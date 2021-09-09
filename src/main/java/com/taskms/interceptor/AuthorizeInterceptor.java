package com.taskms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.taskms.entity.Account;

@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			session.setAttribute("back-uri", request.getRequestURI());
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
}
