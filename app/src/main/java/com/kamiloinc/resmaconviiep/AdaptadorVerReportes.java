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

        Glide.with(context).load(datos.getImgCorreo()).into(holder.perfil);
        holder.nombreUser.setText(datos.getNombreUser());
        holder.anioReport.setText(datos.getAnio());

        holder.personaReportada.setText(datos.getPersonaReportada());
        holder.cursoPersonaReportada.setText(datos.getCursoSeleccionado());
        holder.periodo.setText(datos.getPeriodoReporte());
        holder.docenteQRP.setText(datos.getDocenteQRP());
        holder.faltaCometidaPersonaReportadaN1.setText(datos.getFaltaCometidaN1());
        holder.faltaCometidaPersonaReportadaN2.setText(datos.getFaltaCometidaN2());
        holder.faltaCometidaPersonaReportadaN3.setText(datos.getFaltaCometidaN3());
        holder.correptivoPersonaReportada.setText(datos.getCorrectivoEstudiante());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreUser,anioReport, personaReportada, cursoPersonaReportada, periodo ,faltaCometidaPersonaReportadaN1,faltaCometidaPersonaReportadaN2,faltaCometidaPersonaReportadaN3,correptivoPersonaReportada,docenteQRP;

        ImageView perfil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            perfil = itemView.findViewById(R.id.imgPerfilItem);
            nombreUser = itemView.findViewById(R.id.nomUsuarioReport);
            anioReport = itemView.findViewById(R.id.anioReport);
            personaReportada = itemView.findViewById(R.id.nombrePersonaReportada);
            cursoPersonaReportada = itemView.findViewById(R.id.cursoPersonaReportada);
            periodo = itemView.findViewById(R.id.periodoPersonaReportada);
            docenteQRP = itemView.findViewById(R.id.docenteQRP);
            faltaCometidaPersonaReportadaN1 = itemView.findViewById(R.id.faltaCometidaPersonaReportadaN1);
            faltaCometidaPersonaReportadaN2 = itemView.findViewById(R.id.faltaCometidaPersonaReportadaN2);
            faltaCometidaPersonaReportadaN3 = itemView.findViewById(R.id.faltaCometidaPersonaReportadaN3);
            correptivoPersonaReportada = itemView.findViewById(R.id.correctivoPersonaReportada);

        }
    }

}
