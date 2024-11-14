package net.scorgister.tam.file;

import java.io.File;
import java.io.IOException;

import net.argus.file.CardinalFile;

public class CSVFile extends CardinalFile {

	public CSVFile(File file) {
		super(file);
	}
	
	public CSVFile(String path) {
		super(path);
	}
	
	public CSVData getData() throws IOException {
		return CSVParser.parse(read());
	}

}
