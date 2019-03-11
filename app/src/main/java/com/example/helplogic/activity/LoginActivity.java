package com.example.helplogic.activity;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button botaoLogar;

    private EditText editTextEmail;
    private EditText editTextSenha;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogar = findViewById(R.id.botaoLogar);

        auth = FirebaseConfig.getFirebaseAuth();

        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextSenha = findViewById(R.id.editTextSenhaLogin);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });
    }

    /**
     * Método responsável por verificar e realizar o login do usuário.
     *
     */
    private void logar() {

        // Recupera as Strings dos EditText.
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        // Variável de controle.
        boolean valido = true;

        // Verifica se os campos foram preenchidos.
        if (email.isEmpty()) {
            editTextEmail.setError("Campo vazio!");
            valido = false;
        }

        if (senha.isEmpty()) {
            editTextSenha.setError("Campo vazio!");
            valido = false;
        }

        // Se os campos não estiverem vazios, passa para a próxima etapa de autenticação.
        if (valido) {

            // Inicializa o DialogFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            dialogFragment = new CarregandoAlertFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.show(transaction, "");

            // Autentica utilizando firebase.
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    // Destrói o DialogFragment
                    dialogFragment.dismiss();

                    // Verifica se foi possível realizar o login.
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(),EscolhaActivity.class);
                        startActivity(intent);
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            Toast.makeText(getApplicationContext(),
                                    "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao realizar login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
        }

    }
}
