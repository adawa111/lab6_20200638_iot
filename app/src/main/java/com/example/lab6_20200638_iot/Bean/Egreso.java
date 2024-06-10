package com.example.lab6_20200638_iot.Bean;

import java.io.Serializable;
import java.util.Date;

public class Egreso implements Serializable {

    private String id;
    private String titulo;
    private String description;

    private double monto;

    private Date fecha;


    private String iduser;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public Egreso(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Egreso(String user, String titulo, String description, double monto, Date fecha) {
        this.titulo = titulo;
        this.iduser = user;
        this.monto = monto;
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
