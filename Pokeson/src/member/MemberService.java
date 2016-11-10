package member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.sun.media.sound.SoftMixingSourceDataLine;

public class MemberService {
	private Member_Interface dao;
	private MessageDigest md; //java.security
	
	public MemberService(){
		dao = new MemberDAO();
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	static //驗證帳號是否已經存在資料庫
	MemberService service = new MemberService();
	private static List<MemberVO> list = service.getAll();
	
	public boolean exist(String member_id){
		boolean flag = false;
		for(MemberVO mbr: list){
			if(mbr.getMember_id().equals(member_id.trim())){
				System.out.println("帳號已存在");
				flag=true;
				break;
			}
		}
		return flag;	
	}
	
	//驗證帳號密碼是否正確
	public boolean loginCheck(String member_id, String member_password){
		boolean flag = false;
		//將使用者輸入轉成亂碼
//		byte[]temp = member_password.getBytes(); //使用者輸入:明碼
//		temp = md.digest(temp);	//使用者輸入:亂碼
//		System.out.println(temp);
//		member_password = DatatypeConverter.printHexBinary(temp);//使用者輸入:亂碼
		
		for(MemberVO mbr : list){
			if(mbr.getMember_id().equals(member_id)){	
				if(mbr.getMember_password().equals(member_password)){
					flag=true;
					break;
				}				
			}
		}
		return flag;
	}
	
	public boolean googleExist(String member_GoogleId){
		List<MemberVO> list = service.getAll();
		boolean flag = true;
//		System.out.println(member_GoogleId);
		for(MemberVO mbr: list){
			String googleId=mbr.getMember_GoogleId();
			System.out.println(googleId);
				if(mbr.getMember_GoogleId()!=null&&googleId.equals(member_GoogleId)){
			System.out.println("驗證Google帳號重複");
				flag=false;
				break;
			}
			else{
				System.out.println("驗證Google帳號無重複");
			}
		}	
		return flag;
		
	}
	
	
	public MemberVO addMbr(String member_name, String member_id, 
			String member_password, String member_phone, String member_address, String member_gender,
			String member_Email, Date member_birthday, Integer member_bonus,String member_GoogleId ){
		
//		//轉成亂碼
//		byte[]temp = member_password.getBytes(); //使用者輸入:明碼
//		temp = md.digest(temp);	//使用者輸入:亂碼
//		System.out.println(temp);
//		member_password = DatatypeConverter.printHexBinary(temp); //轉成亂碼
		
		MemberVO mbr = new MemberVO();
		
		mbr.setMember_name(member_name);
		mbr.setMember_id(member_id);
		mbr.setMember_password(member_password);
		mbr.setMember_phone(member_phone);
		mbr.setMember_address(member_address);
		mbr.setMember_gender(member_gender);
		mbr.setMember_Email(member_Email);
		mbr.setMember_birthday(member_birthday);
		mbr.setMember_bonus(member_bonus);
		mbr.setMember_GoogleId(member_GoogleId);
		
		dao.insert(mbr);
		
		return mbr;
	}
	
	public MemberVO addGoogleMbr(String member_name,String member_Email,String member_GoogleId ){
		
		MemberVO mbr = new MemberVO();
		
		mbr.setMember_name(member_name);
		mbr.setMember_Email(member_Email);
		System.out.println(member_GoogleId);
		mbr.setMember_GoogleId(member_GoogleId);
	
		dao.insert(mbr);
		
		return mbr;
	}
	
	
	
	public MemberVO updateMbr(Integer member_no, String member_name, String member_id, 
			String member_password, String member_phone, String member_address, String member_gender,
			String member_Email, Date member_birthday, Integer member_bonus,String member_GoogleId ){
		
//		//轉成亂碼
//		byte[]temp = member_password.getBytes(); //使用者輸入:明碼
//		temp = md.digest(temp);	//使用者輸入:亂碼
//		System.out.println(temp);
//		member_password = DatatypeConverter.printHexBinary(temp); //轉成亂碼
		
		MemberVO mbr = new MemberVO();
		
		mbr.setMember_no(member_no);
		mbr.setMember_name(member_name);
		mbr.setMember_id(member_id);
		mbr.setMember_password(member_password);
		mbr.setMember_phone(member_phone);
		mbr.setMember_address(member_address);
		mbr.setMember_gender(member_gender);
		mbr.setMember_Email(member_Email);
		mbr.setMember_birthday(member_birthday);
		mbr.setMember_bonus(member_bonus);
		mbr.setMember_GoogleId(member_GoogleId);
		dao.update(mbr);
		
		return dao.findByPrimaryKey(member_no);
	}
	
	public void deleteMbr(Integer member_no){
		dao.delete(member_no);
	}
	
	public MemberVO getOneMbr(Integer member_no){
		return dao.findByPrimaryKey(member_no);
		
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
		
	}
	
	public MemberVO getOneById(String member_id){
		return dao.findById(member_id);
	}
	public List<MemberVO> getGoogleOneByNo(String member_GoogleId){
		return dao.findGoogleById(member_GoogleId);
	}
	
	
	//測試驗證帳號方法exist()
//	public static void main(String[] args){
//		MemberService svc =new MemberService();
//		System.out.println(svc.exist("apple"));
//	}
}
