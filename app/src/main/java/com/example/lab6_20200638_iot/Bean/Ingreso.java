package com.example.lab6_20200638_iot.Bean;

import java.io.Serializable;
import java.util.Date;

public class Ingreso implements Serializable {

    private String id;

    private String titulo;
    private String description;

    private double monto;

    private Date fecha;

    public Ingreso(){

    }
    private String iduser;

    public String getIduser() {
        return iduser;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public Ingreso(String user, String titulo, String description, double monto, Date fecha) {
        this.titulo = titulo;
        this.monto = monto;
        this.iduser = user;
        this.fecha = fecha;
        this.description = description;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
