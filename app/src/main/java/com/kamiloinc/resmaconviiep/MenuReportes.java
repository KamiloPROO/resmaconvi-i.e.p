package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.units.qual.s;

public class MenuReportes extends AppCompatActivity {

   Button reportDocente, reportEstudiante, reportAnonimo;

    String UsuarioAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reportes);

        UsuarioAdmin = "depresivo";


        reportDocente = findViewById(R.id.btn_reportarDocente);
        reportEstudiante = findViewById(R.id.btn_reportarEstudiante);
        reportAnonimo = findViewById(R.id.btn_reportarAnonimamente);

       // reportDocente.setVisibility(View.GONE);
      //  reportAnonimo.setVisibility(View.GONE);


        roles();
        conexiones();
        referenciar2Admin();
        referenciar2User();
    }

    private void roles() {

        FirebaseUser usuarioLogin = FirebaseAuth.getInstance().getCurrentUser();


        if (usuarioLogin.getEmail().equals(UsuarioAdmin)) {

            BottomNavigationView bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
            BottomNavigationView bottomNavigationViewUser = findViewById(R.id.bottomNavigationView);


            bottomNavigationViewAdmin.setVisibility(View.VISIBLE);
            bottomNavigationViewUser.setVisibility(View.GONE);



        }else {

            BottomNavigationView bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
            BottomNavigationView bottomNavigationViewUser = findViewById(R.id.bottomNavigationView);


            bottomNavigationViewAdmin.setVisibility(View.GONE);
            bottomNavigationViewUser.setVisibility(View.VISIBLE);

        }


    }

    private void conexiones() {

        reportDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuReportes.this,SeleccionarAnioDocente.class);
                startActivity(intent);


            }
        });

        reportEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuReportes.this,SeleccionarAnioEstudiante.class);
                startActivity(intent);


            }
        });

        reportAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuReportes.this,SeleccionarAnioAnonimo.class);
                startActivity(intent);


            }
        });


    }


    private void referenciar2Admin() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewAdmin);


        bottomNavigationView.setSelectedItemId(R.id.reportar);

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

    private void referenciar2User() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.reportar);



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
                        return true;
                }

                return false;
            }
        });
    }
}