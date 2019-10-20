package com.example.mypopularmoviesapplication;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

class Movie {

    @SerializedName( "title" )
    private String title;
    @SerializedName ( "vote_average" )
    private int vote_average;
    @SerializedName ( "poster_path" )
    private String posterPath;
    @SerializedName ( "release_date" )
    private String date;
    @SerializedName ( "overview" )
    private String overview;
    @SerializedName ( "popularity" )
    private int popularity;


    /*Constructor*/
    public Movie ( String title , int vote_average , String posterPath , String date , String overview , int popularity ) {
        this.title = title;
        this.vote_average = vote_average;
        this.posterPath = posterPath;
        this.date = date;
        this.overview = overview;
        this.popularity = popularity;
    }

    /*Setter & Getter */
    String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    int getVote_average () {
        return vote_average;
    }

    public void setVote_average ( int vote_average ) {
        this.vote_average = vote_average;
    }

    String getPosterPath () {
        return posterPath;
    }

    public void setPosterPath ( String posterPath ) {
        this.posterPath = posterPath;
    }

    String getDate () {
        return date;
    }

    public void setDate ( String date ) {
        this.date = date;
    }

    String getOverview () {
        return overview;
    }

    public void setOverview ( String overview ) {
        this.overview = overview;
    }

    int getPopularity () {
        return popularity;
    }

    public void setPopularity ( int popularity ) {
        this.popularity = popularity;
    }

    static Comparator<Movie> sortPopData= ( o1 , o2 ) -> {
        int s1 = o1.getPopularity ();
        int s2=o2.getPopularity ();

        return s1-s2;
    };
    static Comparator<Movie> sortVoteData= ( o1 , o2 ) -> {
        double s1 = o1.getVote_average ();
        int s2=o2.getVote_average ();

        return (int) (s1-s2);
    };
}
