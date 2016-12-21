package zous.customviewsamples.easy.shaderpratice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import zous.customviewsamples.R;
import zous.customviewsamples.utils.MeasureUtil;

public class ShaderView extends View {
  private static final int RECT_SIZE = 400;
  private Paint mPaint;
  private final int screenX;
  private final int screenY;

  public ShaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    int[] screenSize = MeasureUtil.getScreenSize(context);

    screenX = screenSize[0] / 2;
    screenY = screenSize[1] / 2;


    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
    mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR));
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawRect(0, 0, screenX, screenY, mPaint);
  }
}
