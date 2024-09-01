package com.example.newsapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.newsapp.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    private static final String TAG ="RequestManager";
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context){
        this.context = context;
    }

    public void getNewsHeadlines(OnFetchDataListener listener,String category,String query){
        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callHeadlines("us",category,query,context.getString(R.string.api_key));
        try{
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                  if(!response.isSuccessful()) {
                      Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                      Log.e(TAG, "Error:" + response.message());
                      return;

                  }
                  if (response.body()==null){
                      Toast.makeText(context, "Response body is null", Toast.LENGTH_SHORT).show();
                      Log.e(TAG,"Response body is null");
                      return;
                  }
                  listener.onFetchData(response.body().getArticles(),response.message());


                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Request Failed" + t.getMessage(),t);
                    listener.onError("Request Failed!");

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"Exception:"+e.getMessage(),e);
        }
    }


    //public RequestManager(Context context) {this.context = context;}

    public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String q,
                @Query("apiKey") String api_key
        );
    }
}
