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

public class RegistrarIngresoActivity extends AppCompatActivity {

    private EditText tituloEditText, descripcionEditText, montoEditText, fechaEditText;
    private Button btnGuardar;

    FirebaseFirestore db;
    //ayuda de chatgpt

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_ingreso);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        tituloEditText = findViewById(R.id.tituloEditText);
        descripcionEditText = findViewById(R.id.descripcionEditText);
        montoEditText = findViewById(R.id.montoEditText);
        fechaEditText = findViewById(R.id.fechaEditText);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Egreso egreso = new Egreso() ;
                egreso.setTitulo(tituloEditText.getText().toString().trim());
                egreso.setDescription( descripcionEditText.getText().toString().trim());
                //egreso.setFecha(fechaEditText.getText().toString().trim());
                egreso.setMonto( Double.parseDouble(montoEditText.getText().toString().trim()) );
                egreso.setIduser( currentUser.getUid());
                db.collection( "ingresos" )
                        .document()
                        .set(egreso)
                        .addOnSuccessListener(unused -> {
                            Log.d("msg-test" ,"Data guardada exitosamente ");
                            Intent intent = new Intent(RegistrarIngresoActivity.this, MainActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> e.printStackTrace()) ;
            }
        });

    }
}