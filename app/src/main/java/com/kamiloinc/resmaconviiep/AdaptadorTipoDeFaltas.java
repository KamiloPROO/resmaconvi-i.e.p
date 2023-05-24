package com.kamiloinc.resmaconviiep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamiloinc.resmaconviiep.Model.DataDocentes;
import com.kamiloinc.resmaconviiep.Model.DataTipoDeFaltas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorTipoDeFaltas extends RecyclerView.Adapter<AdaptadorTipoDeFaltas.ViewHolder> {

    List<DataTipoDeFaltas> listDatos;
    Context context;
    List<DataTipoDeFaltas> originalItems;

    public AdaptadorTipoDeFaltas(Context context, List<DataTipoDeFaltas> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemfaltas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataTipoDeFaltas datos = listDatos.get(position);


        holder.descri.setText(datos.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descri;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descri = itemView.findViewById(R.id.descriFalta);


        }
    }
}
