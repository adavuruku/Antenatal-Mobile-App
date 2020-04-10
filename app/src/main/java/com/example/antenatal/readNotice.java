package com.example.antenatal;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

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


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
