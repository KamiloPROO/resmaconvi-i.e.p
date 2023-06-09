package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.kamiloinc.resmaconviiep.Model.DataVerTodosLosReportes;

import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    ImageButton imgCerarSesion,imgCerarSesion2, eliminarCuenta;
    //Button btnCerrar;
    /*Button btnEliCu;*/
    private FirebaseAuth mAuth;
    private TextView textid, textnombre, textemail;
    private ImageView imagenUser;


    List<DataVerTodosLosReportes> listDatos;
    AdaptadorVerReportes adaptadorEstudiantes;
    FirebaseStorage storageRef;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.rcVerReportesPersonales);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listDatos = new ArrayList<DataVerTodosLosReportes>();
        user = FirebaseAuth.getInstance().getCurrentUser();

        llenarLista();

        referenciar2();
        imagenUser = findViewById(R.id.imagenUser);
        textid = findViewById(R.id.textid);
        textnombre = findViewById(R.id.textNom);
        textemail = findViewById(R.id.textCorreo);
        imgCerarSesion = findViewById(R.id.cerrarsesion);
        imgCerarSesion2 = findViewById(R.id.cerrarsesion2);
        eliminarCuenta = findViewById(R.id.eliminarcuenta);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        textid.setText(currentUser.getUid());
        textnombre.setText(currentUser.getDisplayName());
        textemail.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(imagenUser);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        imgCerarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            Perfil.this.finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "No se pudo cerrar sesion con Google", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imgCerarSesion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, Inicio.class);
                startActivity(intent);
            }
        });

        eliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if (signInAccount != null) {
                    AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("Perfil", "onSuccess::Usuario Eliminado");
                                        signOut();
                                    }
                                });
                            } else {
                                Log.e("Perfil", "onComplete: Error al eliminar el usuario",
                                        task.getException());
                            }
                        }
                    });
                } else {
                    Log.d("Perfil", "Error: reAuthenticateUser: user account is null");
                }
            }
        });

    }

    private void llenarLista() {

        String nombreUser = user.getDisplayName();



        db.collection(nombreUser).orderBy("fecha", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombreUser = document.getString("nombreUser");
                                String cadenaImgCorreo = document.getString("imgCorreo");
                                String cadenaAnio = document.getString("año");
                                String cadenatipoFaltaSeleccionado = document.getString("tipoFaltaSeleccionado");
                                String cadenacursoSeleccionado = document.getString("cursoSeleccionado");
                                String cadenaPeriodo = document.getString("periodoAcademico");
                                String cadenafaltaCometida = document.getString("faltaCometida");
                                String cadenapersonaReportada = document.getString("personaReportada");
                                String cadenacompromisoEstudiante = document.getString("compromisoEstudiante");




                                DataVerTodosLosReportes datos = new DataVerTodosLosReportes(cadenaNombreUser, cadenaImgCorreo, cadenaAnio,cadenatipoFaltaSeleccionado,cadenacursoSeleccionado,cadenaPeriodo,cadenafaltaCometida,cadenapersonaReportada,cadenacompromisoEstudiante);
                                listDatos.add(datos);

                                adaptadorEstudiantes = new AdaptadorVerReportes(Perfil.this, listDatos);
                                recyclerView.setAdapter(adaptadorEstudiantes);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.perfil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inicio:
                        startActivity(new Intent(getApplicationContext()
                                , Inicio.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext()
                                , Administrador.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.reportar:
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.perfil:
                        return true;
                }

                return false;
            }
        });
    }

    private void signOut() {
        //sign out de firebase
        FirebaseAuth.getInstance().signOut();
        //sign out de "google sign in"
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Perfil.this.finish();
            }
        });
    }



}