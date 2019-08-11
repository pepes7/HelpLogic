package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.helplogic.R;
import com.example.helplogic.adapter.AdapterExercicios;
import com.example.helplogic.helper.RecyclerItemClickListener;
import com.example.helplogic.model.Exercicios;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerExercicios;
    private List<Exercicios> exercicios = new ArrayList<>();
    private AdapterExercicios adapterExercicios;
    private DatabaseReference exerciciosFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void carregaDados() {

    }

    public void inicializarComponentes() {
        recyclerExercicios = findViewById(R.id.recyclerViewFavoritos);

        // Adicionando evento de click.
        recyclerExercicios.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerExercicios,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Exercicios ex = exercicios.get(position);

                                Intent intent = new Intent(getApplicationContext(), ExercicioActivity.class);
                                intent.putExtra("titulo", ex.getTitulo());
                                intent.putExtra("enunciado", ex.getEnunciado());
                                intent.putExtra("favorito", ex.getFavorito());
                                intent.putExtra("estado", ex.getEstado());

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
