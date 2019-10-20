package com.example.mypopularmoviesapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("UnusedAssignment")
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

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.details);

        Intent intent = getIntent ();
        Movie mIntent = (Movie) intent.getSerializableExtra ( "detail" );

        ButterKnife.bind ( this );

        this.plot=  findViewById ( R.id.plotIv );
        this.title=  findViewById ( R.id.titleIv );
        this.poster= findViewById ( R.id.imageView );
        this.release_date=  findViewById ( R.id.dateIv );
        this.vote=  findViewById ( R.id.voteIv );

        poster.setImageResource ( intent.getIntExtra ( "image",0 ) );


        int receivedImage=intent.getIntExtra ( "image",0 );
        poster.setImageResource ( receivedImage );
        title.setText ( Objects.requireNonNull ( mIntent ).getTitle () );
        vote.setText ( mIntent.getVote_average () );
        plot.setText ( mIntent.getOverview () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd MMM, yyyy" );
        SimpleDateFormat dateInput = new SimpleDateFormat ( "yyyy-MM-dd" );
        String date;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                date = dateFormat.format ( Objects.requireNonNull ( dateInput.parse ( mIntent.getDate () ) ) );
            }
        } catch (ParseException e) {
            e.printStackTrace ();
            date = mIntent.getDate ();
        }
        release_date.setText ( mIntent.getDate () );
        //back Button
        Objects.requireNonNull ( getSupportActionBar () ).setDisplayHomeAsUpEnabled ( true );
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
}

