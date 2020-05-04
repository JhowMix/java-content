package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Business {
	String execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
