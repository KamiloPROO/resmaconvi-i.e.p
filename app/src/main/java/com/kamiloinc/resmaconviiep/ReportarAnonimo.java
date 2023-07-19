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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportarAnonimo extends AppCompatActivity {

    Spinner ListCursos, ListEstudiantes, ListTipoDeFalta, ListFaltas, peridos, reportePara, ListDocentes ,ListFaltasDocentes;

    TextView selectEstudiante, SelecFaltaTipo, SelecFaltaCometida, textSelecAquienAno,textSelecDocenteAnonimoMelo, txtCursos;

    ProgressDialog pd;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser user;

    String tipoFaltaSelect , cursoSelect, estudianteSelect , faltaSelect ,periSelect , aQuienSelect, docenteSelect,faltaSelectDocentes;

    EditText editCompromisoEstudiante;

    Button reportarAnonimo;

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_anonimo);

        data = getIntent().getExtras();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        pd= new ProgressDialog(this);

        ListCursos = findViewById(R.id.idSpinnerSeleCursosAnonimo);
        ListEstudiantes = findViewById(R.id.idSpinnerSeleEstudianteAnonimo);
        ListTipoDeFalta = findViewById(R.id.idSpinnerSeleFaltaTipoDeFaltaAnonimo);
        ListFaltas = findViewById(R.id.idSpinnerSeleFaltaAnonimo);
        peridos = findViewById(R.id.idSpinnerSelePeriodosAnonimo);
        reportePara = findViewById(R.id.idSpinnerSeleReportePara);
        ListDocentes = findViewById(R.id.idSpinnerSeleDocenteAnonimo);
        ListFaltasDocentes = findViewById(R.id.idSpinnerSeleFaltaDocenteAnonimo);

        selectEstudiante = findViewById(R.id.textSelecEstudianteAnonimo);
        SelecFaltaTipo = findViewById(R.id.textSelecFaltaTipoAnonimo);
        SelecFaltaCometida = findViewById(R.id.textSelecFaltaCometidaAnonimo);
        textSelecAquienAno = findViewById(R.id.textSelecAquien);
        textSelecDocenteAnonimoMelo = findViewById(R.id.textSelecDocenteAnonimo);
        txtCursos = findViewById(R.id.textSelecCursosAno);

        editCompromisoEstudiante = findViewById(R.id.editCompromisoEstudianteAnonimo);

        reportarAnonimo = findViewById(R.id.btnReportarAnimo);


        llenarSpiners();
        subimosFirestore();
        referenciar2();


    }

    private void llenarSpiners() {

        ArrayAdapter<CharSequence> adapterPeri = ArrayAdapter.createFromResource(this,R.array.PeriodosAcademicos, R.layout.styli_spiner);

        peridos.setAdapter(adapterPeri);

        peridos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                periSelect = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<CharSequence> adapterPara = ArrayAdapter.createFromResource(this,R.array.reportePara, R.layout.styli_spiner);

        reportePara.setAdapter(adapterPara);

        reportePara.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                aQuienSelect = adapterView.getItemAtPosition(i).toString();

                if (aQuienSelect.equals("Docente")){


                    llenarListaDocentes();
                    textSelecDocenteAnonimoMelo.setVisibility(View.VISIBLE);
                    ListDocentes.setVisibility(View.VISIBLE);
                    txtCursos.setVisibility(View.GONE);
                    ListCursos.setVisibility(View.GONE);
                    ListTipoDeFalta.setVisibility(View.GONE);
                    SelecFaltaCometida.setVisibility(View.GONE);
                    reportarAnonimo.setVisibility(View.GONE);

                    cursoSelect = "Docente";
                    tipoFaltaSelect = "Incumplimiento a sus Obligaciones";





                }else {

                    llenarCursos();
                    txtCursos.setVisibility(View.VISIBLE);
                    ListCursos.setVisibility(View.VISIBLE);
                    textSelecDocenteAnonimoMelo.setVisibility(View.GONE);
                    ListDocentes.setVisibility(View.GONE);
                    SelecFaltaCometida.setVisibility(View.GONE);
                    reportarAnonimo.setVisibility(View.GONE);
                   ListFaltasDocentes.setVisibility(View.GONE);

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });


    }

    private void llenarCursos() {

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

                                ArrayAdapter<DataSpinnerEstudiante> arrayAdapter = new ArrayAdapter<>(ReportarAnonimo.this, R.layout.styli_spiner, estudiantes);
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
                    editCompromisoEstudiante.setVisibility(View.GONE);
                    reportarAnonimo.setVisibility(View.VISIBLE);
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

                                ArrayAdapter<DataSpinnerFaltasCometidas> arrayAdapter = new ArrayAdapter<>(ReportarAnonimo.this, R.layout.styli_spiner, faltasCometidas);
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

    private void llenarListaDocentes() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSliderDocentes> docentes = new ArrayList<>();

        firebaseFirestore.collection("Docentes").orderBy("nombre", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombre = document.getString("nombre");


                                DataSliderDocentes datos = new DataSliderDocentes(cadenaNombre);
                                docentes.add(datos);

                                ArrayAdapter<DataSliderDocentes> arrayAdapter = new ArrayAdapter<>(ReportarAnonimo.this, R.layout.styli_spiner, docentes);
                                ListDocentes.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });


        ListDocentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                estudianteSelect = adapterView.getItemAtPosition(i).toString();
                llenarSpinerFaltasCometidasDocente();
                SelecFaltaCometida.setVisibility(View.VISIBLE);
                ListFaltasDocentes.setVisibility(View.VISIBLE);
                reportarAnonimo.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void llenarSpinerFaltasCometidasDocente() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FaltasDocentes, R.layout.styli_spiner);

        ListFaltasDocentes.setAdapter(adapter);

        ListFaltasDocentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        reportarAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String periodoSeleccionado = periSelect.trim();
                String cursoSeleccionado = cursoSelect.trim();
                String estudianteSeleccionado = estudianteSelect.trim();
                String docenteSeleccionado = estudianteSelect.trim();
                String faltaDocenteCometida = faltaSelect.trim();
                String tipoFaltaSeleccionado = tipoFaltaSelect.trim();
                String faltaCometida = faltaSelect.trim();

                if (cursoSeleccionado.isEmpty() && estudianteSeleccionado.isEmpty() && tipoFaltaSeleccionado.isEmpty() && faltaCometida.isEmpty() && docenteSeleccionado.isEmpty() && faltaDocenteCometida.isEmpty()){

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();

                }else{

                    subimosDataFirestore(periodoSeleccionado,cursoSeleccionado,estudianteSeleccionado,tipoFaltaSeleccionado,faltaCometida,faltaDocenteCometida,docenteSeleccionado);
                    subimosDataPersonal(periodoSeleccionado,cursoSeleccionado,estudianteSeleccionado,tipoFaltaSeleccionado,faltaCometida,faltaDocenteCometida,docenteSeleccionado);

                }

            }
        });

    }



    private void subimosDataFirestore(String periodoSeleccionado, String cursoSeleccionado, String estudianteSeleccionado, String tipoFaltaSeleccionado, String faltaCometida, String faltaDocenteCometida, String docenteSeleccionado) {

        String anio = data.getString("anioSeleccionadoAnonimoReporte");
        String todo = "Todos";

        String MeloFirestore = periodoSeleccionado +" "+ anio + " " + todo;

        pd.setTitle("Reportando...");

        pd.show();


        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();


        Date date = new Date();
        String fecha = date.toString();

        Map<String, Object> map = new HashMap<>();


        map.put("idUser","Anonimo");
        map.put("correoUser","Anonimo");
        map.put("nombreUser","Anonimo");
        map.put("imgCorreo","Anonimo");

        map.put("id", id);
        map.put("año",anio);
        map.put("fecha", fecha);
        map.put("tipoFaltaSeleccionado", tipoFaltaSeleccionado);


        map.put("periodoAcademico",periodoSeleccionado);
        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometida", faltaCometida);
        map.put("personaReportada", estudianteSeleccionado);
        map.put("compromisoEstudiante", "No Aplica (Reporte Anonimo)");



        firebaseFirestore.collection(MeloFirestore).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarAnonimo.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarAnonimo.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarAnonimo.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void subimosDataPersonal(String periodoSeleccionado, String cursoSeleccionado, String estudianteSeleccionado, String tipoFaltaSeleccionado, String faltaCometida, String faltaDocenteCometida, String docenteSeleccionado) {

        String anio = data.getString("anioSeleccionadoAnonimoReporte");

        pd.setTitle("Reportando...");

        pd.show();

        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();


        Date date = new Date();
        String fecha = date.toString();

        Map<String, Object> map = new HashMap<>();


        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);

        map.put("id", id);
        map.put("año",anio);
        map.put("fecha", fecha);
        map.put("tipoFaltaSeleccionado", tipoFaltaSeleccionado);


        map.put("periodoAcademico",periodoSeleccionado);
        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometida", faltaCometida);
        map.put("personaReportada", estudianteSeleccionado);
        map.put("compromisoEstudiante", "No Aplica (Reporte Anonimo)");


        firebaseFirestore.collection(nombreUser).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarAnonimo.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarAnonimo.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarAnonimo.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


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