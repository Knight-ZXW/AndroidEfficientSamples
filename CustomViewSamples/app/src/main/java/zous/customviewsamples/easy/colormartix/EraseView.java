package zous.customviewsamples.easy.colormartix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import zous.customviewsamples.R;

public class EraseView extends View {

  private Bitmap mSrcBitmap;
  private Paint mFingerPaint;
  private Bitmap mForegroundBitmap;
  private Canvas mForegroundCanvas;
  private float preX;
  private float preY;
  private Path mPath; //橡皮擦的绘制路径

  public EraseView(Context context) {
    this(context, null);
  }

  public EraseView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EraseView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    mFingerPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    mFingerPaint.setStyle(Paint.Style.STROKE);
    mFingerPaint.setStrokeCap(Paint.Cap.ROUND);
    mFingerPaint.setStrokeJoin(Paint.Join.ROUND);
    mFingerPaint.setStrokeWidth(50);
    mFingerPaint.setARGB(0, 255, 0, 0);
    mFingerPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    mPath = new Path();
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    //create foreground bitmap
    mForegroundBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    mForegroundCanvas = new Canvas(mForegroundBitmap);
    mForegroundCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
    mForegroundCanvas.drawColor(0xFF808080);

  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawBitmap(mSrcBitmap, canvas.getWidth() / 2, canvas.getHeight() / 2, null);
    canvas.drawBitmap(mForegroundBitmap, 0, 0, null);
    mForegroundCanvas.drawPath(mPath, mFingerPaint);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        preX = x;
        preY = y;
        mPath.reset();
        mPath.moveTo(x, y);
        break;
      case MotionEvent.ACTION_MOVE:
        float dx = Math.abs(x - preX);
        float dy = Math.abs(y - preY);
        if (dx >= 2 || dy >= 2) {
          mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
          preX = x;
          preY = y;
        }
        break;
      case MotionEvent.ACTION_UP:
        break;
      default:
        break;
    }
    invalidate();
    return true;
  }
}
