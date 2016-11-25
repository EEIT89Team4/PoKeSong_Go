package notifymembercontroller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifymember.model.INotifyMemberDAO;
import com.notifymember.model.NotifyMemberDAO;
import com.notifymember.model.NotifyMemberVO;

import member.MemberVO;
import product.ProductService;
import product.ProductVO;




@WebServlet("/notifyMember")
public class NotifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  MemberVO membervo = (MemberVO) session.getAttribute("mbr");
	  PrintWriter out = response.getWriter();	
	  if(membervo==null){	  
		  out.write("0");//需要登入
	  }
	  else{
		  String productid = request.getParameter("p_id");
		  System.out.println(productid);
//		  System.out.println(Integer.valueOf(productid));
		  ProductService ps = new ProductService();
		  ProductVO product = ps.getProductById(Integer.valueOf(productid));
		  NotifyMemberVO notifymembervo = new NotifyMemberVO();
		  notifymembervo.setMemberVO(membervo);
		  notifymembervo.setProductVO(product);
		  INotifyMemberDAO notifymemberdao = new NotifyMemberDAO();
		  notifymemberdao.insert(notifymembervo);
		  out.write("1");
	  }		
	}

}
