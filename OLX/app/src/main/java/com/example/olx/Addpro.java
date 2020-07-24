package com.example.olx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olx.HttpParse;
import com.example.olx.R;

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

public class Addpro extends AppCompatActivity {
    TextView tv;
    EditText name,brand, desc,price,year;
    public static String a1;
    public static String  a2,a3;
    Button RegisterStudent,reg2;
    String nameholder,brandholder, descholder,priceholder,yearholder,typeholder ;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.43.28/olx/addproduct.php";
    Spinner cat;
    JSONObject json = null;
    String str = "";
    HttpResponse response;
    Context context;
    public String text,lid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpro);
reg2=findViewById(R.id.reg1);
        name = (EditText)findViewById(R.id.su1);
        brand= (EditText)findViewById(R.id.su2);
        desc=(EditText)findViewById(R.id.su3);
        price = (EditText)findViewById(R.id.su5);
        year = (EditText)findViewById(R.id.su4);
        cat=(Spinner)findViewById(R.id.spinner);
        tv=(TextView)findViewById(R.id.txt);
        RegisterStudent = (Button)findViewById(R.id.reg);
        lid=Login.log_usn;
reg2.setEnabled(false);
        reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Addpro.this,Upload.class));
                new GetTextViewData2(context).execute();
            }
            });
        new GetTextViewData(context).execute();
        RegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       Toast.makeText(Addpro.this,a1, Toast.LENGTH_SHORT).show();
                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();
            if(typeholder.equals("Properties"))
                 a2=100+"";
            else
            if(typeholder.equals("Cars"))
                a2=101+"";
            else if(typeholder.equals("Furniture"))
                a2=102+"";
            else if(typeholder.equals("Jobs"))
                a2=103+"";
            else if(typeholder.equals("Electronics and Appliances"))
                a2=104+"";
            else if(typeholder.equals("Mobiles"))
                a2=105+"";
            else if(typeholder.equals("Bikes"))
                a2=106+"";
            else if(typeholder.equals("Books, Sports and Hobbies"))
                a2=107+"";
            else if(typeholder.equals("Fashion"))
                a2=108+"";

                if(CheckEditText){

                        StudentRegistration(nameholder,brandholder,descholder,priceholder,yearholder,typeholder,lid,a1);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Addpro.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(adapter);
        cat.setOnItemSelectedListener(new guideselection());



    }

    class guideselection implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }


        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    public void StudentRegistration(final String Namef,final String brandf,final String descf, final String pricef,final String yearf,final String typef,final String idu,final String a) {

        class StudentRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Addpro.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Addpro.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                if(httpResponseMsg.equals("Data entered Successfully"))
                    reg2.setEnabled(true);
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("fname",params[0]);

                hashMap.put("lname",params[1]);

                hashMap.put("phn",params[2]);

                hashMap.put("email",params[3]);
                hashMap.put("address",params[4]);
                hashMap.put("password",params[5]);
                hashMap.put("id",params[6]);
                hashMap.put("pn",params[7]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRegistrationClass studentRegistrationClass = new StudentRegistrationClass();

        studentRegistrationClass.execute(Namef,brandf,descf,pricef,yearf,typef,idu,a);
    }


    public void CheckEditTextIsEmptyOrNot(){

        nameholder = name.getText().toString();
        brandholder=brand.getText().toString();
        descholder = desc.getText().toString();
        priceholder=price.getText().toString();
        yearholder=year.getText().toString();
typeholder=text;

        if(TextUtils.isEmpty(nameholder) && TextUtils.isEmpty(descholder) && TextUtils.isEmpty(priceholder) && TextUtils.isEmpty(yearholder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

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
            HttpPost myConnection = new HttpPost("http://192.168.43.28/olx/phn.php");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("ID", lid));
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
                a1=json.getString("Phone");





            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    private class GetTextViewData2 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetTextViewData2(Context context)
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
            HttpPost myConnection = new HttpPost("http://192.168.43.28/olx/iid.php");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("ID", lid));
                nameValuePairs.add(new BasicNameValuePair("ID2", a2));
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
                a3=json.getString("iid");


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}


