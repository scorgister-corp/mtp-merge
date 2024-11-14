package net.scorgister.tam;

public class Stop {
		
	private String id, name, lat, lon, locType, parentStation;
	private int code;
	private boolean wheelchairBoarding;
	
	public Stop(String id, int code, String name, String lat, String lon, String locType, String parentStation, boolean wheelchairBoarding) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.locType = locType;
		this.parentStation = parentStation;
		this.wheelchairBoarding = wheelchairBoarding;
	}

	public String getName() {
		return name;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public String getLocationType() {
		return locType;
	}

	public String getParentStation() {
		return parentStation;
	}

	public String getId() {
		return id;
	}

	public int getCode() {
		return code;
	}

	public boolean isWheelchairBoarding() {
		return wheelchairBoarding;
	}
	
	@Override
	public String toString() {
		return "stop@[id=" + id + ", code=" + code + ", name=" + name + ", lat=" + lat + ", lon=" + lon + ", locType=" + locType + ", parentStation=" + parentStation + ", weelchairBoarding=" + wheelchairBoarding + "]";
	}

}
