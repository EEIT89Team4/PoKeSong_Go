

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cm.model.CmDAO;
import com.cm.model.CmVO;

import product.ProductService;
import product.ProductVO;

/**
 * Servlet implementation class ProductImage
 */
@WebServlet("/getindexImage")
public class turnImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null ;
		byte data[]=null;
		int count = 0;
		Integer cm_no = null;
		request.setCharacterEncoding("UTF-8");
		try {
			cm_no = Integer.valueOf(request.getParameter("cm_no"));
			
			
		} catch (NumberFormatException e) {
			System.out.println("Image格式轉換錯誤");
			e.printStackTrace();
		}
		CmDAO cmdao= new CmDAO();
		 CmVO list = cmdao.findByPrimaryKey(cm_no);
		
		data=list.getPicture();
		os = response.getOutputStream();
		os.write(data, 0, data.length);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
