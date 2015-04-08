package vn.teamdev.tidefishing.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskGetData extends AsyncTask<String, String, String> {

	private static final String TAG = "AsyncTaskGetData";
	public ProcessResult processResult;

	public AsyncTaskGetData(ProcessResult processResult) {
		this.processResult = processResult;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String result = "";
		try {
			Log.i(TAG, params[0]);
			URL myURL = new URL(params[0]);
			BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result += inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, result);
		return result;
	}

	@Override
	protected void onPostExecute(String result) {

		Log.i("onPostExecute", result);
		
		processResult.processResult(result);

		super.onPostExecute(result);
	}

}
