package com.example.procesadorplanillaspublicis.model;
public class Empleado {

    private int id;
    private String nombre;
    private float montoMensual;
    private boolean activo;

    public Empleado(int id, String nombre, float montoMensual, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.montoMensual = montoMensual;
        this.activo = activo;
    }
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public float getMontoMensual() {return montoMensual;}
    public boolean getEstado() {return activo;}




}
