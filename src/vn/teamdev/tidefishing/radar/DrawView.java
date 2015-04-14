package vn.teamdev.tidefishing.radar;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawView extends View {

    private Paint circlePaint;
    private int scrWidth = 0, scrHeight = 0;
    private int padding = 100;
    private int nPart = 5;
    private int nArc = 12;
    private int nArcSplit = 24;
    private float[] listRadiusCicle = new float[nPart];
    private List<List<Point>> listIntersectPoint = new ArrayList<List<Point>>();

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public DrawView(Context context) {
        super(context);
        initParams();
    }

    private void initParams() {
        Log.i("DRAW", "initParams");
        this.setWillNotDraw(false);
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStrokeWidth(2);
    }

    private void drawPoly(Canvas canvas, int color, Point[] points) {
        // line at minimum...
        if (points.length < 2) {
            return;
        }

        // paint
        Paint polyPaint = new Paint();
        polyPaint.setColor(color);
        polyPaint.setStyle(Style.FILL);
        polyPaint.setStrokeWidth(5);

        // path
        Path polyPath = new Path();
        polyPath.moveTo(points[0].x, points[0].y);
        int i, len;
        len = points.length;
        for (i = 0; i < len; i++) {
            polyPath.lineTo(points[i].x, points[i].y);
        }
        polyPath.lineTo(points[0].x, points[0].y);

        // draw
        canvas.drawPath(polyPath, polyPaint);
    }

    private void calculatePoint() {
        Log.i("DRAW", "calculatePoint");
        calculateRadiusCirlce(nPart, padding);
        for (int i = 0; i < nPart; i++) {
            List<Point> spittedPoint = calculateIntersectPoint(nArcSplit,
                    scrWidth / 2, scrHeight / 2, listRadiusCicle[i]);
            listIntersectPoint.add(spittedPoint);
        }
    }

    private void calculateRadiusCirlce(int num, int padding) {
        int width = scrWidth - padding;
        float partSize = (float) width / (float) num;
        for (int i = 0; i < num; i++) {
            listRadiusCicle[i] = ((i + 1) * partSize) / 2;
        }
    }

    private List<Point> calculateIntersectPoint(int count, int x0, int y0, float r) {
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w < h) {
            this.scrWidth = w;
            this.scrHeight = h;
        } else {
            this.scrWidth = h;
            this.scrHeight = w;
        }
        Log.i("DRAW", scrWidth + "--" + scrHeight);
        calculatePoint();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("DRAW", "onDraw");
        for (List<Point> list : listIntersectPoint) {
            for (int i = 0; i < list.size(); i++) {
                Point p = list.get(i);
                canvas.drawPoint(p.x, p.y, circlePaint);
                if (i < list.size() - 1) {
                    Point pNext = list.get(i + 1);
                    canvas.drawLine(p.x, p.y, pNext.x, pNext.y, circlePaint);
                } else {
                    Point pFirst = list.get(0);
                    Point pLast = list.get(list.size() - 1);
                    canvas.drawLine(pFirst.x, pFirst.y, pLast.x, pLast.y,
                            circlePaint);
                }
            }
        }
        List<Point> lastList = listIntersectPoint
                .get(listIntersectPoint.size() - 1);
        for (Point p : lastList) {
            canvas.drawLine(scrWidth / 2, scrHeight / 2, p.x, p.y, circlePaint);
        }
        super.onDraw(canvas);
    }
}
