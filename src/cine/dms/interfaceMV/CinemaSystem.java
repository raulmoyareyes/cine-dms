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
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
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

        reloj = new Clock(8 * 60 * 60); // Hora inicial 08:00:00

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

    /**
     * Suceso de llegada de un nuevo cliente
     *
     * @param cliente Cliente
     * @warning No está terminado
     */
    private void llegada() {
        //Aquí se determina el tipo de cliente que llega
        //return new Client(numTicket, palomitas);

        //Calcular el tiempo de la siguiente llegada y actualizar la lista de sucesos

        /* 
         * ALGORITMO GENÉRICO (de la teoría)
         * 
         *  SI Servidor está libre
         *      Incrementa en 1 el número de clientes servidor
         *      Pone el estado del Servidor a ocupado
         *      Calcula el tiempo de salida y actualizar lista de sucesos
         *  SI NO
         *      Incrementa 1 el número de clientes en cola
         *      SI la cola está llena
         *          Escribe mensaje de error y para la simulación
         *      SINO
         *          Almacena el tiempo de llegada del cliente
         *          Recalcular área bajo Q(t)
         *  FIN SI
         *  Calcular tiempo siguiente llegada y actualizar lista de sucesos
         * 
         */
    }

    /**
     * Asigna un cliente a la cola de una taquilla
     *
     * @param cliente
     */
    private void asignacionTicket(Client cliente) {

        TicketOffice taquilla = this.getTaquillaMenosOcupada();
        if (taquilla.isLibre()) { //Servidor libre
            taquilla.switchEstado();
            //Calcular el tiempo de salida
            //Actualizar la lista de sucesos
        } else { //Servidor ocupado
            taquilla.addClienteEnCola(cliente);
            //Almacena el tiempo de llegada
            //Recalcular área bajo Q(t)
        }

    }

    private void entradaTicket() {

    }

    private void salidaTicket(TicketOffice taquilla) {
        
        if (taquilla.getColaSize() == 0) { //La cola esta vacía
            taquilla.switchEstado();
            //Calcula el retardo para el siguiente cliente
            //Recalcular los datos estadísticos
            taquilla.addClientesServidos();
        } else { //La cola tiene clientes

        }
    }

    private void entradaPop() {

    }

    private void salidaPop() {

    }

    /**
     * Evento de salida de un cliente de la taquilla
     *
     * @param taquilla Taquilla de la que sale el cliente
     * @warning No está terminado
     */
    private void salida(TicketOffice taquilla) {
        //Aquí se actualiza el estado de las variables


        /*
         * ALGORITMO GENÉRICO (de la teoría)
         * 
         *  SI La cola está vacía
         *      Pone el estado ser servidor desocupado
         *      Modifica lista de sucesos con infinito en el suceso de salida
         *  SI NO
         *      Decrementa en 1 el número de clientes en cola
         *      Calcula el retardo para el cliente y recalcula los datos estadísticos
         *      Incrementa en 1 el número de clientes servidos
         *      Calcula el tiempo de salida y actualiza la lista de sucesos
         *  FIN SI
         */
    }

    private void finSimulacion() {
        //Fin ^_^
    }

    private void generacionDeInforme() {

    }

    /**
     * Generar resultados de la simulación
     *
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

    /**
     * Devuelve el número de taquillas ocupadas
     *
     * @return Número de taquillas ocupadas
     */
    private Integer contarTaquillasOcupadas() {
        Integer resultado = 0;
        for (TicketOffice t : this.taquillas) {
            if (!t.isLibre()) {
                ++resultado;
            }
        }
        return resultado;
    }

    /**
     * Devuelve la taquilla que menos cola tiene
     */
    private TicketOffice getTaquillaMenosOcupada() {
        TicketOffice taquillaReturn = null;
        for (TicketOffice t : this.taquillas) {
            if (taquillaReturn != null) {
                if (t.getColaSize() < taquillaReturn.getColaSize()) {
                    taquillaReturn = t;
                }
            } else {
                taquillaReturn = t;
            }
            if (taquillaReturn.isLibre()) {
                return taquillaReturn;
            }
        }
        return taquillaReturn;
    }

    /**
     * Genera resultados globales de la simulación
     *
     * @return Lista con los valores resultado
     */
    public List<String> generarResultadosGlobales() {
        List<String> resultado = new ArrayList();
        return resultado;
    }
}
