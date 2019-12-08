package com.example.mypopularmoviesapplication.Review;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class review implements Parcelable {
    @SerializedName ( "author" )
    private String author;
    @SerializedName ( "content" )
    private String reviewContent;
    @SerializedName ( "id" )
    private String id;
    @SerializedName ( "url" )
    private String url;

    public review ( String author , String reviewContent , String id , String url ) {
        super();
        this.author=author;
        this.reviewContent=reviewContent;
        this.id=id;
        this.url=url;
    }

    public review ( String author , String reviewContent , String url ) {
        this.author=author;
        this.reviewContent=reviewContent;
        this.url=url;
    }


    public static final Creator<review> CREATOR=new Creator<review> () {
        @Override
        public review createFromParcel ( Parcel in ) {
            return new review ( in );
        }

        @Override
        public review[] newArray ( int size ) {
            return new review[size];
        }
    };

    private review ( Parcel in ){
        this.author=((String) in.readValue((String.class.getClassLoader())));
        this.reviewContent=((String) in.readValue((String.class.getClassLoader())));
        this.id=((String) in.readValue((String.class.getClassLoader())));
        this.url=((String) in.readValue((String.class.getClassLoader())));
    }

    public review () {
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor ( String author ) {
        this.author=author;
    }

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id=id;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url=url;
    }

    public String getReviewContent () {
        return reviewContent;
    }

    public void setReviewContent ( String reviewContent ) {
        this.reviewContent=reviewContent;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( Parcel dest , int flags ) {
        dest.writeString ( author );
        dest.writeString ( reviewContent );
        dest.writeString ( id );
        dest.writeString ( url );
    }
}
