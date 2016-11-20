package pagination;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class GetSomeProduct
 */
@WebServlet("/searchPage")
public class SearchTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//分頁宣告
		int searchPage;//第幾頁
		int searchSize;//每頁顯示幾筆
		//關鍵字宣告
		String searchText;//搜尋關鍵字
		//進階搜尋宣告
		String[] advs;//所有依品牌篩選的點選
		String advRequst = "";//將點選陣列排成查詢字串;
		String ordertype;//依排序
		
		
		//抓取各種Requset--------------------------------
			//抓取搜尋關鍵字
			request.setCharacterEncoding("UTF-8");//有中文輸入問題
			searchText = new String (request.getParameter("search").getBytes(),"UTF-8");
			System.out.println("搜尋關鍵字為"+ "\"" + searchText + "\"");
			
			//抓取分頁
			//預設商品首頁為第1頁
			try{searchPage = Integer.parseInt(request.getParameter("searchPage"));} 
			catch (NumberFormatException e) { searchPage=1;}
			//預設每頁顯示10筆商品
			try{searchSize = Integer.parseInt(request.getParameter("searchSize"));}
			catch(Exception e){searchSize =10;}
			//抓取篩選的點選
			advs = request.getParameterValues("adv");
			if(advs!=null){
				if(advs.length==1){//當篩選條件只有1個
					for (String adv : advs){
						advRequst = "&adv=" + adv;//把陣列製作成查詢字串 &adv=1...
					}
				}else{//當篩選條件有2個以上時
					String tempstr;
					for (String adv : advs){
						tempstr = "&adv=" + adv;//把陣列製作成查詢字串 &adv=1&adv=2....	
						advRequst += tempstr;
					}				
				}
				System.out.println(advRequst);
			}
			//抓取排序的點選
			ordertype = request.getParameter("order");
		
		//傳送Requset
		request.setAttribute("searchText",searchText);
		request.setAttribute("searchPage",searchPage);
		request.setAttribute("searchSize",searchSize);
		request.setAttribute("advRequst",advRequst);
		request.setAttribute("ordertype",ordertype);
		
		RequestDispatcher rd = request.getRequestDispatcher("/searchPage.jsp");
        rd.forward(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
