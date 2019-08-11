package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        if (exercicios.getFavorito()) {
            MenuItem menuItem = findViewById(R.id.action_favorite);
            menuItem.setIcon(R.drawable.ic_favorite_white_24dp);
        }
    }

    private void iniciaDados() {
        textViewTitulo = findViewById(R.id.textViewTituloExercicio);
        textViewEnunciado = findViewById(R.id.textViewEnunciadoExercicio);

        textViewTitulo.setText(exercicios.getTitulo());
        textViewEnunciado.setText(exercicios.getEnunciado());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exercicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Adiciona/Remove dos Favoritos
        if (id == R.id.action_favorite){
            if (exercicios.getFavorito()) {
                exercicios.setFavorito(false);
                item.setIcon(R.drawable.ic_favorite_border_white);
                Toast.makeText(this, "Removido dos favoritos", Toast.LENGTH_SHORT).show();
            } else {
                exercicios.setFavorito(true);
                item.setIcon(R.drawable.ic_favorite_white_24dp);
                Toast.makeText(this, "Adicionado aos favoritos", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
