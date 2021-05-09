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

import com.bank.models.Account;
import com.bank.models.User;
import com.bank.services.AccountService;
import com.bank.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Accounts extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private UserService us = new UserService();
	private AccountService as = new AccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession ses = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		String json = null;
		String[] sections = url.split("/");
		
		if(ses != null) {
			if(sections.length == 3 && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee"))) { //Find accounts
				List<Account> myList = as.getAllAccount();
				
				json = om.writeValueAsString(myList);
				resp.setStatus(200);
			}else if(sections.length == 3) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else if(sections.length == 4 && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee"))) { //Find accounts by id
				Account a = as.getAccountById(Integer.parseInt(sections[3]));
				
				json = om.writeValueAsString(a);
				resp.setStatus(200);
			}else if(sections.length == 4) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else if(sections.length == 5 && sections[3].equals("status") && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee"))) { //Find accounts by status
				List<Account> a = as.getAccountsByStatus(Integer.parseInt(sections[4]));
				
				json = om.writeValueAsString(a);
				resp.setStatus(200);
			}else if(sections.length == 5 && sections[3].equals("status")) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}else if(sections.length == 5 && sections[3].equals("owner") && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee"))) { //Find accounts by user
				List<Account> a = as.getAccountsByUser(Integer.parseInt(sections[4]));
				
				json = om.writeValueAsString(a);
				resp.setStatus(200);
			}else if(sections.length == 5 && sections[3].equals("owner")) {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		Account myAccount = om.readValue(body, Account.class);
		
		if(ses != null) {
			boolean selfUpdate = (us.getUserByUsername((String) ses.getAttribute("username")).getUserId()) == myAccount.getUserId();
			if(sections.length == 3  && (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee") || selfUpdate)) {//submit account
				if(as.addAccount(myAccount)) { //This should update the ID, but we will make sure our json is valid later.
					json = om.writeValueAsString(as.getAccountById(myAccount.getAccountId())); //returning the object from the database to ensure it is valid
					resp.setStatus(201);
				}
				
			}else if(sections.length == 3) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}
			else {
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
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		Account myAccount = om.readValue(body, Account.class);
		
		if(ses != null) {
			if(sections.length == 3  && (ses.getAttribute("role").equals("Admin"))) {//submit account
				if(as.updateAccount(myAccount)) { //This should update the ID, but we will make sure our json is valid later.
					json = om.writeValueAsString(as.getAccountById(myAccount.getAccountId())); //returning the object from the database to ensure it is valid
					resp.setStatus(201);
				}
				
			}else if(sections.length == 3) {
				json = "{\"message\": \"The requested action is not permitted\"}";
				resp.setStatus(401);
			}
			else {
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
