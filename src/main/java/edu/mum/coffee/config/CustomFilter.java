package edu.mum.coffee.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.*;

public class CustomFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		request.getSession().setAttribute("email", request.getParameter("username"));
		System.out.println("uss"+ request.getParameter("username"));
		return super.attemptAuthentication(request, response); 
	}
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		request.getSession().setAttribute("email", request.getParameter("username"));
		System.out.println("uss"+ request.getUserPrincipal());
		return "abc";
	}
}
