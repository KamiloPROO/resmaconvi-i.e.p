package com.kamiloinc.resmaconviiep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamiloinc.resmaconviiep.Model.DataDeberesDerechos;
import com.kamiloinc.resmaconviiep.Model.DataTipoDeFaltas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDeberesDerechos  extends RecyclerView.Adapter<AdaptadorDeberesDerechos.ViewHolder> {

    List<DataDeberesDerechos> listDatos;
    Context context;
    List<DataDeberesDerechos> originalItems;

    public AdaptadorDeberesDerechos(Context context, List<DataDeberesDerechos> listDatos) {
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
        DataDeberesDerechos datos = listDatos.get(position);


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
