package Final.vo;

public class Path {
	int carid;
	int idx;
	String lng;
	String lat;

	public Path() {
	}

	public Path(int carid, int idx, String lng, String lat) {
		super();
		this.carid = carid;
		this.idx = idx;
		this.lng = lng;
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "Path [carid=" + carid + ", idx=" + idx + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}
