package zous.customviewsamples.easy.waterripple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

public class SinRippleView extends View {

  private static final int STRETCH_FACTORY_A = 20;
  private static final int OFFSET_Y = 200;
  private static final int WAVE_PAINT_COLOR = 0x880000aa;
  private int mTotalWidth;
  private int mTotalHeight;
  //原始波纹的y值
  private float[] mYPositions;
  private double mCycleFactorW;
  private Paint mWavePaint;
  private DrawFilter mDrawFilter;

  private int mXOneOffset;
  private int mXOffsetSpeedOne =20;
  public SinRippleView(Context context) {
    this(context, null);
  }

  public SinRippleView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SinRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mWavePaint = new Paint();
    mWavePaint.setAntiAlias(true);
    mWavePaint.setStyle(Paint.Style.FILL);
    mWavePaint.setColor(WAVE_PAINT_COLOR);
    // 给Canvas 设置DrawFilter 抗锯齿
    mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.setDrawFilter(mDrawFilter);

    for (int i = 0; i < mTotalWidth; i++) {
      int offset = i + mXOneOffset;
      if (offset >= mTotalWidth){
        offset -= mTotalWidth;
      }
      canvas.drawLine(i, mTotalHeight - mYPositions[offset], i, mTotalHeight, mWavePaint);
    }
    mXOneOffset += mXOffsetSpeedOne;
    if (mXOneOffset >= mTotalWidth) {
      mXOneOffset = 0;
    }
    postInvalidate();
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mTotalWidth = w;
    mTotalHeight = h;
    mYPositions = new float[w];
    mCycleFactorW = 2 * Math.PI / mTotalWidth;
    for (int i = 0; i < mTotalWidth; i++) {
      mYPositions[i] = (float) (STRETCH_FACTORY_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
    }
  }
}
