package com.example.helplogic.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.helplogic.R;
import com.example.helplogic.adapter.AdapterExercicios;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.model.Exercicios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExercicioLogica extends AppCompatActivity {
    private RecyclerView recyclerExercicios;
    private List<Exercicios> exercicios = new ArrayList<>();
    private AdapterExercicios adapterExercicios;
    private DatabaseReference exerciciosFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_logica);

        //coloca o botão de voltar para activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //configurações iniciais
        exerciciosFirebase = FirebaseConfig.getFirebaseDatabase().child("exercicios").child("logica");

        inicializarComponentes();
        recyclerExercicios.setLayoutManager(new LinearLayoutManager(this));
        recyclerExercicios.hasFixedSize();
        adapterExercicios = new AdapterExercicios(exercicios,this);

        recyclerExercicios.setAdapter(adapterExercicios);
        recuperarExercicios();
    }

    public void inicializarComponentes(){
        recyclerExercicios = findViewById(R.id.recyclerExercicios);

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}