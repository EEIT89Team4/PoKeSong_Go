package Adminster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminsterDAO implements AdminsterInterface {
	private static final String INSERT="insert into adminster values(?,?,?,?)";
	private static final String UPDATE="update adminster set adminster_password=?,adminster_name=?,job_title=? where adminster_id=?";
	private static final String DELETE="delete from adminster where adminster_id=?";
	private static final String GET_ONE="select adminster_id,adminster_password,adminster_name,job_title from adminster where adminster_id=?";
	private static final String GET_ALL="select adminster_id,adminster_password,adminster_name,job_title from adminster";
	private String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url="jdbc:sqlserver://localhost:1433;DatabaseName=supermarket";
    Connection conn=null;
    PreparedStatement stmt=null;
    private String id="sa";
    private String pwd="wdsry759";
    int num=0;
	@Override
	public Integer insert(AdminsterVO adminster)throws SQLException {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pwd);
			stmt=conn.prepareStatement(INSERT);
			stmt.setString(1, adminster.getAdminster_id());
			stmt.setString(2, adminster.getAdminster_password());
			stmt.setString(3, adminster.getAdminster_name());
			stmt.setString(4, adminster.getJob_title());
			num=stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public Integer update(AdminsterVO adminster) {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pwd);
			stmt=conn.prepareStatement(UPDATE);
			stmt.setString(1, adminster.getAdminster_password());
			stmt.setString(2, adminster.getAdminster_name());
			stmt.setString(3, adminster.getJob_title());
			stmt.setString(4, adminster.getAdminster_id());
			num=stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public Integer delete(String adminster_id) {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pwd);
			stmt=conn.prepareStatement(DELETE);
			stmt.setString(1, adminster_id);
			num=stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public AdminsterVO findByPrimaryKey(String adminster_id) {
		AdminsterVO adminsterVO =null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pwd);
			stmt=conn.prepareStatement(GET_ONE);
			stmt.setString(1, adminster_id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				adminsterVO=new AdminsterVO();
				adminsterVO.setAdminster_id(rs.getString("adminster_id"));
				adminsterVO.setAdminster_password(rs.getString("adminster_password"));
				adminsterVO.setAdminster_name(rs.getString("adminster_name"));
				adminsterVO.setJob_title(rs.getString("job_title"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adminsterVO;
	}

	@Override
	public List<AdminsterVO> getAll() {
		AdminsterVO adminsterVO =null;
		List<AdminsterVO> list = new ArrayList<AdminsterVO>();
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pwd);
			stmt=conn.prepareStatement(GET_ALL);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				adminsterVO=new AdminsterVO();
				adminsterVO.setAdminster_id(rs.getString("adminster_id"));
				adminsterVO.setAdminster_password(rs.getString("adminster_password"));
				adminsterVO.setAdminster_name(rs.getString("adminster_name"));
				adminsterVO.setJob_title(rs.getString("job_title"));
				list.add(adminsterVO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static void main (String[] args){
		//insert
//		AdminsterVO adminster = new AdminsterVO();
//		adminster.setAdminster_id("qqq");
//		adminster.setAdminster_password("123");
//		adminster.setAdminster_name("pig");
//		adminster.setJob_title("employee");
//		AdminsterDAO test = new AdminsterDAO();
//		test.insert(adminster);
//		System.out.println(test.num);
		
		//update
//		AdminsterVO adminster = new AdminsterVO();
//		adminster.setAdminster_id("qqq");
//		adminster.setAdminster_password("456");
//		adminster.setAdminster_name("dog");
//		adminster.setJob_title("cat");
//		AdminsterDAO test = new AdminsterDAO();
//		test.update(adminster);
//		System.out.println(test.num);
		
		//delete
	
//		AdminsterDAO test = new AdminsterDAO();
//		test.delete("qqq");
//		System.out.println(test.num);
		
		//GET_ONE
//		AdminsterDAO test = new AdminsterDAO();
//		AdminsterVO adminsterVO=test.findByPrimaryKey("1");
//		System.out.println(adminsterVO.getAdminster_id()+","+adminsterVO.getAdminster_password()+
//				","+adminsterVO.getAdminster_name()+","+adminsterVO.getJob_title());
		//GET_ALL
		AdminsterDAO test = new AdminsterDAO();
	                     List<AdminsterVO> list = test.getAll();
	                     for(AdminsterVO adminster:list){
	                    	 System.out.println(adminster.getAdminster_id()+","+adminster.getAdminster_password()+
	                    			 ","+adminster.getAdminster_name()+","+adminster.getJob_title());
	                    	 
	                     }
		
	}

}
