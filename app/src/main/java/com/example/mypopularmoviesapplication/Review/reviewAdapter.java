package com.example.mypopularmoviesapplication.Review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypopularmoviesapplication.R;

import java.util.List;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.holder> {
    private final List<review> review;

    /*Constructor*/
    public reviewAdapter ( List<com.example.mypopularmoviesapplication.Review.review> review ) {
        this.review=review;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.review_item,parent,false );
        return new holder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull holder holder , int position ) {
        holder.author.setText (review.get ( position ).getAuthor () );
        holder.content.setText (review.get ( position ).getReviewContent () );
    }

    @Override
    public int getItemCount () {
        return review.size ();
    }

    public class holder extends RecyclerView.ViewHolder {
        final TextView author;
        final TextView content;

        holder ( @NonNull View itemView ) {
            super ( itemView );
            author=itemView.findViewById ( R.id.author );
            content=itemView.findViewById ( R.id.reviewIV );

        }
    }


}