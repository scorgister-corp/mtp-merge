package net.scorgister.tam;

import java.util.ArrayList;
import java.util.List;

public class GroupedTrip {
	
	private List<StopTime> stopTimes = new ArrayList<StopTime>();
	private Trip trip;
	
	public GroupedTrip(Trip trip, List<StopTime> stopTimes) {
		this.trip = trip;
		this.stopTimes = stopTimes;
	}
	
	public List<StopTime> getStopTimes() {
		return stopTimes;
	}
	
	public StopTime get(int i) {
		return stopTimes.get(i);
	}
	
	public Trip getTrip() {
		return trip;
	}
	
	@Override
	public String toString() {
		String ret = "grouped-trip@[";
		for(StopTime st : stopTimes)
			ret += st.toString() + ", ";
		
		if(ret.endsWith(", "))
			ret = ret.substring(0, ret.length()-2);
		
		return ret + "]";
	}
	
}
