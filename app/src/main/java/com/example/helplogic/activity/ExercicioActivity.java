package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.model.Exercicios;

public class ExercicioActivity extends AppCompatActivity {

    private Exercicios exercicios;
    private TextView textViewTitulo;
    private TextView textViewEnunciado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciaExercicios();
        iniciaDados();
    }

    private void iniciaExercicios() {
        Bundle data = getIntent().getExtras();

        String titulo = data.getString("titulo");
        String enunciado = data.getString("enunciado");
        String favorito = data.getString("favorito");
        String estado = data.getString("estado");

        exercicios = new Exercicios();
        exercicios.setTitulo(titulo);
        exercicios.setEnunciado(enunciado);
        exercicios.setFavorito(Boolean.parseBoolean(favorito));
        exercicios.setEstado(estado);
    }

    private void iniciaDados() {
        textViewTitulo = findViewById(R.id.textViewTituloExercicio);
        textViewEnunciado = findViewById(R.id.textViewEnunciadoExercicio);

        textViewTitulo.setText(exercicios.getTitulo());
        textViewEnunciado.setText(exercicios.getEnunciado());
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
