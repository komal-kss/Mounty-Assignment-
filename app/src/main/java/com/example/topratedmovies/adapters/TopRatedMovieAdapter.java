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

import java.net.BindException;
import java.util.List;

public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedMovieHolder> {

    private Context context;
    private List<TopRatedMovie> topRatedMovieList;

    public TopRatedMovieAdapter(Context context, List<TopRatedMovie> topRatedMovieList) {
        this.context = context;
        this.topRatedMovieList = topRatedMovieList;
    }

    @NonNull
    @Override
    public TopRatedMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_top_rated_movie,parent,false);
        return new TopRatedMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedMovieHolder holder, int position) {

        holder.title.setText(topRatedMovieList.get(position).getTitle());
        //call the method to set the image
        holder.onImagePoster(topRatedMovieList.get(position).getImageUrl());

        final String title=topRatedMovieList.get(position).getTitle();
        final String desc=topRatedMovieList.get(position).getDescription();
        final String poster=topRatedMovieList.get(position).getImageUrl();
        final String rating=topRatedMovieList.get(position).getRatings();
        final String release_date=topRatedMovieList.get(position).getReleaseDate();
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

        Log.d("inside onBIndHolder",topRatedMovieList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return  topRatedMovieList.size();
    }

    public class TopRatedMovieHolder  extends RecyclerView.ViewHolder{
        TextView title;
        TextView rating;
        ImageView movieImage;
        CardView cardView;
        public TopRatedMovieHolder(@NonNull View itemView) {
            super(itemView);

             title=itemView.findViewById(R.id.top_rated_titleId);
             rating=itemView.findViewById(R.id.top_rated_ratingId);
             movieImage=itemView.findViewById(R.id.top_rated_imageId);
             cardView=itemView.findViewById(R.id.top_rated_movie_cardId);

        }

        public void onImagePoster(String imagePath){

            //placeholder image
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+imagePath).into(movieImage);
        }
    }
}
