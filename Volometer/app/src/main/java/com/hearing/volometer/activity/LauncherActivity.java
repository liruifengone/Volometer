package com.hearing.volometer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hearing.volometer.MainActivity;
import com.hearing.volometer.R;

public class LauncherActivity extends AppCompatActivity {

    private static final int LOAD_DISPLAY_TIME = 1500;
    private float density;
    private double screenInches;
    private RelativeLayout rl_launcher;
    private ImageView iv_launch_logo;
    private TextView tv_launch_title;
    private ImageView iv_launch_subtitle;
    private TextView tv_company;
    private TextView tv_website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startScan();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN //全屏，状态栏会盖在布局上
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //透明状态栏
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //透明导航栏
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_launcher);
        initView();
        getScreenSize();
        autoAdjust();

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        rl_launcher.startAnimation(alphaAnimation);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //Go to main activity, and finish load activity
                Intent mainIntent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, LOAD_DISPLAY_TIME);
    }

    private void getScreenSize(){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density = dm.density;
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double diagonal = Math.sqrt(Math.pow(width,2)+Math.pow(height,2));
        screenInches = diagonal/(double)dm.densityDpi;
        Log.d("LLL","屏幕的物理尺寸是："+screenInches);
        Log.d("LLL", "屏幕的分辨率是:"+String.valueOf(width)+"*"+String.valueOf(height));
        Log.d("LLL","屏幕的DPI是："+dm.densityDpi);

    }

    private void autoAdjust() {
        // 根据屏幕尺寸和密度调整text与image大小
        double tmp = (screenInches/4.082)/density;
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv_launch_title.measure(w, w);
        tv_company.measure(w, w);
        tv_website.measure(w, w);
        iv_launch_logo.measure(w, w);
        tv_launch_title.setTextSize((float) (0.25 * tmp * tv_launch_title.getMeasuredWidth()));
        tv_company.setTextSize((float) (0.06 * tmp * tv_company.getMeasuredWidth()));
        tv_website.setTextSize((float) (0.08 * tmp * tv_website.getMeasuredWidth()));

        tmp = tmp * iv_launch_logo.getMeasuredWidth();
        if (tmp <= 80) {
            iv_launch_logo.setImageResource(R.mipmap.launcher_img_logo_verysmall);
            iv_launch_subtitle.setImageResource(R.mipmap.launcher_img_subtitle_verysmall);
        }
        else if (tmp <= 120) {
            iv_launch_logo.setImageResource(R.mipmap.launcher_img_logo_small);
            iv_launch_subtitle.setImageResource(R.mipmap.launcher_img_subtitle_small);
        }
        else if (tmp <= 180) {
            iv_launch_logo.setImageResource(R.mipmap.launcher_img_logo_medium);
            iv_launch_subtitle.setImageResource(R.mipmap.launcher_img_subtitle_medium);
        }
        else if (tmp <= 250) {
            iv_launch_logo.setImageResource(R.mipmap.launcher_img_logo_large);
            iv_launch_subtitle.setImageResource(R.mipmap.launcher_img_subtitle_large);
        }
        else {
            iv_launch_logo.setImageResource(R.mipmap.launcher_img_logo_verylarge);
            iv_launch_subtitle.setImageResource(R.mipmap.launcher_img_subtitle_verylarge);
        }
    }

    //这里搜集设备信息
    private void startScan() {

    }

    private void initView() {
        rl_launcher = (RelativeLayout) findViewById(R.id.rl_launcher);
        iv_launch_logo = (ImageView) findViewById(R.id.iv_launch_logo);
        tv_launch_title = (TextView) findViewById(R.id.tv_launch_title);
        iv_launch_subtitle = (ImageView) findViewById(R.id.iv_launch_subtitle);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_website = (TextView) findViewById(R.id.tv_website);
    }
}
