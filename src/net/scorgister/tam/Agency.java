package net.scorgister.tam;

public class Agency {
	
	private String id, name, url, timezone, lang, phone, fareUrl;
	
	public Agency(String id, String name, String url, String timezone, String lang, String phone, String fareUrl) {
		this.id = id;
		this.name = name;
		this.fareUrl = url;
		this.timezone = timezone;
		this.lang = lang;
		this.phone = phone;
		this.fareUrl = fareUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getLang() {
		return lang;
	}

	public String getPhone() {
		return phone;
	}

	public String getFareUrl() {
		return fareUrl;
	}

}
