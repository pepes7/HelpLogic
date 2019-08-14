package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class AprenderLogicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_logica);

        //coloca o botão de voltar para activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void intro(View view) {
        startActivity(new Intent(getApplicationContext(), NocoesDeLogicaActivity.class));
    }

    public void topicosPreliminares(View view) {
        startActivity(new Intent(getApplicationContext(), TopicosPreliminaresActivity.class));
    }

    public void estruturaControle(View view) {
        startActivity(new Intent(getApplicationContext(), EstruturaControleActivity.class));
    }
}
