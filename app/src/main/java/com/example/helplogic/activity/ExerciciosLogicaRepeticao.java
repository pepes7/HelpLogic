package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class ExerciciosLogicaRepeticao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_logica_repeticao);

        //coloca o botão de voltar para activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ex1(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioRepeticao1.class));
    }

    public void ex2(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioRepeticao2.class));
    }

    public void ex3(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioRepeticao3.class));
    }

    public void ex4(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioRepeticao4.class));
    }
}
