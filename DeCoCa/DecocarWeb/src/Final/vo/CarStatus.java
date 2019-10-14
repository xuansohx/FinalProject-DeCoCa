package Final.vo;

public class CarStatus {
	int statusid;
	int battery;
	int tire;
	int door;
	int safebelt;
	int brake;
	int engine;
	int handle;
	String temperature;
	String speed;
	String carlng;
	String carlat;
	
	public CarStatus() {
		
	}

	public CarStatus(int statusid, int battery, int tire, int door, int safebelt, int brake, int engine, int handle,
			String temperature, String speed, String carlng, String carlat) {
		
		this.statusid = statusid;
		this.battery = battery;
		this.tire = tire;
		this.door = door;
		this.safebelt = safebelt;
		this.brake = brake;
		this.engine = engine;
		this.handle = handle;
		this.temperature = temperature;
		this.speed = speed;
		this.carlng = carlng;
		this.carlat = carlat;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getTire() {
		return tire;
	}

	public void setTire(int tire) {
		this.tire = tire;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public int getSafebelt() {
		return safebelt;
	}

	public void setSafebelt(int safebelt) {
		this.safebelt = safebelt;
	}

	public int getBrake() {
		return brake;
	}

	public void setBrake(int brake) {
		this.brake = brake;
	}

	public int getEngine() {
		return engine;
	}

	public void setEngine(int engine) {
		this.engine = engine;
	}

	public int getHandle() {
		return handle;
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getCarlng() {
		return carlng;
	}

	public void setCarlng(String carlng) {
		this.carlng = carlng;
	}

	public String getCarlat() {
		return carlat;
	}

	public void setCarlat(String carlat) {
		this.carlat = carlat;
	}
	
}

