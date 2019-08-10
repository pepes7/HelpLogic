package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class ExerciciosLogicaSelecaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_logica_selecao);

        //coloca o botão de voltar para activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ex1(View view) {
        startActivity(new Intent(getApplicationContext(), Exercicio1Activity.class));
    }

    public void ex2(View view) {
        startActivity(new Intent(getApplicationContext(), Exercicio2Activity.class));
    }

    public void ex3(View view) {
        startActivity(new Intent(getApplicationContext(), Exercicio3Activity.class));
    }

    public void ex4(View view) {
        startActivity(new Intent(getApplicationContext(), Exercicio4Activity.class));
    }
}
