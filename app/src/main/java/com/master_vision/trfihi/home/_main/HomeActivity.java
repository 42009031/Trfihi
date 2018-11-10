package com.master_vision.trfihi.home._main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.create_event.view.CreateEventActivity;
import com.master_vision.trfihi.edit_profile.EditProfileActivity;
import com.master_vision.trfihi.login.LoginActivity;

import java.util.Locale;


public class HomeActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    private ViewPager mViewPager;
    private FirebaseAuth mAuth;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabBar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();


        int[] tab_icons = {R.drawable.ic_game, R.drawable.ic_chat, R.drawable.ic_alarm, R.drawable.ic_medal, R.drawable.ic_event};
        for (int iconId : tab_icons) {
            View view1 = getLayoutInflater().inflate(R.layout.adapter_tab_item, null);
            view1.findViewById(R.id.icon).setBackgroundResource(iconId);
            tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        }


        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CreateEventActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        String defaultLang = String.valueOf(Locale.getDefault());
        if (defaultLang.equalsIgnoreCase("ar")) {
            menu.findItem(R.id.change_language).setTitle("English");
        } else {
            menu.findItem(R.id.change_language).setTitle("العربية");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            SharedPreferencesManager.clearSharedPreference();
            updateUI();
            return true;
        } else if (id == R.id.edit_profile) {
            startActivity(new Intent(HomeActivity.this, EditProfileActivity.class));
            return true;
        } else if (id == R.id.change_language) {
            if (item.getTitle().equals("English")) {
                setLocal("en");
                item.setTitle("العربية");
            } else {
                setLocal("ar");
                item.setTitle("English");
            }
            restartActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private void updateUI() {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }

    private void setLocal(String mComingLocal) {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        Locale local = Locale.getDefault();
        if (mComingLocal.contains(Helper.AR)) {
            local = new Locale(Helper.AR, "eg");
        } else {
            local = new Locale(Helper.EN, "us");
        }
        Locale.setDefault(local);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(local);
            conf.setLayoutDirection(local);
        } else {
            conf.locale = local;
        }
        res.updateConfiguration(conf, dm);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

//    enum Language{
//        ARABIC,
//        ENGLISH
//    }
}
