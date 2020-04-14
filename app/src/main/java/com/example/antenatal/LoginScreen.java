package com.example.antenatal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {
    Button login;
    TextView userid, password;
    ProgressDialog pd;
    AlertDialog.Builder builder;
//    String address = "https://antenantal.000webhostapp.com/antenatalrest.php";
    String allResult, userName, userPassword, studentName;
    SharedPreferences MyId;
    public byte[] byteArray=null;
    private dbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button login = findViewById(R.id.btnSave);
        userid  = findViewById(R.id.userid);

        MyId = this.getSharedPreferences("MyId", this.MODE_PRIVATE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userid.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    displayMessage("Invalid Data's Provided - Please Verify");
                } else {
                    //login online
                    volleyJsonArrayRequest(dbColumnList.address);
                }
            }
        });

        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Processing Request ...");
        pd.setTitle(R.string.app_name);
        pd.setIcon(R.mipmap.ic_launcher);
        pd.setIndeterminate(true);
        pd.setCancelable(true);
    }


    public void displayMessage(String msg) {
        if(pd.isShowing()){
            pd.hide();
            pd.cancel();
        }
        builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setCancelable(false);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        alert.show();
    }


    public void volleyJsonArrayRequest(String url){
        if(pd.isShowing()){
            pd.cancel();
            pd.hide();
        }
        pd.show();
        String  REQUEST_TAG = "com.volley.volleyJsonArrayRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()<=2){
                            displayMessage("Error: Wrong Hospital ID !!!");
//                            Toast.makeText(getApplicationContext(), response ,Toast.LENGTH_LONG).show();
                        }else{
                            allResult = response;
//                            Toast.makeText(getApplicationContext(), response ,Toast.LENGTH_LONG).show();
                            new ReadJSON().execute();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(pd.isShowing()){
                            pd.hide();
                        }
                        displayMessage("Error: No Internet Connection !!!");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("opr", "login");
                params.put("userID", userName);
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }


    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                SharedPreferences.Editor editor;

                JSONArray jsonarray = new JSONArray(allResult);

                JSONObject jsonobject = jsonarray.getJSONObject(0);
                dbHelper = new dbHelper(getApplicationContext());
                studentName = jsonobject.getString("patientName");

                dbHelper.saveUserInformation(
                        jsonobject.getString("HID"),jsonobject.getString("patientName"),
                        jsonobject.getString("contactAddress"),jsonobject.getString("createdBy"),
                        jsonobject.getString("dateReg"),jsonobject.getString("officeAddress"),
                        jsonobject.getString("patientEmail"),jsonobject.getString("illnesDescription"),
                        jsonobject.getString("patientLocalGovt"),jsonobject.getString("patientState"),
                        jsonobject.getString("patientPhone")
                );
                if (jsonarray.length() > 1){
                    jsonobject = jsonarray.getJSONObject(1);

                    dbHelper.savePregnantInformation(
                            jsonobject.getString("HID"),
                            jsonobject.getString("pregStart"),
                            jsonobject.getString("pregExpectedEndDate"),
                            jsonobject.getString("NoOfBaby"),
                            jsonobject.getString("babyGenderDescription")

                    );

                }

                //userID
                editor = MyId.edit();
                editor.putString("MyId",jsonobject.getString("HID"));
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if(pd.isShowing()){
                    pd.cancel();
                    pd.hide();
                }
                Toast.makeText(getApplicationContext(),"Welcome "+ studentName + " To ABUADTH Antenatal APP",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), HomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                displayMessage("Error: No Internet Connection !!!");
            }

            super.onPostExecute(s);
        }
    }

    //menu settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.close:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
