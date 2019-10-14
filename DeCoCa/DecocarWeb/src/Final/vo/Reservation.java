package Final.vo;

public class Reservation {
	
	int calid;
	String userid;
	String calName;
	String calDate;
	String sTime;
	String eTime;
	String sLng;
	String sLat;
	String eLng;
	String eLat;
	int carid;
	int sStyle;
	String reuserid;
	int pinNum;
	int calstatus;
	
	//주소명 (출발, 도착)
	String sAddress;
	String eAddress;
	String memo;
	

	public Reservation() {
	}


	public Reservation(int calid, String userid, String calName, String calDate, String sTime, String eTime,
			String sLng, String sLat, String eLng, String eLat, int carid, int sStyle, String reuserid, int pinNum,
			int calstatus, String sAddress, String eAddress, String memo) {
		this.calid = calid;
		this.userid = userid;
		this.calName = calName;
		this.calDate = calDate;
		this.sTime = sTime;
		this.eTime = eTime;
		this.sLng = sLng;
		this.sLat = sLat;
		this.eLng = eLng;
		this.eLat = eLat;
		this.carid = carid;
		this.sStyle = sStyle;
		this.reuserid = reuserid;
		this.pinNum = pinNum;
		this.calstatus = calstatus;
		this.sAddress = sAddress;
		this.eAddress = eAddress;
		this.memo = memo;
	}


	public int getCalid() {
		return calid;
	}


	public void setCalid(int calid) {
		this.calid = calid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getCalName() {
		return calName;
	}


	public void setCalName(String calName) {
		this.calName = calName;
	}


	public String getCalDate() {
		return calDate;
	}


	public void setCalDate(String calDate) {
		this.calDate = calDate;
	}


	public String getsTime() {
		return sTime;
	}


	public void setsTime(String sTime) {
		this.sTime = sTime;
	}


	public String geteTime() {
		return eTime;
	}


	public void seteTime(String eTime) {
		this.eTime = eTime;
	}


	public String getsLng() {
		return sLng;
	}


	public void setsLng(String sLng) {
		this.sLng = sLng;
	}


	public String getsLat() {
		return sLat;
	}


	public void setsLat(String sLat) {
		this.sLat = sLat;
	}


	public String geteLng() {
		return eLng;
	}


	public void seteLng(String eLng) {
		this.eLng = eLng;
	}


	public String geteLat() {
		return eLat;
	}


	public void seteLat(String eLat) {
		this.eLat = eLat;
	}


	public int getCarid() {
		return carid;
	}


	public void setCarid(int carid) {
		this.carid = carid;
	}


	public int getsStyle() {
		return sStyle;
	}


	public void setsStyle(int sStyle) {
		this.sStyle = sStyle;
	}


	public String getReuserid() {
		return reuserid;
	}


	public void setReuserid(String reuserid) {
		this.reuserid = reuserid;
	}


	public int getPinNum() {
		return pinNum;
	}


	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}


	public int getCalstatus() {
		return calstatus;
	}


	public void setCalstatus(int calstatus) {
		this.calstatus = calstatus;
	}


	public String getsAddress() {
		return sAddress;
	}


	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}


	public String geteAddress() {
		return eAddress;
	}


	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	@Override
	public String toString() {
		return "Reservation [calid=" + calid + ", userid=" + userid + ", calName=" + calName + ", calDate=" + calDate
				+ ", sTime=" + sTime + ", eTime=" + eTime + ", sLng=" + sLng + ", sLat=" + sLat + ", eLng=" + eLng
				+ ", eLat=" + eLat + ", carid=" + carid + ", sStyle=" + sStyle + ", reuserid=" + reuserid + ", pinNum="
				+ pinNum + ", calstatus=" + calstatus + ", sAddress=" + sAddress + ", eAddress=" + eAddress + ", memo="
				+ memo + "]";
	}

}
