package com.example.mypopularmoviesapplication.Favorite;

import android.provider.BaseColumns;

class favoriteCont {
    private static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME="favorite";
        public static final String COLUMN_MOVIEID="movieid";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_USER_RATING="user_rating";
        public static final String COLUMN_POSTER_PATH="poster_path";
        public static final String COLUMN_PLOT_SYNOPSIS="overview";
    }
}
