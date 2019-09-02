package com.example.helplogic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.model.Conteudo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConteudoActivity extends AppCompatActivity {

    private TextView textViewTitulo;
    private TextView textViewTexto;

    private Conteudo conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciaConteudo();
        iniciaDados();
    }

    private void iniciaConteudo() {
        Bundle data = getIntent().getExtras();

        String titulo = data.getString("titulo");
        String texto = data.getString("texto");
        String id = data.getString("id");

        conteudo = new Conteudo(Integer.parseInt(id), titulo, texto);
    }

    public void iniciaDados() {
        textViewTexto = findViewById(R.id.textViewTextoConteudo);
        textViewTitulo = findViewById(R.id.textViewTituloConteudo);

        textViewTexto.setText(conteudo.getTexto());
        textViewTitulo.setText(conteudo.getTitulo());
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
