package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Inicio extends AppCompatActivity {

   Button faltas1, faltas2, faltas3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        faltas1 = findViewById(R.id.btnSituaciones1);
        faltas2 = findViewById(R.id.btnSituaciones2);
        faltas3 = findViewById(R.id.btnSituaciones3);

        referenciar();
        referenciar2();


    }

    private void referenciar() {

        faltas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 1");
                startActivity(intent);

            }
        });

        faltas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 2");
                startActivity(intent);

            }
        });

        faltas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 3");
                startActivity(intent);

            }
        });

    }



    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.inicio);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext()
                                , Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext()
                                , Administrador.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.reportar:
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.inicio:
                        return true;
                }

                return false;
            }
        });
    }


}