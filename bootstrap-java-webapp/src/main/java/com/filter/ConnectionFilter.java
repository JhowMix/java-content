package com.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import com.sql.ConnectionFactory;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			request.setAttribute("connection", connection);
			chain.doFilter(request, response);
			connection.close();	
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {

	}
}
