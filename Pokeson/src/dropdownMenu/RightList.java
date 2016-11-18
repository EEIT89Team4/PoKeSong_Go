package dropdownMenu;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import com.productclass.model.ProductClassDAO;
import com.productclassdetail.model.ProductClassdetailVO;

import product.ProductDAO;
import product.ProductVO;

@WebServlet("/rightList")
public class RightList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classno = 0;
		Set<ProductClassdetailVO> detailSet = null;
		List<ProductVO> proList = null;
		ProductClassDAO classdao = null;
		ProductDAO prodao = null;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		classno = Integer.parseInt(request.getParameter("classno"));
		classdao = new ProductClassDAO();
		detailSet = classdao.getClassdetailsByClass_no(classno);
		
		prodao = new ProductDAO();
		
		
		List l1 = new LinkedList();
		
		for(ProductClassdetailVO vo :detailSet){
			Map m1 = new HashMap();
			Set set = new HashSet();
			List l2 = new LinkedList();
			
			m1.put("classdetail_no", vo.getClassdetail_no());
			m1.put("classdetail_name", vo.getClassdetail_name());
			
			proList = prodao.getAllByClassDetailNo(vo.getClassdetail_no());
			
			for(ProductVO provo:proList){
				if (!l2.contains(provo.getSupplier_name())){
					l2.add(provo.getSupplier_name());	
				}
			}
			
			m1.put("supplier_name", l2);
			
			l1.add(m1);
		}		
		
		String jsonString = JSONValue.toJSONString(l1);
		out.println(jsonString);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
