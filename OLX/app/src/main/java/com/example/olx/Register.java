package com.example.olx;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olx.HttpParse;
import com.example.olx.R;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    TextView tv;
    EditText Fname,Lname, Mobile,Email,Address,Password, ConfirmP;
    Button RegisterStudent;
    String FNameHolder,LnameHolder, PhoneHolder,EmailHolder,AddressHolder, PasswordHolder,ConfirmPHolder ;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.43.28/olx/signupolx.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fname = (EditText)findViewById(R.id.su1);
        Lname= (EditText)findViewById(R.id.su2);
        Mobile=(EditText)findViewById(R.id.su3);
        Email = (EditText)findViewById(R.id.su4);
        Address = (EditText)findViewById(R.id.su5);
        Password=(EditText)findViewById(R.id.su6);
        ConfirmP = (EditText)findViewById(R.id.su7);

        tv=(TextView)findViewById(R.id.txt);
        RegisterStudent = (Button)findViewById(R.id.reg);


        RegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    if(PasswordHolder.equals(ConfirmPHolder)) {
                        StudentRegistration(FNameHolder,LnameHolder, PhoneHolder, EmailHolder,AddressHolder,ConfirmPHolder);
                    }
                    else
                        tv.setText("Confirm password is not same as password");

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Register.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void StudentRegistration(final String Namef,final String NameL,final String phone, final String email,final String address,final String confirm) {

        class StudentRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Register.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Register.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("fname",params[0]);

                hashMap.put("lname",params[1]);

                hashMap.put("phn",params[2]);

                hashMap.put("email",params[3]);
                hashMap.put("address",params[4]);
                hashMap.put("password",params[5]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRegistrationClass studentRegistrationClass = new StudentRegistrationClass();

        studentRegistrationClass.execute(Namef,NameL,phone,email,address,confirm);
    }


    public void CheckEditTextIsEmptyOrNot(){

        FNameHolder = Fname.getText().toString();
        LnameHolder=Lname.getText().toString();
        PhoneHolder = Mobile.getText().toString();
        EmailHolder=Email.getText().toString();
        AddressHolder=Address.getText().toString();
        PasswordHolder = Password.getText().toString();
        ConfirmPHolder = ConfirmP.getText().toString();

        if(TextUtils.isEmpty(FNameHolder) && TextUtils.isEmpty(LnameHolder) && TextUtils.isEmpty(PhoneHolder) && TextUtils.isEmpty(EmailHolder) && TextUtils.isEmpty(AddressHolder) && TextUtils.isEmpty(PasswordHolder) && TextUtils.isEmpty(ConfirmPHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }
}


