package Final.vo;

import org.springframework.web.multipart.MultipartFile;

public class Product {

	int product_no;
	String cate;
	String product_nm;
	double product_price;
	int product_st;
	String product_picture1;
	String product_picture2;
	String product_picture3;
	String farm_license_no;
	String farm_nm;
	String farm_ceo;
	String farm_addr;
	String farm_phone;
	MultipartFile mf;
	MultipartFile mf1;
	MultipartFile mf2;
	public Product() {
	
	}
	public Product(int product_no, String cate, String product_nm, double product_price, int product_st,
			String product_picture1, String product_picture2, String product_picture3, String farm_license_no,
			String farm_nm, String farm_ceo, String farm_addr, String farm_phone, MultipartFile mf, MultipartFile mf1,
			MultipartFile mf2) {
		super();
		this.product_no = product_no;
		this.cate = cate;
		this.product_nm = product_nm;
		this.product_price = product_price;
		this.product_st = product_st;
		this.product_picture1 = product_picture1;
		this.product_picture2 = product_picture2;
		this.product_picture3 = product_picture3;
		this.farm_license_no = farm_license_no;
		this.farm_nm = farm_nm;
		this.farm_ceo = farm_ceo;
		this.farm_addr = farm_addr;
		this.farm_phone = farm_phone;
		this.mf = mf;
		this.mf1 = mf1;
		this.mf2 = mf2;
	}
	public Product(String cate, String product_nm, double product_price, int product_st, String product_picture1,
			String product_picture2, String product_picture3, String farm_license_no, String farm_nm, String farm_ceo,
			String farm_addr, String farm_phone, MultipartFile mf, MultipartFile mf1, MultipartFile mf2) {
		super();
		this.cate = cate;
		this.product_nm = product_nm;
		this.product_price = product_price;
		this.product_st = product_st;
		this.product_picture1 = product_picture1;
		this.product_picture2 = product_picture2;
		this.product_picture3 = product_picture3;
		this.farm_license_no = farm_license_no;
		this.farm_nm = farm_nm;
		this.farm_ceo = farm_ceo;
		this.farm_addr = farm_addr;
		this.farm_phone = farm_phone;
		this.mf = mf;
		this.mf1 = mf1;
		this.mf2 = mf2;
	}
	public Product(String cate, String product_nm, double product_price, int product_st, String product_picture1,
			String product_picture2, String product_picture3, String farm_license_no, String farm_nm, String farm_ceo,
			String farm_addr, String farm_phone, MultipartFile mf) {
		super();
		this.cate = cate;
		this.product_nm = product_nm;
		this.product_price = product_price;
		this.product_st = product_st;
		this.product_picture1 = product_picture1;
		this.product_picture2 = product_picture2;
		this.product_picture3 = product_picture3;
		this.farm_license_no = farm_license_no;
		this.farm_nm = farm_nm;
		this.farm_ceo = farm_ceo;
		this.farm_addr = farm_addr;
		this.farm_phone = farm_phone;
		this.mf = mf;
	}
	public Product(String cate, String product_nm, double product_price, int product_st, String product_picture1,
			String product_picture2, String product_picture3, String farm_license_no, String farm_nm, String farm_ceo,
			String farm_addr, String farm_phone, MultipartFile mf, MultipartFile mf1) {
		super();
		this.cate = cate;
		this.product_nm = product_nm;
		this.product_price = product_price;
		this.product_st = product_st;
		this.product_picture1 = product_picture1;
		this.product_picture2 = product_picture2;
		this.product_picture3 = product_picture3;
		this.farm_license_no = farm_license_no;
		this.farm_nm = farm_nm;
		this.farm_ceo = farm_ceo;
		this.farm_addr = farm_addr;
		this.farm_phone = farm_phone;
		this.mf = mf;
		this.mf1 = mf1;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getProduct_nm() {
		return product_nm;
	}
	public void setProduct_nm(String product_nm) {
		this.product_nm = product_nm;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public int getProduct_st() {
		return product_st;
	}
	public void setProduct_st(int product_st) {
		this.product_st = product_st;
	}
	public String getProduct_picture1() {
		return product_picture1;
	}
	public void setProduct_picture1(String product_picture1) {
		this.product_picture1 = product_picture1;
	}
	public String getProduct_picture2() {
		return product_picture2;
	}
	public void setProduct_picture2(String product_picture2) {
		this.product_picture2 = product_picture2;
	}
	public String getProduct_picture3() {
		return product_picture3;
	}
	public void setProduct_picture3(String product_picture3) {
		this.product_picture3 = product_picture3;
	}
	public String getFarm_license_no() {
		return farm_license_no;
	}
	public void setFarm_license_no(String farm_license_no) {
		this.farm_license_no = farm_license_no;
	}
	public String getFarm_nm() {
		return farm_nm;
	}
	public void setFarm_nm(String farm_nm) {
		this.farm_nm = farm_nm;
	}
	public String getFarm_ceo() {
		return farm_ceo;
	}
	public void setFarm_ceo(String farm_ceo) {
		this.farm_ceo = farm_ceo;
	}
	public String getFarm_addr() {
		return farm_addr;
	}
	public void setFarm_addr(String farm_addr) {
		this.farm_addr = farm_addr;
	}
	public String getFarm_phone() {
		return farm_phone;
	}
	public void setFarm_phone(String farm_phone) {
		this.farm_phone = farm_phone;
	}
	public MultipartFile getMf() {
		return mf;
	}
	public void setMf(MultipartFile mf) {
		this.mf = mf;
	}
	public MultipartFile getMf1() {
		return mf1;
	}
	public void setMf1(MultipartFile mf1) {
		this.mf1 = mf1;
	}
	public MultipartFile getMf2() {
		return mf2;
	}
	public void setMf2(MultipartFile mf2) {
		this.mf2 = mf2;
	}
	@Override
	public String toString() {
		return "Product [product_no=" + product_no + ", cate=" + cate + ", product_nm=" + product_nm
				+ ", product_price=" + product_price + ", product_st=" + product_st + ", product_picture1="
				+ product_picture1 + ", product_picture2=" + product_picture2 + ", product_picture3=" + product_picture3
				+ ", farm_license_no=" + farm_license_no + ", farm_nm=" + farm_nm + ", farm_ceo=" + farm_ceo
				+ ", farm_addr=" + farm_addr + ", farm_phone=" + farm_phone + ", mf=" + mf + ", mf1=" + mf1 + ", mf2="
				+ mf2 + "]";
	}
    
	
    
    
    
    
	
}
