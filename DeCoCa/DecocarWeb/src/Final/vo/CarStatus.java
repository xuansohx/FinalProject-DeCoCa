package Final.vo;

public class CarStatus {
	int carid;
	String status;
//	int statusid;
//	int battery;
//	int tire;
//	int door;
//	int safebelt;
//	int brake;
//	int engine;
//	int handle;
//	String temperature;
//	String speed;
//	String carlng;
//	String carlat;

	public CarStatus() {

	}

	public CarStatus(int carid, String status) {
		this.carid = carid;
		this.status = status;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CarStatus [carid=" + carid + ", status=" + status + "]";
	}	
}
