package member;

import java.io.Serializable;
import java.sql.Date;

public class MemberVO implements Serializable{
	private Integer member_no;
	private String member_name;
	private String member_id;
	private String member_password;
	private String member_phone;
	private String member_address;
	private String member_gender;
	private String member_Email;
	private Date member_birthday;
	private Integer member_bonus;
	private String member_GoogleId;
	
	
	
	
	
	public String getMember_GoogleId() {
		return member_GoogleId;
	}
	public void setMember_GoogleId(String member_GoogleId) {
		this.member_GoogleId = member_GoogleId;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_Email() {
		return member_Email;
	}
	public void setMember_Email(String member_Email) {
		this.member_Email = member_Email;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}
	public Integer getMember_bonus() {
		return member_bonus;
	}
	public void setMember_bonus(Integer member_bonus) {
		this.member_bonus = member_bonus;
	}
}
