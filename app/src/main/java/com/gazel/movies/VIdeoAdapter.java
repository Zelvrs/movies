package com.gazel.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gazel.movies.R;
import com.gazel.movies.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VIdeoAdapter extends ArrayAdapter<Video> {
    private Context mCOntext;
    private int mResource;
    private ArrayList<Video> mVideos;

    private  static class VIewHolder {
        ImageView ivThumbnail;
    }

    public VIdeoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Video> videos) {
        super(context, resource, videos);

        mCOntext = context;
        mResource = resource;
        mVideos = videos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mCOntext);

        VIewHolder vIewHolder = new VIewHolder();

        if (convertView == null) {
            convertView = layoutInflater.inflate(mResource, parent, false);

            vIewHolder.ivThumbnail = convertView.findViewById(R.id.ivThumbnail);

            convertView.setTag(vIewHolder);
        } else {
            vIewHolder = (VIewHolder) convertView.getTag();
        }

        Video video = mVideos.get(position);

        Picasso.with(mCOntext).load(video.getThumbnail()).into(vIewHolder.ivThumbnail);

        return convertView;
    }
}
