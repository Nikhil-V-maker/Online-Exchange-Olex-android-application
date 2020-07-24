package com.example.olx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olx.R;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Sellerdetails extends Activity {
    public String Glob_usnp,imgp;
    TextView textview1,textView2,textView3,textView4,textView5;
    public static String v1,v2,v3,v4,v5,a1,a2;
    JSONObject json = null;
    String str = "";
    HttpResponse response;
    Context context;
    ProgressBar progressbar;
    Button button;
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String,String> hashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerdetails);

        progressbar = (ProgressBar) findViewById(R.id.progressBar1);
        textview1 = (TextView) findViewById(R.id.pd1);
        textView2 = (TextView) findViewById(R.id.pd2);
        textView3 = (TextView) findViewById(R.id.pd3);
        textView4 = (TextView) findViewById(R.id.pd4);



        Glob_usnp = Viewpro.idu;
        GetImageViewData(Glob_usnp);

        // button.setOnClickListener(new View.OnClickListener() {

        //  @Override
        //    public void onClick(View v) {
        // TODO Auto-generated method stub



        new GetTextViewData(context).execute();

    }
//        });
//    }


    private class GetTextViewData extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetTextViewData(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://192.168.43.28/olx/profiledisp.php");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("ID", Glob_usnp));
                myConnection.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = myClient.execute(myConnection);
                str = EntityUtils.toString(response.getEntity(), "UTF-8");


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                JSONArray jArray = new JSONArray(str);
                json = jArray.getJSONObject(0);
                json = jArray.getJSONObject(1);
                json = jArray.getJSONObject(2);
                json = jArray.getJSONObject(3);
                json = jArray.getJSONObject(4);
                json = jArray.getJSONObject(5);
                json = jArray.getJSONObject(6);



            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {
                a1=json.getString("Lname");
                a2=json.getString("Fname");
                textview1.setText(a2+" "+a1);
                textView2.setText(json.getString("Phone"));
                textView3.setText(json.getString("Email"));
                textView4.setText(json.getString("Address"));

//                v1=textview1.getText().toString();





            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.


        }
    }
    public void  GetImageViewData(final String id1) {
        class GetImageViewData extends AsyncTask<String, Void, String> {
            String HttpURL = "http://192.168.43.28/olx/getImagep.php";
            ImageView img=findViewById(R.id.imageViewShow);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // progressDialog = ProgressDialog.show(ViewImage.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                // progressDialog.dismiss();
                imgp=httpResponseMsg;
                Picasso.with(Sellerdetails.this).load(imgp).resize(600, 700).into(img);
                PhotoViewAttacher pAttacher;
                pAttacher = new PhotoViewAttacher(img);
                pAttacher.update();
                //Toast.makeText(ViewImage.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("ID", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }
        GetImageViewData getImageViewData=new GetImageViewData();
        getImageViewData.execute(id1);
    }


}
