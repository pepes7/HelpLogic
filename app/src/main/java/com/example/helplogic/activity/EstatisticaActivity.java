package com.example.helplogic.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.model.Escore;
import com.example.helplogic.model.EstadoExercicio;
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

public class EstatisticaActivity extends AppCompatActivity {

    private TextView textViewNPropostos;
    private TextView textViewNAcertos;
    private TextView textViewNPulos;

    private FirebaseAuth auth =  FirebaseConfig.getFirebaseAuth();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference respondidos;

    private List<Exercicios> exercicios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatistica);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        respondidos = referencia.child("usuarios").child(auth.getUid()).child("respondidos");

        iniciaComponentes();
        recuperarExercicios();
    }

    private void iniciaComponentes() {
        textViewNPropostos = findViewById(R.id.textViewNPropostos);
        textViewNAcertos = findViewById(R.id.textViewNAcertos);
        textViewNPulos = findViewById(R.id.textViewNPulos);
    }

    private void recuperarExercicios() {
        respondidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exercicios.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    exercicios.add(ds.getValue(Exercicios.class));
                }

                Collections.reverse(exercicios);

                if (exercicios != null && !exercicios.isEmpty()) carregaDados();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // Cansei de fazer magia
    private void carregaDados() {
        int tamanho = exercicios.size();
        int acertos = 0;
        int pulos = 0;

        for (Exercicios e : exercicios) {
            if (e.getEstado().equals(EstadoExercicio.ACERTOU.toString())) {
                acertos++;
            } else if (e.getEstado().equals(EstadoExercicio.PULOU.toString())) {
                pulos++;
            }
        }

        textViewNPropostos.setText("" + tamanho);
        textViewNPulos.setText("" + pulos);
        textViewNAcertos.setText("" + acertos);
    }



    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
