package com.example.helplogic.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.example.helplogic.R;
import com.example.helplogic.adapter.AdapterExercicios;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.helper.RecyclerItemClickListener;
import com.example.helplogic.model.Exercicios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciciosLogicaSelecaoActivity extends AppCompatActivity {

    private RecyclerView recyclerExercicios;
    private List<Exercicios> exercicios = new ArrayList<>();
    private AdapterExercicios adapterExercicios;
    private DatabaseReference exerciciosFirebase;
    private ProgressBar progressImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_logica_selecao);

        //coloca o botão de voltar para activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        exerciciosFirebase = FirebaseConfig.getFirebaseDatabase().child("exercicios").child("selecao");

        progressImage = findViewById(R.id.progress_selecao);

        inicializarComponentes();
        recyclerExercicios.setLayoutManager(new LinearLayoutManager(this));
        recyclerExercicios.hasFixedSize();
        adapterExercicios = new AdapterExercicios(exercicios, this);

        recyclerExercicios.setAdapter(adapterExercicios);

        progressImage.setVisibility(View.VISIBLE);
        recuperarExercicios();
    }

    public void inicializarComponentes() {
        recyclerExercicios = findViewById(R.id.recyclerViewSelecao);

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
                                intent.putExtra("id", ex.getId().toString());

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


    private void recuperarExercicios(){
        exerciciosFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exercicios.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    exercicios.add(ds.getValue(Exercicios.class));
                }

                Collections.reverse(exercicios);
                adapterExercicios.notifyDataSetChanged();
                progressImage.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
