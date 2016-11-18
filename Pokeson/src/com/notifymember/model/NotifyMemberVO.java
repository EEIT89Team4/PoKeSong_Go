package com.notifymember.model;

import java.io.Serializable
;

import member.MemberVO;
import product.ProductVO;


public class NotifyMemberVO implements Serializable {
private ProductVO productVO;
private MemberVO memberVO;
public ProductVO getProductVO() {
	return productVO;
}
public void setProductVO(ProductVO productVO) {
	this.productVO = productVO;
}
public MemberVO getMemberVO() {
	return memberVO;
}
public void setMemberVO(MemberVO memberVO) {
	this.memberVO = memberVO;
}




}
