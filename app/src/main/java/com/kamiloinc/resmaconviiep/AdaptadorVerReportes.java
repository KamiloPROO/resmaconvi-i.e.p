package com.kamiloinc.resmaconviiep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kamiloinc.resmaconviiep.Model.DataTipoDeFaltas;
import com.kamiloinc.resmaconviiep.Model.DataVerTodosLosReportes;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorVerReportes extends RecyclerView.Adapter<AdaptadorVerReportes.ViewHolder> {

    List<DataVerTodosLosReportes> listDatos;
    Context context;
    List<DataVerTodosLosReportes> originalItems;

    public AdaptadorVerReportes(Context context, List<DataVerTodosLosReportes> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver_reportes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorVerReportes.ViewHolder holder, int position) {

        DataVerTodosLosReportes datos = listDatos.get(position);


        holder.nombreUser.setText(datos.getNombreUser());
        holder.anioReport.setText(datos.getAnio());
        Glide.with(context).load(datos.getImgCorreo()).into(holder.perfil);

        holder.personaReportada.setText(datos.getPersonaReportada());
        holder.cursoPersonaReportada.setText(datos.getCursoSeleccionado());
        holder.tipoFaltaReportada.setText(datos.getTipoFaltaSeleccionado());
        holder.faltaCometidaPersonaReportada.setText(datos.getFaltaCometida());
        holder.compromisoPersonaReportada.setText(datos.getCompromisoEstudiante());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreUser,anioReport, personaReportada, cursoPersonaReportada, tipoFaltaReportada,faltaCometidaPersonaReportada,compromisoPersonaReportada;

        ImageView perfil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreUser = itemView.findViewById(R.id.nomUsuarioReport);
            anioReport = itemView.findViewById(R.id.anioReport);
            perfil = itemView.findViewById(R.id.imgPerfilItem);
            personaReportada = itemView.findViewById(R.id.nombrePersonaReportada);
            cursoPersonaReportada = itemView.findViewById(R.id.cursoPersonaReportada);
            tipoFaltaReportada = itemView.findViewById(R.id.tipoFaltaReportada);
            faltaCometidaPersonaReportada = itemView.findViewById(R.id.faltaCometidaPersonaReportada);
            compromisoPersonaReportada = itemView.findViewById(R.id.compromisoPersonaReportada);

        }
    }

}
