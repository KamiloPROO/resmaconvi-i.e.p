package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class AgregarDocentes extends AppCompatActivity {

    Button agregarDocentesFirebase;

    EditText nombre, numDocumento, fechaNacimiento, genero, cargo, sede, especialidad, nivelEnsenianza, recidencia, numTelefono, correo;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_docentes);

        agregarDocentesFirebase.findViewById(R.id.btn_agregarDocente);

        nombre.findViewById(R.id.editNombreDocente);
        numDocumento.findViewById(R.id.editNumDocuDocente);
        fechaNacimiento.findViewById(R.id.editFechaNacimiento);
        genero.findViewById(R.id.editGeneroDocente);
        cargo.findViewById(R.id.editGeneroDocente);
        sede.findViewById(R.id.editSedeDocente);
        especialidad.findViewById(R.id.editEspecialidadDocente);
        nivelEnsenianza.findViewById(R.id.editNivelDeEnsenianzaDocente);
        recidencia.findViewById(R.id.editRecidenciaDocente);
        numTelefono.findViewById(R.id.editNumTelefonoDocente);
        correo.findViewById(R.id.editCorreoDocente);

        firebaseFirestore = FirebaseFirestore.getInstance();


        referenciar();
        referenciar2();

    }

    private void referenciar() {

   agregarDocentesFirebase.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

           String nombreDocente = nombre.getText().toString().trim();
           String numDocumentoDocente = numDocumento.getText().toString().trim();
           String fechaNacimientoDocente = fechaNacimiento.getText().toString().trim();
           String generoDocente = genero.getText().toString().trim();
           String cargoDocente = cargo.getText().toString().trim();
           String sedeDocente = sede.getText().toString().trim();
           String especialidadDocente = especialidad.getText().toString().trim();
           String nivelEnsenianzaDocente = nivelEnsenianza.getText().toString().trim();
           String recidenciaDocente = recidencia.getText().toString().trim();
           String numTelefonoDocente = numTelefono.getText().toString().trim();
           String correoDocente = correo.getText().toString().trim();

           if (nombreDocente.isEmpty() && numDocumentoDocente.isEmpty() && fechaNacimientoDocente.isEmpty() && generoDocente.isEmpty() && cargoDocente.isEmpty() && sedeDocente.isEmpty()
           && especialidadDocente.isEmpty() && nivelEnsenianzaDocente.isEmpty() && recidenciaDocente.isEmpty() && numTelefonoDocente.isEmpty() && correoDocente.isEmpty() ){

               Toast.makeText(AgregarDocentes.this, "Por favor ingresa todos los datos para poder continuar...", Toast.LENGTH_SHORT).show();

           }else {

               subimosFirebase(nombreDocente,numDocumentoDocente,fechaNacimientoDocente,generoDocente,cargoDocente,sedeDocente,especialidadDocente,nivelEnsenianzaDocente,recidenciaDocente,numTelefonoDocente,correoDocente);

           }


       }
   });


    }

    private void subimosFirebase(String nombreDocente, String numDocumentoDocente, String fechaNacimientoDocente, String generoDocente, String cargoDocente, String sedeDocente, String especialidadDocente , String nivelEnsenianzaDocente, String recidenciaDocente, String numTelefonoDocente, String correoDocente) {

        Map<String, Object> map = new HashMap<>();

        map.put("nombre", nombreDocente);
        map.put("numDocumento", numDocumentoDocente);
        map.put("fechaNacimiento", fechaNacimientoDocente);
        map.put("genero", generoDocente);
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

              Toast.makeText(AgregarDocentes.this, "Docente Agregado Correctamente", Toast.LENGTH_SHORT).show();

          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {

              Toast.makeText(AgregarDocentes.this, "Error Al Agregar Docente", Toast.LENGTH_SHORT).show();


          }
      });

    }

    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        //bottomNavigationView.setSelectedItemId(R.id.inicio);

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
                        return true;
                }

                return false;
            }
        });
    }


}