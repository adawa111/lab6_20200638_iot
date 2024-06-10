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

import com.example.lab6_20200638_iot.Bean.Ingreso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class EditarIngresoActivity extends AppCompatActivity {


    private EditText descripcionEditText, montoEditText;

    private TextView tituloText, fechaText;
    private Button btnGuardar;

    private SimpleDateFormat dateFormat;

    FirebaseFirestore db;
    //ayuda de chatgpt

    private FirebaseAuth mAuth;
    private String documentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_ingreso);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        tituloText = findViewById(R.id.tituloEditText2);
        descripcionEditText = findViewById(R.id.descripcionEditText2);
        montoEditText = findViewById(R.id.montoEditText2);
        fechaText = findViewById(R.id.fechaEditText2);
        Button btnGuardar = findViewById(R.id.btnGuardar2);

        documentId = getIntent().getStringExtra("DOCUMENT_ID");
        Ingreso ingreso1 = (Ingreso) getIntent().getSerializableExtra("ingreso");

        if (documentId != null) {
            // Fetch the existing data
            db.collection("ingresos").document(documentId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            Ingreso ingreso = documentSnapshot.toObject(Ingreso.class);
                            if (ingreso != null) {
                                tituloText.setText(ingreso.getTitulo());
                                descripcionEditText.setText(ingreso.getDescription());
                                montoEditText.setText(String.valueOf(ingreso.getMonto()));
                                try{
                                   fechaText.setText(dateFormat.format(ingreso.getFecha()));
                                }catch (NullPointerException e){
                                    fechaText.setText("Fecha no especificada.");
                                }
                                // Set other fields if necessary
                            }
                        }
                    });
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ingreso ingreso = new Ingreso();
                ingreso.setTitulo(tituloText.getText().toString().trim());
                ingreso.setDescription(descripcionEditText.getText().toString().trim());
                ingreso.setMonto(Double.parseDouble(montoEditText.getText().toString().trim()));
                ingreso.setIduser(currentUser.getUid());

                if (documentId != null) {
                    // Update the existing document
                    db.collection("ingresos").document(documentId)
                            .update("description", ingreso.getDescription(), "monto", ingreso.getMonto())
                            .addOnSuccessListener(unused -> {
                                Log.d("msg-test", "Data actualizada exitosamente");
                                Intent intent = new Intent(EditarIngresoActivity.this, MainActivity.class);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> e.printStackTrace());
                } else {
                    // Add a new document
                    db.collection("ingresos")
                            .add(ingreso)
                            .addOnSuccessListener(unused -> {
                                Log.d("msg-test", "Data guardada exitosamente");
                                Intent intent = new Intent(EditarIngresoActivity.this, MainActivity.class);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> e.printStackTrace());
                }
            }
        });

    }
}