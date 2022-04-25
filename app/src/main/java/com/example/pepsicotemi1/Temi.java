package com.example.pepsicotemi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.VideoView;

import com.example.pepsicotemi1.Clases.TTS;

public class Temi extends AppCompatActivity {

    private TTS tts;
    private Handler hablaHandler = new Handler();
    private Runnable hablaRunnable;
    private int numSpeech;
    private VideoView vv_Temi;



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
        setContentView(R.layout.activity_temi);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().getStringExtra("numSpeech") != null){
            numSpeech = Integer.parseInt(getIntent().getStringExtra("numSpeech"));
        }else{
            numSpeech = 1;
        }

        vv_Temi = findViewById(R.id.vv_Temi);


        tts = new TTS(this, this, getLocalClassName().toLowerCase(), numSpeech, vv_Temi);

        hablaRunnable = new Runnable() {
            @Override
            public void run() {
                switch (numSpeech){
                    case 1:
                        tts.Speak("Hola bienvenido, síganme para llevarlos a la entrada de los elevadores");
                        break;
                    case 2:
                        tts.Speak("Hemos llegado, espero verte pronto, bai");
                        break;
                }
            }
        };
        hablaHandler.postDelayed(hablaRunnable, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}