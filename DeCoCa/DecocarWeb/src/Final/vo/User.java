package Final.vo;

public class User {
	String userid;
	String pwd;
	String name;
	String pnumber;
	int usertype;
	String userdevice;
	
	public User() {
	}

	public User(String userid, String pwd, String name, String pnumber, int usertype, String userdevice) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.pnumber = pnumber;
		this.usertype = usertype;
		this.userdevice = userdevice;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getUserdevice() {
		return userdevice;
	}

	public void setUserdevice(String userdevice) {
		this.userdevice = userdevice;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", pnumber=" + pnumber + ", usertype="
				+ usertype + ", userdevice=" + userdevice + "]";
	}
	
}
