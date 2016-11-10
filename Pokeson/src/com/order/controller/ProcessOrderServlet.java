package com.order.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ShoppingCart.ShoppingCart;
import member.MemberVO;
import order.OrderlistService;
import order.OrderlistVO;
import orderdetails.OrderDetailsService;
import orderdetails.OrderDetailsVO;
import product.ProductService;
import product.ProductVO;
@WebServlet("/ProcessOrder.do")
public class ProcessOrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                     req.setCharacterEncoding("UTF-8");
                 	Map<String, String> errorMsgs = new HashMap<String, String>();
            		req.setAttribute("errorMsgs", errorMsgs);
                     try {
		String finalDecision = req.getParameter("finalDecision");
		System.out.println(finalDecision);
		HttpSession session = req.getSession();
		if (session == null) {   // 使用逾時
			resp.sendRedirect(getServletContext().getContextPath() + "/productindex.jsp"  );
			return;
		}
		MemberVO mb = (MemberVO) session.getAttribute("MemberVO");
		if (mb == null) {
			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		ShoppingCart sc = (ShoppingCart)session.getAttribute("mycart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			resp.sendRedirect(getServletContext().getContextPath() + "/productindex.jsp"  );
			return;
		}
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("mycart");;
			resp.sendRedirect("productindex.jsp");
			return;  // 一定要記得 return 
		}
		int member_no=mb.getMember_no();            //會員編號
		String member_name = mb.getMember_name();   //會員姓名
		String member_phone = mb.getMember_phone();   //會員電話
		String member_address = mb.getMember_address();   //會員地址
//        Date today = req.getParameter("today");	          //訂購時間
		Date today = (Date)session.getAttribute("today");
		//--------------------收貨人------------------
		String shippingName = req.getParameter("ShippingName");
		if (shippingName == null || shippingName.trim().length() == 0) {
			errorMsgs.put("shippingNameError","收貨人姓名: 請勿空白");
		}
		
		String enameReg1 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if(!shippingName.trim().matches(enameReg1) ) { 
			errorMsgs.put("shippingNameError", "收貨人姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }
		
		String shippingPhone = req.getParameter("ShippingPhone");
		if (shippingPhone == null || shippingPhone.trim().length() == 0) {
			errorMsgs.put("shippingPhoneError","請輸入電話");
		}
		String userPhoneReg1 = "^[0-9]{8,10}$";
		if(!shippingPhone.trim().matches(userPhoneReg1)){
			 errorMsgs.put("shippingPhoneError","電話格式錯誤");
		}
		String shippingAddress = req.getParameter("ShippingAddress");
		if(shippingAddress==null || shippingAddress.trim().length()==0){
			errorMsgs.put("shippingAddressError","請輸入收貨人地址");
		}
		String userAddressReg1 = "^[(0-9)(\u4e00-\u9fa5)]+$";
		if(!shippingAddress.trim().matches(userAddressReg1)){
			errorMsgs.put("shippingAddressError","地址格式錯誤");
		}
//        String shippingName=req.getParameter("ShippingName");   //收貨人姓名
//        String shippingPhone=req.getParameter("ShippingPhone"); //收貨人電話
//        String shippingAddress=req.getParameter("ShippingAddress"); //收貨人地址
        String subtotal=req.getParameter("subtotal");       //小計
        int sl=Integer.parseInt(subtotal);
        String usebouns=req.getParameter("usebouns");       //紅利
        int us=Integer.parseInt(usebouns);
        String freight=req.getParameter("freight");         //運費
        int ft=Integer.parseInt(freight);
		String total=req.getParameter("total");          //總金額
		int tl=Integer.parseInt(total);
		String orderstate="備貨中";          //訂單狀態
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher rd = req.getRequestDispatcher("OrderConfirm.jsp");
			rd.forward(req, resp);
			return;
		}
//		System.out.println("小計:"+subtotal+",紅利:"+usebouns+",運費:"+freight+",總金額:"+total+",姓名:"+member_name);
		OrderlistService orderlistService = new OrderlistService();
		OrderlistVO orderlistVO=orderlistService.addOrderlist(member_no, member_name, member_phone, member_address, today, shippingName, shippingPhone, shippingAddress, sl, us, tl, ft, orderstate);
				orderlistService.insertOrderlist(orderlistVO);
				Integer order_no=orderlistService.findInsertOrder_no();
				
	
		
		Set<OrderDetailsVO> items = new HashSet<OrderDetailsVO>(); 
		Map<Integer, List<ProductVO>> cart = sc.getAllProduct();
		Set<Integer> set = cart.keySet();
		OrderDetailsService orderDetailsService = new OrderDetailsService();
		ProductService productService = new ProductService();
		ProductVO productVO = new ProductVO();
		for (Integer k : set) {
			List<ProductVO> oib = cart.get(k);
			int product_no = oib.iterator().next().getProduct_no(); 
			int class_no = oib.iterator().next().getClass_no();
			int orderproduct_quantity=oib.size();
			orderDetailsService.addOrderDetails(product_no, order_no, orderproduct_quantity);
			int product_quantity1=productService.getProductById(product_no).getProduct_quantity();
			int product_quantity=product_quantity1 - orderproduct_quantity;
			productService.updateQuantity(product_no, product_quantity);;
			
		}
		session.removeAttribute("mycart");
		resp.sendRedirect("productindex.jsp");
                     } catch(Exception e) {
                    	 errorMsgs.put("ordererror", "訂購失敗");
                    	 RequestDispatcher rd = req.getRequestDispatcher("/productindex.jsp");
                    	 rd.forward(req, resp);
			e.printStackTrace();
		} 
	}

	}


