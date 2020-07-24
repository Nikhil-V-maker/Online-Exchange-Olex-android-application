package com.example.olx;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class UpdateProfile extends AppCompatActivity {
public String u1,u2,u3,u4,u5,u6;
EditText t1,t2,t3,t4,t5,t6;
    public String Glob_usn;
    EditText  e1,e2,e3,e4,e5;
    Button RegisterStudent,ShowStudents;
    String  eh1,eh2,eh3,eh4,eh5;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.43.28/olx/Update.php";
    public String Glob_usnc,Glob_usna,Glob_usnp,Glob_usne;
   // public String u1,u2,u3;
    Bitmap bitmap;

    boolean check = true;

    Button SelectImageGallery, UploadImageServer;

    EditText imageName;


    String GetImageNameEditText;

    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;

    String ServerUploadPath ="http://192.168.43.28/olx/imgtoserverp.php" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        t1=findViewById(R.id.up1);
        t2=findViewById(R.id.up2);
        t4=findViewById(R.id.up4);
        t5=findViewById(R.id.up5);
        t6=findViewById(R.id.up6);
        u1=profile.a2;
        u2=profile.a1;
        u3=profile.v2;
        u4=profile.v3;
        u5=profile.v4;
        u6=profile.v5;
        t1.setText(u1);
        t2.setText(u2);
        t4.setText(u3);
        t5.setText(u4);
        t6.setText(u5);
        u1=Login.log_usn;
        e1 = (EditText)findViewById(R.id.up1);
        e2=(EditText)findViewById(R.id.up2);
        e3 = (EditText)findViewById(R.id.up4);
        e4=(EditText)findViewById(R.id.up5);
        e5=(EditText)findViewById(R.id.up6);
        RegisterStudent = (Button)findViewById(R.id.sub2);


        RegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    //Toast.makeText(Studdetail.this, "Pl ."+Glob_usn, Toast.LENGTH_LONG).show();
                    StudentUpdate(u6,eh1,eh2,eh3,eh4,eh5);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(UpdateProfile.this, "No field is entered.", Toast.LENGTH_LONG).show();

                }

            }
        });
        imageName = (EditText)findViewById(R.id.editTextImageName);

        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);

        UploadImageServer = (Button)findViewById(R.id.buttonUpload);

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });


        UploadImageServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageNameEditText = imageName.getText().toString();

                ImageUploadToServerFunction();

            }
        });

    }



    public void StudentUpdate(final String id, final String fn, final String ln,final String phn, final String em,final String add ){

        class StudentUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateProfile.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateProfile.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("ID",params[0]);

                hashMap.put("fn",params[1]);

                hashMap.put("ln",params[2]);

                hashMap.put("phn",params[3]);

                hashMap.put("em",params[4]);
                hashMap.put("add",params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentUpdateClass studentUpdateClass = new StudentUpdateClass();

        studentUpdateClass.execute(id,fn,ln,phn,em,add);
    }


    public void CheckEditTextIsEmptyOrNot(){

        Glob_usn=Login.log_usn;


        eh1 = e1.getText().toString();
        eh2 = e2.getText().toString();
        eh3 = e3.getText().toString();
        eh4 = e4.getText().toString();
        eh5 = e5.getText().toString();

        if(TextUtils.isEmpty(eh1) && TextUtils.isEmpty(eh2) && TextUtils.isEmpty(eh3) && TextUtils.isEmpty(eh4) &&  TextUtils.isEmpty(eh5))
        {

            CheckEditText = false;

        }
        else {
            CheckEditText = true;
        }
    }
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);



            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateProfile.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(UpdateProfile.this,string1,Toast.LENGTH_LONG).show();

                // Setting image as transparent after done uploading.



            }

            @Override
            protected String doInBackground(Void... params) {

              ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName, GetImageNameEditText);

                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("uid",u1);
                //HashMapParams.put("cid",u2);
                //HashMapParams.put("iid",u3);
                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

}

