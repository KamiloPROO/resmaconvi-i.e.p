package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.kamiloinc.resmaconviiep.Model.DataEstudiantes;
import com.kamiloinc.resmaconviiep.Model.DataVerTodosLosReportes;

import java.util.ArrayList;
import java.util.List;

public class VerReportes extends AppCompatActivity {

    List<DataVerTodosLosReportes> listDatos;
    AdaptadorVerReportes adaptadorEstudiantes;
    FirebaseStorage storageRef;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes);

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.rcVerTodosLosReportes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listDatos = new ArrayList<DataVerTodosLosReportes>();

        llenarLista();
        referenciar2();

    }

    private void llenarLista() {




        db.collection("2023").orderBy("fecha", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombreUser = document.getString("nombreUser");
                                String cadenaImgCorreo = document.getString("imgCorreo");
                                String cadenaAnio = document.getString("año");
                                String cadenatipoFaltaSeleccionado = document.getString("tipoFaltaSeleccionado");
                                String cadenacursoSeleccionado = document.getString("cursoSeleccionado");
                                String cadenafaltaCometida = document.getString("faltaCometida");
                                String cadenapersonaReportada = document.getString("personaReportada");
                                String cadenacompromisoEstudiante = document.getString("compromisoEstudiante");




                                DataVerTodosLosReportes datos = new DataVerTodosLosReportes(cadenaNombreUser, cadenaImgCorreo, cadenaAnio,cadenatipoFaltaSeleccionado,cadenacursoSeleccionado,cadenafaltaCometida,cadenapersonaReportada,cadenacompromisoEstudiante);
                                listDatos.add(datos);

                                adaptadorEstudiantes = new AdaptadorVerReportes(VerReportes.this, listDatos);
                                recyclerView.setAdapter(adaptadorEstudiantes);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.admin);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext()
                                , Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.inicio:
                        startActivity(new Intent(getApplicationContext()
                                , Inicio.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.reportar:
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext()
                                , Administrador.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }

}