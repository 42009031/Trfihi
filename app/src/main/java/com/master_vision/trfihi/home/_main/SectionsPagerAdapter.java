package com.master_vision.trfihi.home._main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.master_vision.trfihi.home.events.view.EventFragment;
import com.master_vision.trfihi.home.games.view.GameFragment;
import com.master_vision.trfihi.home.join.view.JoinFragment;
import com.master_vision.trfihi.home.messages.view.MessageFragment;
import com.master_vision.trfihi.home.notification.view.NotificationFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;

    public SectionsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GameFragment.newInstance();
            case 1:
                return MessageFragment.newInstance();
            case 2:
                return NotificationFragment.newInstance();
            case 3:
                return JoinFragment.newInstance();
            case 4:
                return EventFragment.newInstance();
            default:
                return GameFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
