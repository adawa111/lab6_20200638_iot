package com.example.lab6_20200638_iot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab6_20200638_iot.Bean.Egreso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarEgresoActivity extends AppCompatActivity {

    private EditText tituloEditText, descripcionEditText, montoEditText, fechaEditText;
    private Button btnGuardar;

    FirebaseFirestore db;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_egreso);
        db = FirebaseFirestore.getInstance();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        tituloEditText = findViewById(R.id.tituloEditText2);
        descripcionEditText = findViewById(R.id.descripcionEditText2);
        montoEditText = findViewById(R.id.montoEditText2);
        fechaEditText = findViewById(R.id.fechaEditText2);
        Button btnGuardar = findViewById(R.id.btnGuardar2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Egreso egreso = new Egreso() ;
                egreso.setTitulo(tituloEditText.getText().toString().trim());
                egreso.setDescription( descripcionEditText.getText().toString().trim());
                //egreso.setFecha(fechaEditText.getText().toString().trim());
                egreso.setMonto( Double.parseDouble(montoEditText.getText().toString().trim()) );
                egreso.setIduser( currentUser.getUid());
                db.collection( "egresos" )
                        .document()
                        .set(egreso)
                        .addOnSuccessListener(unused -> {
                            Log.d("msg-test" ,"Data guardada exitosamente ");
                            Intent intent = new Intent(RegistrarEgresoActivity.this, MainActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> e.printStackTrace()) ;
            }
        });

    }
}