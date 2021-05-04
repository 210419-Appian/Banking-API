package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String URI = req.getRequestURI();
		System.out.println(URI);
		
		PrintWriter pw = resp.getWriter();
		
		pw.print("<h1>Cool test</h1>");
	}
	
}
