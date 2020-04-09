package com.example.antenatal;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout;

import java.util.Random;

public class ReadTips extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_tips);

        String trimDescription = getIntent().getStringExtra("trimDescription");
        String trimTitle = getIntent().getStringExtra("trimTitle");
        String trimType = getIntent().getStringExtra("trimType");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.body);

        SubtitleCollapsingToolbarLayout t = findViewById(R.id.collapsing);

        title.setText(trimTitle);
        content.setText(Html.fromHtml(trimDescription));

        t.setSubtitle(trimType);

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
}
