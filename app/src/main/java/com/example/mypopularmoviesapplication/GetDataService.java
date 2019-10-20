package com.example.mypopularmoviesapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface GetDataService {
    /*second part of url
    */
    @GET("/movie/popular")
    Call<List<Movie>> getMovies( @Query  ( "api_key" ) String key);
}
