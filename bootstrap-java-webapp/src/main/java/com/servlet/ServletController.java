package com.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Business;

@WebServlet(urlPatterns = { "/starter" })
public class ServletController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controllerName = request.getParameter("controller");
		String path = "com.controller." + controllerName;
		
		try {
			Class<?> controllerClass = Class.forName(path);
			Business controller = (Business) controllerClass.newInstance();
			String page = controller.execute(request, response);
			
			request.getRequestDispatcher(page).forward(request, response);
		} catch (Exception error) {
			throw new ServletException("Error on dispatching page.", error);
		}
	}
}
