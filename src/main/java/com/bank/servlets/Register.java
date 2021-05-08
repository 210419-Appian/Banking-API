package com.bank.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.models.User;
import com.bank.models.UserDTO;
import com.bank.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Register extends HttpServlet {
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		HttpSession ses = req.getSession(false);
		
		System.out.println(ses != null);
		
		if(ses != null && ses.getAttribute("role").equals("Admin")) {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			
			User myUser = om.readValue(body, User.class);
			User newUser = us.addUser(myUser);
			
			
			if(newUser != null) {
				resp.setStatus(201);
				
				resp.setContentType("application/json");
				pw.print(om.writeValueAsString(newUser));
			}else {
				resp.setStatus(400);
				
				resp.setContentType("application/json");
				pw.print("{\"message\": \"Invalid fields\"}");
			}
		}else {
			resp.setStatus(401);
			
			resp.setContentType("application/json");
			pw.print("{\"message\": \"The requested action is not permitted\"}");
		}
	}
	
}
