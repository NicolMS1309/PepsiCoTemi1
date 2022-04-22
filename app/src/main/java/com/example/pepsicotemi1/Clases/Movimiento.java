package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;

public class Movimiento implements OnGoToLocationStatusChangedListener {

    private static final String TAG = "Movimiento";
    private Robot robot;
    private Context context;
    private AppCompatActivity main;

    public Movimiento(Context context, AppCompatActivity main){
        this.context = context;
        this.main = main;
        robot =  Robot.getInstance();
    }

    public void irA(String ubicacion){
        robot.goTo(ubicacion.toLowerCase());
    }

    public void obtenetUbicaciones(){
        for (int i = 0; i < robot.getLocations().size(); i ++){
            Log.i(TAG, "Ubicacion: " + robot.getLocations().get(i));
        }
    }

    public void addListener(){
        robot.addOnGoToLocationStatusChangedListener(this::onGoToLocationStatusChanged);
    }

    public void  removeListener(){
        robot.removeOnGoToLocationStatusChangedListener(this::onGoToLocationStatusChanged);
    }


    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {
        Log.i(TAG, "Ubicación: " + s + "\nEstatus: " + s1 + "\nIdDescripción: " + i + "\nDescripción: " + s2);
    }
}
