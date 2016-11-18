package com.notifymember.model;

import java.util.List
;

import product.ProductVO;



public interface INotifyMemberDAO {

	void insert(NotifyMemberVO notifymembervo);

	void deteteByProductVO(ProductVO productvo);

	List<NotifyMemberVO> getListMemberByProduct(ProductVO productvo);

	Object[] getListMemberEmail(ProductVO productvo);

}