package com.example.olx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Options extends AppCompatActivity {
public static String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ImageView pro1=findViewById(R.id.op1);
        pro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=100+"";
            }
        });
        ImageView pro2=findViewById(R.id.op2);
        pro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=101+"";
            }
        });
        ImageView pro3=findViewById(R.id.op3);
        pro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=102+"";
            }
        });
        ImageView pro4=findViewById(R.id.op4);
        pro4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=104+"";
            }
        });
        ImageView pro5=findViewById(R.id.op5);
        pro5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=105+"";
            }
        });
        ImageView pro6=findViewById(R.id.op6);
        pro6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=106+"";
            }
        });
        ImageView pro7=findViewById(R.id.op7);
        pro7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=107+"";
            }
        });
        ImageView pro8=findViewById(R.id.op8);
        pro8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=108+"";
            }
        });
        ImageView pro9=findViewById(R.id.op9);
        pro9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, listofitems.class));
                cid=103+"";
            }
        });

    }
}
