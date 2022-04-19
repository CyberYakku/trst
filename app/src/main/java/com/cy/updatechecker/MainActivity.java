package com.cy.updatechecker;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cyberyakku.updatechecker.CyberYakkuUpdateChecker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button mUpdate;




    int versionCode = BuildConfig.VERSION_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CyberYakkuUpdateChecker cyberYakkuUpdateChecker = new CyberYakkuUpdateChecker(MainActivity.this,"https://sheetapi.cyberyakku.com/api?id=1IT1Oo1D60ZmuLV4-wAKo6rkqQ1WpPkXk2kVuSX_bT70&columns=false&sheet=sheet1");
        cyberYakkuUpdateChecker.setVersionName(BuildConfig.VERSION_NAME);




        mUpdate = (Button) findViewById(R.id.Update);



        mUpdate.setOnClickListener(view -> {
            Toast.makeText(this, "Update Checking..", Toast.LENGTH_SHORT).show();
            cyberYakkuUpdateChecker.check();
        });




    }




}