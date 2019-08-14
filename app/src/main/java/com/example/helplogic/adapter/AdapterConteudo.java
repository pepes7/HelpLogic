package com.example.helplogic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.model.Conteudo;

import java.util.List;

public class AdapterConteudo extends RecyclerView.Adapter<AdapterConteudo.MyViewHolder> {

    private List<Conteudo> conteudos;
    private Context context;

    public AdapterConteudo(List<Conteudo> conteudos, Context context) {
        this.conteudos = conteudos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_conteudos,viewGroup,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Conteudo conteudo = conteudos.get(i);
        myViewHolder.titulo.setText(conteudo.getTitulo());

    }

    @Override
    public int getItemCount() {
        return conteudos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;

        public MyViewHolder(View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.buttonTituloConteudo);
        }
    }
}