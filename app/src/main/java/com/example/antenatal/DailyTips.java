package com.example.antenatal;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class DailyTips extends AppCompatActivity implements DailyPage.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tips);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SubtitleCollapsingToolbarLayout t = findViewById(R.id.collapsing);
        t.setSubtitle("Daily Pregnancy Tips.");
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setSaveEnabled(true);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        int tabIndex = Integer.parseInt(getIntent().getStringExtra("TABID"));
        TabLayout.Tab tab = tabs.getTabAt(tabIndex);
        tab.select();

        changeBackgroundImage();
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override public void onTabSelected(TabLayout.Tab tab) {
//                dbColumnList.TabPostion = tab.getPosition();
//                dbColumnList.TabText = tab.getText().toString();
//            }
//
//            @Override public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }



    public void changeBackgroundImage(){
        ImageView profile_pic = findViewById(R.id.profile_pic);
        int min = 1; int max=12;
        Random r = new Random();
        int imgId = r.nextInt((max - min) + 1) + min;
        switch(imgId){
            case 1:
                profile_pic.setImageResource(R.drawable.baby1);
                break;
            case 2:
                profile_pic.setImageResource(R.drawable.baby2);
                break;
            case 3:
                profile_pic.setImageResource(R.drawable.baby3);
                break;
            case 4:
                profile_pic.setImageResource(R.drawable.baby4);
                break;
            case 5:
                profile_pic.setImageResource(R.drawable.baby5);
                break;
            case 6:
                profile_pic.setImageResource(R.drawable.baby6);
                break;
            case 7:
                profile_pic.setImageResource(R.drawable.baby7);
                break;
            case 8:
                profile_pic.setImageResource(R.drawable.baby8);
                break;
            case 9:
                profile_pic.setImageResource(R.drawable.baby9);
                break;
            case 10:
                profile_pic.setImageResource(R.drawable.baby10);
                break;
            case 11:
                profile_pic.setImageResource(R.drawable.baby11);
                break;
            case 12:
                profile_pic.setImageResource(R.drawable.baby12);
                break;

        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return DailyPage.getInstance(position);
        }



        @Override
        public int getCount() {
            return dbColumnList.tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return dbColumnList.tabs[position];
        }
    }
}
