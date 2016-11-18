package pagination;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.ProductDAO;
import product.ProductVO;


@WebServlet("/searchData")
public class SearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//基本Dao宣告
		ProductDAO dao=null;
		List<ProductVO> list = null;
		//分頁宣告
		int pageNumber = 0;
		int pageSize = 0;
		int firstResult = 0;
		int total = 0; //商品總數
		//進階搜尋宣告
		String[] advs = null;//所有依品牌篩選的點選
		List<String> advslist = new ArrayList<>();
		//排序
		String ordertype;
		Set<String> brandset = new HashSet<String>();//呈現該搜尋產品所有品牌
		
		//抓取各種Request
			//抓取搜尋關鍵字-------------------------------
//			request.setCharacterEncoding("UTF-8");//有中文輸入問題
			String searchText = new String (request.getParameter("search").getBytes(),"UTF-8");
			//抓取分頁-----------------------------------
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			//抓取品牌篩選---------------------------------
			advs = request.getParameterValues("adv");
			//抓取排序------------------------------------
			ordertype = request.getParameter("order");
		
		//進行搜尋 
			//先求搜尋結果總筆數
			dao = new ProductDAO();
			list = dao.getAllSearch(searchText,null,null);
			total = list.size();
			//製造條件篩選框格
			if (total > 1){
				for (ProductVO prouductvo : list) {
					brandset.add(prouductvo.getSupplier_name().trim());
				}
			request.setAttribute("brandset", brandset);
			}
			
			//如果搜尋總筆數小於10筆，就直接顯示所有結果
			if(total < 10){	
				list = dao.getAllSearch(searchText,advs,ordertype);
				total = list.size();
				request.setAttribute("products", list);
				request.setAttribute("total",total);
			}
			//如果總筆數大於每頁最小顯示10筆的話，抓出所有搜尋產品並分頁
			else{
				//先求出該搜尋產品總筆數
				total = dao.getAllSearch(searchText,advs,ordertype).size();
				request.setAttribute("total",total);
				//分頁
				firstResult = pageSize * (pageNumber - 1);
				list = dao.getSearchPage(searchText, firstResult, pageSize, advs, ordertype);
				request.setAttribute("products",list);
			}

		request.setAttribute("searchText",searchText);
		request.setAttribute("searchPage",pageNumber);
		request.setAttribute("searchSize",pageSize);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/searchPagination.jsp");
        rd.forward(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
