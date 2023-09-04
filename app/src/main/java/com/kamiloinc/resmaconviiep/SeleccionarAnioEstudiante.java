package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SeleccionarAnioEstudiante extends AppCompatActivity {

    Button continuarEstudiante;

    Spinner aniosSeleccion;

    String anioSeleccionado;

    String UsuarioAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_anio_estudiante);

        UsuarioAdmin = "depresivo";


        continuarEstudiante = findViewById(R.id.btnContinuarEstudiante);
        aniosSeleccion = findViewById(R.id.idSpinnerAniosEstudiante);

        roles();
        referenciar();
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
    private void referenciar() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.AniosReporte, R.layout.styli_spiner);

        aniosSeleccion.setAdapter(adapter);

        aniosSeleccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                anioSeleccionado = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        continuarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SeleccionarAnioEstudiante.this, ReportarEstudiante.class );
                intent.putExtra("anioSeleccionadoEstudiante",anioSeleccionado);
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
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);;
                }

                return false;
            }
        });
    }

}