package com.example.mypopularmoviesapplication.Review;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mypopularmoviesapplication.GetDataService;
import com.example.mypopularmoviesapplication.R;
import com.example.mypopularmoviesapplication.RetrofitClientInstance;

import retrofit2.Call;


/**
 * A simple {@link Fragment} subclass.
 */
class reviewFragment extends Fragment {

    /*Add your key*/
    private final String KEY="a1929f608371156c06e3be63aca37892";


    public reviewFragment () {
        // Required empty public constructor
    }
    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_review, container,false);
        int id =getId ();
        Context context = getContext ();
        review review = getIntent().getParcelableExtra("Movie");
        GetDataService APIService = RetrofitClientInstance.getRetrofit ().create ( GetDataService.class );
        Call<review> call = APIService.getReviews (id , KEY);
        return view;
    }




}
