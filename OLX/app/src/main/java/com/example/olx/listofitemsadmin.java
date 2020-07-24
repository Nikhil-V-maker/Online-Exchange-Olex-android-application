package com.example.olx;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class listofitemsadmin extends AppCompatActivity {

    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;
    NetworkImageView im;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    public String s;

    RecyclerView.Adapter recyclerViewadapter;
    CardView cd;
    String GET_JSON_DATA_HTTP_URL;
    String JSON_IMAGE_TITLE_NAME = "name";
    String JSON_IMAGE_URL = "img1";
    String JSON_IMAGE_ID="iid";
    JsonArrayRequest jsonArrayRequest ;
    public String cidc;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listofitemsadmin);
        cidc=Options.cid ;
        cidc=Optionsadmin.cid;
        if(cidc.equals("100"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver.php";
        else
        if(cidc.equals("101"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver1.php";
        else
        if(cidc.equals("102"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver2.php";
        else
        if(cidc.equals("103"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver3.php";
        else
        if(cidc.equals("104"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver4.php";
        else
        if(cidc.equals("105"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver5.php";
        else
        if(cidc.equals("106"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver6.php";
        else
        if(cidc.equals("107"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver7.php";
        else
        if(cidc.equals("108"))
            GET_JSON_DATA_HTTP_URL = "http://192.168.43.28/olx/fetchfromserver8.php";



        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();


    }

    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setImageTitleNamee(json.getString(JSON_IMAGE_TITLE_NAME));

                GetDataAdapter2.setImageServerUrl(json.getString(JSON_IMAGE_URL));

                GetDataAdapter2.setImageid(json.getString(JSON_IMAGE_ID));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterAdmin(GetDataAdapter1, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }

}
