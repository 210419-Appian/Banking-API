package com.bank.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.models.User;
import com.bank.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Users extends HttpServlet{
	private ObjectMapper om = new ObjectMapper();
	private UserService us = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession ses = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		String json = null;
		String[] sections = url.split("/");
		
		System.out.println(sections.length);
		System.out.println(url);
		for(int i = 0; i < sections.length; i++) {
			System.out.println(sections[i]);
		}
		
		if(ses != null) {
			if(sections.length == 3 && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee"))) { //find users
				List<User> myList = us.getAllUser();
				
				json = om.writeValueAsString(myList);
				resp.setStatus(200);
			}else if(sections.length == 3) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else if(sections.length == 4 && ses.getAttribute("role").equals("Admin")){ //find users by ID
				User myUser = us.getUserById(Integer.parseInt(sections[3]));
				
				json = om.writeValueAsString(myUser);
				resp.setStatus(200);
			}else if(sections.length == 4) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else{
				json = "{\"message\": \"The requested action was invalid\"}";
				resp.setStatus(404);
			}
		}else {
			json = "{\"message\": \"The requested action is not permitted\"}";
			resp.setStatus(401);
		}
		
		resp.setContentType("application/json");
		pw.print(json);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String url = req.getRequestURI();
		HttpSession ses = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		String json = null;
		String[] sections = url.split("/");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line = reader.readLine();
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		String body = new String(sb);
		User myUser = om.readValue(body, User.class);
		
		System.out.println(sections.length);
		System.out.println(url);
		for(int i = 0; i < sections.length; i++) {
			System.out.println(sections[i]);
		}
		
		if(ses != null) {
			//TODO: consider putting the userId in the session
			boolean selfUpdate = ((us.getUserByUsername((String) ses.getAttribute("username")).getUserId()) == (myUser.getUserId()));
			if(sections.length == 3 && (ses.getAttribute("role").equals("Admin") || selfUpdate )) { //update user
				//TODO: consider making a more efficient way of checking the user ID later
				
				//TODO: Consider adding additional security. Now users can change themselves to admins.
				
				if(us.updateUser(myUser)) { //if the update succeeds...
					if(selfUpdate) { //update username for session if necessary
						ses.setAttribute("username", myUser.getUsername());
					}
					
					//Getting a new object to show the accounts
					json = om.writeValueAsString(us.getUserById(myUser.getUserId()));
					resp.setStatus(200);
				}//TODO: consider making a better way to handle invalid data
				
			}else if(sections.length == 3) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else{
				json = "{\"message\": \"The requested action was invalid\"}";
				resp.setStatus(404);
			}
		}else {
			json = "{\"message\": \"The requested action is not permitted\"}";
			resp.setStatus(401);
		}
		
		resp.setContentType("application/json");
		pw.print(json);
	}
}
