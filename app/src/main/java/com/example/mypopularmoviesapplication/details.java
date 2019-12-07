package com.example.mypopularmoviesapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypopularmoviesapplication.Favorite.Favorites;
import com.example.mypopularmoviesapplication.Trailer.Trailer;
import com.example.mypopularmoviesapplication.Trailer.TrailerAdapter;
import com.example.mypopularmoviesapplication.Trailer.TrailerRes;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class details extends AppCompatActivity {

    @BindView( R.id.plotIv )
    TextView plot;
    @BindView ( R.id.titleIv )
    TextView title;
    @BindView ( R.id.imageView )
    ImageView poster;
    @BindView ( R.id.voteIv )
    TextView vote;
    @BindView ( R.id.dateIv )
    TextView release_date;
    @BindView ( R.id.reviewIV )
    TextView review;
    private int movie_id;
    private Movie movie;
    private  TrailerAdapter trailerAdapter;
    private List<Trailer> trailers;
    private moviesDB moviesDb;
    List<Favorites> favorites=new ArrayList<> (  );
    int movieID;

    @RequiresApi(api=Build.VERSION_CODES.N)

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.details);

        Intent intent = getIntent ();
        movie = getIntent().getParcelableExtra("Movie");
        //fav helper
        moviesDb=moviesDB.getsInstance ( getApplicationContext () );

        ButterKnife.bind ( this );

        this.plot=  findViewById ( R.id.plotIv );
        this.title=  findViewById ( R.id.titleIv );
        this.poster= findViewById ( R.id.imageView );
        this.release_date=  findViewById ( R.id.dateIv );
        this.vote=  findViewById ( R.id.voteIv );
        poster.setImageResource ( intent.getIntExtra ( "image",0 ) );

        int receivedImage=intent.getIntExtra ( "image",0 );
        poster.setImageResource ( receivedImage );



        if(Objects.requireNonNull ( movie ).getPosterPath ()!= null && movie.getPosterPath ().length ()>0){
            //load using picasso
            String posterPath = "https://image.tmdb.org/t/p/w500"+ movie.getPosterPath ();
            Picasso.get ().load ( posterPath ).placeholder ( R.drawable.poster_placeholder).into ( poster );
        }else {
            Toast.makeText ( getApplicationContext (), "Empty Image URL", Toast.LENGTH_LONG ).show ();
            Picasso.get ().load ( R.drawable.poster_placeholder ).into ( poster );
        }
        title.setText ( Objects.requireNonNull ( movie ).getTitle () );
        vote.setText ( (int) movie.getVote_average () );
        plot.setText ( movie.getOverview () );
        movie_id=movie.getMovieId ();
        String date=movie.getDate ();

        //Favorite
        MaterialFavoriteButton materialFavoriteButtonFav =findViewById ( R.id.favorite_button );

        materialFavoriteButtonFav.setOnFavoriteChangeListener (
                new MaterialFavoriteButton.OnFavoriteChangeListener () {
                    @Override
                    public void onFavoriteChanged ( MaterialFavoriteButton buttonView , boolean favorite ) {
                        if (favorite){
                            SharedPreferences.Editor editor = getSharedPreferences ( "com.example.mypopularmoviesapplication.details", MODE_PRIVATE ).edit ();
                            editor.putBoolean ( "Favorite Added", true );
                            editor.commit ();
                            saveFavorite ();
                            Snackbar.make ( buttonView, "Movie Added to Favorite",
                                    Snackbar.LENGTH_SHORT).show ();
                        }else{
                            SharedPreferences.Editor editor = getSharedPreferences ( "com.example.mypopularmoviesapplication.details", MODE_PRIVATE ).edit ();
                            editor.putBoolean ( "Favorite Removed" ,true);
                            editor.commit ();
                            Snackbar.make ( buttonView, "Movie Removed from Favorite", Snackbar.LENGTH_SHORT ).show ();
                        }
                    }
                }

        );

        //trailer & Reviews
        initView ();

        //back Button
        Objects.requireNonNull ( getSupportActionBar () ).setDisplayHomeAsUpEnabled ( true );
    }

    private void saveFavorite (){
        float avg_rate = movie.getVote_average ();
        final Favorites favorites = new Favorites ( movie_id ,  title ,  poster , plot ,   avg_rate , release_date  ) ;

        favoriteExec.getInstance().diskIO().execute ( () -> moviesDB.favoriteDao ().InsertFavorite ( favorites ) );
    }
    private void deleteFavorite (){
        favoriteExec.getInstance().diskIO().execute ( () -> moviesDB.favoriteDao ().deleteFavoriteWithId ( movie_id ) );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ().inflate ( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if(item.getItemId ()==android.R.id.home){
            onBackPressed ();
        }
        return super.onOptionsItemSelected ( item );
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed ();
    }

    private RecyclerView rv;

    private void initView(){

        trailerAdapter=new TrailerAdapter ( this , trailers );
        rv =findViewById ( R.id. videoRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager ( getApplicationContext () );
        rv.setLayoutManager ( mLayoutManager );
        rv.setAdapter ( trailerAdapter );
        trailerAdapter.notifyDataSetChanged();
        load();
        load_Review();

    }
    int movieId=Objects.requireNonNull ( getIntent ().getExtras () ).getInt ( "id" );
    private void load () {

        try {
            if (BuildConfig.DATABASE_NAME.isEmpty ()) {
                Toast.makeText ( getApplicationContext () , "Please Get you Key from themoviedb.org" , Toast.LENGTH_SHORT ).show ();
                return;
            }
            GetDataService APIService=RetrofitClientInstance.getRetrofit ().create ( GetDataService.class );
            Call<TrailerRes> call=APIService.getVideos ( movieId , BuildConfig.DATABASE_NAME );
            call.enqueue ( new Callback<TrailerRes> () {
                @Override
                public void onResponse ( Call<TrailerRes> call , Response<TrailerRes> response ) {
                    if (response.isSuccessful ()) {
                        if(response.body ()!=null){
                            List<Trailer> trailers=response.body ().getTrailer ();
                            rv.setAdapter ( new TrailerAdapter ( getApplicationContext () , trailers ) );
                            rv.smoothScrollToPosition ( 0 );
                        }
                    }
                }
                @Override
                public void onFailure ( Call<TrailerRes> call , Throwable throwable ) {
                    Log.d ( "Error" , Objects.requireNonNull ( throwable.getMessage () ) );
                    Toast.makeText ( details.this , "Error Fetching Trailer Data" , Toast.LENGTH_SHORT ).show ();
                }
            } );
        } catch (Exception e) {
            e.printStackTrace ();
            Toast.makeText ( this, e.toString (), Toast.LENGTH_SHORT ).show ();
        }

    }
    //TODO
    private void load_Review(){

        try {
            if (BuildConfig.DATABASE_NAME.isEmpty ()) {
                Toast.makeText ( getApplicationContext () , "Please Get you Key from themoviedb.org" , Toast.LENGTH_SHORT ).show ();
                return;
            }
            GetDataService APIService=RetrofitClientInstance.getRetrofit ().create ( GetDataService.class );
            Call<TrailerRes> call=APIService.getVideos ( movieId , BuildConfig.DATABASE_NAME );
            call.enqueue ( new Callback<TrailerRes> () {
                @Override
                public void onResponse ( Call<TrailerRes> call , Response<TrailerRes> response ) {
                    if (response.isSuccessful ()) {
                        if(response.body ()!=null){
                            ///
                            List<Trailer> trailers=response.body ().getTrailer ();
                            rv.setAdapter ( new TrailerAdapter ( getApplicationContext () , trailers ) );
                            rv.smoothScrollToPosition ( 0 );
                        }
                    }
                }
                @Override
                public void onFailure ( Call<TrailerRes> call , Throwable throwable ) {
                    Log.d ( "Error" , Objects.requireNonNull ( throwable.getMessage () ) );
                    Toast.makeText ( details.this , "Error Fetching Trailer Data" , Toast.LENGTH_SHORT ).show ();
                }
            } );
        } catch (Exception e) {
            e.printStackTrace ();
            Toast.makeText ( this, e.toString (), Toast.LENGTH_SHORT ).show ();
        }
    }
}