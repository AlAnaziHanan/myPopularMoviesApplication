package com.example.mypopularmoviesapplication.Favorite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*Data Access Objects*/
@Dao
public
interface favorite_DAO {
    //Queries and LiveData
    @Query( "SELECT * FROM favoriteTable" )//table created in Favorites class
    LiveData<List<Favorites>> loadAllFavorite ();

    @Query ( "SELECT * FROM favoriteTable WHERE title=:title" )
    LiveData<List<Favorites>> loadAll ( String title );

    @Insert
    void InsertFavorite ( Favorites favorite );

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite ( Favorites favorites );

    @Delete
    void deleteAllFavorite (  );

    @Query ( "DELETE FROM favoriteTable WHERE movieid=:movieID" )
    void deleteFavoriteWithId ( int movieID );

    @Query ( "DELETE FROM favoriteTable " )
    void deleteFavorite ( Favorites favorites );

    @Query ( "SELECT * FROM favoriteTable WHERE id=:id" )
    LiveData<List<Favorites>> loadFavoriteById ( int id );

}
