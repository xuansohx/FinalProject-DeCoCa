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

	public CarStatus(int catid, String status) {
		this.carid = catid;
		this.status = status;
	}

	public int getCatid() {
		return carid;
	}

	public void setCatid(int catid) {
		this.carid = catid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CarStatus [catid=" + carid + ", status=" + status + "]";
	}	
}
