package net.scorgister.tam;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.scorgister.tam.file.CSVData;
import net.scorgister.tam.file.CSVFile;

public class Loader {
	
	public static NetworkSession loadDatas() throws IOException {
		CSVFile agencyFile = new CSVFile("./data/agency.txt");
		
		List<Agency> agencies = new ArrayList<Agency>();
		
		CSVData agencyData = agencyFile.getData();

		List<String> agencyIds = agencyData.get("agency_id");
		List<String> agencyNames = agencyData.get("agency_name");
		List<String> agencyURLs = agencyData.get("agency_url");
		List<String> agencyTimezones = agencyData.get("agency_timezone");
		List<String> agencyLangs = agencyData.get("agency_lang");
		List<String> agencyPhones = agencyData.get("agency_phone");
		List<String> agencyFareURLs = agencyData.get("agency_fare_url");
		
		for(int i = 0; i < agencyIds.size(); i++)
			agencies.add(new Agency(agencyIds.get(i), agencyNames.get(i), agencyURLs.get(i),
					agencyTimezones.get(i), agencyLangs.get(i), agencyPhones.get(i), agencyFareURLs.get(i)));
		
		CSVFile routeFile = new CSVFile("./data/routes.txt");
		
		List<Route> routes = new ArrayList<Route>();
		
		CSVData routesData = routeFile.getData();
		List<String> routeIds = routesData.get("route_id");
		List<String> agencyIdsFromRoute = routesData.get("agency_id");
		List<String> routeShortNames = routesData.get("route_short_name");
		List<String> routeLongNames = routesData.get("route_long_name");
		List<Integer> routeTypes = routesData.getInt("route_type");
		List<Color> routeColors = routesData.getColor("route_color");
		List<Color> routeTextColors = routesData.getColor("route_text_color");
		List<String> routeUrls = routesData.get("route_url");
		
		
		List<Agency> agenciesRoutes = new ArrayList<Agency>();

		
		for(String str : agencyIdsFromRoute)
			for(Agency agency : agencies)
				if(agency.getId().equals(str))
					agenciesRoutes.add(agency);
		
		
		for(int i = 0; i < routeIds.size(); i++)
			routes.add(new Route(routeIds.get(i), agenciesRoutes.get(i), routeShortNames.get(i),
					routeLongNames.get(i), routeUrls.get(i), routeTypes.get(i), routeColors.get(i),
					routeTextColors.get(i)));
		
		CSVFile stopFile = new CSVFile("./data/stops.txt");
		
		List<Stop> stops = new ArrayList<Stop>();
		
		CSVData stopData = stopFile.getData();
		List<String> stopIds = stopData.get("stop_id");
		List<Integer> stopCodes = stopData.getInt("stop_code");
		List<String> stopNames = stopData.get("stop_name");
		List<String> stopLats = stopData.get("stop_lat");
		List<String> stopLons = stopData.get("stop_lon");
		List<String> stopLocationTypes = stopData.get("location_type");
		List<String> stopParentStations = stopData.get("parent_station");
		List<Boolean> stopWheelchairBoardings = stopData.getBool("wheelchair_boarding");
		
		for(int i = 0; i < stopIds.size(); i++)
			stops.add(new Stop(stopIds.get(i), stopCodes.get(i), stopNames.get(i),
					stopLats.get(i), stopLons.get(i), stopLocationTypes.get(i), stopParentStations.get(i), stopWheelchairBoardings.get(i)));
		
		CSVFile tripFile = new CSVFile("./data/trips.txt");
		
		List<Trip> trips = new ArrayList<Trip>();
		
		CSVData tripsData = tripFile.getData();
		List<String> tripRouteIds = tripsData.get("route_id");
		List<String> tripServiceIds = tripsData.get("service_id");
		List<String> tripIds = tripsData.get("trip_id");
		List<String> tripHeadsigns = tripsData.get("trip_headsign");
		List<Integer> tripDirectionIds = tripsData.getInt("direction_id");
		List<Boolean> tripWheelchairAccessibles = tripsData.getBool("wheelchair_accessible");
		List<Boolean> tripBikesAllowed = tripsData.getBool("bikes_allowed");
		
		
		List<Route> routeTrip = new ArrayList<Route>();

		
		for(String str : tripRouteIds)
			for(Route route : routes)
				if(route.getId().equals(str))
					routeTrip.add(route);
		
		
		for(int i = 0; i < tripIds.size(); i++) {
			trips.add(new Trip(routeTrip.get(i), tripServiceIds.get(i), tripIds.get(i),
					tripHeadsigns.get(i), tripDirectionIds.get(i), tripWheelchairAccessibles.get(i), tripBikesAllowed.get(i)));
		}
		CSVFile stopTimeFile = new CSVFile("./data/stop_times.txt");
		
		List<StopTime> stopTimes = new ArrayList<StopTime>();
		
		CSVData stopTimesData = stopTimeFile.getData();
		List<String> stTripIds = stopTimesData.get("trip_id");
		List<Date> stArivalTimes = stopTimesData.getDate("arrival_time", "hh:mm:ss");
		List<Date> stDepartureTimes = stopTimesData.getDate("departure_time", "hh:mm:ss");
		List<String> stStopIds = stopTimesData.get("stop_id");
		List<Integer> stStopSequences = stopTimesData.getInt("stop_sequence");
		List<Integer> stPickupType = stopTimesData.getInt(" pickup_type");
		List<Integer> stDropOffType = stopTimesData.getInt("drop_off_type");
		
		
		List<Trip> stTrips = new ArrayList<Trip>();
	m:	for(String str : stTripIds) {
			for(Trip trip : trips) {
				if(trip.getId().equals(str)) {

					stTrips.add(trip);
					continue m;
				}
			}
			stTrips.add(null);
		}
		
		List<Stop> stStops = new ArrayList<Stop>();
		for(String str : stStopIds)
			for(Stop stop : stops)
				if(stop.getId().equals(str))
					stStops.add(stop);
		
		
		for(int i = 0; i < stTripIds.size(); i++)
			stopTimes.add(new StopTime(stTrips.get(i), 
					stArivalTimes.get(i),
					stDepartureTimes.get(i),
					stStops.get(i), stStopSequences.get(i), stPickupType.get(i), stDropOffType.get(i)));
		
		
		List<GroupedTrip> groupedTrips = new ArrayList<GroupedTrip>();
		
		for(int i = 0; i < stopTimes.size();) {
			int last = -1;
			int j = i;
			List<StopTime> sts = new ArrayList<StopTime>();
			Trip trip = null;
			while(j < stopTimes.size()) {
				int cur = stopTimes.get(j).getStopSequence();
				if(last >= cur)
					break;
				
				if(trip == null)
					trip = stopTimes.get(j).getTrip();
				
				sts.add(stopTimes.get(j));
				last = cur;
				j++;
			}
			groupedTrips.add(new GroupedTrip(trip, sts));
			i = j;
		}

		return new NetworkSession(agencies, routes, stops, trips, stopTimes, groupedTrips);
	}

}
