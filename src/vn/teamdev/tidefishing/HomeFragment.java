package vn.teamdev.tidefishing;

import com.google.gson.Gson;

import vn.teamdev.tidefishing.SharedPreferences.LocalSharedPreferences;
import vn.teamdev.tidefishing.asynctask.AsyncTaskGetData;
import vn.teamdev.tidefishing.asynctask.ParseJSON;
import vn.teamdev.tidefishing.asynctask.ProcessResult;
import vn.teamdev.tidefishing.config.Configuration;
import vn.teamdev.tidefishing.entity.City;
import android.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment implements ProcessResult {

	private static final String TAG = "HomeFragment";

	private TextView textView;
	private AsyncTaskGetData asyncTaskGetData = new AsyncTaskGetData(this);
	private City mCity;
	private LocalSharedPreferences prefs;
	private String weatherInfo;
	private ParseJSON parseJSON;

	// Close Beta, hard code city ID
	public String key = "hcmcityID";
	public String id = Configuration.HCM_ID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "On Create");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "On Create View");
		View view = inflater.inflate(R.layout.home_fragment, container, false);

		textView = (TextView) view.findViewById(R.id.textView1);
		prefs = new LocalSharedPreferences(getActivity().getApplicationContext());

//		asyncTaskGetData
//				.execute("http://api.wunderground.com/api/69636a4a29825f1f/hourly/q/CA/HaNoi.json");

		parseJSON = new ParseJSON();
		mCity = new City(Configuration.HCM_ID);
		loadCityWeatherInformation(Configuration.HCM_ID);
		
		// get city if it saved
		// if no, run asyncTask
		// save it to SharePreferences with date-time (yymmddhh)
		// if yes, get date-time, try to load from SharePreferences
		// run asyncTask and save if cannot load
		return view;
	}

	public void loadCityWeatherInformation(String id) {
		String mCityId = prefs.loadFrom(key);
		if (mCityId == null) {
			mCityId = Configuration.HCM_ID;
		}
		String dateTimeKey = getCurrentDateTime();
		weatherInfo = prefs.loadFrom(dateTimeKey);

		if (weatherInfo == null) {
			new AsyncTaskGetData(this).execute(String.format(
					Configuration.DAILY_FORMAT, Configuration.SERVER_URL,
					mCityId));
		} else {
			mCity.setWeatherDaily(parseJSON.parseToClass(weatherInfo));
			textView.setText(weatherInfo);
			Gson gson = new Gson();
			Log.i(TAG, gson.toJson(mCity.getWeatherDaily()));
		}
		
		// get Infor

	}

	public String getCurrentDateTime() {
		Time currentTime = new Time();
		currentTime.setToNow();
		String dateTimeKey = currentTime.format("%Y%m%d%H");
		return dateTimeKey;
	}

	
	@Override
	public void processResult(String result) {
		weatherInfo = result;
		String dateTimeKey = getCurrentDateTime();
		prefs.saveTo(dateTimeKey, weatherInfo);
		mCity.setWeatherDaily(parseJSON.parseToClass(weatherInfo));
		textView.setText(weatherInfo);
		Gson gson = new Gson();
		Log.i(TAG, gson.toJson(mCity.getWeatherDaily()));
	}
}
