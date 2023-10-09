package com.example.procesadorplanillaspublicis.controller;


import com.example.procesadorplanillaspublicis.model.Empleado;
import com.example.procesadorplanillaspublicis.services.ProveedorMiembrosPlanilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin("*")
public class ProcesadorPlanillas {

    @Autowired
    private ProveedorMiembrosPlanilla proveedorMiembrosPlanillas;

    public ProcesadorPlanillas(ProveedorMiembrosPlanilla proveedorMiembrosPlanillas) {
        this.proveedorMiembrosPlanillas = proveedorMiembrosPlanillas;
    }

    @GetMapping("/totalPagar")
    public float obtenerTotalPagar() {
        List<Empleado> empleados = proveedorMiembrosPlanillas.obtenerListaEmpleados();
        float totalPagar = 0;
        for (Empleado empleado : empleados) {
            if (empleado.getEstado()) {
                if (empleado.getMontoMensual() < 0) {
                    throw new IllegalArgumentException("El empleado " + empleado.getNombre() +
                            " tiene un monto mensual registrado no permitido");
                }
                if (empleado.getId() == 0) {
                    throw new IllegalArgumentException("El empleado " + empleado.getNombre() +
                            " tiene ID igual a " + empleado.getId());

                }
                if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del empleado esta vacio o es nulo");
                }

                totalPagar += empleado.getMontoMensual();
            }
        }

        return totalPagar;
    }


}
