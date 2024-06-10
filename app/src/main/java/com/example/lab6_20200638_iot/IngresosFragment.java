package com.example.lab6_20200638_iot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6_20200638_iot.Adapter.IngresosAdapter;
import com.example.lab6_20200638_iot.Bean.Ingreso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class IngresosFragment extends Fragment {

    private RecyclerView recyclerViewIngresos;
    private IngresosAdapter adapter;
    private List<Ingreso> ingresoList;
    private Button btnRegistrarIngreso;

    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingresos, container, false);

        btnRegistrarIngreso = view.findViewById(R.id.btnRegistrarIngreso);
        recyclerViewIngresos = view.findViewById(R.id.recyclerViewIngresos);

        ingresoList = new ArrayList<>();
        adapter = new IngresosAdapter(ingresoList);
        recyclerViewIngresos.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewIngresos.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            /*Toast.makeText(EgresosFragment.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*/
        }

        btnRegistrarIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para registrar un nuevo ingreso
            }
        });

        adapter.setOnItemClickListener(new IngresosAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                // Código para editar el ingreso
            }

            @Override
            public void onDeleteClick(int position) {
                // Código para eliminar el ingreso
                ingresoList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        // Método para cargar ingresos
        cargarIngresos();

        return view;
    }

    private void cargarIngresos() {
        /*// Código para cargar la lista de ingresos
        // Ejemplo de ingresos
        ingresoList.add(new Ingreso("Ingreso 1", 100.0, "2023-06-01"));
        ingresoList.add(new Ingreso("Ingreso 2", 200.0, "2023-06-02"));*/
        adapter.notifyDataSetChanged();
    }
}
