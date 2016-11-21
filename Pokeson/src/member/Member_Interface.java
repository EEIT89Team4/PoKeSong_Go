package member;

import java.util.List;

public interface Member_Interface {
	public void insert(MemberVO memberVO);
	public void delete(int memberNo);
	public void update(MemberVO memberVO);
	public void updateGooglePhone(int member_no,String phone,String email,String name);
	public void updateGoogleAddress(int member_no,String address,String email,String name);
	public MemberVO findByPrimaryKey(Integer member_no);
	public List<MemberVO> getAll();
	public MemberVO findById(String member_id);
	public MemberVO findByEmail(String member_Email);
	public List<MemberVO> findGoogleById(String member_GoogleId);
}
