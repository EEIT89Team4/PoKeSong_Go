package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MailService;
import member.MemberService;
import member.MemberVO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		String check = request.getParameter("check");
		// String requestURI = (String) session.getAttribute("requestURI");

		if ("insert".equals(action)) { // 接收addMbr.jsp的資料，新增部分
			String servletPath = (String) session.getAttribute("target");
			String contextPath = request.getContextPath();
			String uri = contextPath + servletPath;
			
			Map<String, String> errorMsgs = new HashMap<String, String>();// 用Map在jsp比較好取出來用
			request.setAttribute("errorMsgs", errorMsgs);
			String member_id = null;
			MemberService mbrSvc = null;

			try {
				/************* 1.接收請求參數，輸入格式錯誤處理 ************/
				String member_name = request.getParameter("member_name");
				// 姓名欄不可空白中文共二到五位數 varchar10
				// System.out.println(member_name); //測試用
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.put("ErrNameEmpty", "請輸入姓名");
				}
				String userNameReg = "^[\\u4E00-\\u9FA5]{2,10}$"; // 倒斜線要兩條
				if (!(member_name.trim().matches(userNameReg))) { // matches專門用在regex
					errorMsgs.put("ErrNameFormat", "姓名格式錯誤");
				}

				// 帳號欄不可空白不可重複，只能是英數_，varchar20
				member_id = request.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.put("ErrIdEmpty", "請輸入帳號");
				}
				String userIdReg = "^[a-zA-Z0-9_]{1,20}$";
				if (!member_id.trim().matches(userIdReg)) {
					errorMsgs.put("ErrIdFormat", "帳號格式錯誤");
				}
				// 驗證帳號是否已經存在
				mbrSvc = new MemberService();
				// if (mbrSvc.exist(member_id)) {
				// errorMsgs.put("ErrId", "此帳號已經有人使用");
				// System.out.println("2");
				// String str1 = "帳號已存在";
				// out.write(str1);
				// } else {
				// String str1 = "帳號不存在";
				// out.write(str1);
				// }

				// 密碼欄不可空白，只能是英(大小寫)數_，varchar20
				String member_password = request.getParameter("member_password1");
				if (member_password == null || member_password.trim().length() == 0) {
					errorMsgs.put("ErrPasswordEmpty", "請輸入密碼");
				}
				String userPswdReg = "^[A-Za-z0-9]{6,20}$";
				if (!member_password.trim().matches(userPswdReg)) {
					errorMsgs.put("ErrPasswordFormat", "密碼格式錯誤");
				}

				// 密碼欄2用來驗證是否有輸入錯誤
				String member_password2 = request.getParameter("member_password2");
				if (!member_password.equals(member_password2)) {
					errorMsgs.put("ErrPassword", "請確認密碼");
				}

				// 生日不可空白
				java.sql.Date member_birthday = null;
				try {
					member_birthday = java.sql.Date.valueOf(request.getParameter("member_birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("ErrDateEmpty", "請輸入日期");
				}
				// 性別不可空白
				String member_gender = request.getParameter("member_gender");
				if (member_gender == null) {
					errorMsgs.put("ErrGenderEmpty", "請選擇性別");
				}

				// 電話欄不可空白，只能輸入數字varchar10
				String member_phone = request.getParameter("member_phone");
				if (member_phone == null || member_phone.trim().length() == 0) {
					errorMsgs.put("ErrPhoneEmpty", "請輸入電話");
				}
				String userPhoneReg = "^[0-9]{8,10}$";
				if (!member_phone.trim().matches(userPhoneReg)) {
					errorMsgs.put("ErrPhoneFormat", "電話格式錯誤");
				}

				// Email欄不可空白，varchar50
				String member_Email = request.getParameter("member_Email");
				if (member_Email == null || member_Email.trim().length() == 0) {
					errorMsgs.put("ErrEmailEmpty", "請輸入Email");
				}
				String userEamilReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
				// 地址欄不可空白，只能中文數字 varchar100
				String city = request.getParameter("city");
				String address = request.getParameter("address");
				if (city.equals("請選擇")) {
					errorMsgs.put("ErrCityEmpty", "請選擇縣市");
				}
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("ErrAdderssEmpty", "請輸入地址");
				}
				String userAddressReg = "^[(0-9)(\\u4e00-\\u9fa5)]+$";
				if (!address.trim().matches(userAddressReg)) {
					errorMsgs.put("ErrAddressFormat", "地址格式錯誤");
				}
				String member_address = city + address;

				Integer member_bonus = new Integer(request.getParameter("member_bonus"));
				String member_GoogleId = "";
				String member_state = "一般會員";
				
				MemberVO mbr = new MemberVO();
                
				// 如果有錯誤，回到原頁
				if (!errorMsgs.isEmpty()) {
					
						RequestDispatcher errorView = request.getRequestDispatcher("/addMbr.jsp");
						errorView.forward(request, response);
						return;
				}
					/******************* 2.永續層存取(開始新增) *****************/
					mbr = mbrSvc.addMbr(member_name, member_id, member_password2, member_phone, member_address,
							member_gender, member_Email, member_birthday, member_bonus, member_GoogleId,member_state);
					out.write("success");
					// 寄送驗證信給新加入會員
					MailService mail = new MailService();
					mail.sendMail(member_Email);
//					session.setAttribute("mbr", mbr);
					/******************** 3.轉交 ********************/
//					RequestDispatcher rd = request.getRequestDispatcher("/mbrZone.jsp");
//					rd.forward(request, response);
//					response.sendRedirect("/Pokeson/index.jsp");
					// 其他錯誤處理				
			} catch (Exception e) {
				errorMsgs.put("errOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/addMbr.jsp");
				errorView.forward(request, response);
			}

		}

		if ("login".equals(action)) { // 接收來自login.jsp的
			String servletPath = (String) session.getAttribute("target");
            System.out.println(servletPath);
			String contextPath = request.getContextPath();

			String uri = contextPath + servletPath;
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorLogin", errorMsgs);

			try {
				// 帳號欄不可空白不可重複，只能是英數_，varchar20
				String member_id = request.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.put("ErrIdEmpty", "請輸入帳號");
				}
				String userIdReg = "^[a-zA-Z0-9_]{1,20}$";
				if (!member_id.trim().matches(userIdReg)) {
					errorMsgs.put("ErrIdFormat", "帳號格式錯誤");
				}
				// 驗證驗證碼
				String randomWords = (String) request.getSession().getAttribute("randomWords");
				// System.out.println(randomWords);
				String identity = request.getParameter("identity").toUpperCase();
//				System.out.print(identity);
				if (identity == null || identity.trim().length() == 0) {
					errorMsgs.put("ErrIdentityEmpty", "請輸入驗證碼");
				}
				if (!identity.equals(randomWords)) {
					errorMsgs.put("ErrIdentity", "驗證碼錯誤");
				}

				MemberService mbrSvc = new MemberService();

				// 密碼欄不可空白，只能是英(大小寫)數_，varchar20
				String member_password = request.getParameter("member_password1");
				if (member_password == null || member_password.trim().length() == 0) {
					errorMsgs.put("ErrPasswordEmpty", "請輸入密碼");
				}
				String userPswdReg = "^[A-Za-z0-9]{6,20}$";
				if (!member_password.trim().matches(userPswdReg)) {
					errorMsgs.put("ErrPasswordFormat", "密碼格式錯誤");
				}
				//驗證會員是否為黑名單並登入
				if(!mbrSvc.blacklist(member_id)){
					if (!mbrSvc.loginCheck(member_id, member_password)) {
						errorMsgs.put("Err", "帳號或密碼錯誤");
					}
				}else{
					errorMsgs.put("ErrState", "會員狀態異常");
				}
				/******************* remember me *****************/
				String rm = request.getParameter("rm");
				// System.out.println(rm+"is remeberme");
				Cookie cookieUser = null;
				Cookie cookiePwd = null;
				Cookie cookieRm = null;

				// 1.先new cookie物件 2.給生命長度(秒) 3.setPath(uri)
				if (rm != null) {
					cookieUser = new Cookie("user", member_id);
					cookieUser.setMaxAge(30 * 60 * 60); // 給cookie存活時間
					cookieUser.setPath(request.getContextPath());

					cookiePwd = new Cookie("password", member_password);
					cookiePwd.setMaxAge(30 * 60 * 60);
					cookiePwd.setPath(request.getContextPath());

					cookieRm = new Cookie("rm", "true");
					cookieRm.setMaxAge(30 * 60 * 60);
					cookieRm.setPath(request.getContextPath());

				} else {
					cookieUser = new Cookie("user", member_id);
					cookieUser.setMaxAge(0); // 給cookie存活時間
					cookieUser.setPath(request.getContextPath());

					cookiePwd = new Cookie("password", member_password);
					cookiePwd.setMaxAge(0);
					cookiePwd.setPath(request.getContextPath());

					cookieRm = new Cookie("rm", "false");
					cookieRm.setMaxAge(30 * 60 * 60);
					cookieRm.setPath(request.getContextPath());
				}
				response.addCookie(cookieUser);
				response.addCookie(cookiePwd);
				response.addCookie(cookieRm);
				/******************* 2.永續層存取(開始新增) *****************/
				MemberVO mbr = new MemberVO();
				mbr = mbrSvc.getOneById(member_id);

				if (!errorMsgs.isEmpty()) { // 如果有錯
//					request.setAttribute("mbr", mbr);
					RequestDispatcher errorView = request.getRequestDispatcher("/index.jsp");
					errorView.forward(request, response);
					return;
				}

				/******************** 3.轉交 ********************/
				session.setAttribute("mbr", mbr);
                session.setAttribute("MemberVO", mbr);
                if (servletPath != null) {
					session.removeAttribute("target");
					response.sendRedirect(uri);
				} else {
					response.sendRedirect(uri);
				}
			} catch (Exception e) {
				errorMsgs.put("errOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/index.jsp");
				errorView.forward(request, response);
			}
		}
		if ("getMbr".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer member_no = new Integer(request.getParameter("member_no"));
				// System.out.println(request.getParameter("member_no"));
				MemberService mbrSvc = new MemberService();
				MemberVO mbr = mbrSvc.getOneMbr(member_no);
				// System.out.println(mbr.getMember_name());

				request.setAttribute("mbr", mbr);
				RequestDispatcher rd = request.getRequestDispatcher("/update.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				errorMsgs.put("errorOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/update.jsp");
				errorView.forward(request, response);
			}
		}
		if ("update".equals(action)) {
			String servletPath = (String) session.getAttribute("target");

			String contextPath = request.getContextPath();

			String uri = contextPath + servletPath;
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			MemberVO mbr = new MemberVO();
			try {
				/************* 1.接收請求參數，輸入格式錯誤處理 ************/
				String member_GoogleId = request.getParameter("member_GoogleId");
				System.out.println(member_GoogleId);
				
				Integer member_no = new Integer(request.getParameter("member_no"));
				// System.out.println(member_no);

				String member_name = request.getParameter("member_name");
				// 姓名欄不可空白中文共二到五位數 varchar10
				// System.out.println(member_name); //測試用
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.put("ErrNameEmpty", "請輸入姓名");
				} else {
					String userNameReg = "^[\\u4E00-\\u9FA5]{2,10}$"; // 倒斜線要兩條
					if (!(member_name.trim().matches(userNameReg))) { // matches專門用在regex
						errorMsgs.put("ErrNameFormat", "姓名格式錯誤");
					}
				}
				// 帳號欄不可空白不可重複，只能是英數_，varchar20
				String member_id = request.getParameter("member_id");
				
				// Email欄不可空白，varchar50
				String member_Email = request.getParameter("member_Email");
//				System.out.println(member_Email);
				if (member_Email == null || member_Email.trim().length() == 0) {
					errorMsgs.put("ErrEmailEmpty", "請輸入Email");
				} else {
					// String userEmail = "^.+@.+\..{2,4}$"; // \.無法辨識
					String userEamilReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
					if (!member_Email.trim().matches(userEamilReg)) {
						errorMsgs.put("ErrEmailFormat", "email格式錯誤");
					}
				}
				
				//會員狀態
				String member_state = request.getParameter("member_state");
				System.out.println(member_state);
				
				String member_password = request.getParameter("member_password1");
				
				String member_password2 = request.getParameter("member_password2");
				
				java.sql.Date member_birthday = null;
				
				String member_gender = request.getParameter("member_gender");

				String member_phone = request.getParameter("member_phone");
				
				String city = request.getParameter("city");

				String address = request.getParameter("address");
				
				String member_address = city + address;
				
				Integer member_bonus = new Integer(request.getParameter("member_bonus"));

				//如果沒有google帳號，以下值不可為空值
				if(member_GoogleId==null){
					// 密碼欄不可空白，只能是英(大小寫)數_，varchar20
					if (member_password == null || member_password.trim().length() == 0) {
						errorMsgs.put("ErrPasswordEmpty", "請輸入密碼");
					} else {
						String userPswdReg = "^[A-Za-z0-9]{6,40}$";
						if (!member_password.trim().matches(userPswdReg)) {
							errorMsgs.put("ErrPasswordFormat", "密碼格式錯誤");
						}
					}
					// 密碼欄2用來驗證是否有輸入錯誤
					if (!member_password.equals(member_password2)) {
						errorMsgs.put("ErrPassword", "請確認密碼");
					}
	
					// 生日不可空白
					try {
						member_birthday = java.sql.Date.valueOf(request.getParameter("member_birthday").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("ErrDateEmpty", "請輸入日期");
					}

	
					// 電話欄不可空白，只能輸入數字varchar10
					if (member_phone == null || member_phone.trim().length() == 0) {
						errorMsgs.put("ErrPhoneEmpty", "請輸入電話");
					} else {
						String userPhoneReg = "^[0-9]{8,10}$";
						if (!member_phone.trim().matches(userPhoneReg)) {
							errorMsgs.put("ErrPhoneFormat", "電話格式錯誤");
						}
					}
	
					// 地址欄不可空白，只能中文數字 varchar100
					if (city.equals("請選擇")) {
						errorMsgs.put("ErrCityEmpty", "請選擇縣市");
					}
					if (address == null || address.trim().length() == 0) {
						errorMsgs.put("ErrAdderssEmpty", "請輸入地址");
					}

					//如果有google帳號，以下值可為空值
				}else{	
					// 生日不可空白
					try {
						member_birthday = java.sql.Date.valueOf(request.getParameter("member_birthday").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("ErrDateEmpty", "請輸入日期");
					}
	
					// 電話欄不可空白，只能輸入數字varchar10
					if (member_phone !="") {
						String userPhoneReg = "^[0-9]{8,10}$";
						if (!member_phone.trim().matches(userPhoneReg)) {
							errorMsgs.put("ErrPhoneFormat", "電話格式錯誤");
						}
					}

				}
					mbr.setMember_no(member_no);
					mbr.setMember_name(member_name);
					mbr.setMember_id(member_id);
					mbr.setMember_password(member_password);
					mbr.setMember_phone(member_phone);
					mbr.setMember_address(member_address);
					mbr.setMember_gender(member_gender);
					mbr.setMember_Email(member_Email);
					mbr.setMember_birthday(member_birthday);
					mbr.setMember_bonus(member_bonus);
					mbr.setMember_GoogleId(member_GoogleId);
				
				// 如果有錯誤，回到原頁
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					request.setAttribute("mbr", mbr);
					RequestDispatcher errorView = request.getRequestDispatcher(servletPath);
					errorView.forward(request, response);
					return;
				}

				/******************* 2.永續層存取(開始新增) *****************/
				MemberService mbrSvc = new MemberService(); // 與驗證帳號重複
				mbr = mbrSvc.updateMbr(member_no, member_name, member_id, member_password2, member_phone,
						member_address, member_gender, member_Email, member_birthday, member_bonus, member_GoogleId,member_state);

				/******************** 3.轉交 ********************/
				session.setAttribute("mbr", mbr);
				out.write("success");

				// 其他錯誤處理
			}catch (Exception e) {
				errorMsgs.put("errOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/update.jsp");
				errorView.forward(request, response);
			}
		}
		
		if ("forgetPwd".equals(action)) {
			String servletPath = (String) session.getAttribute("target");
//			System.out.println(servletPath);
			String contextPath = request.getContextPath();
			String uri = contextPath + servletPath;
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// Email
				String member_Email = request.getParameter("member_Email");
				// System.out.println("ppppppppppppppppppppp");

				if (member_Email == null || member_Email.trim().length() == 0) {
					errorMsgs.put("ErrEmailEmpty", "請輸入Email");
				} else {
					// String userEmail = "^.+@.+\..{2,4}$"; // \.無法辨識
					String userEamilReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
					if (!member_Email.trim().matches(userEamilReg)) {
						errorMsgs.put("ErrEmailFormat", "email格式錯誤");
					}
				}
				// 檢驗Email是否存在
				MemberService mbrSvc = new MemberService();
				if (!mbrSvc.emailCheck(member_Email)) {
//					System.out.println(!(mbrSvc.emailCheck(member_Email)));
					errorMsgs.put("ErrEmail", "email不存在");
				}

				// 驗證碼
				String randomWords = (String) request.getSession().getAttribute("randomWords");

				String identity = request.getParameter("identity").toUpperCase();
				if (identity == null || identity.trim().length() == 0) {
					errorMsgs.put("ErrIdentityEmpty", "請輸入驗證碼");
				} else {
					if (!identity.equals(randomWords)) {
						errorMsgs.put("ErrIdentity", "驗證碼錯誤");
					}
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher errorView = request.getRequestDispatcher(servletPath);
					System.out.println("ppppppppppppppppppppp");
					errorView.forward(request, response);
					return;
				}
				/*********************** 2. ************************/
				// 寄信到會員信箱
				 MailService ms = new MailService();
				 ms.resetPwd(member_Email);
				 out.write("success");
				System.out.println("zzzzzzzzzzzzzzzzzzzzz");
//				if (servletPath != null) {
//					session.removeAttribute("target");
//					response.sendRedirect("/Pokeson/index.jsp");
//				} else {
//					response.sendRedirect("/Pokeson/index.jsp");
//				}			
			} catch (Exception e) {
				errorMsgs.put("errOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher(servletPath);
				errorView.forward(request, response);
			}
		}
		
		if ("getMbrByEmail".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String member_Email = new String(Base64.getDecoder().decode(request.getParameter("member_Email")));
				System.out.println(member_Email);
				
				MemberService mbrSvc = new MemberService();
				MemberVO mbr = mbrSvc.getOneByEmail(member_Email);
				
				request.setAttribute("mbr", mbr);
				RequestDispatcher rd = request.getRequestDispatcher("/resetPwd.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				errorMsgs.put("errorOthers", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/index.jsp");
				errorView.forward(request, response);
			}
		}
		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer member_no = new Integer(request.getParameter("member_no"));

				MemberService mbrSvc = new MemberService();
				mbrSvc.deleteMbr(member_no);

				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				errorMsgs.put("errOther", e.getMessage());
				RequestDispatcher errorView = request.getRequestDispatcher("/member.jsp");
				errorView.forward(request, response);
			}
		}
		//------------------------登出-------------------------------------
		if ("signOut".equals(action)) {
			session.invalidate();
			out.print("signOutOK");
//			session.removeAttribute("MemberVO");
			}
		}

	}


