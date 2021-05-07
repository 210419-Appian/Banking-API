package com.bank.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.models.User;
import com.bank.models.UserDTO;
import com.bank.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Login extends HttpServlet {
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		UserDTO udto = om.readValue(body, UserDTO.class);
		
		if(us.validateLogin(udto)) {
			User myUser = us.getUserByUsername(udto.username);
			HttpSession ses = req.getSession();
			ses.setAttribute("username", myUser.getUsername());
			ses.setAttribute("role", myUser.getRole().getRole());
			
			pw.print(om.writeValueAsString(myUser));
		}else {
			resp.setStatus(400);
			pw.print("{\"message\": \"Invalid Credentials\"}");
		}
		
	}
	
}
