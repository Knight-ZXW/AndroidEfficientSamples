package zous.customviewsamples.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MeasureUtil {
  public static int[] getScreenSize(Context context) {
    WindowManager wm = (WindowManager) context
        .getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(outMetrics);
    return new int[] {outMetrics.widthPixels, outMetrics.heightPixels};
  }
}
