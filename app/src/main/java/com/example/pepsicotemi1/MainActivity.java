package com.example.pepsicotemi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pepsicotemi1.Clases.Deteccion;
import com.example.pepsicotemi1.Clases.Movimiento;
import com.robotemi.sdk.Robot;

public class MainActivity extends AppCompatActivity {


    private Deteccion deteccion;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            View view = getWindow().peekDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        deteccion = new Deteccion(this, this);
        deteccion.AddListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        deteccion.RemoveListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        deteccion.RemoveListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}