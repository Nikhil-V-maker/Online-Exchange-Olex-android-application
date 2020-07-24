package com.example.olx;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by JUNED on 6/16/2016.
 */

public class RecyclerViewAdapterAdmin extends RecyclerView.Adapter<RecyclerViewAdapterAdmin.ViewHolder> {

    Context context;
    CardView cardView;
    public static String u,url1;
    List<GetDataAdapter> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerViewAdapterAdmin(List<GetDataAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, int position) {

        GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getImageServerUrl(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.

                )
        );

        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);
        Viewholder.ImageTitleNameView.setText(getDataAdapter1.getImageTitleName());
        Viewholder.Imageidview.setText(getDataAdapter1.getImageid());

    Viewholder.Imageidview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            url1=Viewholder.Imageidview.getText().toString();
            Toast.makeText(context, url1, Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,ViewPro2.class));
        }
    });
//}
//
//        Viewholder.Imageidview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                url1 = Viewholder.Imageidview.getText().toString();
//                Toast.makeText(context, url1, Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context, Viewpro.class));
//            }
//        });
//    }
     }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView ;
        public TextView Imageidview;
        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item) ;
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;
            Imageidview=(TextView)itemView.findViewById(R.id.iid);
        }
    }

}
