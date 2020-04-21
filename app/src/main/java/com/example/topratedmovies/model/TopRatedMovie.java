package com.example.topratedmovies.model;

public class TopRatedMovie {

    String imageUrl;
    String title;
    String description;
    String ratings;
    String releaseDate;


    public TopRatedMovie() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public TopRatedMovie(String imageUrl, String title, String description, String releaseDate, String ratings) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description=description;
        this.releaseDate=releaseDate;
        this.ratings=ratings;

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

}
