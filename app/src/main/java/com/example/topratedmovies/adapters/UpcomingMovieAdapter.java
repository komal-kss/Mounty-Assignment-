package com.example.topratedmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topratedmovies.R;
import com.example.topratedmovies.UpcomingMovieDetails;
import com.example.topratedmovies.model.TopRatedMovie;
import com.example.topratedmovies.model.UpcomingMovieModel;

import java.net.BindException;
import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMovieHolder> {

    private Context context;
    private List<UpcomingMovieModel> upcomingMovieModelList;

    public UpcomingMovieAdapter(Context context, List<UpcomingMovieModel> upcomingMovieModelList) {
        this.context = context;
        this.upcomingMovieModelList = upcomingMovieModelList;
    }

    @NonNull
    @Override
    public UpcomingMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_upcoming_movie,parent,false);
        return new UpcomingMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMovieHolder holder, int position) {

        holder.title.setText(upcomingMovieModelList.get(position).getTitle());

        //call the method to set the image
        holder.onImagePoster(upcomingMovieModelList.get(position).getImageUrl());
        final String title=upcomingMovieModelList.get(position).getTitle();
        final String desc=upcomingMovieModelList.get(position).getDescription();
        final String poster=upcomingMovieModelList.get(position).getImageUrl();
        final String rating=upcomingMovieModelList.get(position).getRating();
        final String release_date=upcomingMovieModelList.get(position).getReleaseDate();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieIntent=new Intent(context, UpcomingMovieDetails.class);
                movieIntent.putExtra("title",title);
                movieIntent.putExtra("desc",desc);
                movieIntent.putExtra("poster",poster);
                movieIntent.putExtra("rating",rating);
                movieIntent.putExtra("release_date",release_date);

                context.startActivity(movieIntent);

            }
        });
        Log.d("inside onBIndHolder",upcomingMovieModelList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return  upcomingMovieModelList.size();
    }

    public class UpcomingMovieHolder  extends RecyclerView.ViewHolder{
        TextView title;
        TextView rating;
        ImageView movieImage;
        CardView cardView;
        public UpcomingMovieHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.upcoming_titleId);
            rating=itemView.findViewById(R.id.upcoming_ratingId);
            movieImage=itemView.findViewById(R.id.upcoming_imageId);
            cardView=itemView.findViewById(R.id.cardView);


        }

        public void onImagePoster(String imagePath){

            //placeholder image
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+imagePath).into(movieImage);
        }
    }
}

