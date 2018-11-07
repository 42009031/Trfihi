package com.master_vision.trfihi.home._main;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.edit_profile.EditProfileActivity;


public class HomeActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    private ViewPager mViewPager;
    private FirebaseAuth mAuth;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            updateUI();
            return true;
        } else if (id == R.id.edit_profile) {
            startActivity(new Intent(HomeActivity.this, EditProfileActivity.class));
            return true;
        } else if (id == R.id.change_language) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(this, "display Login ", Toast.LENGTH_SHORT).show();
    }
}
