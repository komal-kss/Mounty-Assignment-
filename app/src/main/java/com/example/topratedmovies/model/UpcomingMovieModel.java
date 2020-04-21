package com.example.topratedmovies.model;

public class UpcomingMovieModel {

    String imageUrl;
    String title;
    String description;
    String rating;
    String releaseDate;

    public UpcomingMovieModel() {
    }

    public UpcomingMovieModel(String imageUrl, String title, String description,String rating,String releaseDate) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description=description;
        this.rating=rating;
        this.releaseDate=releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription(){return description;}
    public void setDescription(String desc){this.description=desc;}
    public String getRating(){return rating;}
    public void setRating(String ratings){this.rating=ratings;}
    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(String releaseDate){this.releaseDate=releaseDate;}

}
