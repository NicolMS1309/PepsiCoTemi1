package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pepsicotemi1.Temi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;

public class Deteccion implements OnDetectionStateChangedListener {

    private final String TAG = "Deteccion";
    private Robot robot;

    private Context context;
    private AppCompatActivity main;

    public Deteccion(Context context, AppCompatActivity main){
        this.context = context;
        this.main = main;
        robot = Robot.getInstance();
        if (!robot.isDetectionModeOn()){
            robot.setDetectionModeOn(true);
        }
        if (!robot.isTrackUserOn()){
            int entro_al_track = Log.i(TAG, "Entro al Track");
            robot.setDetectionModeOn(true);
        }
    }

    @Override
    public void onDetectionStateChanged(int i) {
        switch (i){
            case 0:
                Log.i(TAG, "No se ha detectado nada");
                break;

            case 1:
                Log.i(TAG, "Perdí a la Persona");
                break;

            case 2:
                Log.i(TAG, "Detecte a alguien");
                Intent next = new Intent(context, Temi.class);
                next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                main.startActivity(next);
                break;
        }
    }

    public void AddListener(){
        robot.addOnDetectionStateChangedListener(this);
    }

    public void RemoveListener(){
        robot.removeOnDetectionStateChangedListener(this);
    }
}
