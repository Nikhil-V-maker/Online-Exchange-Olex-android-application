package com.example.olx;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olx.HttpParse;
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

public class ViewPro2 extends AppCompatActivity {
    Context context;
    public  String imgp,id,stdusn,id2;
    public static String idu;
    HttpResponse response;
    String str = "";
    Button approve,disapprove;
    public String usn,comp,appr,disappr;
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String,String> hashMap = new HashMap<>();
    ImageView im;
    TextView t1,t2,t3,t4,t5,t6;
    JSONObject json = null;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pro2);
        id=RecyclerViewAdapterAdmin.url1;
        im=findViewById(R.id.imageViewShow);
        GetImageViewData(id);
        t1=findViewById(R.id.vp1);
        t2=findViewById(R.id.vp2);
        t3=findViewById(R.id.vp4);
        t4=findViewById(R.id.vp5);
        t5=findViewById(R.id.vp6);
        t6=findViewById(R.id.nm4);
        approve=findViewById(R.id.reg);
        disapprove=findViewById(R.id.reg2);
        id2=RecyclerViewAdapterAdmin.url1;
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewPro2.this,Sellerdetails.class));

            }
        });
        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImageViewData3(id2);

            }
        });
        new GetTextViewData(context).execute();
        //Toast.makeText(ViewImage.this, "hhh"+str, Toast.LENGTH_SHORT).show();
    }
    public void  GetImageViewData(final String id1) {
        class GetImageViewData extends AsyncTask<String, Void, String> {
            String HttpURL = "http://192.168.43.28/olx/getImage.php";
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
                Picasso.with(ViewPro2.this).load(imgp).resize(600, 700).into(img);
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
            HttpPost myConnection = new HttpPost("http://192.168.43.28/olx/itemdetails.php");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("ID", id));
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
                stdusn=json.getString("price");
                t1.setText("Rs. "+stdusn);
                t2.setText(json.getString("name"));
                t3.setText(json.getString("brand"));
                t4.setText(json.getString("year"));
                t5.setText(json.getString("description"));
                idu=json.getString("Userid");





            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }
    public void  GetImageViewData3(final String id1) {
        class GetImageViewData extends AsyncTask<String, Void, String> {
            String HttpURL = "http://192.168.43.28/olx/delete.php";
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
