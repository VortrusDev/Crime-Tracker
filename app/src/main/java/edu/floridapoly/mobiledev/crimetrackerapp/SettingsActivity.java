package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    public static int currentTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        mViewPager = (ViewPager) findViewById(R.id.settings_fragmentView);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),SettingsActivity.this);
        TabLayout tabs = findViewById(R.id.settingsTabBar);

        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setupWithViewPager(mViewPager);

        tabs.getTabAt(0).setText("Location");
        tabs.getTabAt(1).setText("Notifications");
        tabs.getTabAt(2).setText("Activities");

        mViewPager.setCurrentItem(tabs.getSelectedTabPosition());
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentTab==0){
            mViewPager.setCurrentItem(0);
        }
    }

    public class ViewPagerAdapter  extends FragmentStatePagerAdapter {

        private Context context;

        public ViewPagerAdapter(FragmentManager fm, SettingsActivity settingsActivity) {
            super(fm);
            context = settingsActivity;
            new Settings_Location_Fragment();
        }

        @Override        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Settings_Location_Fragment firstFragment = new Settings_Location_Fragment();
                    return firstFragment;

                case 1:
                    Settings_Notifications_Fragment secondFragment= new Settings_Notifications_Fragment ();
                    return secondFragment;

                case 2:
                    Settings_Activities_Fragment thirdFragment = new Settings_Activities_Fragment();
                    return thirdFragment;
                default:
                   return null;
            }
        }

        @Override        public int getCount() {
            return 3;
        }
    }
}
