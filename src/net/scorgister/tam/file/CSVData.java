package net.scorgister.tam.file;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CSVData {
	
	private Map<String, List<String>> data;
	
	public CSVData(Map<String, List<String>> data) {
		this.data = data;
	}
	
	public List<String> get(String descr) {
		
		return data.get(descr);
	}
	
	public List<Integer> getInt(String descr) {
		List<String> strs = data.get(descr);

		List<Integer> ints = new ArrayList<Integer>();
		for(String str : strs)
			ints.add(Integer.valueOf(str));
		
		return ints;
	}
	
	public List<Boolean> getBool(String descr) {
		List<String> strs = data.get(descr);

		List<Boolean> bools = new ArrayList<Boolean>();
		
		for(String str : strs)
			bools.add(Boolean.valueOf(str));
		
		return bools;
	}
	
	public List<Color> getColor(String descr) {
		List<String> strs = data.get(descr);
		
		List<Color> colors = new ArrayList<Color>();
		
		for(String str : strs)
			try {
				if(str.startsWith("#"))
					colors.add(Color.decode(str));
				else
					colors.add(Color.decode("#" + str));
			}catch(NumberFormatException e) {
				colors.add(null);
			}
		
		return colors;
	}
	
	public List<Date> getDate(String descr, String format) {
		List<String> strs = data.get(descr);
		
		List<Date> dates = new ArrayList<Date>();
		SimpleDateFormat dt = new SimpleDateFormat(format); 
		
		for(String str : strs)
			try {dates.add(dt.parse(str));}
			catch(ParseException e) {dates.add(new Date());}
		
		return dates;
	}
	
	public List<String> get(String descr, String ... opts) {
		if(opts.length % 2 != 0)
			throw new IllegalArgumentException();
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for(Entry<String, List<String>> entry : data.entrySet())
			for(int i = 0; i < opts.length / 2; i+=2)
				if(entry.getKey().equals(opts[i]) && entry.getValue().contains(opts[i+1])) 
					for(int j = 0; j < entry.getValue().size(); j++)
						if(!ids.contains(j) && entry.getValue().get(j).equals(opts[i+1]))
							ids.add(j);
				
		List<String> strs = new ArrayList<String>();
		for(int i : ids) 
			strs.add(data.get(descr).get(i));
		
		return strs;
	}
	
	public int descrCount() {
		return data.size();
	}
	
	
	public Map<String, List<String>> getData() {
		return data;
	}

}
