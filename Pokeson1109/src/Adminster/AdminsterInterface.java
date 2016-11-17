package Adminster;

import java.sql.SQLException;

import java.util.List;



public interface AdminsterInterface {
	public Integer insert(AdminsterVO adminster)throws SQLException;
	public Integer update(AdminsterVO adminster);
	public Integer delete(String adminster_id);
	public AdminsterVO findByPrimaryKey(String adminster_id);
	public List<AdminsterVO> getAll(); 
}
