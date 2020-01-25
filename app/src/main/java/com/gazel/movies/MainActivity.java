package com.gazel.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    GridView mGridView;
    ArrayList<Movie> mMovies;
    CustomAdapter mCustomAdapter;
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = findViewById(R.id.gridView);
        mProgressBar = findViewById(R.id.progressBar);

        mMovies = new ArrayList<>();

        mCustomAdapter = new CustomAdapter(this, R.layout.item_movie, mMovies);

        mGridView.setAdapter(mCustomAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = mMovies.get(position);

                Log.d("###", "onResponse: " + movie.getId() + " " + movie.getTittle());

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", movie.getId());
                intent.putExtra("title", movie.getTittle());
                intent.putExtra("poster", movie.getPoster());
                intent.putExtra("backdrop", movie.getBackdrop());
                intent.putExtra("overview", movie.getOverview());

                startActivity(intent);
            }
        });

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=490bc3f8bd238721511d3c3c21b9e925&language=en-US&page=1";

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("###", "OnFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    try {
                        JSONObject jsonObject = new JSONObject(json);

                        JSONArray results = jsonObject.getJSONArray("results");
                        for (int index = 0; index < results.length(); index++) {
                            JSONObject result = results.getJSONObject(index);
                            String tittle = result.getString("title");
                            int id = result.getInt("id");
                            String overview = result.getString("overview");
                            String poster = "https://image.tmdb.org/t/p/w500" + result.getString("poster_path");
                            String backdrop = "https://image.tmdb.org/t/p/original" + result.getString("backdrop_path");

                            Movie movie = new Movie(id, tittle, overview , poster, backdrop);

                            mMovies.add(movie);

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mCustomAdapter.notifyDataSetChanged();

                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    mGridView.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
