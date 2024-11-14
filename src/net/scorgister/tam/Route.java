package net.scorgister.tam;

import java.awt.Color;

public class Route  {
	
	private Agency agency;
	
	private String id, shortName, longName, url;
	private int type;
	private Color color, textColor;
	
	public Route(String id, Agency agency, String shortName, String longName, String url, int type, Color color, Color textColor) {
		this.id = id;
		this.shortName = shortName;
		this.longName = longName;
		this.url = url;
		this.type = type;
		this.color = color;
		this.textColor = textColor;
	}

	public Agency getAgency() {
		return agency;
	}

	public String getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}

	public String getUrl() {
		return url;
	}

	public int getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public Color getTextColor() {
		return textColor;
	}
}
