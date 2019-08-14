package com.example.helplogic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.helplogic.R;

public class NocoesDeLogicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocoes_de_logica);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
