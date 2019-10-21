package com.example.mypopularmoviesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar myProgressBar;
    /*Add your key*/
    private final String key="a1929f608371156c06e3be63aca37892";

    private void populateGridView( List<Movie> moviesList ){
        this.moviesList = moviesList;
        GridView myGridView = findViewById ( R.id.moviesGrid );
        GridViewAdapter myAdapter = new GridViewAdapter ( moviesList , this );
        myGridView.setAdapter ( myAdapter );
        myGridView.setOnItemClickListener ( ( parent , view , position , id ) -> {

            Movie movie = new Movie();
            Intent intent = new Intent ( MainActivity.this, details.class );
            intent.putExtra ( "Movie",movie );
            startActivity ( intent );
        } );
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        final ProgressBar myProgressBar = findViewById ( R.id.progressBar );
        myProgressBar.setIndeterminate ( true );
        myProgressBar.setVisibility ( View.VISIBLE );

        GetPopService APIService = RetrofitClientInstance.getRetrofit ().create ( GetPopService.class );

        Call<Model> call = APIService.getPopMovies (key);
        call.enqueue ( new Callback<Model> () {
            @Override
            public void onResponse ( Call<Model> call , Response<Model> response ) {
                myProgressBar.setVisibility ( View.GONE );
                List<Movie> result = response.body ().getResults();
                populateGridView ( result);
            }

            @Override
            public void onFailure ( Call<Model> call , Throwable throwable ) {
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
    private List<Movie> moviesList;
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {

        switch (item.getItemId ()){
            case R.id.popularMovies:
                if(moviesList!=null) {
                    sortByPop();
                    return true;
                }

            case R.id.topRatedMovies:
                if(moviesList!=null) {
                   sortByVote ();
                    return true;
                }
        }
        return super.onOptionsItemSelected ( item );
    }

    private void sortByPop (){
        GetPopService APIService = RetrofitClientInstance.getRetrofit ().create ( GetPopService.class );
        Call<Model> call = APIService.getPopMovies (key);
        call.enqueue ( new Callback<Model> () {
            @Override
            public void onResponse ( Call<Model> call , Response<Model> response ) {
                myProgressBar.setVisibility ( View.GONE );
                List<Movie> result = response.body ().getResults();
                populateGridView ( result);
            }

            @Override
            public void onFailure ( Call<Model> call , Throwable throwable ) {
                myProgressBar.setVisibility ( View.GONE );
                Toast.makeText ( MainActivity.this,throwable.getMessage (),Toast.LENGTH_LONG ).show ();
            }
        } );
    }
    private void sortByVote (){
        GetVoteService APIService = RetrofitClientInstance.getRetrofit ().create ( GetVoteService.class );
        Call<Model> call = APIService.getVoteMovies (key);
        call.enqueue ( new Callback<Model> () {
            @Override
            public void onResponse ( Call<Model> call , Response<Model> response ) {

                myProgressBar.setVisibility ( View.GONE );
                List<Movie> result = response.body ().getResults();
                populateGridView ( result);
            }

            @Override
            public void onFailure ( Call<Model> call , Throwable throwable ) {
                myProgressBar.setVisibility ( View.GONE );
                Toast.makeText ( MainActivity.this,throwable.getMessage (),Toast.LENGTH_LONG ).show ();
            }
        } );
    }
}
