package com.bus.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bus.beans.Customer;

@Component
public class BusFilter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int i = response.getStatus();
		if(i==500) {
			response.sendRedirect("/loginForm");
			return false;
		}
		return true;
	}
	
	

}
