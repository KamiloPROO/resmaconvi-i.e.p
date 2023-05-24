package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuFaltas extends AppCompatActivity {

    Button verSituacione1,verSituacione2,verSituacione3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_faltas);

        verSituacione1 = findViewById(R.id.btn_verSituaciones1);
        verSituacione2 = findViewById(R.id.btn_verSituaciones2);
        verSituacione3 = findViewById(R.id.btn_verSituaciones3);


        conexiones();
        referenciar2();

    }

    private void conexiones() {

        verSituacione1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuFaltas.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 1");
                startActivity(intent);

            }
        });

        verSituacione2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuFaltas.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 2");
                startActivity(intent);

            }
        });


        verSituacione3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuFaltas.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 2");
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
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext()
                                , Administrador.class));
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
                }

                return false;
            }
        });
    }

}