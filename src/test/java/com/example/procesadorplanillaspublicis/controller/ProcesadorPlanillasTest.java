package com.example.procesadorplanillaspublicis.controller;

import com.example.procesadorplanillaspublicis.model.Empleado;
import com.example.procesadorplanillaspublicis.services.ProveedorMiembrosPlanilla;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcesadorPlanillasTest {

    @Mock
    private ProcesadorPlanillas procesadorPlanillas;
    private ProveedorMiembrosPlanilla proveedorMiembrosPlanillas;

    @BeforeEach
    public void setUp() {
        proveedorMiembrosPlanillas = mock(ProveedorMiembrosPlanilla.class);
        procesadorPlanillas = new ProcesadorPlanillas(proveedorMiembrosPlanillas);
    }


    @Test
    public void testObtenerTotalPagar_EmpleadosValidos() {
        List< Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "Jose", 2000.50f, true);
        Empleado empleado2 = new Empleado(2, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, "Sebastian", 3540.35f, true);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        float resultado = procesadorPlanillas.obtenerTotalPagar();

        assertEquals(9541.20, resultado, 0.001);
        assertEquals(3, empleados.size());
        assertTrue(resultado >= 0);
        for (Empleado empleado : empleados) {
            assertNotNull(empleado.getNombre());
            assertFalse(empleado.getNombre().isEmpty());
            assertTrue(empleado.getEstado());
            assertTrue(empleado.getMontoMensual() >= 0);
        }
    }


    @Test
    public void testObtenerTotalPagar_EmpleadoInactivo() {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "Jose", 2000.50f, false);
        Empleado empleado2 = new Empleado(2, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, "Sebastian", 3540.35f, false);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        float resultado = procesadorPlanillas.obtenerTotalPagar();

        assertEquals(4000.35, resultado, 0.001);
        for (Empleado empleado : empleados) {
            if (empleado.getEstado()) {
                assertNotNull(empleado.getNombre());
                assertFalse(empleado.getNombre().isEmpty());
                assertTrue(empleado.getMontoMensual() >= 0);
            }
        }
    }

    @Test
    public void testExcepcion_MontoNegativo() {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "Jose", -1700f, true);
        Empleado empleado2 = new Empleado(2, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, "Sebastian", 3540.35f, true);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        try {
            procesadorPlanillas.obtenerTotalPagar();
            fail("Se esperaba una excepción IllegalArgumentException para el empleado con monto negativo.");
        } catch (IllegalArgumentException e) {
            assertEquals("El empleado Jose tiene un monto mensual registrado no permitido", e.getMessage());
        }
    }

    @Test
    public void testExcepcion_IdCero() {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "Jose", 2000.50f, true);
        Empleado empleado2 = new Empleado(0, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, "Sebastian", 3540.35f, true);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        try {
            procesadorPlanillas.obtenerTotalPagar();
            fail("Se esperaba una excepción IllegalArgumentException para el empleado con ID igual a 0.");
        } catch (IllegalArgumentException e) {
            assertEquals("El empleado Vicente tiene ID igual a 0", e.getMessage());
        }
    }

    @Test
    public void testExcepcion_NombreVacio() {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "", 2000.50f, true);
        Empleado empleado2 = new Empleado(2, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, "Sebastian", 3540.35f, true);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        try {
            procesadorPlanillas.obtenerTotalPagar();
            fail("Se esperaba una excepción IllegalArgumentException para el empleado con nombre vacío.");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del empleado esta vacio o es nulo", e.getMessage());
        }
    }


    @Test
    public void testExcepcion_NombreNulo() {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(1, "Jose", 2000.50f, true);
        Empleado empleado2 = new Empleado(2, "Vicente", 4000.35f, true);
        Empleado empleado3 = new Empleado(3, null, 3540.35f, true);
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        when(proveedorMiembrosPlanillas.obtenerListaEmpleados()).thenReturn(empleados);

        try {
            procesadorPlanillas.obtenerTotalPagar();
            fail("Se esperaba una excepción IllegalArgumentException para el empleado con nombre nulo.");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del empleado esta vacio o es nulo", e.getMessage());
        }
    }


}