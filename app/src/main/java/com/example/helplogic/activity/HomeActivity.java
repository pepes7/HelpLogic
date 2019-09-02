package com.example.helplogic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.config.FirebaseConfig;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth =  FirebaseConfig.getFirebaseAuth();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private TextView nome;
    private TextView email;
    private CircleImageView imagemPerfil;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imagemPerfil = findViewById(R.id.imgPerfil);
        storageReference = FirebaseConfig.getFirebaseStorage();

        carregarInformacoesNav();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //logof e volta para tela inicial
        if (id == R.id.action_settings){
            auth.signOut();
            Intent intent = new Intent(HomeActivity.this, SliderActivity.class);
            finish();
            startActivity(intent);
            return true;
        } else if (id == R.id.action_line_chart) {
            startActivity(new Intent(getApplicationContext(), EstatisticaActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_perfil){
            Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_favoritos) {
            startActivity(new Intent(getApplicationContext(), FavoritosActivity.class));
        }
        else if (id == R.id.nav_sobre){
            startActivity(new Intent(getApplicationContext(), SobreActivity.class));

        } /*else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void aprender(View view) {
        startActivity(new Intent(getApplicationContext(), AprenderLogicaActivity.class));
    }

    public void exercitar(View view) {
        startActivity(new Intent(getApplicationContext(), ExercitarLogicaActivity.class));
    }

    public void carregarInformacoesNav(){
        //acessar a referencia do nó usuarios e seu filho(usuario logados)
        DatabaseReference usuario = referencia.child("usuarios").child(auth.getUid());

        //cria um listener para o nó
        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                //recupera as informações do firebase e coloca dentro do objeto Usuario
                Usuario user = dataSnapshot.getValue(Usuario.class);
                //referencia da view do nav header
                email = findViewById(R.id.nav_user_email);
                nome = findViewById(R.id.nav_user_nome);

                //exibe as informações
                email.setText(user.getEmail());
                nome.setText(user.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final StorageReference imagemRef = storageReference
                .child("imagens")
                .child("perfil")
                .child(auth.getUid())
                .child("perfil.jpeg");

        imagemRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri downloadUrl){
                imagemPerfil = findViewById(R.id.imgPerfil);
                Picasso.get()
                        .load(downloadUrl.toString())
                        .into(imagemPerfil);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                imagemPerfil = findViewById(R.id.imgPerfil);
                final Bitmap nullImage = BitmapFactory.decodeResource(getResources(), R.drawable.padrao);
                imagemPerfil.setImageBitmap(nullImage);
            }
        });

    }
}