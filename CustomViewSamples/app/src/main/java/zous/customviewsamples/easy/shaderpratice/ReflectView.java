package zous.customviewsamples.easy.shaderpratice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import zous.customviewsamples.R;
import zous.customviewsamples.utils.MeasureUtil;

public class ReflectView extends View {
  private Bitmap mSrcBitmap, mRefBitmap;
  private Paint mPaint;
  private PorterDuffXfermode mXfermode;

  private int x, y;

  //一个原图 高度非
  public ReflectView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);

    //init a martix
    Matrix matrix = new Matrix();
    matrix.setScale(1F, -1F);

    mRefBitmap =
        Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix,
            true);

    int[] screenSize = MeasureUtil.getScreenSize(context);
    x = screenSize[0] / 2 - mSrcBitmap.getWidth() / 2;
    y = screenSize[1] / 2 - mSrcBitmap.getHeight() / 2;
    mPaint = new Paint();
    mPaint.setShader(new LinearGradient(x, y + mSrcBitmap.getHeight(), x,
        y + mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4, 0xAA000000, Color.TRANSPARENT,
        Shader.TileMode.CLAMP));
    mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
  }


  @Override protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.BLACK);
    canvas.drawBitmap(mSrcBitmap, x, y, null);
    int sc = canvas.saveLayer(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);

    canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight(), null);

    mPaint.setXfermode(mXfermode);
    canvas.drawRect(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, mPaint);

    mPaint.setXfermode(null);

    canvas.restoreToCount(sc);
  }
}
