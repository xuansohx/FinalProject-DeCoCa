package Final.vo;

public class Cart {
	int cart_id;
	String customer_id;
	int product_no;
	int product_qt;
	double product_price;
	String product_picture1;
	public Cart() {	
	}
	public Cart(String customer_id, int product_no, int product_qt, double product_price, String product_picture1) {
		super();
		this.customer_id = customer_id;
		this.product_no = product_no;
		this.product_qt = product_qt;
		this.product_price = product_price;
		this.product_picture1 = product_picture1;
	}

	public Cart(int cart_id, String customer_id, int product_no, int product_qt, double product_price) {
		
		this.cart_id = cart_id;
		this.customer_id = customer_id;
		this.product_no = product_no;
		this.product_qt = product_qt;
		this.product_price = product_price;
	}
	
	public Cart(String customer_id, int product_no, int product_qt, double product_price) {

		this.customer_id = customer_id;
		this.product_no = product_no;
		this.product_qt = product_qt;
		this.product_price = product_price;
	}
	
	
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getProduct_qt() {
		return product_qt;
	}

	public void setProduct_qt(int product_qt) {
		this.product_qt = product_qt;
	}

	public String getProduct_picture1() {
		return product_picture1;
	}

	public void setProduct_picture1(String product_picture1) {
		this.product_picture1 = product_picture1;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", customer_id=" + customer_id + ", product_no=" + product_no
				+ ", product_qt=" + product_qt + ", product_price=" + product_price + ", product_picture1="
				+ product_picture1 + "]";
	}


	
}
