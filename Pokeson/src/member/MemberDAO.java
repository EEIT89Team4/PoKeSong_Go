package member;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

import org.hibernate.Query;
import org.hibernate.Session;


import hibernate.util.HibernateUtil;

public class MemberDAO implements Member_Interface{
	@Override
	public void updateGooglePhone(int member_no,String phone,String email,String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			Query query = session.createQuery("update MemberVO set member_phone=? where member_no=?");
//			query.setParameter(0,phone);
//			query.setParameter(1, member_no);
//			
//			query.executeUpdate();
			Query query = session.createQuery("update MemberVO set member_phone=? member_Email=? member_name=? where member_no=?");
			query.setParameter(0, phone);
			query.setParameter(1, email);
			query.setParameter(2, name);
			query.setParameter(3, member_no);
			query.executeUpdate();
			System.out.println("99999999999999999999999999999999999999999");
			session.getTransaction().commit();
			System.out.println("fffffffffffffffffffffffffffffffffffffffffffffff");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void updateGoogleAddress(int member_no,String address,String email,String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("update MemberVO set member_address=?,member_Email=?,member_name=? where member_no=?");
			query.setParameter(0, address);
			query.setParameter(1, email);
			query.setParameter(2, name);
			query.setParameter(3, member_no);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}		
	}

	private static final String GET_ALL_STMT = "from MemberVO order by member_no ";
	//private static final String GET_NO = "form MemberVO where member_id";

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MemberDAO dao = new MemberDAO();
		MemberVO mbr= new MemberVO();
		//加密
		final MessageDigest md = MessageDigest.getInstance("MD5");
	     dao.updateGooglePhone(133, "0928123456","sggggg@gmail.com","梁鈞翔");
		//新增測試
//		mbr.setMember_name("小熊維尼");
//		mbr.setMember_id("winne");
//		mbr.setMember_password("winniethepooh");
//		mbr.setMember_phone("0911000123");
//		mbr.setMember_address("新北市新店區檳榔路10號10樓");
//		mbr.setMember_gender("男");
//		mbr.setMember_Email("winniepooh@gmail.com");
//		mbr.setMember_birthday(java.sql.Date.valueOf("1991-01-01"));
//		mbr.setMember_bonus(0);
//		
//		dao.insert(mbr);
//		
		//修改測試
//		
//		mbr.setMember_no(121);
//		mbr.setMember_name("史瑞克");
//		mbr.setMember_password("shrek0000");
//	    mbr.setMember_address("台南市東區中華路15號9樓");
//	    mbr.setMember_gender("男");
//	    mbr.setMember_phone("0900111123");
//	    mbr.setMember_id("shrek438");
//	    mbr.setMember_birthday(java.sql.Date.valueOf("1911-10-10"));
//	    mbr.setMember_Email("shrekkk6@yahoo.com.tw");
//	    
//	    dao.update(mbr);
	    
	    //刪除測試
//	    dao.delete(123);
		
		//單一查詢
//		MemberVO vo = dao.findByPrimaryKey(129);
//		System.out.print(vo.getMember_name()+", ");
//		System.out.print(vo.getMember_id()+", ");
//		System.out.print(vo.getMember_password()+", ");
//		System.out.print(vo.getMember_phone()+", ");
//		System.out.print(vo.getMember_address()+", ");
//		System.out.print(vo.getMember_gender()+", ");
//		System.out.print(vo.getMember_Email()+", ");
//		System.out.print(vo.getMember_birthday()+", ");
//		System.out.println(vo.getMember_bonus());
//		
	  //全部查詢
//		List<MemberVO> list = dao.getAll();
//		for(MemberVO vo : list){
//			System.out.print(vo.getMember_no() + ", ");
//			System.out.print(vo.getMember_name() + ", ");
//			System.out.print(vo.getMember_id() + ", ");
//			System.out.print(vo.getMember_password() + ", ");
//			System.out.print(vo.getMember_phone() + ", ");
//			System.out.print(vo.getMember_address() + ", ");
//			System.out.print(vo.getMember_gender() + ", ");
//			System.out.print(vo.getMember_Email() + ", ");
//			System.out.print(vo.getMember_birthday() + ", ");
//			System.out.println(vo.getMember_bonus());
//		}
		
		//用帳號單一查詢
//		MemberVO vo = dao.findById("john");
//			System.out.print(vo.getMember_no() + ", ");
//			System.out.print(vo.getMember_name() + ", ");
//			System.out.print(vo.getMember_id() + ", ");
//			System.out.print(vo.getMember_password() + ", ");
//			System.out.print(vo.getMember_phone() + ", ");
//			System.out.print(vo.getMember_address() + ", ");
//			System.out.print(vo.getMember_gender() + ", ");
//			System.out.print(vo.getMember_Email() + ", ");
//			System.out.print(vo.getMember_birthday() + ", ");
//			System.out.println(vo.getMember_bonus());

	    //把所有密碼加密
//	    List<MemberVO> list = dao.getAll();
//	    for(MemberVO vo : list){
//	    	String pwd = vo.getMember_password();
////	    	System.out.println(pwd);
//	    	byte[] temp = pwd.getBytes(); // 資料庫取出:明碼
//	    	temp = md.digest(temp); 	  //轉換成:亂碼
//	    	pwd = DatatypeConverter.printHexBinary(temp);
////	    	System.out.println(pwd);    	
//	    	mbr.setMember_no(vo.getMember_no());
//			mbr.setMember_name(vo.getMember_name());
//			mbr.setMember_password(pwd);
//		    mbr.setMember_address(vo.getMember_address());
//		    mbr.setMember_gender(vo.getMember_gender());
//		    mbr.setMember_phone(vo.getMember_phone());
//		    mbr.setMember_id(vo.getMember_id());
//		    mbr.setMember_birthday(vo.getMember_birthday());
//		    mbr.setMember_Email(vo.getMember_Email());
//			mbr.setMember_bonus(0);
		    
//			System.out.print(vo.getMember_no() + ", ");
//			System.out.print(vo.getMember_name() + ", ");
//			System.out.print(vo.getMember_id() + ", ");
//			System.out.print(vo.getMember_password() + ", ");
//			System.out.print(vo.getMember_phone() + ", ");
//			System.out.print(vo.getMember_address() + ", ");
//			System.out.print(vo.getMember_gender() + ", ");
//			System.out.print(vo.getMember_Email() + ", ");
//			System.out.print(vo.getMember_birthday() + ", ");
//			System.out.println(vo.getMember_bonus());
		    
//		    dao.update(mbr);
//	    }
	}

	@Override
	public void insert(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void delete(int member_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery("delete MemberVO where member_no=?");
			query.setParameter(0, member_no);
			System.out.println("刪除筆數= " + query.executeUpdate());
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@Override
	public MemberVO findByPrimaryKey(Integer member_no) {
		MemberVO mbrVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			mbrVO = (MemberVO) session.get(MemberVO.class, member_no);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return mbrVO;
	}

	List<MemberVO> list = null;
	@Override
	public List<MemberVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
//			for(MemberVO mm:list){
//				System.out.println(mm.getMember_name()+"444444444444444444444");
//				System.out.println(mm.getMember_Email()+"55555555555555555");
//			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	public MemberVO findById(String member_id){
		List<MemberVO> list=null;
		MemberVO member=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			
			Query query = session.createQuery("from MemberVO where member_id=?");
			query.setParameter(0, member_id);		
			
			list=query.list();
			member=list.get(0);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
		return member;
	}
	
	public MemberVO findByEmail(String member_Email){
		List<MemberVO> list=null;
		MemberVO member=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			
			Query query = session.createQuery("from MemberVO where member_Email=?");
			query.setParameter(0, member_Email);		
			
			list=query.list();
			member=list.get(0);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
		return member;
	}
	
	public List<MemberVO> findGoogleById(String member_GoogleId){
		List<MemberVO> list=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			
			Query query = session.createQuery("from MemberVO where member_GoogleId=?");
			query.setParameter(0, member_GoogleId);		
			
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
		return list;
	}
}
