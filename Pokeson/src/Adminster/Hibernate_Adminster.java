package Adminster;

import org.hibernate.Session;


import org.omg.CORBA.PUBLIC_MEMBER;

import Adminster.AdminsterVO;

import org.hibernate.Query;

import java.util.*;

import hibernate.util.HibernateUtil;
import oracle.jdbc.dcn.QueryChangeDescription.QueryChangeEventType;



public class Hibernate_Adminster {
	
		public void insert(AdminsterVO adminsterVO){
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.beginTransaction();
				session.saveOrUpdate(adminsterVO);
				session.getTransaction().commit();
			}catch(RuntimeException e){
				session.getTransaction().rollback();
				throw e;
			}
		}
		
		public void update(AdminsterVO adminsterVO){
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.beginTransaction();
				session.saveOrUpdate(adminsterVO);
				session.getTransaction().commit();
			}catch(RuntimeException e){
				session.getTransaction().rollback();
				throw e;
			}
		}
		
		public void delete(String myFavoriteId){
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.beginTransaction();
				AdminsterVO adminsterVO=(AdminsterVO)session.get(AdminsterVO.class, myFavoriteId);
				session.delete(adminsterVO);
				session.getTransaction().commit();
				
			}catch(RuntimeException e){
				session.getTransaction().rollback();
				throw e;
			}
		}
		public AdminsterVO findByPrimaryKey(String myFavoriteId){
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			AdminsterVO adminsterVO=null;
			try{
				session.beginTransaction();
				  adminsterVO=(AdminsterVO)session.get(AdminsterVO.class, myFavoriteId);
				
				
				session.getTransaction().commit();
				
			}catch(RuntimeException e){
				session.getTransaction().rollback();
				throw e;
			}return adminsterVO;
		}
		
		public List<AdminsterVO> getAll(){
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			List<AdminsterVO> list=null;
			try{
				session.beginTransaction();
				Query query = session.createQuery("from Adminster.AdminsterVO "); //HQL - Query介面 - 可封裝查詢條件
				 list = query.list();
				session.getTransaction().commit();
			}catch(RuntimeException e){
				session.getTransaction().rollback();
				throw e;
			}
			return list;
		}
		
		
		
		public static void main(String[] args){
			//INSERT
//			AdminsterVO adminster=new AdminsterVO();
//			adminster.setAdminster_id("tyu");
//			adminster.setAdminster_password("123");
//			adminster.setAdminster_name("5567");
//			adminster.setJob_title("rrr");
//			Hibernate_Adminster hibernate = new Hibernate_Adminster();
//			hibernate.insert(adminster);
//			
			//UPDATE
//			AdminsterVO adminster=new AdminsterVO();
//			adminster.setAdminster_id("aaa");
//			adminster.setAdminster_password("123");
//			adminster.setAdminster_name("5566");
//			adminster.setJob_title("rrr");
//			Hibernate_Adminster hibernate = new Hibernate_Adminster();
//			hibernate.update(adminster);
//			
			//DELETE
//			Hibernate_Adminster hibernate = new Hibernate_Adminster();
//			hibernate.delete("ooo");
			
			//FinByPrimaryKey
//			Hibernate_Adminster hibernate = new Hibernate_Adminster();
//			AdminsterVO aa=hibernate.findByPrimaryKey("aaa");
//			System.out.println(aa.getAdminster_id()+","+aa.getAdminster_password()+","+
//			aa.getAdminster_name()+","+aa.getJob_title());
			
			//GET_ALL
			Hibernate_Adminster hibernate = new Hibernate_Adminster();
			List<AdminsterVO> list=hibernate.getAll();
			for (AdminsterVO adminster : list) {
				System.out.print(adminster.getAdminster_id() + ",");
				System.out.print(adminster.getAdminster_password() + ",");
				System.out.print(adminster.getAdminster_name() + ",");
				System.out.print(adminster.getJob_title() + ",");
				System.out.println();
			}
		}
		
}
