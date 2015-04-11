package vn.teamdev.tidefishing.radar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DrawView extends ImageView {

	private static final String TAG = "DrawView";
	public Paint line_paint, fill_paint, text_paint;
	private int width, height;
	private Point x = new Point(0, 0);
	private Point center = new Point(0, 0);
//	private List<Circle> listCircles = new ArrayList<Circle>();
	private List<Point> listLines = new ArrayList<Point>();
	private int textSize = 14;

	public DrawView(Context context) {
		super(context);
		setFocusable(true);
		initParams();
	}

	public DrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
		initParams();
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		initParams();
	}

	protected void initParams() {
		fill_paint = new Paint();
		fill_paint.setColor(Color.CYAN);
		fill_paint.setAlpha(255);

		line_paint = new Paint();
		line_paint.setAntiAlias(true);
		line_paint.setStrokeWidth(2);
		line_paint.setStyle(Paint.Style.STROKE);
		line_paint.setColor(Color.BLUE);

		text_paint = new Paint();
		text_paint.setAntiAlias(true);
		text_paint.setStrokeWidth(1);
		text_paint.setColor(Color.RED);
		text_paint.setTextSize(textSize);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = getWidth();
		height = getHeight();
		center.x = width / 2;
		center.y = height / 2;
		Log.i(TAG, width + " - " + height);

		// Draw cicle
		int segment = width / 16;
		for (int i = 2; i <= 7; i++) {
			Log.i(TAG, (segment * i) + "SIZE");
			canvas.drawCircle(center.x, center.y, segment * i, line_paint);
//			Circle circle = new Circle(center.x, center.y, segment * i);
//			listCircles.add(circle);
		}

		// Draw line
		listLines = calculateListPoint(segment * 7, center.x, center.y, 24);
		List<Point> listCoorText = calculateListPoint((float) (segment * 7.5),
				center.x, center.y, 24);
		int i = 0;
		for (Point p : listLines) {
			Log.i(TAG, p.toString());
			canvas.drawText(String.valueOf(i), listCoorText.get(i).x - textSize
					/ 2, listCoorText.get(i).y + textSize / 2, text_paint);
			canvas.drawLine(center.x, center.y, p.x, p.y, line_paint);
			i++;
		}

		canvas.drawPoint(x.x, x.y, line_paint);
	}

	private ArrayList<Point> calculateListPoint(float r, float x0, float y0,
			int count) {
		ArrayList<Point> listPoint = new ArrayList<Point>();
		for (int i = 0; i < count; i++) {
			double angle = i * (2 * Math.PI / count);
			Point point = new Point();
			point.x = (int) (x0 + r * Math.sin(angle));
			point.y = (int) (y0 - r * Math.cos(angle));
			listPoint.add(point);
		}
		return listPoint;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int X = (int) event.getX();
		int Y = (int) event.getY();
		Log.i(TAG, String.format("Touch at coordinates: X=%d, Y=%d", X, Y));

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			break;

		case MotionEvent.ACTION_MOVE:
			x.x = X;
			x.y = Y;
			break;

		case MotionEvent.ACTION_UP:
			break;
		}

		invalidate();
		return true;

	}
}
