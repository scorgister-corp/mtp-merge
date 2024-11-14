package net.scorgister.tam;

import java.util.Date;

public class StopTime {
	
	private Trip trip;
	private Date arrivalTime, departureTime;
	private Stop stop;
	private int stopSequence, pickupType, dropOffType;
	
	public StopTime(Trip trip, Date arrivalTime, Date departureTime, Stop stop, int stopSequence, int pickupType, int dropOffType) {
		this.trip = trip;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stop = stop;
		this.stopSequence = stopSequence;
		this.pickupType = pickupType;
		this.dropOffType = dropOffType;
	}

	public Trip getTrip() {
		return trip;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public Stop getStop() {
		return stop;
	}

	public int getStopSequence() {
		return stopSequence;
	}

	public int getPickupType() {
		return pickupType;
	}

	public int getDropOffType() {
		return dropOffType;
	}
	
	@Override
	public String toString() {
		return "stop-time@[trip=" + trip + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", stop=" + stop + ", stopSequence=" + stopSequence +", pickupType=" + pickupType + ", dropOffType=" + dropOffType + "]";
	}

}
