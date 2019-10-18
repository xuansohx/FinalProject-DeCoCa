package Final.vo;

public class Car {
	int carid;
	int statusid;
	int centerid;
	int cartype;
	String carnumber;
	int calid;
	
	public Car() {
		
	}

	public Car(int carid, int statusid, int centerid, int cartype, String carnumber, int calid) {
		super();
		this.carid = carid;
		this.statusid = statusid;
		this.centerid = centerid;
		this.cartype = cartype;
		this.carnumber = carnumber;
		this.calid = calid;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getCenterid() {
		return centerid;
	}

	public void setCenterid(int centerid) {
		this.centerid = centerid;
	}

	public int getCartype() {
		return cartype;
	}

	public void setCartype(int cartype) {
		this.cartype = cartype;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public int getCalid() {
		return calid;
	}

	public void setCalid(int calid) {
		this.calid = calid;
	}

	@Override
	public String toString() {
		return "Car [carid=" + carid + ", statusid=" + statusid + ", centerid=" + centerid + ", cartype=" + cartype
				+ ", carnumber=" + carnumber + ", calid=" + calid + "]";
	}
	
	

}
