package zous.customviewsamples.easy.shaderpratice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import zous.customviewsamples.R;
import zous.customviewsamples.utils.MeasureUtil;

public class ShaderView extends View {
  private static final int RECT_SIZE = 400;// 矩形尺寸的一半

  private Paint mPaint;// 画笔

  private int left, top, right, bottom;// 矩形坐上右下坐标
  private int screenX, screenY;

  public ShaderView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // 获取屏幕尺寸数据
    int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

    // 获取屏幕中点坐标
    screenX = screenSize[0] / 2;
    screenY = screenSize[1] / 2;

    // 计算矩形左上右下坐标值
    left = screenX - RECT_SIZE;
    top = screenY - RECT_SIZE;
    right = screenX + RECT_SIZE;
    bottom = screenY + RECT_SIZE;

    // 实例化画笔
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // 获取位图
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

    // 实例化一个Shader
    BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    // 实例一个矩阵对象
    Matrix matrix = new Matrix();

    // 设置矩阵变换
    matrix.setTranslate(left-400, top);
    // 设置Shader的变换矩阵
    bitmapShader.setLocalMatrix(matrix);

    // 设置着色器
    mPaint.setShader(bitmapShader);
    // mPaint.setShader(new LinearGradient(left, top, right - RECT_SIZE, bottom - RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR));
    // mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, null, Shader.TileMode.MIRROR));
    // mPaint.setShader(new SweepGradient(screenX, screenY, Color.RED, Color.YELLOW));
    // mPaint.setShader(new SweepGradient(screenX, screenY, new int[] { Color.GREEN, Color.WHITE, Color.GREEN }, null));
  }

  @Override
  protected void onDraw(Canvas canvas) {
    // 绘制矩形
    canvas.drawRect(left, top, right, bottom, mPaint);
    // canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
  }
}