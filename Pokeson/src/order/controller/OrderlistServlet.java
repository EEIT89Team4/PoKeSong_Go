package order.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;
import order.OrderlistVO;
import java.sql.*;
import java.sql.Date;
import order.*;
@MultipartConfig
public class OrderlistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		if ("getOne_For_Update".equals(action)) { // //來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
			     String order_no = new String(req.getParameter("order_no"));
			     int order_NO = Integer.parseInt(order_no);
				/***************************2.開始查詢資料****************************************/
				OrderlistService orderlistSvc = new OrderlistService();
				OrderlistVO orderlistVO = orderlistSvc.getOneOrderlist(order_NO);
								
				/***************************3.查詢完成,準備轉交����(Send the Success view)************/
				req.setAttribute("orderlistVO", orderlistVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/update_orderlist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/listAllOrderlist.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { //來自update_orderlist_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String order_no = new String(req.getParameter("order_no").trim());
				
				String order_name = req.getParameter("order_name");
				if (order_name == null || order_name.trim().length() == 0) {
					errorMsgs.add("訂購人姓名: 請勿空白");
				}
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(!order_name.trim().matches(enameReg) ) { 
					errorMsgs.add("訂購人姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String order_phone = req.getParameter("order_phone");
				if (order_phone == null || order_phone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}
				String userPhoneReg = "^[0-9]{8,10}$";
				if(!order_phone.trim().matches(userPhoneReg)){
					 errorMsgs.add("電話格式錯誤");
				}
				
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String order_address = req.getParameter("order_address");
				if(order_address==null || order_address.trim().length()==0){
					errorMsgs.add("請輸入地址");
				}
				String userAddressReg = "^[(0-9)(\u4e00-\u9fa5)]+$";
				if(!order_address.trim().matches(userAddressReg)){
					errorMsgs.add("地址格式錯誤");
				}
				//String job = req.getParameter("job").trim();				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(req.getParameter("order_date").trim());
				} catch (IllegalArgumentException e) {
					order_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
                String receipt_name = new String(req.getParameter("receipt_name").trim());
				
				String receiptname = req.getParameter("receipt_name");
				if (receiptname == null || receiptname.trim().length() == 0) {
					errorMsgs.add("收貨人姓名: 請勿空白");
				}
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String enameReg1 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(!order_name.trim().matches(enameReg1) ) { 
					errorMsgs.add("訂購人姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String receipt_phone = req.getParameter("receipt_phone");
				if (receipt_phone == null || receipt_phone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}
				String userPhoneReg1 = "^[0-9]{8,10}$";
				if(!receipt_phone.trim().matches(userPhoneReg1)){
					 errorMsgs.add("電話格式錯誤");
				}
				String receipt_address = req.getParameter("receipt_address");
				if(receipt_address==null || receipt_address.trim().length()==0){
					errorMsgs.add("請輸入收貨人地址");
				}
				String userAddressReg1 = "^[(0-9)(\u4e00-\u9fa5)]+$";
				if(!receipt_address.trim().matches(userAddressReg1)){
					errorMsgs.add("地址格式錯誤");
				}
				Integer subtotal = null;
				try {
					subtotal = new Integer(req.getParameter("subtotal").trim());
				} catch (NumberFormatException e) {
					subtotal = 0;
					errorMsgs.add("請輸入金額");
				}

				Integer usebonus = null;
				try {
					usebonus = new Integer(req.getParameter("usebonus").trim());
				} catch (NumberFormatException e) {
					usebonus = 0;
					errorMsgs.add("請輸入需使用紅利點數");
				}
				Integer total = null;
				try {
					total = new Integer(req.getParameter("toltal").trim());
				} catch (NumberFormatException e) {
					total = 0;
					errorMsgs.add("請輸入小計");
				}
				
				Integer freight = null;
				try {
					freight = new Integer(req.getParameter("freight").trim());
				} catch (NumberFormatException e) {
					freight = 0;
					errorMsgs.add("請輸入運費");
				}
				String orderState =null;
				try{
					orderState = new String(req.getParameter("orderState").trim());
				} catch (NumberFormatException e) {
					orderState="0" ;			
					errorMsgs.add("請輸入訂單狀態");
				}
				OrderlistVO orderlistVO = new OrderlistVO();
			
				orderlistVO.setOrder_name(order_name);
				orderlistVO.setOrder_phone(order_phone);
				orderlistVO.setOrder_address(order_address);
				orderlistVO.setOrder_date(order_date);
				orderlistVO.setReceipt_name(receiptname);
				orderlistVO.setReceipt_phone(receipt_phone);
				orderlistVO.setReceipt_address(receipt_address);
				orderlistVO.setSubtotal(subtotal);
				orderlistVO.setUsebonus(usebonus);
				orderlistVO.setTotal(total);
				orderlistVO.setFreight(freight);
				orderlistVO.setOrderState(orderState);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderlistVO", orderlistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/update_oderlist_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.開始修改資料*****************************************/
//			     OrderlistService orderlistSVC = new  OrderlistService();
//				orderlistVO = orderlistSVC.updateOrderlist(order_NO,order_name, order_phone,order_address, order_date, receipt_name, receipt_phone, receipt_address,
//						 subtotal, usebonus, total, freight, orderState);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderlistVO", orderlistVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/listOneOrderlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/update_orderlist_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	            String order_name = new String(req.getParameter("order_name").trim());
				
				String ordername = req.getParameter("ordername");
				if (ordername == null || ordername.trim().length() == 0) {
					errorMsgs.add("訂購人姓名: 請勿空白");
				}
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(!ordername.trim().matches(enameReg) ) { 
					errorMsgs.add("訂購人姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String order_phone = req.getParameter("order_phone");
				if (order_phone == null || order_phone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}
				String userPhoneReg = "^[0-9]{8,10}$";
				if(!order_phone.trim().matches(userPhoneReg)){
					 errorMsgs.add("電話格式錯誤");
				}
				
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String order_address = req.getParameter("order_address");
				if(order_address==null || order_address.trim().length()==0){
					errorMsgs.add("請輸入地址");
				}
				String userAddressReg = "^[(0-9)(\u4e00-\u9fa5)]+$";
				if(!order_address.trim().matches(userAddressReg)){
					errorMsgs.add("地址格式錯誤");
				}
				//String job = req.getParameter("job").trim();				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(req.getParameter("order_date").trim());
				} catch (IllegalArgumentException e) {
					order_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
                String receipt_name = new String(req.getParameter("receipt_name").trim());
				
				String receiptname = req.getParameter("receipt_name");
				if (receiptname == null || receiptname.trim().length() == 0) {
					errorMsgs.add("收貨人姓名: 請勿空白");
				}
				//�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				String enameReg1 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(!ordername.trim().matches(enameReg1) ) { 
					errorMsgs.add("訂購人姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String receipt_phone = req.getParameter("receipt_phone");
				if (receipt_phone == null || receipt_phone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}
				String userPhoneReg1 = "^[0-9]{8,10}$";
				if(!receipt_phone.trim().matches(userPhoneReg1)){
					 errorMsgs.add("電話格式錯誤");
				}
				String receipt_address = req.getParameter("receipt_address");
				if(receipt_address==null || receipt_address.trim().length()==0){
					errorMsgs.add("請輸入收貨人地址");
				}
				String userAddressReg1 = "^[(0-9)(\u4e00-\u9fa5)]+$";
				if(!receipt_address.trim().matches(userAddressReg1)){
					errorMsgs.add("地址格式錯誤");
				}
				Integer subtotal = null;
				try {
					subtotal = new Integer(req.getParameter("subtotal").trim());
				} catch (NumberFormatException e) {
					subtotal = 0;
					errorMsgs.add("請輸入金額");
				}

				Integer usebonus = null;
				try {
					usebonus = new Integer(req.getParameter("usebonus").trim());
				} catch (NumberFormatException e) {
					usebonus = 0;
					errorMsgs.add("請輸入需使用紅利點數");
				}
				Integer total = null;
				try {
					total = new Integer(req.getParameter("toltal").trim());
				} catch (NumberFormatException e) {
					total = 0;
					errorMsgs.add("請輸入小計");
				}
				
				Integer freight = null;
				try {
					freight = new Integer(req.getParameter("freight").trim());
				} catch (NumberFormatException e) {
					freight = 0;
					errorMsgs.add("請輸入運費");
				}
				String orderState =null;
				try{
					orderState = new String(req.getParameter("orderState").trim());
				} catch (NumberFormatException e) {
					orderState="0" ;			
					errorMsgs.add("請輸入訂單狀態");
				}
				OrderlistVO orderlistVO = new OrderlistVO();
				orderlistVO.setOrder_name(ordername);
				orderlistVO.setOrder_phone(order_phone);
				orderlistVO.setOrder_address(order_address);
				orderlistVO.setOrder_date(order_date);
				orderlistVO.setReceipt_name(receiptname);
				orderlistVO.setReceipt_phone(receipt_phone);
				orderlistVO.setReceipt_address(receipt_address);
				orderlistVO.setSubtotal(subtotal);
				orderlistVO.setUsebonus(usebonus);
				orderlistVO.setTotal(total);
				orderlistVO.setFreight(freight);
				orderlistVO.setOrderState(orderState);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderlistVO", orderlistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/addOrderlist.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
//				OrderlistService orderlistSVC = new OrderlistService();
//				orderlistVO = orderlistSVC.addOrderlist( order_name,order_phone,order_address, order_date, receipt_name, receipt_phone, receipt_address, subtotal, usebonus, total,freight, orderState);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/Orderlist_DataTables.html";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************2.開始修改資料**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/addOrderlist.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
			String order_no = new String(req.getParameter("order_no"));
				
				/***************************2.�}�l�R�����***************************************/
//				OrderlistService orderlistSVC = new OrderlistService();
//				orderlistSVC.deleteOrderlist(order_no);
//				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/listAllOrderlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/listAllOrderlist.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
