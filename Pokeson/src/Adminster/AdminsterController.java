package Adminster;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.SQLServerException;
@WebServlet("/AdminsterController")
public class AdminsterController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);
		request.setCharacterEncoding("UTF-8");
	String adminster_id=request.getParameter("adminster_id");
	if (adminster_id == null || adminster_id.trim().length() == 0) {
		errorMessage.add("帳號欄必須輸入");
	}
	String adminster_password=request.getParameter("adminster_password");
	if (adminster_password == null || adminster_password.trim().length() == 0) {
		errorMessage.add("密碼欄必須輸入");
	}
	String adminster_name=request.getParameter("adminster_name");
	if (adminster_name == null || adminster_name.trim().length() == 0) {
		errorMessage.add("姓名欄必須輸入");
	}
	String job_title=request.getParameter("job_title");
	if (job_title == null || job_title.trim().length() == 0) {
		errorMessage.add("職稱必須輸入");
	}
	
	if (!errorMessage.isEmpty()) {
		RequestDispatcher rd = request
				.getRequestDispatcher("/InsertMemberError.jsp");
		rd.forward(request, response);
		return;
	}
	
	
	AdminsterVO adminster = new AdminsterVO(adminster_id, adminster_password, adminster_name, job_title) ;
	
	try{
		AdminsterDAO adminsterDAO=new AdminsterDAO();
		adminsterDAO.insert(adminster);
		request.setAttribute("adminster", adminster);
		RequestDispatcher rd = request
				.getRequestDispatcher("/InsertMemberSuccess.jsp");
		rd.forward(request, response);
		return;
	}catch(SQLException e) {
			if (e.getMessage().indexOf("重複的索引鍵") != -1 || 
				e.getMessage().indexOf("Duplicate entry") != -1) {
				errorMessage.add("帳號重複，請重新輸入帳號");
			} else {
				errorMessage.add("資料庫存取錯誤:" + e.getMessage());
			}
			RequestDispatcher rd = request
				.getRequestDispatcher("/InsertMemberError.jsp");
			rd.forward(request, response);
			return;
		}
	
	
	
	
	}
}
