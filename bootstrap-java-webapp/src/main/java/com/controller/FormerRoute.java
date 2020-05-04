package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormerRoute implements Business{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "/WEB-INF/views/basic_former.html";
	}

}
