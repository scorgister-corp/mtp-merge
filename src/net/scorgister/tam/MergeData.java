package net.scorgister.tam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.argus.cjson.value.CJSONArray;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONString;
import net.argus.cjson.value.CJSONValue;
import net.argus.file.CJSONFile;

public class MergeData {
	
	public static void merge(NetworkSession session) {
		Map<String, List<GroupedTrip>> routeIdMap = new HashMap<String, List<GroupedTrip>>();
		for(GroupedTrip trip : session.getGroupedTrips()) {
			if(routeIdMap.get(trip.getTrip().getRoute().getId()) != null)
				routeIdMap.get(trip.getTrip().getRoute().getId()).add(trip);
			else {
				List<GroupedTrip> l = new ArrayList<GroupedTrip>();
				l.add(trip);
				routeIdMap.put(trip.getTrip().getRoute().getId(), l);
			}
		}
		Map<String, List<GroupedTrip>> routeIdHeadsignMap = new HashMap<String, List<GroupedTrip>>();
		for(Entry<String, List<GroupedTrip>> entry : routeIdMap.entrySet()) {
			for(GroupedTrip trip : entry.getValue() ) {
				if(routeIdHeadsignMap.get(entry.getKey() + ";" + trip.getTrip().getHeadsign()) != null)
					routeIdHeadsignMap.get(entry.getKey() + ";" + trip.getTrip().getHeadsign()).add(trip);
				else {
					List<GroupedTrip> l = new ArrayList<GroupedTrip>();
					l.add(trip);
					routeIdHeadsignMap.put(entry.getKey() + ";" + trip.getTrip().getHeadsign(), l);
				}
			}
		}
		
		Map<String, List<GroupedTrip>> routeIdHeadsignStopNameMap = new HashMap<String, List<GroupedTrip>>();
		for(Entry<String, List<GroupedTrip>> entry : routeIdHeadsignMap.entrySet()) {
			for(GroupedTrip trip : entry.getValue()) {
				for(int i = 0; i < trip.getStopTimes().size(); i++) {
					if(routeIdHeadsignStopNameMap.get(entry.getKey() + ";" + trip.get(i).getStop().getName()) != null)
						routeIdHeadsignStopNameMap.get(entry.getKey() + ";" + trip.get(i).getStop().getName()).add(trip);
					else {
						List<GroupedTrip> l = new ArrayList<GroupedTrip>();
						l.add(trip);
						routeIdHeadsignStopNameMap.put(entry.getKey() + ";" + trip.get(i).getStop().getName(), l);
					}
				}
				
			}
		}
		
		List<CJSONValue> objs = new ArrayList<CJSONValue>();
		for(Entry<String, List<GroupedTrip>> entity : routeIdHeadsignStopNameMap.entrySet()) {
			if(entity.getValue().size() == 0)
				continue;
			
			CJSONObject obj = new CJSONObject();
			String routeId = entity.getKey().split(";")[0];
			String headsign = entity.getKey().split(";")[1];
			String stopName = entity.getKey().split(";")[2];
			String stopId = null;
			
			for(int i = 0; i < entity.getValue().get(0).getStopTimes().size(); i++) {
				if(entity.getValue().get(0).getStopTimes().get(i).getStop().getName().equals(stopName)) {
					stopId = entity.getValue().get(0).getStopTimes().get(i).getStop().getId();
					break;
				}
			}

			obj.addItem("route_id", new CJSONString(routeId));
			
			List<CJSONValue> vals = new ArrayList<CJSONValue>();
			for(GroupedTrip trip : entity.getValue())
				vals.add(new CJSONString(trip.getTrip().getId()));
			
			obj.addItem("trip_id", new CJSONArray(vals));

			
			obj.addItem("trip_headsign", new CJSONString(headsign));
			obj.addItem("stop_name", new CJSONString(stopName));
			obj.addItem("stop_id", new CJSONString(stopId));
			
			objs.add(obj);
		}
		
		CJSONArray arr = new CJSONArray(objs);
		try {
			new	CJSONFile("./merged_data.json").write(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createStop(NetworkSession session) {	
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, List<String>> mapLines = new HashMap<String, List<String>>();
		
		for(GroupedTrip trip : session.getGroupedTrips()) {
			for(StopTime times : trip.getStopTimes()) {
				if(map.get(times.getStop().getName()) != null) {
					if(!map.get(times.getStop().getName()).contains(trip.getTrip().getHeadsign()))
						map.get(times.getStop().getName()).add(trip.getTrip().getHeadsign());
				}else {
					List<String> l = new ArrayList<String>();
					l.add(trip.getTrip().getHeadsign());
					map.put(times.getStop().getName(), l);
				}
				
				String routeId = trip.getTrip().getRoute().getId();
				routeId = routeId.substring(routeId.indexOf('-') + 1);
				
				if(mapLines.get(times.getStop().getName()) != null) {
					if(!mapLines.get(times.getStop().getName()).contains(routeId))
						mapLines.get(times.getStop().getName()).add(routeId);
				}else {
					List<String> l = new ArrayList<String>();
					l.add(routeId);
					mapLines.put(times.getStop().getName(), l);
				}	
			}
				
		}
		
		CJSONObject obj = new CJSONObject();
		
		for(Entry<String, List<String>> entry : map.entrySet()) {
			CJSONObject stopObj = new CJSONObject();
			List<CJSONValue> vals = new ArrayList<CJSONValue>();
			for(String str : entry.getValue())
				vals.add(new CJSONString(str));
			
			stopObj.addItem("directions", new CJSONArray(vals));
			
			obj.addItem(entry.getKey(), stopObj);
		}
		
		for(Entry<String, List<String>> entry : mapLines.entrySet()) {
			List<CJSONValue> vals = new ArrayList<CJSONValue>();
			for(String str : entry.getValue())
				vals.add(new CJSONString(str));
			
			if(obj.getValue(entry.getKey()) != null) {
				obj.getObject(entry.getKey()).addItem("lines", new CJSONArray(vals));
			}else {
				CJSONObject linesObj = new CJSONObject();
				linesObj.addItem("lines", new CJSONArray(vals));
				obj.addItem(entry.getKey(), linesObj);
			}	
		}
		
		
		try {
			new	CJSONFile("./directions.json").write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
