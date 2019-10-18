package com.example.helplogic.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helplogic.R;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.fragment.CarregandoAlertFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private Button redefinir;
    private FirebaseAuth auth;
    private EditText email;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        redefinir = findViewById(R.id.redefinir);
        auth = FirebaseConfig.getFirebaseAuth();
        email = findViewById(R.id.editText_redefinir_senha);

        redefinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redefinirSenha();
            }
        });
        getSupportActionBar().hide();
    }

    public void redefinirSenha(){
        if(!email.getText().toString().isEmpty()){
            // Inicializa o DialogFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            dialogFragment = new CarregandoAlertFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.show(transaction, "");
            auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Destrói o DialogFragment
                    dialogFragment.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(EsqueceuSenhaActivity.this, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EsqueceuSenhaActivity.this, "Erro ao enviar o email, tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else{
            email.setError("Preencha o campo!");

        }

    }
}
