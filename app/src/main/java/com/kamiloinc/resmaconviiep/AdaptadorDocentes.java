package com.kamiloinc.resmaconviiep;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kamiloinc.resmaconviiep.Model.DataDocentes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorDocentes  extends RecyclerView.Adapter<AdaptadorDocentes.ViewHolder> {

    List<DataDocentes> listDatos;
    Context context;
    List<DataDocentes> originalItems;


    public AdaptadorDocentes(Context context, List<DataDocentes> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdocentes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataDocentes datos = listDatos.get(position);


        holder.nombre.setText(datos.getNombre());
        holder.numDocumento.setText(datos.getNumDocumento());
        holder.correo.setText(datos.getCorreo());
        holder.cargo.setText(datos.getCargo());
        holder.sede.setText(datos.getSede());
        holder.especialidad.setText(datos.getEspecialidad());
        holder.nivelEnseñanza.setText(datos.getNivelEnseñanza());
        holder.numeroTelefono.setText(datos.getNumeroTelefono());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    /*public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            listDatos.clear();
            listDatos.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<DataDocentes> collect = originalItems.stream()
                        .filter(i -> i.getCategorias().toLowerCase().contains(strSearch.toLowerCase()))
                        .collect(Collectors.toList());
                listDatos.clear();
                listDatos.addAll(collect);
            }
            else {
                for (Datos i : originalItems) {
                    if (i.getCategorias().toLowerCase().contains(strSearch.toLowerCase())){
                        listDatos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, numDocumento,correo, cargo, sede, especialidad, nivelEnseñanza, numeroTelefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreDocente);
            numDocumento = itemView.findViewById(R.id.numDocumentoDocente);
            correo = itemView.findViewById(R.id.correoDocente);
            cargo = itemView.findViewById(R.id.cargoDocente);
            sede = itemView.findViewById(R.id.sedeDocente);
            especialidad = itemView.findViewById(R.id.especialidadDocente);
            nivelEnseñanza = itemView.findViewById(R.id.nivelEnseñanzaDocente);
            numeroTelefono = itemView.findViewById(R.id.numTeleDocente);

        }
    }

}