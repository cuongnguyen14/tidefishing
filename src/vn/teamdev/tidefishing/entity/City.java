package vn.teamdev.tidefishing.entity;

public class City {

	private String cityName;
	private String cityId;
	private WeatherDaily weatherDaily;
	private WeatherHourly weatherHourly;
	
	public City(String cityId) {
		super();
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public WeatherDaily getWeatherDaily() {
		return weatherDaily;
	}

	public void setWeatherDaily(WeatherDaily weatherDaily) {
		this.weatherDaily = weatherDaily;
	}

	public WeatherHourly getWeatherHourly() {
		return weatherHourly;
	}

	public void setWeatherHourly(WeatherHourly weatherHourly) {
		this.weatherHourly = weatherHourly;
	}
	
}
