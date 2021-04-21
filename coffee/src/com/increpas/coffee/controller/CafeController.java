package com.increpas.coffee.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public interface CafeController {
	String exec(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
