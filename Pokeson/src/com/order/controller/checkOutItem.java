package com.order.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orderdetails.OrderDetailsService;
@WebServlet("/checkout")
public class checkOutItem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  HttpSession session = req.getSession();
	  if (session == null) { // 使用逾時
			req.setAttribute("Errors", "使用逾時，請重新登入");
			RequestDispatcher rd = req
					.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
			return;
		}
		Object sc = session.getAttribute("mycart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			resp.sendRedirect(getServletContext().getContextPath()
					+ "/productindex.jsp");
			return;
		}
		String subtotal=req.getParameter("subtotal"); //小計總金額
		String alltotal=req.getParameter("alltotal"); //扣除紅利金額
//		double st=Double.parseDouble(subtotal);
		int st=Integer.parseInt(subtotal);        //*
//		double at=Double.parseDouble(alltotal);
		int at=Integer.parseInt(alltotal);     //*
//		double selectbouns=st-at;          //計算使用的紅利
		int selectbouns=st-at;          //*
		int sb=(int) Math.round(selectbouns);
//		double freightrule=888;           //運費規則 888 元
//		double freight=100;               //運費 100 元
		int freightrule=888;       //*
		int freight=100;          //*
		if(st<freightrule){
			req.setAttribute("freight", freight);
		}
		else{
			req.setAttribute("freight", 0);
		}
		if(selectbouns==st){
			req.setAttribute("usebouns", 0);
		}
		else{
			req.setAttribute("usebouns", sb);
		}
		req.setAttribute("subtotal", st);
		req.setAttribute("alltotal", at);
		// 結帳
		RequestDispatcher rd = req.getRequestDispatcher("OrderConfirm.jsp");
		rd.forward(req, resp);
		return;
	}

}
