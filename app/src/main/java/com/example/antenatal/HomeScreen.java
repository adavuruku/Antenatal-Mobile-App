package com.example.antenatal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class HomeScreen extends AppCompatActivity implements Profile.OnFragmentInteractionListener,
        Notice.OnFragmentInteractionListener, Appointment.OnFragmentInteractionListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences MyId;
    String userID,DrawerFullname,DrawerEmail;
    Toolbar toolbar;
    dbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setSaveEnabled(true);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        MyId = this.getSharedPreferences("MyId", this.MODE_PRIVATE);
        userID = MyId.getString("MyId", "");
        //retrieve student information
        dbHelper = new dbHelper(getApplicationContext());

        changeBackgroundImage();
        initNavigationDrawer();
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
    public void initNavigationDrawer() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                int id = menuItem.getItemId();
                drawerLayout.closeDrawers();
                switch (id) {
                    case R.id.about:
                        intent = new Intent(getApplicationContext(), about.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.day:
                        intent = new Intent(getApplicationContext(), DailyTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.week:
                        intent = new Intent(getApplicationContext(), WeeklyTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.month:
                        intent = new Intent(getApplicationContext(), MonthlyTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.nutrition:
                        intent = new Intent(getApplicationContext(), NutritionTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.health:
                        intent = new Intent(getApplicationContext(), HealthTips.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.trimester:
                        intent = new Intent(getApplicationContext(), TrimesterTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.fetal:
                        intent = new Intent(getApplicationContext(), FetalTips.class);
                        intent.putExtra("TABID","0");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        break;
                    case R.id.close:
                        SharedPreferences.Editor editor;
                        editor = MyId.edit();
                        editor.putString("MyId", "");
                        editor.apply();
                        finish();
                        break;
                }
                return true;
            }
        });


        View header = navigationView.getHeaderView(0);

        ImageView imageV = header.findViewById(R.id.navImage);
        int min = 1; int max=12;
        Random r = new Random();
        int imgId = r.nextInt((max - min) + 1) + min;
        switch(imgId){
            case 1:
                imageV.setImageResource(R.drawable.baby1);
                break;
            case 2:
                imageV.setImageResource(R.drawable.baby2);
                break;
            case 3:
                imageV.setImageResource(R.drawable.baby3);
                break;
            case 4:
                imageV.setImageResource(R.drawable.baby4);
                break;
            case 5:
                imageV.setImageResource(R.drawable.baby5);
                break;
            case 6:
                imageV.setImageResource(R.drawable.baby6);
                break;
            case 7:
                imageV.setImageResource(R.drawable.baby7);
                break;
            case 8:
                imageV.setImageResource(R.drawable.baby8);
                break;
            case 9:
                imageV.setImageResource(R.drawable.baby9);
                break;
            case 10:
                imageV.setImageResource(R.drawable.baby10);
                break;
            case 11:
                imageV.setImageResource(R.drawable.baby11);
                break;
            case 12:
                imageV.setImageResource(R.drawable.baby12);
                break;

        }

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

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
            switch(position){
                case 0:
                    Profile profile = new Profile();
                    profile.setRetainInstance(true);
                    return profile;
                case 1:
                    Appointment appointment = new Appointment();
                    appointment.setRetainInstance(true);
                    return appointment;
                case 2:
                    Notice notice = new Notice();
                    notice.setRetainInstance(true);
                    return notice;

                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Profile";
                case 1:
                    return "Appointments";
                case 2:
                    return "Articles";

            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
