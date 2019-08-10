package com.example.helplogic.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.helplogic.R;
import com.example.helplogic.config.FirebaseConfig;
import com.example.helplogic.helper.Permissao;
import com.example.helplogic.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends AppCompatActivity {

    private FirebaseAuth auth =  FirebaseConfig.getFirebaseAuth();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private EditText nome;
    private EditText email;
    private RadioButton rbMasc;
    private RadioButton rbFemin;
    private TextView textView;
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private CircleImageView imagemPerfil;
    private StorageReference storageReference;


    private String[] permisssoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int permissaoResultado : grantResults){
            if(permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidaPermissao();
            }
        }
    }

    public void alertaValidaPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para a melhor utilização do app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Permissao.validarPermissao(permisssoes,this, 1);

        getSupportActionBar().setTitle("Editar perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

        textView = findViewById(R.id.textEdit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarFoto();
            }
        });
    }

    public void mudarFoto(){
        final String[] modes = new String[]{"Tirar da camera","Selecione da galeria"};

        //Cria uma AlertDialog
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        //Seta o título
        builder.setTitle("Selecionar uma foto")
                //Seta os itens de opção
                .setItems(modes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Verifica o índice do item
                        switch (i){
                            //Se o usuário escolheu tirar uma foto com a camera
                            case 0:
                                if (getBaseContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if(intent.resolveActivity(getPackageManager()) != null){
                                        startActivityForResult(intent, SELECAO_CAMERA);
                                    }
                                }else{
                                    //alertCameraNotFound();
                                }
                                break;
                            //Se o usuário escolheu selecionar foto pela galeria
                            case 1:
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if(intent.resolveActivity(getPackageManager()) != null){
                                    startActivityForResult(intent, SELECAO_GALERIA);
                                }
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imagemPerfil = findViewById(R.id.imageProfile);

        if(resultCode == RESULT_OK){
            Bitmap imagem = null;

            try{
                switch(requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada );
                        break;
                }

                if( imagem != null){
                    imagemPerfil.setImageBitmap(imagem);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
