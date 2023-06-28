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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VerReportesMenuDocentes extends AppCompatActivity {

    Button continuarVerRepor;

    Spinner anioVer, periodoVer;

    String anioVerSele, periodoVerSele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes_menu);

        continuarVerRepor = findViewById(R.id.btnContinuarVerReportesDocentes);

        anioVer = findViewById(R.id.idSpinnerAniosVerReportesDocentes);
        periodoVer = findViewById(R.id.idSpinnerPeriodosVerReportesDocentes);

        referenciar();
        referenciar2();

    }

    private void referenciar() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.AniosReporte, R.layout.styli_spiner);

        anioVer.setAdapter(adapter);

        anioVer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                anioVerSele = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this,R.array.PeriodosAcademicos, R.layout.styli_spiner);

        periodoVer.setAdapter(adapterPeriodo);

        periodoVer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                periodoVerSele = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        continuarVerRepor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(anioVerSele.equals("Seleccionar Año")){

                    Toast.makeText(VerReportesMenuDocentes.this, "Seleccione un año", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent( VerReportesMenuDocentes.this, VerReportesDocentes.class );
                    intent.putExtra("anioVerDocente",anioVerSele);
                    intent.putExtra("periodoVerDocente",periodoVerSele);
                    startActivity(intent);

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