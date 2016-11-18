
import java.io.File;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.productclass.model.ProductClassDAO;
import com.productclass.model.ProductClassVO;
import com.productclassdetail.model.ProductClassdetailDAO;
import com.productclassdetail.model.ProductClassdetailVO;

import product.ProductService;
import product.ProductVO;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet("/getOneProduct")
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer product_no = 0;
		int class_no = 0;
		int classdetail_no = 0;
		ProductVO productvo = null;
		List<ProductVO> proHistory = null;
		ProductClassVO classvo = null;
		ProductClassDAO classDao = null;
		Set<ProductClassdetailVO> detailsSet = null;
		ProductClassdetailVO detailsvo = null;
		ProductClassdetailDAO detailsDao = null;

		product_no = Integer.valueOf(request.getParameter("productid"));

		ProductService productservice = new ProductService();
		productvo = productservice.getProductById(product_no);
		request.setAttribute("oneproduct", productvo);

		classDao = new ProductClassDAO();
		detailsSet = classDao.getClassdetailsByClass_no(productvo.getProductClassVO().getClass_no());
		request.setAttribute("details", detailsSet);

		detailsDao = new ProductClassdetailDAO();
		detailsvo = detailsDao.findByPrimaryKey(productvo.getClassdetail_no());

		request.setAttribute("onedetail", detailsvo);

		// 推薦商品code---------------------------------------------
		// 找該分類的所有商品隨機取4個呈現
		List<ProductVO> tempList = null;
		tempList = productservice.getAllByClassNo(productvo.getProductClassVO().getClass_no());
		int random = 0;
		;
		List<ProductVO> recomList = new ArrayList();
		for (int i = 1; i < 5; i++) {
			random = (int) (Math.random() * (tempList.size() - 1));
			recomList.add(tempList.get(random));
			tempList.remove(random);
		}

		request.setAttribute("recomList", recomList);

		// 搜尋紀錄code---------------------------------------------
		HttpSession session = request.getSession();

		if (session.getAttribute("proHistory") == null) {
			proHistory = new ArrayList<ProductVO>();
			proHistory.add(productvo);

		} else {
			proHistory = (List) session.getAttribute("proHistory");
			Collections.reverse(proHistory);

			for (int i = 0; i < proHistory.size(); i++) {
				int compareNo = proHistory.get(i).getProduct_no();
				if (compareNo == product_no) {
					proHistory.remove(i);
				}
			}

			proHistory.add(productvo);

			if (proHistory.size() > 3) {
				proHistory.remove(0);
			}
		}

		// 顛倒List放在history用來呈現在JSP上(後進先出)
		Collections.reverse(proHistory);
		session.setAttribute("proHistory", proHistory);
		// ------------------------------------------------------

		// 爬蟲------------------------------------------------------
		URL fileUrl = Thread.currentThread().getContextClassLoader().getResource("crawl.txt");
		URI filepath = null;
		try {
			filepath = fileUrl.toURI();
			// System.out.println(filepath + ".........");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File(filepath);
		// File file = new File("c://temp/crawl.txt");
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			System.out.println("parse error");
			e.printStackTrace();
		}
		String p_weight = String.valueOf(productvo.getWeight());
		if (productvo.getProduct_name().contains("*")) {
			String[] strarray = new String[2];
			strarray = productvo.getProduct_name().split("//*");
			Elements eles = doc.getElementsContainingOwnText(productvo.getProduct_name());
			if (eles != null) {
				for (Element ele : eles) {
					if (ele.nextElementSibling().text().contains(strarray[1])
							&& ele.nextElementSibling().text().contains(p_weight))
						;
					request.setAttribute("getdollar", ele.nextElementSibling().nextElementSibling().text());
				}
			}
		} else {
			Elements eles = doc.getElementsContainingOwnText(productvo.getProduct_name());
			if (eles != null) {
				for (Element ele : eles) {
					if (!ele.nextElementSibling().text().matches(".*/ [0-9]{1,}.*")) {
						request.setAttribute("getdollar", ele.nextElementSibling().nextElementSibling().text());

					}
				}
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/singleProduct.jsp");
		rd.forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
