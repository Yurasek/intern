package com.example.newsfeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Proccess2chRespons {

    public List<Post> postList = new ArrayList<>();

    public List<Post> getCompany(String json) throws JSONException  {

        JSONObject threads;
        JSONArray posts;
        JSONObject post;
        JSONArray files;
        JSONObject file;
        StringBuilder sb = new StringBuilder();
        JSONObject object = new JSONObject(json);

        JSONArray allThreads = object.getJSONArray("threads");

        for(int i = 0;i<allThreads.length();i++){
            threads = allThreads.getJSONObject(i);
            posts = threads.getJSONArray("posts");
            post = posts.getJSONObject(0);
            files = post.getJSONArray("files");
            file = files.getJSONObject(0);
            postList.add(new Post(post.getString("comment").trim(), getImage("https://2ch.hk"+file.getString("path"))));

        }
        return postList;
    }

    private Bitmap getImage(String path){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(path).openConnection().getInputStream());
        }catch(Exception e){
            Log.e("lol", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }


}
