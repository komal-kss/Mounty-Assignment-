package com.example.topratedmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.topratedmovies.pojo.UpcomingMovieResult;
import com.example.topratedmovies.pojo.UpcomingMoviesResponse;
import com.example.topratedmovies.retrofit.TmdbApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpcomingMovieDetails extends MainActivity{
    public static final String RELEASE_DATE="Release Date:";
    public static final String RATINGS="Ratings:";
    public static final String HOURS="hrs";
    private ImageView moviePoster;
    private TextView movieDesc,movieReleaseDate,movieLength,titleView;
    private RatingBar movieRating;
    int id=0;
    Toolbar toolbar;

    String title,poster,desc,release_date;
    double rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for getting the full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.upcoming_movie_details);

        Intent movieIntent=getIntent();
        title= movieIntent.getExtras().getString("title");
        poster= movieIntent.getExtras().getString("poster");
        desc= movieIntent.getExtras().getString("desc");
        release_date= movieIntent.getExtras().getString("release_date");
        Log.d("title",title+" "+poster+" "+desc+" "+release_date);


        moviePoster=findViewById(R.id.imageId);
        movieDesc=findViewById(R.id.descId);
        movieReleaseDate=findViewById(R.id.release_dateId);
        movieRating=findViewById(R.id.ratingId);
        titleView=findViewById(R.id.title);

        titleView.setText(title);
        movieDesc.setText(desc);
        movieReleaseDate.setText("Release Date: "+release_date);

        //Setting the values
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+poster).into(moviePoster);
//        movieDesc.setText(desc);
//        movieReleaseDate.setText(String.format("%s%s\ns%s", RELEASE_DATE, release_date, RATINGS,rating));


    }




    }

