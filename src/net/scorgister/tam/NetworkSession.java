package net.scorgister.tam;

import java.util.ArrayList;
import java.util.List;

public class NetworkSession {
	
	private List<Agency> agencies;
	private List<Route> routes;
	private List<Stop> stops;
	private List<Trip> trips;
	private List<StopTime> stopTimes;
	private List<GroupedTrip> groupedTrips;
	
	public NetworkSession(List<Agency> agencies, List<Route> routes, List<Stop> stops, List<Trip> trips, List<StopTime> stopTimes, List<GroupedTrip> groupedTrips) {
		this.agencies = agencies;
		this.routes = routes;
		this.stops = stops;
		this.trips = trips;
		this.stopTimes = stopTimes;
		this.groupedTrips = groupedTrips;
	}

	public List<Agency> getAgencies() {
		return agencies;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public List<Stop> getStops() {
		return stops;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public List<StopTime> getStopTimes() {
		return stopTimes;
	}

	public List<GroupedTrip> getGroupedTrips() {
		return groupedTrips;
	}
	
	public Route getRoute(String id) {
		for(Route route : routes)
			if(route.getId().equals(id))
				return route;
		return null;
	}
	
	public Agency getAgency(String id) {
		for(Agency agency : agencies)
			if(agency.getId().equals(id))
				return agency;
		return null;
	}
	
	public Stop getStop(String id) {
		for(Stop stop : stops)
			if(stop.getId().equals(id))
				return stop;
		return null;
	}
	
	public List<Stop> getStopByName(String name) {
		List<Stop> stps = new ArrayList<Stop>();
		for(Stop stop : stops)
			if(stop.getName().toLowerCase().equals(name.toLowerCase()))
				stps.add(stop);
		return stps;
	}
	
	public Trip getTrip(String id) {
		for(Trip trip : trips)
			if(trip.getId().equals(id))
				return trip;
		return null;
	}
	
}
