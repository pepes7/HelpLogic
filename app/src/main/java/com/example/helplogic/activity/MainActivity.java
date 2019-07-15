package com.example.helplogic.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helplogic.R;

public class MainActivity extends AppCompatActivity {
    private Button botaoEntrar,botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* try {
            //Criar tabela
            SQLiteDatabase bancoDados = openOrCreateDatabase("androidTeste", MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS exercicio(nome VARCHAR, idade INT(3))");

            //INSERIR DADOS
            bancoDados.execSQL("INSERT INTO exercicio VALUES('Gabriel',5)");

            //Listar
            Cursor cursor = bancoDados.rawQuery("SELECT nome,idade FROM exercicio", null);

            //Indice da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {
                Log.i("Resultado - nome", cursor.getString(indiceNome));
                Log.i("Resultado - idade", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        }catch (Exception e){

        }*/

        botaoEntrar = findViewById(R.id.botaoLogar);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
