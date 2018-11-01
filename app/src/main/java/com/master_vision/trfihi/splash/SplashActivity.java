package com.master_vision.trfihi.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.master_vision.trfihi.intro.IntroActivity;
import com.master_vision.trfihi.R;

public class SplashActivity extends AppCompatActivity{

    private ImageView icon0, icon1, icon2, icon3, icon4;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        declareVar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAnimation();
    }

    private void setAnimation() {
        final Animation rightSwipe = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.right_side);
        final Animation leftSwipe = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.left_side);
        final Animation topSwipe = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.top_side);
        final Animation bottomSwipe = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_side);
        final Animation shake = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.shake); // viberate
        final Animation fadein = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein_anim);
        final Animation bounce = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bounce);

        icon0.startAnimation(bottomSwipe);
        icon1.startAnimation(bottomSwipe);
        icon2.startAnimation(topSwipe);
        icon3.startAnimation(rightSwipe);
        icon4.startAnimation(leftSwipe);

        bottomSwipe.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                icon0.startAnimation(shake);
                icon1.startAnimation(shake);
                icon2.startAnimation(shake);
                icon3.startAnimation(shake);
                icon4.startAnimation(shake);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                appName.setVisibility(View.VISIBLE);
                appName.startAnimation(bounce);
                StartIntroActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    private void declareVar() {
        icon0 = (ImageView) findViewById(R.id.icon0);
        icon1 = (ImageView) findViewById(R.id.icon1);

        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);

        icon4 = (ImageView) findViewById(R.id.icon4);
        appName = (TextView) findViewById(R.id.appName);

        appName.setVisibility(View.GONE);
    }

    private void StartIntroActivity() {
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                finish();
            }
        }.start();
    }
}
