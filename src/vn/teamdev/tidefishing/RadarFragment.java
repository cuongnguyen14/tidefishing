package vn.teamdev.tidefishing;

import vn.teamdev.tidefishing.radar.DrawView;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RadarFragment extends Fragment{

private static final String TAG = "HomeFragment";
	
	private TextView textView;
	private DrawView drawView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "On Create");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "On Create View");
		View view = inflater.inflate(R.layout.radar_fragment, container, false);
		
		textView = (TextView)view.findViewById(R.id.textView1);
		drawView = (DrawView) view.findViewById(R.id.drawView);
		
		return view;
	}
	
}
