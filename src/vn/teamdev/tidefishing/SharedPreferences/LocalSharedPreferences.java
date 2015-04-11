package vn.teamdev.tidefishing.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LocalSharedPreferences {
	
	private static final String TAG = "LocalSharedPreferences";
	private SharedPreferences sharedPreferences;
	private Context context;
	
	public LocalSharedPreferences(Context context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences("vn.teamdev.tidefishing", Context.MODE_PRIVATE);
	}
	
	public void saveTo(String id, String content) {
		Log.i(TAG,"saveTo: " + id + ": " + content);
		sharedPreferences.edit().putString(id, content).apply();
	}
	
	public String loadFrom(String id) {
		Log.i(TAG,"loadFrom: " + id);
		String content = null;
		content = sharedPreferences.getString(id, null);
		Log.i(TAG,"loadFrom: " + content);
		return content;
	}
	
}
