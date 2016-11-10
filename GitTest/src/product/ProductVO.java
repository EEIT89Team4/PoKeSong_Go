package product;

import java.io.InputStream;
import java.sql.Date;

public class ProductVO {
private int product_no;
private int class_no;
private int classdetail_no;
private int product_price;
private String unit;
private String supplier_name;
private String product_name;
private int product_quantity;
private int saleaccount_quantity;
private int weight;
private Date deadline;
private Integer deadlineday;
private byte[] bsizepic;
private byte[] pictrue;
private int product_alertquantity;
private String product_description;
public int getProduct_no() {
	return product_no;
}
public void setProduct_no(int product_no) {
	this.product_no = product_no;
}
public int getClass_no() {
	return class_no;
}
public void setClass_no(int class_no) {
	this.class_no = class_no;
}
public int getClassdetail_no() {
	return classdetail_no;
}
public void setClassdetail_no(int classdetail_no) {
	this.classdetail_no = classdetail_no;
}
public int getProduct_price() {
	return product_price;
}
public void setProduct_price(int product_price) {
	this.product_price = product_price;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getSupplier_name() {
	return supplier_name;
}
public void setSupplier_name(String supplier_name) {
	this.supplier_name = supplier_name;
}
public String getProduct_name() {
	return product_name;
}
public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public int getProduct_quantity() {
	return product_quantity;
}
public void setProduct_quantity(int product_quantity) {
	this.product_quantity = product_quantity;
}
public int getSaleaccount_quantity() {
	return saleaccount_quantity;
}
public void setSaleaccount_quantity(int saleaccount_quantity) {
	this.saleaccount_quantity = saleaccount_quantity;
}
public int getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}
public Date getDeadline() {
	return deadline;
}
public void setDeadline(Date deadline) {
	this.deadline = deadline;
}
public Integer getDeadlineday() {
	return deadlineday;
}
public void setDeadlineday(Integer deadlineday) {
	this.deadlineday = deadlineday;
}
public byte[] getPictrue() {
	return pictrue;
}
public void setPictrue(byte[] pictrue) {
	this.pictrue = pictrue;
}
public byte[] getBsizepic() {
	return bsizepic;
}
public void setBsizepic(byte[] bsizepic) {
	this.bsizepic = bsizepic;
}
public int getProduct_alertquantity() {
	return product_alertquantity;
}
public void setProduct_alertquantity(int product_alertquantity) {
	this.product_alertquantity = product_alertquantity;
}
public String getProduct_description() {
	return product_description;
}
public void setProduct_description(String product_description) {
	this.product_description = product_description;
}

}
