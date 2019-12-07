package com.example.mypopularmoviesapplication;


import com.example.mypopularmoviesapplication.Review.review;
import com.example.mypopularmoviesapplication.Trailer.TrailerRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    /*second part of url*/
    @GET("movie/popular/")
    Call<Model> getPopMovies( @Query  ( "api_key" ) String KEY);

    @GET("movie/top_rated/")
    Call<Model> getVoteMovies( @Query  ( "api_key" ) String KEY);

    @GET("movie/{movie_id}/videos/")
    Call<TrailerRes> getVideos( @Path( "movie_id" ) int id, @Query ( "api_key" )String KEY);

    @GET("movie/{movie_id}/reviews/")
    Call<review> getReviews( @Path( "movie_id" ) int id, @Query ( "api_key" )String KEY);
}

