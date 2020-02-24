package com.yss.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/check/*",filterName = "loginFilter")
public class LoginFilter implements Filter {

	private Logger log=LoggerFactory.getLogger(LoginFilter.class);
	@Override
	public void destroy() {
		log.info("filter destory.....");
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		log.info("filter doFilter.....");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("filter init.....");
	}

}
