package com.example.mypopularmoviesapplication.Favorite;

import android.provider.BaseColumns;

public  class favoriteCont {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME="favorite";
        public static final String COLUMN_MOVIEID="movieid";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_USERRATING="user_rating";
        public static final String COLUMN_POSTER_PATH="poster_path";
        public static final String COLUMN_PLOT_SYNOPSIS="overview";
    }
}
