

import java.io.IOException;

import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productclass.model.ProductClassDAO;
import com.productclass.model.ProductClassVO;


@WebServlet("/classImg")
public class ClassImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null ;
		byte data[]=null;
		int count = 0;
		request.setCharacterEncoding("UTF-8");
		Integer class_no = null;
		try {
			class_no = Integer.valueOf(request.getParameter("classno"));
		} catch (NumberFormatException e) {
			System.out.println("ProductImage格式轉換錯誤");
			e.printStackTrace();
		}
		ProductClassDAO dao = new ProductClassDAO();
		ProductClassVO vo = dao.findByPrimaryKey(class_no);
		data = vo.getClass_pic();
		
		os = response.getOutputStream();
		os.write(data, 0, data.length);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
