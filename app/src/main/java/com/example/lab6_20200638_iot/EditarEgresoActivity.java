package com.example.lab6_20200638_iot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab6_20200638_iot.Bean.Egreso;
import com.example.lab6_20200638_iot.Bean.Ingreso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarEgresoActivity extends AppCompatActivity {


    private EditText descripcionEditText, montoEditText;

    private TextView tituloText, fechaText;
    private Button btnGuardar;

    FirebaseFirestore db;
    //ayuda de chatgpt

    private FirebaseAuth mAuth;
    private String documentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_egreso);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        tituloText = findViewById(R.id.tituloEditText2);
        descripcionEditText = findViewById(R.id.descripcionEditText2);
        montoEditText = findViewById(R.id.montoEditText2);
        fechaText = findViewById(R.id.fechaEditText2);
        Button btnGuardar = findViewById(R.id.btnGuardar2);

        documentId = getIntent().getStringExtra("DOCUMENT_ID");
        Egreso ingreso1 = (Egreso) getIntent().getSerializableExtra("egreso");
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Egreso egreso = new Egreso();
                egreso.setTitulo(tituloText.getText().toString().trim());
                egreso.setDescription(descripcionEditText.getText().toString().trim());
                egreso.setMonto(Double.parseDouble(montoEditText.getText().toString().trim()));
                egreso.setIduser(currentUser.getUid());

                if (documentId != null) {
                    // Update the existing document
                    db.collection("egresos").document(documentId)
                            .update("description", egreso.getDescription(), "monto", egreso.getMonto())
                            .addOnSuccessListener(unused -> {
                                Log.d("msg-test", "Data actualizada exitosamente");
                                Intent intent = new Intent(EditarEgresoActivity.this, MainActivity.class);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> e.printStackTrace());
                } else {
                    // Add a new document
                    db.collection("egresos")
                            .add(egreso)
                            .addOnSuccessListener(unused -> {
                                Log.d("msg-test", "Data guardada exitosamente");
                                Intent intent = new Intent(EditarEgresoActivity.this, MainActivity.class);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> e.printStackTrace());
                }
            }
        });

    }
}