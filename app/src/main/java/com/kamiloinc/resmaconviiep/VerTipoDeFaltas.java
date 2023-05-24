package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.kamiloinc.resmaconviiep.Model.DataDocentes;
import com.kamiloinc.resmaconviiep.Model.DataEstudiantes;
import com.kamiloinc.resmaconviiep.Model.DataTipoDeFaltas;

import java.util.ArrayList;
import java.util.List;

public class VerTipoDeFaltas extends AppCompatActivity {

    List<DataTipoDeFaltas> listDatos;
    AdaptadorTipoDeFaltas adaptadorTipoDeFaltas;
    FirebaseStorage storageRef;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    Bundle data;

    TextView txttoast, tituFaltas;

    ImageView imgtoast;


    SwipeRefreshLayout refreshLayout2;

    int number = 0;

    int REQUEST_CODE = 200;

    FirebaseUser userAdmin = FirebaseAuth.getInstance().getCurrentUser();
    String admin = "stevenarb98@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tipo_de_faltas);

        data = getIntent().getExtras();

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.rcVerTiposDeFaltas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listDatos = new ArrayList<DataTipoDeFaltas>();

        tituFaltas = findViewById(R.id.txtTituFaltas);



        llenarLista();
        referenciar2();

    }

    private void llenarLista() {

        String falta = data.getString("tipoFalta");
        tituFaltas.setText(falta);

        db.collection(falta).orderBy("descripcion", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaDescri = document.getString("descripcion");



                                DataTipoDeFaltas datos = new DataTipoDeFaltas(cadenaDescri);
                                listDatos.add(datos);

                                adaptadorTipoDeFaltas = new AdaptadorTipoDeFaltas(VerTipoDeFaltas.this, listDatos);
                                recyclerView.setAdapter(adaptadorTipoDeFaltas);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
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