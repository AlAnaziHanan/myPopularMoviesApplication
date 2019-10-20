package com.example.mypopularmoviesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressBar myProgressBar;

    private void populateGridView( List<Movie> moviesList ){
        this.moviesList = moviesList;
        GridView myGridView = findViewById ( R.id.moviesGrid );
        GridViewAdapter myAdapter = new GridViewAdapter ( moviesList , this );
        myGridView.setAdapter ( myAdapter );

        myGridView.setOnItemClickListener ( new AdapterView.OnItemClickListener (  ) {

            @Override
            public void onItemClick ( AdapterView<?> parent , View view , int position , long id ) {
                Intent intent = new Intent ( MainActivity.this, details.class );
                intent.putExtra ( "", moviesList.indexOf ( this ) );
                startActivity ( intent );
            }

        });
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        final ProgressBar myProgressBar = findViewById ( R.id.progressBar );
        myProgressBar.setIndeterminate ( true );
        myProgressBar.setVisibility ( View.VISIBLE );


        GetDataService APIService = RetrofitClientInstance.getRetrofit ().create ( GetDataService.class );

        /*Add your key*/
        Call<List<Movie>> call = APIService.getMovies ("a1929f608371156c06e3be63aca37892");
        call.enqueue ( new Callback<List<Movie>> () {
            @Override
            public void onResponse ( Call<List<Movie>> call , Response<List<Movie>> response ) {

                myProgressBar.setVisibility ( View.GONE );
                populateGridView ( response.body () );
            }

            @Override
            public void onFailure ( Call<List<Movie>> call , Throwable throwable ) {
                myProgressBar.setVisibility ( View.GONE );
                Toast.makeText ( MainActivity.this,throwable.getMessage (),Toast.LENGTH_LONG ).show ();
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.menu,menu );
        return true;
    }
    List<Movie> moviesList;
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {


        switch (item.getItemId ()){
            case R.id.popularMovies:
                if(moviesList!=null) {
                    Collections.sort ( moviesList , Movie.sortPopData );
                    return true;
                }
                else populateGridView ( moviesList );
            case R.id.topRatedMovies:
                if(moviesList!=null) {
                    Collections.sort(moviesList, Movie.sortVoteData);
                    return true;}
                else populateGridView ( moviesList );
        }
        return super.onOptionsItemSelected ( item );
    }


}
