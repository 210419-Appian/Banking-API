package com.bank.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.models.Account;
import com.bank.models.User;
import com.bank.models.WithdrawDTO;
import com.bank.services.AccountService;
import com.bank.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deposit extends HttpServlet {
	private UserService us = new UserService();
	private AccountService as = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		HttpSession ses = req.getSession(false);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		boolean success = false;
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		WithdrawDTO myWithdraw = om.readValue(body, WithdrawDTO.class);
		
		if(ses != null ) {
			User myUser = us.getUserByUsername((String) ses.getAttribute("username"));
			
			//TODO: Check if this exists first
			Account myAccount = as.getAccountById(myWithdraw.accountId);
			
			if(ses.getAttribute("role").equals("Admin") || as.checkOwner(myAccount, myUser.getUserId())) {
				as.deposit(myAccount, myWithdraw.amount);
				resp.setStatus(200);
				
				resp.setContentType("application/json");
				pw.print("{\"message\": \"${" + myWithdraw.amount + "} has been deposited to Account #{" + myWithdraw.accountId + "}\"}");
				success = true;
			}
		}
		
		if(success == false){
			resp.setStatus(401);
			
			resp.setContentType("application/json");
			pw.print("{\"message\": \"The requested action is not permitted\"}");
		}
	}
}
