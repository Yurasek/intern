package com.example.newsfeed;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

public class Post{

    private String title;

    private Bitmap bitmap;

    public Post(String title, Bitmap bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
