package com.example.antenatal;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Random;

public class readNotice extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    TextView title, author, date;
    TextView body;
    dbHelper dbHelper;
    String noticeid, userID, DrawerFullname, DrawerEmail;
    private NavigationView navigationView;
    private SharedPreferences MyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_notice);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noticeid = getIntent().getStringExtra("NEWSID");


        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        author = findViewById(R.id.author);
        body = findViewById(R.id.body);

        //retrieve info from sqlite
        dbHelper = new dbHelper(getApplicationContext());
        Cursor cursor = dbHelper.getANotice(noticeid);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            title.setText(cursor.getString(cursor.getColumnIndex(dbColumnList.abuadNotice.COLUMN_TITLE)));
            date.setText(cursor.getString(cursor.getColumnIndex(dbColumnList.abuadNotice.COLUMN_NOTICEDATE)));
            author.setText(cursor.getString(cursor.getColumnIndex(dbColumnList.abuadNotice.COLUMN_AUTHOR)));
            body.setText(cursor.getString(cursor.getColumnIndex(dbColumnList.abuadNotice.COLUMN_DESCRIPTION)));
        }

        MyId = this.getSharedPreferences("MyId", this.MODE_PRIVATE);
        userID = MyId.getString("MyId", "");

        changeBackgroundImage();

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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
