package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pepsicotemi1.MainActivity;
import com.example.pepsicotemi1.R;
import com.example.pepsicotemi1.Temi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;

public class Movimiento implements OnGoToLocationStatusChangedListener {

    private static final String TAG = "Movimiento";
    private Robot robot;
    private Context context;
    private AppCompatActivity main;
    private MediaPlayer mediaPlayer;

    public Movimiento(Context context, AppCompatActivity main){
        this.context = context;
        this.main = main;
        mediaPlayer = MediaPlayer.create(context, R.raw.c1);
        robot =  Robot.getInstance();
    }

    public void irA(String ubicacion){
        robot.goTo(ubicacion.toLowerCase());
    }

    public void ObtenetUbicaciones(){
        for (int i = 0; i < robot.getLocations().size(); i ++){
            Log.i(TAG, "Ubicacion: " + robot.getLocations().get(i));
        }
    }

    public void AddListener(){
        robot.addOnGoToLocationStatusChangedListener(this::onGoToLocationStatusChanged);
    }

    public void  RemoveListener(){
        robot.removeOnGoToLocationStatusChangedListener(this::onGoToLocationStatusChanged);
    }


    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {
        Log.i(TAG, "Ubicación: " + s + "\nEstatus: " + s1 + "\nIdDescripción: " + i + "\nDescripción: " + s2);
        switch (s1){
            case OnGoToLocationStatusChangedListener.START:
                mediaPlayer.start();
                break;
            case OnGoToLocationStatusChangedListener.CALCULATING:
                break;
            case OnGoToLocationStatusChangedListener.GOING:
                mediaPlayer.setLooping(true);
                break;
            case OnGoToLocationStatusChangedListener.REPOSING:
                break;
            case OnGoToLocationStatusChangedListener.ABORT:
                break;
            case OnGoToLocationStatusChangedListener.COMPLETE:
                mediaPlayer.stop();
                if (s.equals("2")){
                    Intent next = new Intent(context, Temi.class);
                    next.putExtra("numSpeech", "2");
                    next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    main.startActivity(next);
                }
                else if (s.equals("home base")){
                    Intent next = new Intent(context, MainActivity.class);
                    next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    main.startActivity(next);
                }
                break;
        }
    }
}
