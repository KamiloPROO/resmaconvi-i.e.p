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
import com.kamiloinc.resmaconviiep.Model.DataEstudiantes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorEstudiantes  extends RecyclerView.Adapter<AdaptadorEstudiantes.ViewHolder> {

    List<DataEstudiantes> listDatos;
    Context context;
    List<DataEstudiantes> originalItems;


    public AdaptadorEstudiantes(Context context, List<DataEstudiantes> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemestudiantes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataEstudiantes datos = listDatos.get(position);


        holder.nombre.setText(datos.getNombre());
        holder.numDocumento.setText(datos.getNumDocumento());
        holder.curso.setText(datos.getCurso());
        holder.sede.setText(datos.getSede());


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

        TextView nombre, numDocumento,curso, sede;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreEstudiantes);
            numDocumento = itemView.findViewById(R.id.numDocumentoEstudiante);
            curso = itemView.findViewById(R.id.cursoEstudiante);
            sede = itemView.findViewById(R.id.sedeEstudiante);


        }
    }

}