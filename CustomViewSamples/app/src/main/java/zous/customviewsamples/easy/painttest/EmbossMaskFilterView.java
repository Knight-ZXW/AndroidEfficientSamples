package zous.customviewsamples.easy.painttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 实现类似巧克力的效果
 */
public class EmbossMaskFilterView extends View {

  private static final int H_COUNT = 2, V_COUNT = 4;
  int[] viewSize;
  private Paint mPaint;
  private PointF[] mPointFs;
  private int width, height;
  private float coorY;

  public EmbossMaskFilterView(Context context) {
    this(context, null);
  }

  public EmbossMaskFilterView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setLayerType(LAYER_TYPE_SOFTWARE, null);
    initPaint();
  }

  private void initPaint() {
    //init paint
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(0xFF603811);

    //set mask filter
    mPaint.setMaskFilter(new EmbossMaskFilter(new float[] {1, 1, 1F}, 0.1F, 10F, 20F));
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    viewSize = new int[] {w, h};
    cal();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.GRAY);
    for (int i = 0; i < 1; i++) {
      canvas.drawRect(mPointFs[i].x, mPointFs[i].y, mPointFs[i].x + width, mPointFs[i].y + height, mPaint);
    }
  }

  private void cal() {
    width = viewSize[0] / H_COUNT;
    height = viewSize[0] / V_COUNT;

    int count = H_COUNT * V_COUNT;
    mPointFs = new PointF[count];
    for (int i = 0; i < count; i++) {
      if (i % 2 == 0) {
        coorY = i * height / 2F;
        mPointFs[i] = new PointF(0, coorY);
      } else {
        mPointFs[i] = new PointF(width, coorY);
      }
    }
  }
}
