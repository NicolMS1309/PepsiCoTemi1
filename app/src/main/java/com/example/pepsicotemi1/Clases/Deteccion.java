package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pepsicotemi1.Temi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;

public class Deteccion implements OnDetectionStateChangedListener {

    private static final String TAG = "Deteccion";
    private Robot robot;
    private Context context;
    private AppCompatActivity main;

    public Deteccion(Context context, AppCompatActivity main){
        this.context = context;
        this.main = main;
        robot = Robot.getInstance();
    }

    @Override
    public void onDetectionStateChanged(int i) {
        switch (i){
            case 0:
                Log.i(TAG, "Deteccion: " + i + " No se ha detectado nada");
                break;

            case 1:
                Log.i(TAG, "Deteccion: " + i + " Perdí a la Persona");
                break;

            case 2:
                Log.i(TAG, "Deteccion: " + i + " Detecte a alguien");
                Intent next = new Intent(context, Temi.class);
                next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                main.startActivity(next);
                break;
        }
    }

    public void addListener(){
        robot.addOnDetectionStateChangedListener(this::onDetectionStateChanged);
    }

    public void removeListener(){
        robot.removeOnDetectionStateChangedListener(this::onDetectionStateChanged);
    }
}
