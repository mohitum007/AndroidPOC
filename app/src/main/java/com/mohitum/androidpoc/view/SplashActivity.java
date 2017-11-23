package com.mohitum.androidpoc.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mohitum.androidpoc.R;

/**
 * Activity class to present application Splash screen
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity implements Runnable {

    /**
     * Handler to handle the runnable thread on completion
     */
    private Handler splashTimeHandler;

    /**
     * Splash display time
     */
    private static final long SPLASH_RUN_TIME = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashTimeHandler = new Handler();
        splashTimeHandler.postDelayed(this, SPLASH_RUN_TIME);
    }

    @Override
    public void run() {
        Intent intent = new Intent(SplashActivity.this, FeedsActivity.class);
        SplashActivity.this.startActivity(intent);
        // finish Splash activity.
        SplashActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        splashTimeHandler.removeCallbacks(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashTimeHandler.removeCallbacks(this);
    }
}
