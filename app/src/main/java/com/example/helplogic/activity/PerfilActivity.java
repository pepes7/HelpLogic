package com.example.helplogic.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;


import com.example.helplogic.R;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilActivity extends AppCompatActivity {

    private FirebaseAuth auth =  FirebaseConfig.getFirebaseAuth();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private EditText nome;
    private EditText email;
    private RadioButton rbMasc;
    private RadioButton rbFemin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        carregar();

    }

    public void carregar(){
            //acessar a referencia do nó usuarios e seu filho(usuario logados)
            DatabaseReference usuario = referencia.child("usuarios").child(auth.getUid());

            usuario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    nome = findViewById(R.id.editar_nome);
                    email = findViewById(R.id.editar_email);
                    rbMasc = findViewById(R.id.radioMasc);
                    rbFemin = findViewById(R.id.radioFem);

                    //recupera os dados do no e coloca no objeto usuario
                    Usuario user = dataSnapshot.getValue(Usuario.class);

                    nome.setText(user.getNome());
                    email.setText(user.getEmail());

                    if(user.getSexo().equals("Masculino")){
                        rbMasc.setChecked(true);
                        rbFemin.setChecked(false);

                    }else{
                        rbFemin.setChecked(true);
                        rbMasc.setChecked(false);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
}
