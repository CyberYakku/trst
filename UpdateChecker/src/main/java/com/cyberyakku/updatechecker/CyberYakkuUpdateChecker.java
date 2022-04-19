package com.cyberyakku.updatechecker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CyberYakkuUpdateChecker {
    private String xcode;
    private String xhed;
    private String xmsg;
    private String xbtn;
    private String xlink;
    private String xclose;


    RequestQueue mUpdate;
    String Update_url;

    Activity context;
    String versionName;

    public CyberYakkuUpdateChecker(Activity context, String url) {
        this.context = context;
        this.Update_url = url;

        mUpdate = Volley.newRequestQueue(context);

    }

    public void setVersionName(String versionName){
        this.versionName = versionName;
    }

    public void check(){
        UpdateContorl(Update_url);
    }


    public void UpdateContorl(String surl) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, surl, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("rows"));
                            for (int i = 0; i < 1; i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);


                                //mTitel.setText(hedmsg);
                                xcode = obj.getString("Version_code");
                                xhed = obj.getString("Update_Title");
                                xmsg = obj.getString("Update_Message");
                                xbtn = obj.getString("Update_Button_text");
                                xlink = obj.getString("Link");
                                //xlink = obj.getString("close");

                                //Toast.makeText(webActivity.this, BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();


                                //	Toast.makeText(webActivity.this, xhed, Toast.LENGTH_SHORT).show();


                                Dialog dialogg = new Dialog(context);
                                dialogg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                dialogg.setContentView(R.layout.update_dialog);
                                dialogg.setCancelable(false);
                                dialogg.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                                        WindowManager.LayoutParams.WRAP_CONTENT);
                                dialogg.getWindow().getAttributes().windowAnimations =
                                        android.R.style.Animation_Dialog;

                                Button mupdatebtn = dialogg.findViewById(R.id.update_btn);
                                TextView mupdatehed = dialogg.findViewById(R.id.update_hed);
                                TextView mupdatemsg = dialogg.findViewById(R.id.update_msg);
                                ImageView imageView = dialogg.findViewById(R.id.close);



                                mupdatehed.setText(xhed);
                                mupdatemsg.setText(xmsg + "\n\n New Version  " + xcode);
                                mupdatebtn.setText(xbtn);

                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogg.dismiss();

                                    }
                                });

                                mupdatebtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(xlink));
                                        context.startActivity(i);

                                    }
                                });








                                String SliderStatus = obj.getString("Version_code");
                                Log.d("test__json**", SliderStatus);
                                if (SliderStatus.equals(versionName)) {


                                } else {
                                    dialogg.show(); }


                                String clos = obj.getString("Update_Dialog_Close");
                                Log.d("test__json**", clos);
                                if (clos .equals("false")) {

                                    imageView.setVisibility(View.GONE);

                                } else {



                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            // ape.setVisibility(View.VISIBLE);


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Log.d(TAG, "onErrorResponse: "+error);
                //  mLoading.setVisibility(View.GONE);
            }
        });

        mUpdate.add(request);


    }

}
