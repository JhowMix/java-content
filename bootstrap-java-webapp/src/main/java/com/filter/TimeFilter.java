package com.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TimeFilter implements Filter{
	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		long init = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		String uri = ((HttpServletRequest) request).getRequestURI();
		String parameters = ((HttpServletRequest) request).getParameter("logic");
		
		logger.info("Request Time [" + uri + "?controller=" + parameters + "]: " + (end - init) + "ms");
				
	}

	@Override
	public void destroy() {

	}

}
