package com.example.lab6_20200638_iot.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6_20200638_iot.Bean.Ingreso;
import com.example.lab6_20200638_iot.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class IngresosAdapter extends RecyclerView.Adapter<IngresosAdapter.IngresoViewHolder> {

    private List<Ingreso> ingresoList;
    private OnItemClickListener listener;

    private SimpleDateFormat dateFormat;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class IngresoViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView;
        public TextView montoTextView;
        public TextView fechaTextView;
        public Button editButton;
        public Button deleteButton;

        public IngresoViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            montoTextView = itemView.findViewById(R.id.montoTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public IngresosAdapter(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    @NonNull
    @Override
    public IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new IngresoViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoViewHolder holder, int position) {
        Ingreso currentIngreso = ingresoList.get(position);
        holder.tituloTextView.setText(currentIngreso.getTitulo());
        holder.montoTextView.setText(String.valueOf(currentIngreso.getMonto()));
        try{
            holder.fechaTextView.setText(dateFormat.format(currentIngreso.getFecha()));
        }catch (NullPointerException e){
            holder.fechaTextView.setText("Fecha no especificada.");
        }

    }

    @Override
    public int getItemCount() {
        return ingresoList.size();
    }
}
