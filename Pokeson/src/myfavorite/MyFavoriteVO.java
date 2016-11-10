package myfavorite;

import java.io.Serializable;

import member.MemberVO;
import product.ProductVO;

public class MyFavoriteVO implements Serializable {
//private int product_no;
//private int member_no;
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
//public int getProduct_no() {
//	return product_no;
//}
//public void setProduct_no(int product_no) {
//	this.product_no = product_no;
//}
//public int getMember_no() {
//	return member_no;
//}
//public void setMember_no(int member_no) {
//	this.member_no = member_no;
//}


}
