package com.cm.model;

import java.util.List;
import java.util.Set;

import com.cm.model.CmDAO;
import com.cm.model.CmVO;

public class CmService {
	public void insert(CmVO cmVO) {
		 CmDAO cmdao = new CmDAO();
		 cmdao.insert(cmVO);
		}

		public void update(CmVO cmVO) {
			 CmDAO cmdao = new CmDAO();
			 cmdao.update(cmVO);

		}

		public void delete(Integer cm_no) {
			CmDAO cmdao = new CmDAO();
			cmdao.delete(cm_no);

		}

		public CmVO findByPrimaryKey(Integer cm_no) {
			CmDAO cmdao = new CmDAO();
			 return cmdao.findByPrimaryKey(cm_no);
		}

		public List<CmVO> getAllCm() {
			CmDAO cmdao = new CmDAO();
			 return cmdao.getAllCm();
		}
}
