package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pepsicotemi1.R;

import java.util.ArrayList;

public class Videos {

    private Context context;
    private AppCompatActivity main;
    private VideoView view;
    private ArrayList<Uri> listVideos = new ArrayList<>();
    private int n;


    public Videos(Context context, AppCompatActivity main, VideoView view){
        this.context = context;
        this.main = main;
        this.view = view;
        this.n = 0;
        ReproducirVideos();
    }

    private void ReproducirVideos(){
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v1));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v2));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v3));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v4));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v5));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v6));
        listVideos.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v7));

        view.setVideoURI(listVideos.get(n));
        view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (n == listVideos.size()){
                    n = 0;
                }else{
                    n++;
                    ReproducirVideos();
                }
            }
        });

        //view.setVideoURI(Uri.parse(videos.get(2)));
        view.start();
    }

    public void DetenerVideos(){
        view.stopPlayback();
    }

}
