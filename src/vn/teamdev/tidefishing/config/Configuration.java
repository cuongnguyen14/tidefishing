package vn.teamdev.tidefishing.config;

public class Configuration {
	
	public static final String API_KEY = "69636a4a29825f1f";
	
	public static final String HCM_ID = "1566083";
	
	public static final String SERVER_URL = "http://api.openweathermap.org/data/2.5/";

	// eg: http://api.openweathermap.org/data/2.5/forecast/daily?id=524901
	// API to get weather of next 7 days 
	public static final String DAILY_FORMAT = "%sforecast/daily?id=%s";

	// eg: http://api.openweathermap.org/data/2.5/forecast?id=1566083
	// API to get weather of next 5 days, group by 3 hour
	public static final String HOURLY_FORMAT = "%sforecast?id=%s";
	
	// eg: http://api.openweathermap.org/data/2.5/weather?id=2172797
	// API to get current weather
	public static final String CURRENT_FORMAT = "%sweather?id=%s";
			
}
