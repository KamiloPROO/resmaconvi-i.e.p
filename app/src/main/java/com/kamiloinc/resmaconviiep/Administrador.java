package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Administrador extends AppCompatActivity {

    Button agregarDocente, agregarEstudiante, agregarManual, irAMenuReportes, verDocentes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        referenciar2();
        conexiones();

    }

    private void conexiones() {

        agregarDocente = findViewById(R.id.btn_agregarDocente);
        agregarEstudiante = findViewById(R.id.btn_agregarEstudiante);
        agregarManual = findViewById(R.id.btn_agregarManual);
        irAMenuReportes = findViewById(R.id.btn_verReportes);
        verDocentes = findViewById(R.id.btn_verDocentes);

        agregarDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Administrador.this, AgregarDocentes.class );
                startActivity(intent);

            }
        });

        agregarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Administrador.this, AgregarEstudiantes.class );
                startActivity(intent);

            }
        });

        agregarManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Administrador.this, AgregarTiposDeFalta.class );
                startActivity(intent);

            }
        });

        irAMenuReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Administrador.this, MenuReportes.class );
                startActivity(intent);

            }
        });

        verDocentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Administrador.this, VerDocentes.class );
                startActivity(intent);

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
                        return true;
                }

                return false;
            }
        });
    }
}