package com.example.procesadorplanillaspublicis.services;

import com.example.procesadorplanillaspublicis.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorMiembrosPlanillaImpl extends ProveedorMiembrosPlanilla{
    @Override
    public List<Empleado> obtenerListaEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        return empleados;
    }
}
