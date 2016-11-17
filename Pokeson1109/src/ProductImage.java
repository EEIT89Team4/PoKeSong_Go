

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.ProductService;
import product.ProductVO;

/**
 * Servlet implementation class ProductImage
 */
@WebServlet("/getImage")
public class ProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null ;
		byte data[]=null;
		int count = 0;
		request.setCharacterEncoding("UTF-8");
		Integer productno = null;
		try {
			productno = Integer.valueOf(request.getParameter("productid"));
		} catch (NumberFormatException e) {
			System.out.println("ProductImage格式轉換錯誤");
			e.printStackTrace();
		}
		ProductService productService = new ProductService();
		ProductVO productVO = productService.getProductById(productno);
		 data = productVO.getPictrue();
		 os = response.getOutputStream();
		os.write(data, 0, data.length);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
