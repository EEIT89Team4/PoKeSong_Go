package com.myfavorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberVO;
import myfavorite.MyFavoriteDAO;
import myfavorite.MyFavoriteService;
import myfavorite.MyFavoriteVO;
import product.ProductVO;

@WebServlet("/MyFavoriteServlet")
public class MyFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Map<String, String> errorMsgs = new HashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String action = req.getParameter("myfavoriteaction");
		/*------------------------------新增一筆我的最愛----------------------------*/
		if ("insert".equals(action)) {
			String member = req.getParameter("member_no");
			String product = req.getParameter("product_no");
//			System.out.println(member);
//			System.out.println(product);
			try {
				if (member == null || member.trim().length() == 0) {
					errorMsgs.put("member", "找不到會員編號");
				}
				if (product == null || product.trim().length() == 0) {
					errorMsgs.put("product", "找不到產品編號");
				}
			} catch (IllegalArgumentException e) {
				out.print("新增失敗");
				errorMsgs.put("Error", "新增失敗");
			}
			try {
				if (errorMsgs.isEmpty()) {
					int member_no = Integer.parseInt(member);
					int product_no = Integer.parseInt(product);
					// System.out.println(member_no);
					// System.out.println(product_no);
					MyFavoriteService myFavoriteDAO = new MyFavoriteService();
					ProductVO productVO = new ProductVO();
					productVO.setProduct_no(product_no);
					MemberVO memberVO = new MemberVO();
					memberVO.setMember_no(member_no);
					MyFavoriteVO myfavoriteVO = new MyFavoriteVO();
					myfavoriteVO.setProductVO(productVO);
					myfavoriteVO.setMemberVO(memberVO);
					myFavoriteDAO.insert(myfavoriteVO);
					out.print("新增成功");
					System.out.println("新增成功一筆我的最愛,產品編號："+product+"會員編號："+member);
					// HttpSession session=req.getSession();
					// session.setAttribute("myfavorite_success", "success");
					// resp.sendRedirect("productindex.js");
					return;
				}
			} catch (SQLException e) { // <---需修改
				out.print("新增失敗");
				// errorMsgs.put("Error","新增失敗");
				// System.out.println("新增失敗");
				if (e.getMessage().indexOf("重複的索引鍵") != -1 || e.getMessage().indexOf("Duplicate entry") != -1) {
					System.out.println("重複");
					errorMsgs.put("myfavorite", "重複");
				} else {
					errorMsgs.put("exception", "資料庫存取錯誤:" + e.getMessage());
				}
				RequestDispatcher rd = req.getRequestDispatcher("productindex.jsp");
				rd.forward(req, resp);
				return;
			}
		}
		/*------------------------------刪除一筆我的最愛----------------------------*/
		if ("delete".equals(action)) {
			String member = req.getParameter("member_no");
			String product = req.getParameter("product_no");
			try {
				if (member == null || member.trim().length() == 0) {
					errorMsgs.put("member", "找不到會員編號");
				}
				if (product == null || product.trim().length() == 0) {
					errorMsgs.put("product", "找不到產品編號");
				}
			  } catch (IllegalArgumentException e) {
				  
				errorMsgs.put("Error", "查詢失敗");
				out.print("刪除失敗");
			}
			try {
				if (errorMsgs.isEmpty()) {
					int member_no = Integer.parseInt(member);
					int product_no = Integer.parseInt(product);
//					System.out.println(member_no);
//					System.out.println(product_no);
					MyFavoriteService myFavoriteDAO = new MyFavoriteService();
					MyFavoriteVO myfavoriteVO = new MyFavoriteVO();
					MemberVO memberVO = new MemberVO();
					ProductVO productVO = new ProductVO();
					memberVO.setMember_no(member_no);
					productVO.setProduct_no(product_no);
					myfavoriteVO.setMemberVO(memberVO);
					myfavoriteVO.setProductVO(productVO);
					myFavoriteDAO.delete(myfavoriteVO);
					System.out.println("刪除我的最愛成功,產品編號："+product+"會員編號："+member);
					
					String deleteOK="deleteOK";
					
					out.println(deleteOK);
					// List<MyFavoriteVO> list =
					// myFavoriteDAO.getMyFavoriteById(myfavoriteVO);
					// for(MyFavoriteVO myfavorite: list){
					// System.out.println(myfavorite.getProductVO().getProduct_no());
					// System.out.println(myfavorite.getMemberVO().getMember_no());
					// System.out.println(myfavorite.getProductVO().getProduct_name());
					// }
					return;
				}
			} catch (Exception e) { //<--需修改
				out.print("刪除失敗");
				System.out.println("我的最愛刪除失敗");
				// if (e.getMessage().indexOf("重複的索引鍵") != -1 ||
				// e.getMessage().indexOf("Duplicate entry") != -1) {
				// errorMsgs.put("myfavorite","重複");
				// } else {
				// errorMsgs.put("exception","資料庫存取錯誤:" + e.getMessage());
				// }RequestDispatcher rd = req
				// .getRequestDispatcher("testJSP.jsp");
				// rd.forward(req, resp);
				// return;
			}
		}
		/*------------------------------查詢我的最愛----------------------------*/
		if("GET_ONE".equals(action)){
		String member = req.getParameter("member_no");
//		System.out.println(member);
		try{
			if (member == null || member.trim().length()==0) { 
				errorMsgs.put("member","找不到會員編號");
			}
		}catch(IllegalArgumentException e){
			errorMsgs.put("Error","查詢失敗");
		}
		try{
			if(errorMsgs.isEmpty()){
				int member_no=Integer.parseInt(member);
//				System.out.println(member_no);
				MyFavoriteService myFavoriteDAO = new MyFavoriteService();
				 MyFavoriteVO myfavoriteVO=new MyFavoriteVO();
				 MemberVO memberVO = new MemberVO();
				 memberVO.setMember_no(member_no);
				 myfavoriteVO.setMemberVO(memberVO);
				 List<MyFavoriteVO> list = myFavoriteDAO.getMyFavoriteById(myfavoriteVO);
//				 for(MyFavoriteVO myfavorite: list){
//				 System.out.println(myfavorite.getProductVO().getProduct_no());
//				 System.out.println(myfavorite.getMemberVO().getMember_no());
//				 System.out.println(myfavorite.getProductVO().getProduct_name());
//				 }
				 req.setAttribute("myfavorites", list);
				 RequestDispatcher rd = req.getRequestDispatcher("/myfavorite.jsp");
				 rd.forward(req, resp);
				 return;
			}
		}catch(Exception e){   //<--需修改
			System.out.println("查詢失敗");
			RequestDispatcher rd = req
						.getRequestDispatcher("/productindex.jsp");
				rd.forward(req, resp);
				return;
		}
		}
	}
}
