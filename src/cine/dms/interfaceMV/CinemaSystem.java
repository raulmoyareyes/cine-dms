/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.dms.interfaceMV;

import cine.dms.classes.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Sistema simulado de un cine
 *
 * @author raul y agustin
 *
 */
public class CinemaSystem {

    /// Reloj
    private Clock reloj;

    /// Taquillas
    private List<TicketOffice> taquillas;

    /// Puestos de palomitas
    private List<PopcornStand> puestosPalomitas;

    private float frecuenciaClientes;
    private float tiempoServicioTaquilla;
    private float tiempoServicioPalomitas;
    private float probabilidadTicketMultiple;
    private float probabilidadPalomitas;

    /// Lista de sucesos (0 - llegada, 1 - salida, 2 - fin)
    private List<Integer> sucesos;

    /// Constructor
    public CinemaSystem() {

    }

    /// Métodos
    /**
     * Rutina de inicialización
     *
     * @param numTicketOffice
     * @param numPopcornStand
     * @param frecuenciaClientes
     * @param tiempoServicioTaquilla
     * @param tiempoServicioPalomitas
     * @param probabilidadTicketMultiple
     * @param probabilidadPalomitas
     */
    public void initialize(int numTicketOffice, int numPopcornStand, float frecuenciaClientes,
            float tiempoServicioTaquilla, float tiempoServicioPalomitas, float probabilidadTicketMultiple,
            float probabilidadPalomitas) {

        reloj = new Clock();

        taquillas = new ArrayList();
        for (int i = 0; i < numTicketOffice; ++i) {
            taquillas.add(new TicketOffice());
        }

        puestosPalomitas = new ArrayList();
        for (int i = 0; i < numPopcornStand; ++i) {
            puestosPalomitas.add(new PopcornStand());
        }

        this.frecuenciaClientes = frecuenciaClientes;
        this.probabilidadPalomitas = probabilidadPalomitas;
        this.probabilidadTicketMultiple = probabilidadTicketMultiple;
        this.tiempoServicioPalomitas = tiempoServicioPalomitas;
        this.tiempoServicioTaquilla = tiempoServicioTaquilla;

        this.sucesos = new ArrayList();

    }

    public void run() {
        //Aquí se llamará a la primera temporización (primera llegada)
        this.temporizacion();

    }

    /**
     * Siguiente sucesso y avanza el reloj
     */
    private void temporizacion() {
        //Aquí se determina el siguiente tipo de suceso

    }

    private void llegada() {
        //Aquí se determina el tipo de cliente que llega
    }

    private void salida() {
        //Aquí se actualiza el estado de las variables
    }

    private void finSimulacion() {
        //Fin ^_^
    }

    /**
     * Generar resultados de la simulación
     * @return Lista con los valores
     */
    public List<String> generarEstado() {
        List<String> resultado = new ArrayList();
        //Tamaño cola taquilla
        resultado.add(this.taquillas.get(0).toString());
        //Número taquillas ocupadas
        resultado.add(this.contarTaquillasOcupadas().toString());
        
        // FALTAAAAAA ////////////////////////////////////////////////////////////////////////////
        
        return resultado;
        
    }
    
    private Integer contarTaquillasOcupadas(){
        Integer resultado = 0;
        for(TicketOffice t: this.taquillas){
            if(!t.isLibre())
                ++resultado;
        }
        return resultado;
    }

    /**
     * Genera resultados globales de la simulación
     * @return Lista con los valores resultado
     */
    public List<String> generarResultadosGlobales() {
        List<String> resultado = new ArrayList();
        return resultado;
    }
}
