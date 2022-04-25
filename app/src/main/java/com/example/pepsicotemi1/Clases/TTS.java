package com.example.pepsicotemi1.Clases;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pepsicotemi1.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TTS{


    private TextToSpeech textToSpeech;
    private Boolean isLoaded = false; // Declaración de la variable para saber si esta disponible el TTS el dispositivo
    private HashMap<String,String> params = new HashMap<>(); //Arreglo que contiene Id para poder identificar cada
    private Bundle bundle = new Bundle(); // Obtiene los valores de una manera más rápida
    private Context context;
    private AppCompatActivity main;
    private String nombreClase;
    private int numSpeech;
    private Movimiento movimiento;
    private VideoView view;

    //Construtor del TTS
    public TTS(Context context, AppCompatActivity main, String nombreClase, int numSpeech,VideoView view){
        this.context = context;
        this.main = main;
        this.nombreClase = nombreClase;
        this.numSpeech = numSpeech;
        this.view = view;
        view.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.v_temi));
        movimiento = new Movimiento(context, main);
        movimiento.AddListener();
        try {
            //Sirve para asignarle un ID a cada frase que va a ejecutar el TTS
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "idHabla");
            //Agrega al HashMap los valores y su id respectivamente
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //Sirve para obtener el id y el valor de cada dato gurdado en el HashMap
                bundle.putString(entry.getKey(), entry.getValue());
            }
            //Se crea un objeto de TTS el cual pide el contexto en el que se va a ejecutar y su listener
            textToSpeech = new TextToSpeech(context, onInitListener);
        } catch (Exception e) {
            Log.e("TTS", "Se encontro el siguiente error " + e);
        }
    }

    //Se configura su Listener
    private TextToSpeech.OnInitListener onInitListener = status -> {
        // Se crea un localizacion de la lengua y el país
        Locale locale = new Locale("es", "MX");
        //Verifica su el dispositivo soporta el TTS
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(locale);
            //Se obtienen los estados del TTS
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    System.out.println("Empece a Hablar");
                    view.start();
                    view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.setLooping(true);
                        }
                    });
                }

                @Override
                public void onDone(String utteranceId) {
                    System.out.println("Termine de Hablar");
                    view.stopPlayback();
                    if (nombreClase != null){
                        if (nombreClase.equals("temi")){
                            switch (numSpeech){
                                case 1:
                                    movimiento.irA("2");
                                    break;
                                case 2:
                                    movimiento.irA("home base");
                                    break;
                            }
                        }
                    }
                }

                @Override
                public void onError(String utteranceId) {

                }
            });
            //Modifica la variable para indicar que si se puede ejecutar el TTS
            isLoaded = true;
        }
    };

    public void Speak(String frase) {
        //Verifica si si se puede ejecutar el TTS en el dispositivo
        if (isLoaded) {
            //Si es verdad entonces indica que el dispositivo tiene que decir la frase que se le fue asignada
            textToSpeech.speak(frase, TextToSpeech.QUEUE_ADD, bundle,"idHabla");
        }
        //Si no entonces arroja un Error en el Log
        else {
            Log.e("TTS", "El TTS no inicializo");
        }
    }

    //Ayuda a cancelar y borrar toda la pila de TTS
    public void Apagar(){
        textToSpeech.shutdown();
        movimiento.RemoveListener();
    }
}
