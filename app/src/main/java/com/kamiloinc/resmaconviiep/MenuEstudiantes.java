package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuEstudiantes extends AppCompatActivity {

    Button prescolar, primero, segundo, tercero, cuarto, quinto, sexto, septimo, octavo, noveno, decimoA, decimoB, onceA, onceB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estudiantes);


       prescolar = findViewById(R.id.btn_gradoPrescolar);
       primero = findViewById(R.id.btn_gradoPrimero);
       segundo = findViewById(R.id.btn_gradoSegundo);
       tercero = findViewById(R.id.btn_gradoTercero);
       cuarto = findViewById(R.id.btn_gradoCuarto);
       quinto = findViewById(R.id.btn_gradoQuinto);
       sexto = findViewById(R.id.btn_gradoSexto);
       septimo = findViewById(R.id.btn_gradoSeptimo);
       octavo = findViewById(R.id.btn_gradoOctavo);
       noveno = findViewById(R.id.btn_gradoNoveno);
       decimoA = findViewById(R.id.btn_gradoDecimoA);
       decimoB = findViewById(R.id.btn_gradoDecimoB);
       onceA = findViewById(R.id.btn_gradoOnceA);
       onceB = findViewById(R.id.btn_gradoOnceB);


        conexiones();
        referenciar2();
    }

    private void conexiones() {

        prescolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "PreEscolar");
                startActivity(intent);

            }
        });

        primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Primero");
                startActivity(intent);

            }
        });

        segundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Segundo");
                startActivity(intent);

            }
        });
        tercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Tercero");
                startActivity(intent);

            }
        });
        cuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Cuarto");
                startActivity(intent);

            }
        });

        quinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Quinto");
                startActivity(intent);

            }
        });

        sexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Sexto");
                startActivity(intent);

            }
        });

        septimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Septimo");
                startActivity(intent);

            }
        });

        octavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Octavo");
                startActivity(intent);

            }
        });

        noveno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Noveno");
                startActivity(intent);

            }
        });

        decimoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Decimo A");
                startActivity(intent);

            }
        });

        decimoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Decimo B");
                startActivity(intent);

            }
        });

        onceA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Once A");
                startActivity(intent);

            }
        });

        onceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuEstudiantes.this, VerEstudiantes.class);
                intent.putExtra("curso", "Once B");
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
