package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgregarDocentes extends AppCompatActivity {

    String generoSpinner;
    Button agregarDocentesFirebase;

    EditText nombre, numDocumento, fechaNacimiento, genero, cargo, sede, especialidad, nivelEnsenianza, recidencia, numTelefono, correo;

    FirebaseFirestore firebaseFirestore;

    ProgressDialog pd;

    Spinner spinnerGenero;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_docentes);

        agregarDocentesFirebase = findViewById(R.id.btnAgregarDocenteForm);

        spinnerGenero = findViewById(R.id.idSpinnerGenero);

        nombre = findViewById(R.id.editNombreDocente);
        numDocumento = findViewById(R.id.editNumDocuDocente);
        fechaNacimiento = findViewById(R.id.editFechaNacimiento);
        //genero = findViewById(R.id.editGeneroDocente);
        cargo = findViewById(R.id.editCargoDocente);
        sede = findViewById(R.id.editSedeDocente);
        especialidad = findViewById(R.id.editEspecialidadDocente);
        nivelEnsenianza = findViewById(R.id.editNivelDeEnsenianzaDocente);
        recidencia = findViewById(R.id.editRecidenciaDocente);
        numTelefono = findViewById(R.id.editNumTelefonoDocente);
        correo = findViewById(R.id.editCorreoDocente);

        pd= new ProgressDialog(this);

        firebaseFirestore = FirebaseFirestore.getInstance();


        referenciar();
        referenciar2();

    }

    private void referenciar() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Generos, R.layout.styli_spiner);

        spinnerGenero.setAdapter(adapter);

        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               generoSpinner = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        agregarDocentesFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String nombreDocente = nombre.getText().toString().trim();
                String numDocumentoDocente = numDocumento.getText().toString().trim();
                String fechaNacimientoDocente = fechaNacimiento.getText().toString().trim();
                String generoDocente = generoSpinner.trim();
                String cargoDocente = cargo.getText().toString().trim();
                String sedeDocente = sede.getText().toString().trim();
                String especialidadDocente = especialidad.getText().toString().trim();
                String nivelEnsenianzaDocente = nivelEnsenianza.getText().toString().trim();
                String recidenciaDocente = recidencia.getText().toString().trim();
                String numTelefonoDocente = numTelefono.getText().toString().trim();
                String correoDocente = correo.getText().toString().trim();

                if (nombreDocente.isEmpty() && numDocumentoDocente.isEmpty() && fechaNacimientoDocente.isEmpty() && generoDocente.isEmpty() && cargoDocente.isEmpty() && sedeDocente.isEmpty() && especialidadDocente.isEmpty() && nivelEnsenianzaDocente.isEmpty() && recidenciaDocente.isEmpty() && numTelefonoDocente.isEmpty() && correoDocente.isEmpty() ){

                    //Toast.makeText(AgregarDocentes.this, "Por favor ingresa todos los datos para poder continuar...", Toast.LENGTH_SHORT).show();

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();


                }else {

                    subimosFirebase(nombreDocente,numDocumentoDocente,fechaNacimientoDocente,generoDocente,cargoDocente,sedeDocente,especialidadDocente,nivelEnsenianzaDocente,recidenciaDocente,numTelefonoDocente,correoDocente);

                }

            }
        });

    }

    private void subimosFirebase(String nombreDocente, String numDocumentoDocente, String fechaNacimientoDocente, String generoDocente, String cargoDocente, String sedeDocente, String especialidadDocente , String nivelEnsenianzaDocente, String recidenciaDocente, String numTelefonoDocente, String correoDocente) {

        pd.setTitle("Añadiendo Docente...");

        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap<>();


        map.put("id", id);
        map.put("nombre", nombreDocente);
        map.put("numDocumento", numDocumentoDocente);
        map.put("fechaNacimiento", fechaNacimientoDocente);
        map.put("genero",generoDocente);
        map.put("cargo", cargoDocente);
        map.put("sede", sedeDocente);
        map.put("especialidad", especialidadDocente);
        map.put("nivelDeEnsenianza", nivelEnsenianzaDocente);
        map.put("recidencia", recidenciaDocente);
        map.put("numTelefono", numTelefonoDocente);
        map.put("correo", correoDocente);



        firebaseFirestore.collection("Docentes").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(AgregarDocentes.this, "Docente Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent( AgregarDocentes.this, Administrador.class );
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(AgregarDocentes.this, "Error Al Agregar Docente", Toast.LENGTH_SHORT).show();


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