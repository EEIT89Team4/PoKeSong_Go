package Adminster;

import java.io.Serializable;

public class AdminsterVO implements Serializable{
	private String adminster_id;
	private String adminster_password;
	private String adminster_name;
	private String job_title;
	
    public AdminsterVO(){
    	
    }
    public AdminsterVO(String adminster_id,String adminster_password,String adminster_name,String job_title){
    	this.adminster_id=adminster_id;
    	this.adminster_password=adminster_password;
    	this.adminster_name=adminster_name;
    	this.job_title=job_title;
    	
    }
	public String getAdminster_id() {
		return adminster_id;
	}
	public void setAdminster_id(String adminster_id) {
		this.adminster_id = adminster_id;
	}
	public String getAdminster_password() {
		return adminster_password;
	}
	public void setAdminster_password(String adminster_password) {
		this.adminster_password = adminster_password;
	}
	public String getAdminster_name() {
		return adminster_name;
	}
	public void setAdminster_name(String adminster_name) {
		this.adminster_name = adminster_name;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	

}
