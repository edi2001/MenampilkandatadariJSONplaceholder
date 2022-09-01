package com.example.tugasandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class albumAdapter extends RecyclerView.Adapter<albumAdapter.albumHolder> {
    private Context context;
    private List<album> albumlist;

    public  albumAdapter(Context context,List<album>albums){
        this.context=context;
        albumlist=albums;

    }
    @NonNull

    @Override
    public albumHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new albumHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  albumAdapter.albumHolder holder, int position) {
    album alb = albumlist.get(position);
    holder.jdl.setText(alb.getTitle().toString());
    Glide.with(context).load(alb.getGambar()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return albumlist.size();
    }

    public class albumHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView jdl;

        public albumHolder(@NonNull  View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.imageView2);
            jdl=itemView.findViewById(R.id.judul);

        }
    }


}
