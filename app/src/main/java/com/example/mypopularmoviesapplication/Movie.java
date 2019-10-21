package com.example.mypopularmoviesapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

class Movie implements Parcelable {


    private String title;
    private float vote_average;
    private String posterPath;
    private String date;
    private String overview;
    private int popularity;


    /*Constructor*/
    private Movie ( Parcel in ) {
        title = in.readString ();
        vote_average = in.readFloat ();
        posterPath = in.readString ();
        date = in.readString ();
        overview = in.readString ();
        popularity = in.readInt ();
    }
    public Movie(){

    }
    public Movie ( String title , float vote_average , String posterPath , String date , String overview , int popularity ) {
        this.title = title;
        this.vote_average = vote_average;
        this.posterPath = posterPath;
        this.date = date;
        this.overview = overview;
        this.popularity = popularity;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie> () {
        @Override
        public Movie createFromParcel ( Parcel in ) {
            return new Movie ( in );
        }

        @Override
        public Movie[] newArray ( int size ) {
            return new Movie[size];
        }
    };

    /*Setter & Getter */
    String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    float getVote_average () {
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

    private int getPopularity () {
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
        float s2=o2.getVote_average ();

        return (int) (s1-s2);
    };

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( Parcel dest , int flags ) {
        dest.writeString ( title );
        dest.writeFloat ( vote_average );
        dest.writeString ( posterPath );
        dest.writeString ( date );
        dest.writeString ( overview );
        dest.writeInt ( popularity );
    }
}
