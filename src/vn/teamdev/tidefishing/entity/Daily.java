package vn.teamdev.tidefishing.entity;

import com.google.gson.annotations.SerializedName;

public class Daily {
	
	@SerializedName("day")
	public String sunrise;
	
	@SerializedName("description")
	public String sunset;
	
	@SerializedName("speed")
	public String moonrise;
	
	@SerializedName("clouds")
	public String moonphase;
	
	@SerializedName("morn")
	public String morning;
	
	@SerializedName("eve")
	public String evening;
}
