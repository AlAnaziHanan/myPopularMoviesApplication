package com.example.mypopularmoviesapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface GetPopService {
    /*second part of url
     */
    @GET("movie/popular/")
    Call<Model> getPopMovies( @Query  ( "api_key" ) String key);

}

interface GetVoteService {
    /*second part of url
     */
    @GET("movie/top_rated/")
    Call<Model> getVoteMovies( @Query  ( "api_key" ) String key);
}