package vn.teamdev.tidefishing;

import vn.teamdev.tidefishing.asynctask.AsyncTaskGetData;
import vn.teamdev.tidefishing.asynctask.ProcessResult;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment implements ProcessResult{

	private static final String TAG = "HomeFragment";
	
	private TextView textView;
	private AsyncTaskGetData asyncTaskGetData = new AsyncTaskGetData(this);

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
		
		textView = (TextView)view.findViewById(R.id.textView1);
		
		asyncTaskGetData.execute("http://api.wunderground.com/api/69636a4a29825f1f/hourly/q/CA/HaNoi.json");
		
		return view;
	}

	@Override
	public void processResult(String result) {
		textView.setText(result);
	}
	
	
}
