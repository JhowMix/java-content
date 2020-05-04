package com.controller;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.model.User;

public class FormerController implements Business{

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection connection = (Connection) req.getAttribute("connection");
		String name = req.getParameter("name");
		String ID = req.getParameter("ID");
		UserDAO userDAO = new UserDAO(connection);
		User user = new User();

		user.setID(Integer.parseInt(ID));
		user.setName(name);
		userDAO.create(user);
		
		logger.info("ID: " + ID + "\t NAME: " + name + "\t STATUS: CREATED");
		return "/index.html";
	}

}
