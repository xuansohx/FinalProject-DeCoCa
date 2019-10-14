package Final.vo;

public class Orderm {
	String order_main_no;
	String customer_id;
	int order_qt;
	double order_total_price;
	String dt_addr;
	String dt_contact_no;
	String order_payment;
	String order_status;
	
	public Orderm() {
		
	}

	public Orderm(String customer_id, int order_qt, double order_total_price, String dt_addr, String dt_contact_no,
			String order_payment, String order_status) {
		
		this.customer_id = customer_id;
		this.order_qt = order_qt;
		this.order_total_price = order_total_price;
		this.dt_addr = dt_addr;
		this.dt_contact_no = dt_contact_no;
		this.order_payment = order_payment;
		this.order_status = order_status;
	}

	public Orderm(String customer_id, int order_qt, double order_total_price, String dt_addr, String dt_contact_no,
			String order_payment) {
	
		this.customer_id = customer_id;
		this.order_qt = order_qt;
		this.order_total_price = order_total_price;
		this.dt_addr = dt_addr;
		this.dt_contact_no = dt_contact_no;
		this.order_payment = order_payment;
	}

	public String getOrder_main_no() {
		return order_main_no;
	}

	public void setOrder_main_no(String order_main_no) {
		this.order_main_no = order_main_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getOrder_qt() {
		return order_qt;
	}

	public void setOrder_qt(int order_qt) {
		this.order_qt = order_qt;
	}

	public double getOrder_total_price() {
		return order_total_price;
	}

	public void setOrder_total_price(double order_total_price) {
		this.order_total_price = order_total_price;
	}

	public String getDt_addr() {
		return dt_addr;
	}

	public void setDt_addr(String dt_addr) {
		this.dt_addr = dt_addr;
	}

	public String getDt_contact_no() {
		return dt_contact_no;
	}

	public void setDt_contact_no(String dt_contact_no) {
		this.dt_contact_no = dt_contact_no;
	}

	public String getOrder_payment() {
		return order_payment;
	}

	public void setOrder_payment(String order_payment) {
		this.order_payment = order_payment;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	@Override
	public String toString() {
		return "Orderm [order_main_no=" + order_main_no + ", customer_id=" + customer_id + ", order_qt=" + order_qt
				+ ", order_total_price=" + order_total_price + ", dt_addr=" + dt_addr + ", dt_contact_no="
				+ dt_contact_no + ", order_payment=" + order_payment + ", order_status=" + order_status + "]";
	}
	
	

	
	
}
