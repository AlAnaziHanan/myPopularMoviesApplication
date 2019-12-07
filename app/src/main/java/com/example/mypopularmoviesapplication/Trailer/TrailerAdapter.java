package com.example.mypopularmoviesapplication.Trailer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypopularmoviesapplication.R;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter <TrailerAdapter.TrailerViewHolder>{
    private final Context context;
    private final List<Trailer> trailerList;
    public TrailerAdapter ( Context context , List<Trailer> trailerList ) {
        this.context=context;
        this.trailerList=trailerList;
    }

    @NonNull
    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view =LayoutInflater.from ( parent.getContext () )
                .inflate ( R.layout.trailer_card, parent, false );
        return  new TrailerViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull TrailerAdapter.TrailerViewHolder holder , int position ) {
        holder.title.setText(trailerList.get ( position ).getName ());
    }

    @Override
    public int getItemCount () {
        return trailerList.size ();
    }
    public class TrailerViewHolder extends  RecyclerView.ViewHolder{
        final TextView title;
        final ImageView image;
        TrailerViewHolder ( View view ){
            super(view);
            title =view.findViewById ( R.id.videoTitle );
            image =view.findViewById ( R.id.imageview );

            view.setOnClickListener ( v -> {
                int position = getAdapterPosition ();
                if(position!=RecyclerView.NO_POSITION){
                    Trailer clickedItem = trailerList.get ( position );
                    String trailerId = trailerList.get ( position ).getKey ();
                    Intent intent = new Intent ( Intent.ACTION_VIEW, Uri.parse ("vnd.youtube:"+trailerId) );
                    intent.putExtra ( "VIDEO_ID", trailerId );

                    context.startActivity ( intent );
                    Toast.makeText (  view.getContext (), clickedItem.getName (), Toast.LENGTH_SHORT).show ();
                }
            } );

        }

    }
}
