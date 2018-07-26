package com.example.newsfeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String MSG_TAG = "msg";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new WideItemDecoration(getApplicationContext()));

        new LoadImgAsyncTask().execute();
    }

    private class LoadImgAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... strings) {
            String lol = null;
            try {
                lol = new Get2chResponse().getJson();
                postList = new Proccess2chRespons().getCompany(lol);
            }catch(Exception e){
                Log.e(MSG_TAG, e.getMessage());
            }

            return lol;
        }

        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap == null)
                Log.d(MSG_TAG,"bitmap == null");
            else {
                adapter = new MyAdapter(postList);
                recyclerView.setAdapter(adapter);
                Log.d(MSG_TAG,"nice!");
            }
        }
    }
}
