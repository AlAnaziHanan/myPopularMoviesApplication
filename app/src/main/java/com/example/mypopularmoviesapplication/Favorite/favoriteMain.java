package com.example.mypopularmoviesapplication.Favorite;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mypopularmoviesapplication.Movie;
import com.example.mypopularmoviesapplication.moviesDB;

import java.util.List;

public class favoriteMain extends AndroidViewModel {
    private static  final String TAG = favoriteMain.class.getSimpleName ();
    private final LiveData<List<Favorites>> fav;
    private favorite_DAO favoriteDao;

    public  favoriteMain ( Application application ){
        super (application);
        moviesDB moviesDB =com.example.mypopularmoviesapplication.moviesDB.getsInstance ( application );
        Log.d (TAG,"Retrieving from DataBase");
        favoriteDao = com.example.mypopularmoviesapplication.moviesDB.favoriteDao ();
        fav = favoriteDao.loadAllFavorite ();
    }

    public Movie getAllFav(){
        return fav;
    }
    public void insert(Favorites fav){
        new InsertFavAsyncTask ( favoriteDao ).execute ( fav );
    }
    public void update(Favorites fav){
        new UpdateFavAsyncTask ( favoriteDao ).execute ( fav );
    }
    public void delete(Favorites fav){
        new DeleteFavAsyncTask ( favoriteDao ).execute ( fav );
    }
    public void deleteAll(){ new DeleteAllFavAsyncTask ( favoriteDao ).execute ( ); }


    private static class InsertFavAsyncTask  extends AsyncTask<Favorites, Void, Void>{
        private favorite_DAO favoriteDao;

        private InsertFavAsyncTask(favorite_DAO favoriteDao){
            this.favoriteDao = favoriteDao;
        }
        @Override
        protected Void doInBackground ( Favorites... favorites ) {
            favoriteDao.InsertFavorite ( favorites[0] );
            return null;
        }
    }
    private static class UpdateFavAsyncTask  extends AsyncTask<Favorites, Void, Void>{
        private favorite_DAO favoriteDao;

        private UpdateFavAsyncTask(favorite_DAO favoriteDao){
            this.favoriteDao = favoriteDao;
        }
        @Override
        protected Void doInBackground ( Favorites... favorites ) {
            favoriteDao.updateFavorite ( favorites[0] );
            return null;
        }
    }
    private static class DeleteFavAsyncTask  extends AsyncTask<Favorites, Void, Void>{
        private favorite_DAO favoriteDao;

        private DeleteFavAsyncTask(favorite_DAO favoriteDao){
            this.favoriteDao = favoriteDao;
        }
        @Override
        protected Void doInBackground ( Favorites... favorites ) {
            favoriteDao.deleteFavorite ( favorites[0] );
            return null;
        }
    }
    private static class DeleteAllFavAsyncTask  extends AsyncTask<Void, Void, Void>{
        private favorite_DAO favoriteDao;

        private DeleteAllFavAsyncTask(favorite_DAO favoriteDao){
            this.favoriteDao = favoriteDao;
        }
        @Override
        protected Void doInBackground ( Void... voids ) {
            favoriteDao.deleteAllFavorite (  );
            return null;
        }
    }
}
