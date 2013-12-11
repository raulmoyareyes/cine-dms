package cine.dms.interfaceMV;

import cine.dms.classes.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Sistema simulado de un cine
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class CinemaSystem {

    ///Constante SUCESOLLEGADA
    public static final int LLEGADATICKET = 0;
    ///Constante SUCESOLLEGADA
    public static final int SALIDATICKET = 1;
    ///Constante SUCESOLLEGADA
    public static final int LLEGADAPALOMITAS = 2;
    ///Constante SUCESOLLEGADA
    public static final int SALIDAPALOMITAS = 3;
    ///Constante INFINITO
    public static final int INFINITO = Integer.MAX_VALUE;
    /// Reloj
    private Clock reloj;
    /// Fin de simulación
    private Clock tiempoFin;
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
    private List<List<Integer>> sucesos;

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
        tiempoFin = new Clock(22 * 60 * 60); // Hora inicial 22:00:00

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

        //Sucesos medidos en segundos
        this.sucesos = new ArrayList();
        sucesos.add(new ArrayList(Arrays.asList(reloj.getSeconds(), INFINITO, INFINITO, INFINITO)));
        for (int i = 1; i < numTicketOffice || i < numPopcornStand; ++i) {
            sucesos.add(new ArrayList(Arrays.asList(INFINITO, INFINITO, INFINITO, INFINITO)));
        }
    }

    public void run() {
        //Aquí se llamará a la primera temporización (primera llegada)
        this.temporizacion();

    }

    /**
     * Siguiente sucesso y avanza el reloj
     */
    private void temporizacion() {
        //Llegada del primer cliente
        this.llegadaCliente();
        //Condición de parada de simulación
        Pair<Integer, Integer> sS = this.siguienteSuceso();
        while (this.reloj.getSeconds() < this.tiempoFin.getSeconds() 
                && this.sucesos.get(sS.posicion).get(sS.tipoSuceso) < this.tiempoFin.getSeconds()) {

            //Comprobamos cuál es el siguiente evento
            switch (sS.tipoSuceso) {
                case LLEGADATICKET:
                    break;
                case SALIDATICKET:
                    break;
                case LLEGADAPALOMITAS:
                    break;
                case SALIDAPALOMITAS:
                    break;
                default:
                    break;
            }
            //Avanza el reloj con el valor de this.siguienteSuceso()
        }
        this.finSimulacion();
    }

    /**
     * Suceso de llegada de un nuevo cliente
     *
     * @param cliente Cliente
     * @warning No está terminado
     */
    private void llegadaCliente() {
        //Aquí se determina el tipo de cliente que llega
        Client cliente = new Client(this.numTickets(), this.comprarPalomitas());

        //Calcular el tiempo de la siguiente llegada y actualizar la lista de sucesos
        this.calculoLlegadaSiguienteCliente();

        //Asignarle una cola
        this.asignacionTicket(cliente);
    }

    /**
     * Asigna un cliente a la cola de una taquilla
     *
     * @param cliente
     */
    private void asignacionTicket(Client cliente) {

        TicketOffice taquilla = this.getTaquillaMenosOcupada();
        if (taquilla.isLibre()) { //Servidor libre
            this.entradaTicket(taquilla);
        } else { //Servidor ocupado
            taquilla.addClienteEnCola(cliente);
            //Almacena el tiempo de llegada en el cliente
            //Recalcular área bajo Q(t)
        }

    }

    private void entradaTicket(TicketOffice taquilla) {
        //Poner la taquilla en estado ocupado
        taquilla.ocupado();

        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(taquilla);
    }

    private void salidaTicket(TicketOffice taquilla) {

        if (taquilla.getColaSize() == 0) { //La cola esta vacía
            taquilla.libre();
            //Calcula el retardo para el siguiente cliente
            //Recalcular los datos estadísticos
            taquilla.addClientesServidos();
        } else { //La cola tiene clientes
        }
        //Cálculo de la entrada del siguiente cliente
    }

    private void entradaPop() {
        //Similar a entradaTicket()
    }

    private void salidaPop() {
        //Sacar al cliente del salida()
    }

    /**
     * Evento de salida de un cliente del sistema
     *
     * @param taquilla Taquilla de la que sale el cliente
     * @warning No está terminado
     */
    private void salida(Client cliente) {
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

    /* *********************** MÉTODOS AUXILIARES *********************** */
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

        // FALTAAAAAA //////////////////////////////////////////////////////////
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

    /**
     * Obtener el tipo del siguiente suceso
     *
     * @return LLEGADATICKET ó SALIDATICKET ó LLEGADAPALOMITAS ó SALIDAPALOMITAS
     */
    private Pair<Integer,Integer> siguienteSuceso() {
        Pair<Integer,Integer> resultado = new Pair(0,LLEGADATICKET);
        for (int i = 0; i < sucesos.size(); ++i) {
            for(int j=0; j< sucesos.get(i).size(); ++j){
                if(sucesos.get(i).get(j) < sucesos.get(resultado.posicion).get(resultado.tipoSuceso)){
                    resultado.posicion = i;
                    resultado.tipoSuceso = j;
                }
            }
        }
        return resultado;
    }

    /**
     * Función aleatoria para calcular el número de tickets a comprar
     *
     * @return Número de tickets a comprar
     * @warning No implementado, siempre compra 1 ticket
     */
    private int numTickets() {
        return 1;
    }

    /**
     * Función aleatoria para calcular si compra palomitas o no
     *
     * @return 1 si compra palomitas o 0 si no compra palomitas
     * @warning No implementado, nunca compra palomitas
     */
    private int comprarPalomitas() {
        return 0;
    }

    /**
     * Cálculo del siguiente cliente en llegar
     *
     * @warning No implementado el tiempo aleatorio de llegada
     */
    private void calculoLlegadaSiguienteCliente() {
        int tiempoLlegada = 60;
        this.sucesos.get(0).set(LLEGADATICKET, this.reloj.getSeconds() + tiempoLlegada);
    }

    /**
     * Cálculo del tiempo de servicio del cliente en taquilla
     *
     * @warning No implementado el tiempo aleatorio de servicio
     */
    private void calculoSalidaSiguienteCliente(TicketOffice taquilla) {
        int tiempoServicio = 20;
        this.sucesos.get(taquilla.getId()).set(SALIDATICKET, this.reloj.getSeconds() + tiempoServicio);
    }
}
