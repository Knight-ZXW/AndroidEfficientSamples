package zous.customviewsamples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

public class CustomViewActivity extends Activity {

  public static final String EXTRA_LAYOUT = "EXTRA_LAYOUT";

  public static void start(Context context, @LayoutRes int layoutId) {
    Intent intent = new Intent(context, CustomViewActivity.class);
    intent.putExtra(EXTRA_LAYOUT, layoutId);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(getIntent().getIntExtra(EXTRA_LAYOUT, 0));
    super.onCreate(savedInstanceState);
  }

  @Override protected void onStart() {
    super.onStart();
  }
}
