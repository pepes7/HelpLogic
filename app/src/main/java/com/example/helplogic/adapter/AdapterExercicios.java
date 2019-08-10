package com.example.helplogic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helplogic.R;
import com.example.helplogic.model.Exercicios;

import java.util.List;

public class AdapterExercicios extends RecyclerView.Adapter<AdapterExercicios.MyViewHolder> {

    private List<Exercicios> exercicios;
    private Context context;

    public AdapterExercicios(List<Exercicios> exercicios, Context context) {
        this.exercicios = exercicios;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_exercicios,viewGroup,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Exercicios exercicio = exercicios.get(i);
        myViewHolder.titulo.setText(exercicio.getTitulo());

    }

    @Override
    public int getItemCount() {
        return exercicios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;

        public MyViewHolder(View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.buttonTitulo);
        }
    }
}