package com.example.helplogic.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.adapter.AdapterExercicios;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.helper.RecyclerItemClickListener;
import com.example.helplogic.model.Exercicios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerExercicios;
    private List<Exercicios> exercicios = new ArrayList<>();
    private AdapterExercicios adapterExercicios;
    private FirebaseAuth auth =  FirebaseConfig.getFirebaseAuth();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference favoritos;

    private TextView textViewFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favoritos = referencia.child("usuarios").child(auth.getUid()).child("favoritos");

        textViewFavoritos = findViewById(R.id.textViewFavoritos);

        inicializarComponentes();
        recyclerExercicios.setLayoutManager(new LinearLayoutManager(this));
        recyclerExercicios.hasFixedSize();
        adapterExercicios = new AdapterExercicios(exercicios, this);

        recyclerExercicios.setAdapter(adapterExercicios);
        recuperarExercicios();
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

    private void recuperarExercicios() {
        favoritos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exercicios.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    exercicios.add(ds.getValue(Exercicios.class));
                }

                Collections.reverse(exercicios);
                adapterExercicios.notifyDataSetChanged();

                if (exercicios.isEmpty()) {
                    textViewFavoritos.setText("Nenhum Exercicio Favorito");
                } else {
                    textViewFavoritos.setText("Exercícios Favoritos");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
