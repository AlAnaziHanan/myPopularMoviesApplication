package com.example.mypopularmoviesapplication;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mypopularmoviesapplication.Favorite.Favorites;
import com.example.mypopularmoviesapplication.Favorite.favorite_DAO;

@SuppressWarnings("SameReturnValue")
@Database ( entities = {Favorites.class}, version = 1, exportSchema = false)
public abstract class moviesDB extends RoomDatabase {

    private static final String LOG_TAG = moviesDB.class.getSimpleName ();

    private static  moviesDB sInstance;

    public static favorite_DAO favoriteDao () {
        return null;
    }

    public static synchronized moviesDB getsInstance ( Context  con) {
        if (sInstance == null) {
            synchronized (moviesDB.class){
                if(sInstance==null){
                    Log.d (LOG_TAG, "Create New Database");
                    sInstance = Room.databaseBuilder (con.getApplicationContext () ,
                            moviesDB.class, "Movie Database")
                            .fallbackToDestructiveMigration ()
                            .build ();
                }
            }
        }
        Log.d(LOG_TAG, "Get Database");
        return sInstance;
    }
    public abstract favorite_DAO favorite_dao();

}

