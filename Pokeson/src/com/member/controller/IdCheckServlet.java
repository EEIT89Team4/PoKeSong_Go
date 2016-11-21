package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberService;

@WebServlet("/IdCheckServlet")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IdCheckServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String check = request.getParameter("check");
		
		String member_id = request.getParameter("member_id");
		MemberService mbrSvc = new MemberService();
		if ("member_id".equals(check)) {
			if (mbrSvc.exist(member_id)) {
				String str1 = "帳號已存在";
				out.write(str1);
			} else {
				String str1 = "帳號不存在";
				out.write(str1);
			}
		}
	}
	

}
