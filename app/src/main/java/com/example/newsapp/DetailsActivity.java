package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize TextViews
        txt_title = findViewById(R.id.text_details_title);
        txt_author = findViewById(R.id.text_details_author);
        txt_time = findViewById(R.id.text_details_time);
        txt_detail = findViewById(R.id.text_detail_detail); // Corrected ID here
        txt_content = findViewById(R.id.text_detail_content);

        // Initialize ImageView
        img_news = findViewById(R.id.img_details_news);

        // Retrieve data passed from previous activity
        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        if (headlines != null) {
            txt_title.setText(headlines.getTitle());
            txt_author.setText(headlines.getAuthor());
            txt_time.setText(headlines.getPublishedAt());
            txt_detail.setText(headlines.getDescription());
            txt_content.setText(headlines.getContent());

            // Load image using Picasso library

            String imageUrl = headlines.getUrlToimage();
            System.out.println(imageUrl);
            Log.d("DetailsActivity", "Image URL:" + imageUrl);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(img_news, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("DetailsActivity", "image loaded Sucessfully");


                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("DetailsActivity", "Error loading image", e);

                    }
                });


            } else {
                Log.e("DetailsActivity", "Image url is empty");
            }

        }else {
            Log.e("DetailsActivity","Headlines data is null");
        }
    }
}

