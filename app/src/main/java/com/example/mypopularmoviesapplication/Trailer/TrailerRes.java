package com.example.mypopularmoviesapplication.Trailer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerRes {

    @SerializedName ( "id" )
    private int trailerId;

    @SerializedName ( "result" )
    private List<Trailer> trailer;


    public int getTrailerId () {
        return trailerId;
    }

    public void setTrailerId ( int trailerId ) {
        this.trailerId=trailerId;
    }

    public List<Trailer> getTrailer () {
        return trailer;
    }

    public void setTrailer ( List<Trailer> trailer ) {
        this.trailer=trailer;
    }
}
