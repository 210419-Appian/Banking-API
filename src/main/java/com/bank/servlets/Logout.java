package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		HttpSession ses = req.getSession(false);
		
		
		if(ses != null) {
			resp.setStatus(202);
			pw.print("{\"message\": \"You have successfully logged out {" + ses.getAttribute("username") + "}\"}");
			ses.invalidate();
		}else {
			resp.setStatus(400);
			pw.print("{\"message\": \"There was no user logged into the session\"}");
		}
	}
	
}
