package com.example.helplogic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helplogic.R;

public class EstatisticaActivity extends AppCompatActivity {

    private TextView textViewNPropostos;
    private TextView textViewNAcertos;
    private TextView textViewNPulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatistica);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciaComponentes();
        carregaDados();
    }

    private void iniciaComponentes() {
        textViewNPropostos = findViewById(R.id.textViewNPropostos);
        textViewNAcertos = findViewById(R.id.textViewNAcertos);
        textViewNPulos = findViewById(R.id.textViewNPulos);
    }

    private void carregaDados() {
        textViewNPropostos.setText("");
        textViewNAcertos.setText("");
        textViewNPulos.setText("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
