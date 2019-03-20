package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class ExercitarLogicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercitar_logica);
    }

    public void selecao(View view) {
        startActivity(new Intent(getApplicationContext(), ExerciciosLogicaSelecaoActivity.class));
    }
}
