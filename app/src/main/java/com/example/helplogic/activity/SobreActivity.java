package com.example.helplogic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

import mehdi.sakout.aboutpage.AboutPage;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sobre);

        String texto = "Olá, terráqueo! Você está utilizando o HelpLogic!, um aplicativo de estímulo a programação. Eu sou o Loginelson, irei aparecer várias vezes por aqui, pois sou a mascote do app.\n" + "O HelpLogic é um aplicativo que promove os estudos individuais dos alunos do curso de Informática, a fim de fazê-los se interessar ainda mais pela área de programação de computadores.\n" + "Contando com um banco de exercícios, os quais contém respostas, o aplicativo pretende ajudar os alunos na busca por resolução de questões referentes a Lógica de Programação.\n" + "Além disso, aqui você pode aprender sobre a disciplina de Lógica, pois há uma variedade de materiais, disponibilizados por professores da área.\n" + "Faça bom proveito do app: tire suas dúvidas, resolva exercícios e esteja por dentro da sua evolução ao acessar o diagnóstico.\n" + "\n" + "* Os materiais aqui presentes são de autoria da professora Márcia Martins e professor Emmerson Santa Rita. Aproveitamos para agradecê-los pelo amparo.";

        View aboutPage = new AboutPage(this)
                .setImage(R.drawable.loginelson)
                .addGroup("Fale conosco")
                .addEmail("helplogic2019@gmail.com","Envie um e-mail")
                .addGroup("Acesse nosso Github")
                .setDescription(texto)
                .addGitHub("pepes7/helplogic","Github")
                .create();

        getSupportActionBar().setTitle("Sobre");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(aboutPage);
    }
}
