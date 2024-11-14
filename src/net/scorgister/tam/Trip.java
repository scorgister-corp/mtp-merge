package net.scorgister.tam;

public class Trip {
	
	private Route route;
	
	private String serviceId, id, headsign;
	private int directionId;
	private boolean wheelchairAccessible, bikesAllowed;
	
	public Trip(Route route, String serviceId, String id, String headsign, int directionId, boolean wheelchairAccessible, boolean bikesAllowed) {
		this.route = route;
		this.serviceId = serviceId;
		this.id = id;
		this.headsign = headsign;
		this.directionId = directionId;
		this.wheelchairAccessible = wheelchairAccessible;
		this.bikesAllowed = bikesAllowed;
		
	}

	public Route getRoute() {
		return route;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getId() {
		return id;
	}

	public String getHeadsign() {
		return headsign;
	}

	public int getDirectionId() {
		return directionId;
	}

	public boolean isWheelchairAccessible() {
		return wheelchairAccessible;
	}

	public boolean isBikesAllowed() {
		return bikesAllowed;
	}
	
}
