package com.example.mypopularmoviesapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL="https://api.themoviedb.org/3/";

    //return instance
    public static  Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder ()
                    .baseUrl ( BASE_URL )
                    .addConverterFactory ( GsonConverterFactory.create () )
                    .build ();
        }
        return retrofit;
    }
}
