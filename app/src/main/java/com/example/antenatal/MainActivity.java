package com.example.antenatal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbColumnList.dailyTips = new ArrayList<>(); //daily tips
        dbColumnList.weeklyTips = new ArrayList<>();
        dbColumnList.monthlyTips = new ArrayList<>();
        dbColumnList.nutritionTips = new ArrayList<>();

        dbColumnList.trimeseterOne= new ArrayList<>();
        dbColumnList.trimeseterTwo = new ArrayList<>();
        dbColumnList.trimeseterThree= new ArrayList<>();

        dbColumnList.momHealth= new ArrayList<>();
        dbColumnList.babyHealth = new ArrayList<>();
        dbColumnList.breastFeeding= new ArrayList<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new LoadLocalData().execute();
            }
        },5000);
    }

    class LoadLocalData extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String json;
            InputStream is;
            dbColumnList.dailyTips.clear();
            dbColumnList.weeklyTips.clear();
            try {
                is = getApplication().getAssets().open("dailytips.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                JSONArray jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    myModels.tipsModel tipsModel = new myModels().new tipsModel(
                            jsonobject.getString("tipsDescription")
                    );
                    dbColumnList.dailyTips.add(tipsModel);
                }

                //weekly
                is = getApplication().getAssets().open("weeklytips.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    myModels.tipsModel tipsModel = new myModels().new tipsModel(
                            jsonobject.getString("tipsDescription").replace("\r\n", "<br>")
                    );
                    dbColumnList.weeklyTips.add(tipsModel);
                }

                //monthly
                is = getApplication().getAssets().open("monthlytips.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    myModels.tipsModel tipsModel = new myModels().new tipsModel(
                            jsonobject.getString("tipsDescription").replace("\r\n", "<br>")
                    );
                    dbColumnList.monthlyTips.add(tipsModel);
                }


                //nutrition
                is = getApplication().getAssets().open("nutrition.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.tipsModel tipsModel = new myModels().new tipsModel(
                            jsonobject.getString("tipsDescription").replace("\r\n", "<br>")
                    );
                    dbColumnList.nutritionTips.add(tipsModel);
                }

                //trimester 1
                is = getApplication().getAssets().open("trimesterone.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "1st Trimester",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.trimeseterOne.add(trimesterModel);
                }

                //trimester 2
                is = getApplication().getAssets().open("trimestertwo.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "2nd Trimester",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.trimeseterTwo.add(trimesterModel);
                }

                //trimester 3
                is = getApplication().getAssets().open("trimesterthree.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "3rd Trimester",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.trimeseterThree.add(trimesterModel);
                }


                //MOM HEALTH
                is = getApplication().getAssets().open("pregnancyhealth.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "Mother's Health",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.momHealth.add(trimesterModel);
                }

                //Baby HEALTH
                is = getApplication().getAssets().open("babyhealth.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "Baby's Health",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.babyHealth.add(trimesterModel);
                }

                //breastfeeding
                is = getApplication().getAssets().open("breastfeeding.json");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    myModels.trimesterModel trimesterModel = new myModels().new trimesterModel(
                            "Breastfeeding",
                            jsonobject.getString("title").replace("\r\n", "<br>"),
                            jsonobject.getString("tipDescription").replace("\r\n", "<br>"),
                            jsonobject.getString("title").replace("\r\n", "<br>")
                    );
                    dbColumnList.breastFeeding.add(trimesterModel);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(getApplication(),HealthTips.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
    }
    public void startProg() {

    }

}
