package com.example.mypopularmoviesapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Model {

    @SerializedName("results")
    @Expose
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults( List<Movie> results) {
        this.results = results;
    }


}
