package com.example.mypopularmoviesapplication.Favorite;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*model class for table columns*/

//table
@Entity(tableName = "favoriteTable")
public class Favorites {

    /*table columns*/
    //id
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="movieid")
    private int movieid;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="poster_Path")
    private String posterPath;

    @ColumnInfo(name="overview")
    private String overview;

    @ColumnInfo(name="user_Reviews")
    private String reviews;

    @ColumnInfo(name="vote_average")
    private float vote_average;

    @ColumnInfo(name="date")
    private String date;

    @ColumnInfo(name="popularity")
    private int popularity;


    public Favorites ( int id , int movieid , String title , String posterPath , String overview , String reviews , float vote_average , String date , int popularity ) {
        this.id=id;
        this.movieid=movieid;
        this.title=title;
        this.posterPath=posterPath;
        this.overview=overview;
        this.reviews=reviews;
        this.vote_average=vote_average;
        this.date=date;
        this.popularity=popularity;
    }


    public Favorites ( int movie_id , TextView title , ImageView poster , TextView plot , float avg_rate , TextView release_date ) {
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public int getMovieid () {
        return movieid;
    }

    public void setMovieid ( int movieid ) {
        this.movieid = movieid;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title=title;
    }

    public String getPosterPath () {
        return posterPath;
    }

    public void setPosterPath ( String posterPath ) {
        this.posterPath=posterPath;
    }

    public String getOverview () {
        return overview;
    }

    public void setOverview ( String overview ) {
        this.overview = overview;
    }

    public String getReviews () {
        return reviews;
    }

    public void setReviews ( String reviews ) {
        this.reviews = reviews;
    }

    public float getVote_average () {
        return vote_average;
    }

    public void setVote_average ( float vote_average ) {
        this.vote_average = vote_average;
    }

    public String getDate () {
        return date;
    }

    public void setDate ( String date ) {
        this.date = date;
    }

    public int getPopularity () {
        return popularity;
    }

    public void setPopularity ( int popularity ) {
        this.popularity = popularity;
    }


}
