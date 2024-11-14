package net.scorgister.tam.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {
	
	public static final String SEPARATOR = ",";
	
	public static CSVData parse(String data) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		String[] lines = data.split("\n");
		
		if(lines.length == 0)
			throw new IllegalArgumentException();
		
		String[] descr = lines[0].split(SEPARATOR);
		for(String desc : descr) {
			map.put(desc, new ArrayList<String>());
		}
		for(int i = 1; i < lines.length; i++) {
			String line = lines[i];
			if(line.equals(new String(new byte[] {0, 0})))
				continue;
			
			int j = 0;
			while(line.contains(SEPARATOR)) {
				map.get(descr[j]).add(line.substring(0, line.indexOf(SEPARATOR)));
				line = line.substring(line.indexOf(SEPARATOR) + 1);

				j++;
			}
			map.get(descr[descr.length-1]).add(line);
		}

		return new CSVData(map);
	}

}
