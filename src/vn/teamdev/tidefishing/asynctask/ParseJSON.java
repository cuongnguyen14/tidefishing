package vn.teamdev.tidefishing.asynctask;


import vn.teamdev.tidefishing.entity.WeatherDaily;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ParseJSON {

	private WeatherDaily weatherDaily;
	private Gson gson = new Gson();
	
	public ParseJSON() {
		super();
		gson = new GsonBuilder()
        .addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                return expose != null && !expose.serialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        })
        .addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                return expose != null && !expose.deserialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        })
        .create();
	}

	public WeatherDaily parseToClass(String json) {
		weatherDaily = gson.fromJson(json, WeatherDaily.class);
		return weatherDaily;
	}
}
