

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import product.ProductService;
import product.ProductVO;

import org.json.simple.JSONArray;

import java.sql.*;
import java.util.List;
@WebServlet("/JsonSimpleDemo")
public class JsonSimpleDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JsonSimpleDemo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String keyword = request.getParameter("keyword");
		try {
			ProductService service = new ProductService();
			List<ProductVO> rs = service.getSeachAllProduct(keyword);
			JSONArray list = new JSONArray();
			for(ProductVO seach:rs){
				list.add(seach.getProduct_name());
				
			}
			
			out.print(list);
		} catch (Exception e) {
			out.println("Error:" + e.getMessage());
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
