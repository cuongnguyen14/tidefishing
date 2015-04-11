package vn.teamdev.tidefishing.entity;

import java.util.ArrayList;
import java.util.List;

public class WeatherDaily {

	public int cod;
	
	public String message;
		
	public int totalItems;
	
	public List<Daily> dailyWeather = new ArrayList<Daily>();
}
