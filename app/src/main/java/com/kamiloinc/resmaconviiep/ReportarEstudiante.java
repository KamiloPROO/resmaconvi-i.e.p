package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kamiloinc.resmaconviiep.Model.DataSliderDocentes;
import com.kamiloinc.resmaconviiep.Model.DataSpinnerEstudiante;
import com.kamiloinc.resmaconviiep.Model.DataSpinnerFaltasCometidas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportarEstudiante extends AppCompatActivity {


    Spinner ListCursos, ListEstudiantes, ListTipoDeFalta, ListFaltas;

    TextView selectEstudiante, SelecFaltaTipo, SelecFaltaCometida;

    ProgressDialog pd;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser user;

    String cursoSelect, estudianteSelect, tipoFaltaSelect, faltaSelect;

    EditText editCompromisoEstudiante;

    Button reportarEstudiante;

    Bundle data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_estudiante);

        data = getIntent().getExtras();

        selectEstudiante = findViewById(R.id.textSelecEstudiante);
        SelecFaltaTipo = findViewById(R.id.textSelecFaltaTipo);
        SelecFaltaCometida = findViewById(R.id.textSelecFaltaCometida);


        ListCursos = findViewById(R.id.idSpinnerSeleCursos);
        ListEstudiantes = findViewById(R.id.idSpinnerSeleEstudiante);
        ListTipoDeFalta = findViewById(R.id.idSpinnerSeleFaltaTipoDeFalta);
        ListFaltas = findViewById(R.id.idSpinnerSeleFalta);

        editCompromisoEstudiante = findViewById(R.id.editCompromisoEstudiante);
        reportarEstudiante = findViewById(R.id.btnReportarEstudiante);


        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        pd= new ProgressDialog(this);


        llenarSpinerCursos();
        subimosFirestore();
        referenciar2();



    }


    private void llenarSpinerCursos() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cursos, R.layout.styli_spiner);

        ListCursos.setAdapter(adapter);

        ListCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cursoSelect = adapterView.getItemAtPosition(i).toString();

                if (cursoSelect.equals("Selecionar Curso")){


                }else{
                    llenarSpinerEstudiante();
                    selectEstudiante.setVisibility(View.VISIBLE);
                    ListEstudiantes.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void llenarSpinerEstudiante() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerEstudiante> estudiantes = new ArrayList<>();

        firebaseFirestore.collection(cursoSelect).orderBy("nombre", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombre = document.getString("nombre");


                                DataSpinnerEstudiante datos = new DataSpinnerEstudiante(cadenaNombre);
                                estudiantes.add(datos);

                                ArrayAdapter<DataSpinnerEstudiante> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, estudiantes);
                                ListEstudiantes.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

              ListEstudiantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                      estudianteSelect = adapterView.getItemAtPosition(i).toString();
                      llenarSpinerTipoFaltas();
                      SelecFaltaTipo.setVisibility(View.VISIBLE);
                      ListTipoDeFalta.setVisibility(View.VISIBLE);

                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> adapterView) {

                  }
              });


    }

    private void llenarSpinerTipoFaltas() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, R.layout.styli_spiner);

        ListTipoDeFalta.setAdapter(adapter);

        ListTipoDeFalta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipoFaltaSelect = adapterView.getItemAtPosition(i).toString();

                if (tipoFaltaSelect.equals("Selecionar Tipo de Falta")){


                }else {

                    llenarSpinerFaltasCometidas();
                    SelecFaltaCometida.setVisibility(View.VISIBLE);
                    ListFaltas.setVisibility(View.VISIBLE);
                    editCompromisoEstudiante.setVisibility(View.VISIBLE);
                    reportarEstudiante.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerFaltasCometidas() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerFaltasCometidas> faltasCometidas = new ArrayList<>();

        firebaseFirestore.collection(tipoFaltaSelect).orderBy("descripcion", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaDescri = document.getString("descripcion");


                                DataSpinnerFaltasCometidas datos = new DataSpinnerFaltasCometidas(cadenaDescri);
                                faltasCometidas.add(datos);

                                ArrayAdapter<DataSpinnerFaltasCometidas> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, faltasCometidas);
                                ListFaltas.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

        ListFaltas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSelect = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }




    private void subimosFirestore() {

        reportarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cursoSeleccionado = cursoSelect.trim();
                String estudianteSeleccionado = estudianteSelect.trim();
                String tipoFaltaSeleccionado = tipoFaltaSelect.trim();
                String faltaCometida = faltaSelect.trim();
                String compromisoEstudiante = editCompromisoEstudiante.getText().toString().trim();

                if (cursoSeleccionado.isEmpty() && estudianteSeleccionado.isEmpty() && tipoFaltaSeleccionado.isEmpty() && faltaCometida.isEmpty() && compromisoEstudiante.isEmpty()){

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();

                }else{

                    subimosDataFirestore(cursoSeleccionado,estudianteSeleccionado,tipoFaltaSeleccionado,faltaCometida,compromisoEstudiante);
                    subimosDataPersonal(cursoSeleccionado,estudianteSeleccionado,tipoFaltaSeleccionado,faltaCometida,compromisoEstudiante);

                }

            }
        });


    }



    private void subimosDataFirestore(String cursoSeleccionado, String estudianteSeleccionado, String tipoFaltaSeleccionado, String faltaCometida, String compromisoEstudiante) {

        String anio = data.getString("anioSeleccionadoEstudiante");

        pd.setTitle("Reportando Estudiante...");

        pd.show();


        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Map<String, Object> map = new HashMap<>();


        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);

        map.put("id", id);
        map.put("año",anio);
        map.put("tipoFaltaSeleccionado", tipoFaltaSeleccionado);



        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometida", faltaCometida);
        map.put("personaReportada", estudianteSeleccionado);
        map.put("compromisoEstudiante", compromisoEstudiante);


        firebaseFirestore.collection(anio).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarEstudiante.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });


    }


    private void subimosDataPersonal(String cursoSeleccionado, String estudianteSeleccionado, String tipoFaltaSeleccionado, String faltaCometida, String compromisoEstudiante) {

        String anio = data.getString("añoSeleccionado");

        pd.setTitle("Reportando Estudiante...");

        pd.show();


        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);

        map.put("id", id);
        map.put("año",anio);
        map.put("tipoFaltaSeleccionado", tipoFaltaSeleccionado);



        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometida", faltaCometida);
        map.put("personaReportada", estudianteSeleccionado);
        map.put("compromisoEstudiante", compromisoEstudiante);

        firebaseFirestore.collection(nombreUser).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarEstudiante.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });



    }

    private void referenciar2() {

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
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }

}