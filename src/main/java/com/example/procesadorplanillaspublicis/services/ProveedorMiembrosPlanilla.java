package com.example.procesadorplanillaspublicis.services;

import com.example.procesadorplanillaspublicis.model.Empleado;

import java.util.List;

public abstract class ProveedorMiembrosPlanilla {

    public ProveedorMiembrosPlanilla(){
    }
    public abstract List<Empleado> obtenerListaEmpleados();
}
