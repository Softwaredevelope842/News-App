package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder  extends RecyclerView.ViewHolder {
    TextView text_Tittle,text_Source;
    ImageView img_headlines;
    CardView cardView;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_Tittle=itemView.findViewById(R.id.text_Title);
        text_Source=itemView.findViewById(R.id.text_Source);
        img_headlines=itemView.findViewById(R.id.img_headline);
        cardView=itemView.findViewById(R.id.main_container);
    }
}
