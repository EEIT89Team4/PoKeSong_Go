package Adminster;

import java.sql.SQLException;

import java.util.List;
import java.util.Set;


public class AdminsterService {

	private AdminsterInterface dao;

	public AdminsterService() {
		dao = new AdminsterDAO();
	}

	public AdminsterVO addadminster(String adminster_id, String adminster_password, String adminster_name,
			String job_title) throws SQLException {

		AdminsterVO adminsterVO = new AdminsterVO();

		adminsterVO.setAdminster_id(adminster_id);
		adminsterVO.setAdminster_password(adminster_password);
		adminsterVO.setAdminster_name(adminster_name);
		adminsterVO.setJob_title(job_title);
		dao.insert(adminsterVO);

		return adminsterVO;
	}
	
	public List<AdminsterVO> getAll() {
		return dao.getAll();
	}

	public AdminsterVO findByPrimaryKey(String adminster_id) {
		return dao.findByPrimaryKey(adminster_id);
	}

//	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
//		return dao.getEmpsByDeptno(deptno);
//	}

	public void delete(String adminster_id) {
		dao.delete(adminster_id);
	}

	
}
