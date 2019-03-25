package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class EscolhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);
    }

    public void escolheLogica(View view) {
        startActivity(new Intent(getApplicationContext(), AprenderOuExercitarLogicaActivity.class));
    }

    public void escolheLinguagemI(View view) {
        // startActivity(new Intent(getApplicationContext(), AprenderOuExercitarLinguagemIActivity.class));
    }
}
