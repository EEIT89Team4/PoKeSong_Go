package myfavorite;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;
import member.MemberDAO;
import member.MemberService;
import member.MemberVO;
import member.Member_Interface;
import product.ProductDAO;
import product.ProductVO;

public class MyFavoriteService {

	private MyFavorite_Interface dao;
	
		public void insert(MyFavoriteVO myfavoriteVO) throws SQLException {
			MyFavorite_Interface dao = new MyFavoriteDAO();
			dao.insert(myfavoriteVO);
		}
		public void delete(MyFavoriteVO myfavoriteVO) {
			MyFavorite_Interface dao = new MyFavoriteDAO();
			dao.delete(myfavoriteVO);
		}
		public List<MyFavoriteVO> getMyFavoriteById(MyFavoriteVO myfavoriteVO) {
			MyFavorite_Interface dao = new MyFavoriteDAO();
			 return dao.getMyFavoriteById(myfavoriteVO);
		}

}