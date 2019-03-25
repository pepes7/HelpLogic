package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class NocoesDeLogicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocoes_de_logica);
    }

    public void oQueLogica(View view) {
        startActivity(new Intent(getApplicationContext(), OQueELogicaActivity.class));
    }

    public void existe(View view) {
        startActivity(new Intent(getApplicationContext(), ExisteLogicaNoDiaADiaActivity.class));
    }

    public void oQueAlgoritimo(View view) {
        startActivity(new Intent(getApplicationContext(), OQueEAlgoritimoActivity.class));
    }

    public void oQueLogicaProgramacao(View view) {
        startActivity(new Intent(getApplicationContext(), OQueELogicaDeProgramacaoActivity.class));
    }
}
