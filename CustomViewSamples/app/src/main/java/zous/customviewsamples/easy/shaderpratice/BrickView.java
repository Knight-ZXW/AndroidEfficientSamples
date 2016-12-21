package zous.customviewsamples.easy.shaderpratice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import zous.customviewsamples.R;

public class BrickView extends View {
  private Paint mBrickPaint;
  private Paint mCirclePaint;

  private float mTouchX;
  private float mTouchY;
  private int mCircleRadius = 300;
  private boolean isTouch = false;

  public BrickView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    BitmapShader bitmapShader =
        new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.brick),
            Shader.TileMode.REPEAT,
            Shader.TileMode.REPEAT);
    mBrickPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    mBrickPaint.setShader(bitmapShader);
    mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    mCirclePaint.setStyle(Paint.Style.STROKE);
    mCirclePaint.setColor(0xFF000000);
    mCirclePaint.setStrokeWidth(5);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (isTouch) {
      canvas.drawCircle(mTouchX, mTouchY, mCircleRadius, mCirclePaint);
      canvas.drawCircle(mTouchX, mTouchY, mCircleRadius, mBrickPaint);
    }
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mTouchX = event.getX();
        mTouchY = event.getY();
        isTouch = true;
        break;
      case MotionEvent.ACTION_MOVE:
        mTouchX = event.getX();
        mTouchY = event.getY();
        break;
      case MotionEvent.ACTION_UP:
        isTouch = false;
        break;
    }
    invalidate();
    return true;
  }
}
