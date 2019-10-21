package com.example.mypopularmoviesapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

class GridViewAdapter extends BaseAdapter {

    private final List<Movie> movies;
    private final Context context;

    /*Constructor*/
    public GridViewAdapter ( List<Movie> movies , Context context ) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount () {
        if(movies!=null){
            return movies.size ();}
        else return 0;
    }

    @Override
    public Object getItem ( int position ) {
        return movies.get ( position );
    }

    @Override
    public long getItemId ( int position ) {
        return position;
    }

    @Override
    public View getView ( int position , View view , ViewGroup parent ) {
        if (view==null){
            //inflate
            view = LayoutInflater.from ( context ).inflate ( R.layout.model,parent,false );
        }

        ImageView movieImageView = view.findViewById ( R.id.imageView );

        final Movie thisMovie = movies.get ( position );

        if(thisMovie.getPosterPath ()!= null && thisMovie.getPosterPath ().length ()>0){
            //load using picasso
            Picasso.get ().load ( thisMovie.getPosterPath () ).placeholder ( R.drawable.poster_placeholder).into ( movieImageView );
        }else {
            Toast.makeText ( context, "Empty Image URL", Toast.LENGTH_LONG ).show ();
            Picasso.get ().load ( R.drawable.poster_placeholder ).into ( movieImageView );
        }

        return view;
    }
}
