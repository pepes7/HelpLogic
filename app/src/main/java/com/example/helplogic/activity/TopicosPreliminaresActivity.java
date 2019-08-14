package com.example.helplogic.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.helplogic.R;
import com.example.helplogic.adapter.AdapterConteudo;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.helper.RecyclerItemClickListener;
import com.example.helplogic.model.Conteudo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicosPreliminaresActivity extends AppCompatActivity {

    private RecyclerView recyclerConteudos;
    private List<Conteudo> conteudos = new ArrayList<>();
    private AdapterConteudo adapterConteudo;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicos_preliminares);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configurações Inciais
        databaseReference = FirebaseConfig.getFirebaseDatabase().child("conteudo").child("preliminares");


        incializarComponentes();
        recuperarConteudos();
    }

    private void incializarComponentes() {
        recyclerConteudos = findViewById(R.id.recyclerViewTopicosPreliminares);

        recyclerConteudos.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerConteudos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Conteudo c = conteudos.get(position);

                Intent intent = new Intent(getApplicationContext(), ConteudoActivity.class);
                intent.putExtra("titulo", c.getTitulo());
                intent.putExtra("texto", c.getTexto());
                intent.putExtra("id", c.getId().toString());

                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        adapterConteudo = new AdapterConteudo(conteudos, this);
        recyclerConteudos.setLayoutManager(new LinearLayoutManager(this));
        recyclerConteudos.hasFixedSize();
        recyclerConteudos.setAdapter(adapterConteudo);
    }

    private void recuperarConteudos() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                conteudos.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    conteudos.add(ds.getValue(Conteudo.class));
                }

                Collections.reverse(conteudos);
                adapterConteudo.notifyDataSetChanged();
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
