package com.example.topratedmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.topratedmovies.adapters.TopRatedMovieAdapter;
import com.example.topratedmovies.adapters.UpcomingMovieAdapter;
import com.example.topratedmovies.model.TopRatedMovie;
import com.example.topratedmovies.model.UpcomingMovieModel;
import com.example.topratedmovies.pojo.TopRatedResult;
import com.example.topratedmovies.pojo.TopRatedMoviesResponse;
import com.example.topratedmovies.pojo.UpcomingMovieResult;
import com.example.topratedmovies.pojo.UpcomingMoviesResponse;
import com.example.topratedmovies.retrofit.TmdbApi;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog1, progressDialog2;
    Retrofit  retrofit;
    TopRatedMovieAdapter topRatedMovieAdapter;
    UpcomingMovieAdapter upcomingMovieAdapter;
    RecyclerView recyclerViewtop, recylerViewUp;
    List<TopRatedMovie> topRatedMovieList;
    List<UpcomingMovieModel> upcomingMovieModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewtop=findViewById(R.id.recycler_viewtop);
        recylerViewUp=findViewById(R.id.recycler_viewupcoming);
        topRatedMovieList=new ArrayList<>();
        upcomingMovieModelList=new ArrayList<>();
        topRatedMovieAdapter=new TopRatedMovieAdapter(MainActivity.this,topRatedMovieList);
        recyclerViewtop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewtop.setAdapter(topRatedMovieAdapter);

        fetchTopRatedMoviesData();
        upcomingMovieAdapter=new UpcomingMovieAdapter(MainActivity.this,upcomingMovieModelList);
        recylerViewUp.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recylerViewUp.setAdapter(upcomingMovieAdapter);
        fetchUpcomingMoviesData();
    }
    public void fetchUpcomingMoviesData()
    {
//        progressDialog1= new ProgressDialog(MainActivity.this, R.style.AppTheme_Dark_Dialog);
//        progressDialog1.setIndeterminate(true);
//        progressDialog1.setMessage("Fetching Movies ...");
//        progressDialog1.setCanceledOnTouchOutside(false);
//        progressDialog1.show();
        retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        TmdbApi tmdbApi=retrofit.create(TmdbApi.class);
        Call<UpcomingMoviesResponse> upcomingMoviesResponseCall=tmdbApi.getUpcomingMovies(Constants.API_KEY);

        upcomingMoviesResponseCall.enqueue(new Callback<UpcomingMoviesResponse>(){

            @Override
            public void onResponse(Call<UpcomingMoviesResponse> call, Response<UpcomingMoviesResponse> response) {
                if(response!=null)
                {
//                    progressDialog1.dismiss();
                    Log.d("tagg","raeagtg46h3");
                    UpcomingMoviesResponse upcomingMoviesResponse=response.body();

                    List<UpcomingMovieResult>  resultsBeanList=upcomingMoviesResponse.getResults();

                    Log.d("Check",resultsBeanList.toString());

                    for(UpcomingMovieResult resultsBean:resultsBeanList){

                        String title=resultsBean.getTitle();
                        String imageUrl=resultsBean.getPosterPath();
                        String description=resultsBean.getOverview();
                        String releaseDate=resultsBean.getReleaseDate();
                        String ratings=resultsBean.getPopularity().toString();
                        Log.d("values",title + " "+imageUrl + " "+releaseDate + " "+ ratings );

                        UpcomingMovieModel upcomingMovieModel=new UpcomingMovieModel(imageUrl,title,description,releaseDate,ratings);

                        upcomingMovieModelList.add(upcomingMovieModel);

                    }

                    upcomingMovieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UpcomingMoviesResponse> call, Throwable t) {
                Log.i("tagg","raeagtg46h3 failed");
            }
        });
    }

    public  void fetchTopRatedMoviesData(){

        //for progressbarDialog
        progressDialog2= new ProgressDialog(MainActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog2.setIndeterminate(true);
        progressDialog2.setMessage("Fetching Movies ...");
        progressDialog2.setCanceledOnTouchOutside(false);
        progressDialog2.show();

        retrofit =new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi=retrofit.create(TmdbApi.class);

        Call<TopRatedMoviesResponse> topRatedMoviesResponseCall=tmdbApi.getTopRatedMovies(Constants.API_KEY);

        topRatedMoviesResponseCall.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {

                if(response.body()!=null){

                    progressDialog2.dismiss();
                    Log.d("Tag-",response.body().toString());

                    TopRatedMoviesResponse topRatedMoviesResponse=response.body();

                    List<TopRatedResult>  resultsBeanList=topRatedMoviesResponse.getResults();

                    Log.d("Check",resultsBeanList.toString());

                    for(TopRatedResult resultsBean:resultsBeanList){

                       String title=resultsBean.getTitle();
                       String imageUrl=resultsBean.getPosterPath();
                       String description=resultsBean.getOverview();
                       String releaseDate=resultsBean.getReleaseDate();
                       String ratings=resultsBean.getPopularity().toString();
                       Log.d("values",title + " "+imageUrl );

                       TopRatedMovie topRatedMovie=new TopRatedMovie(imageUrl,title,description,releaseDate,ratings);

                       topRatedMovieList.add(topRatedMovie);

                    }

                    topRatedMovieAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {

            }
        });
    }
}
