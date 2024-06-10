package com.example.lab6_20200638_iot;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab6_20200638_iot.Adapter.EgresosAdapter;
import com.example.lab6_20200638_iot.Adapter.IngresosAdapter;
import com.example.lab6_20200638_iot.Bean.Egreso;
import com.example.lab6_20200638_iot.Bean.Ingreso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class EgresosFragment extends Fragment {

    private RecyclerView recyclerViewEgresos;
    private EgresosAdapter adapter;
    private List<Egreso> egresoList;
    private Button btnRegistrarEgreso;

    FirebaseFirestore db;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_egresos, container, false);

        btnRegistrarEgreso = view.findViewById(R.id.btnRegistrarEgreso);
        recyclerViewEgresos = view.findViewById(R.id.recyclerViewEgresos);

        egresoList = new ArrayList<>();
        adapter = new EgresosAdapter(egresoList);
        recyclerViewEgresos.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewEgresos.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            /*Toast.makeText(EgresosFragment.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*/
        }

        btnRegistrarEgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para registrar un nuevo ingreso
                Intent intent = new Intent(getActivity(), RegistrarEgresoActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new EgresosAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                // Código para editar el ingreso
            }

            @Override
            public void onDeleteClick(int position) {
                // Código para eliminar el ingreso
                egresoList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        // Método para cargar ingresos
        cargarEgresos();

        return view;
    }

    private void cargarEgresos() {
        /*// Código para cargar la lista de ingresos
        // Ejemplo de ingresos
        ingresoList.add(new Ingreso("Ingreso 1", 100.0, "2023-06-01"));
        ingresoList.add(new Ingreso("Ingreso 2", 200.0, "2023-06-02"));*/
        db.collection("egresos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Egreso egreso = document.toObject(Egreso.class);
                            egresoList.add(egreso);
                            Log.d("msg-test", "Nombre: " + egreso.getTitulo());
                            Log.d("msg-test", "Correo: " + egreso.getDescription());
                        }
                    } else {
                        Log.d("msg-test", "Error getting documents: ", task.getException());
                    }
                });
        adapter.notifyDataSetChanged();
    }
}