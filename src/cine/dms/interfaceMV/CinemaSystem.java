package cine.dms.interfaceMV;

import cine.dms.classes.*;
import excepciones.ExcepcionGeneradorIncorrecto;
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
    ///Cantidad máxima de entradas que puede comprar un cliente
    public static final int MAX_ENTRADAS = 5;
    ///Cantidad máxima de palomitas que puede comprar un cliente
    public static final int MAX_PALOMITAS = 5;
    ///Tiempo máximo entre llegada de clientes
    public static final int MAX_TIEMPO_LLEGADA = 14 * 60 * 60;
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
    ///Generador de número aleatorios [0,1] (parámetros provisionales)
    RandomLehmer randomLehmer = new RandomLehmer(0.84641, 0.645, 1);

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

    public void run() throws ExcepcionGeneradorIncorrecto {
        
        //Llegada del primer cliente
        this.llegadaCliente();
        //Aquí se llamará a temporización
        this.temporizacion();

    }
    
    public void next() throws ExcepcionGeneradorIncorrecto{
        // paso a paso, parecido a temporizacion pero sin bucle.

    }

    /**
     * Siguiente sucesso y avanza el reloj
     */
    private void temporizacion(){
        //Condición de parada de simulación
        Pair<Integer, Integer> sS = this.siguienteSuceso();
        while (this.reloj.getSeconds() < this.tiempoFin.getSeconds()
                && this.sucesos.get(sS.posicion).get(sS.tipoSuceso) < this.tiempoFin.getSeconds()) {

            //Avanza el reloj con el valor de this.siguienteSuceso()
            /* REVISAR LA ACTUALIZACION DEL RELOJ */
            this.reloj.advance(this.sucesos.get(sS.posicion).get(sS.tipoSuceso));
            
            //Comprobamos cuál es el siguiente evento
            switch (sS.tipoSuceso) {
                case LLEGADATICKET: /* REVISADO. DEBE FUNCIONAR */

                    entradaTicket(taquillas.get(sS.posicion));
                    break;
                case SALIDATICKET:
                    salidaTicket(taquillas.get(sS.posicion));
                    break;
                case LLEGADAPALOMITAS:
                    entradaPop(puestosPalomitas.get(sS.posicion));
                    break;
                case SALIDAPALOMITAS:
                    salidaPop(puestosPalomitas.get(sS.posicion));
                    break;
                default:
                    break;
            }
            sS = this.siguienteSuceso();
        }
        this.finSimulacion();
    }

    /**
     * Suceso de llegada de un nuevo cliente
     *
     * @param cliente Cliente
     * @warning No está terminado
     */
    private void llegadaCliente() throws ExcepcionGeneradorIncorrecto {
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
            taquilla.setClienteSirviendose(cliente);
            this.entradaTicket(taquilla);
        } else { //Servidor ocupado
            taquilla.addClienteEnCola(cliente);
        }
    }

    private void entradaTicket(TicketOffice taquilla) {
        //Poner la taquilla en estado ocupado
        taquilla.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(taquilla);
    }

    private void salidaTicket(TicketOffice taquilla) {

        Client clienteServido = taquilla.getClienteSirviendose();
        taquilla.libre();
        taquilla.addClientesServidos();

        if (taquilla.getColaSize() != 0) { //La cola tiene clientes
            Client cliente = taquilla.getSiguienteCliente();
            taquilla.setClienteSirviendose(cliente);
            this.entradaTicket(taquilla);
        }
        //Calcular datos estadísticos

        //Determinar si compra palomitas o sale del sistema
        if (clienteServido.getPalomitas() != 0) {
            //Compra palomitas
            asignacionPop(clienteServido);
        }
    }

    private void asignacionPop(Client cliente) {

        PopcornStand palomitas = this.getPalomitasMenosOcupada();

        if (palomitas.isLibre()) { //Servidor libre
            palomitas.setClienteSirviendose(cliente);
            this.entradaPop(palomitas);
        } else { //Servidor ocupado
            palomitas.addClienteEnCola(cliente);
        }
    }

    private void entradaPop(PopcornStand palomitas) {
        //Poner la taquilla en estado ocupado
        palomitas.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(palomitas);
    }

    private void salidaPop(PopcornStand palomitas) {

        Client clienteServido = palomitas.getClienteSirviendose();
        palomitas.libre();
        palomitas.addClientesServidos();

        if (palomitas.getColaSize() != 0) { //La cola tiene clientes
            Client cliente = palomitas.getSiguienteCliente();
            palomitas.setClienteSirviendose(cliente);
            this.entradaPop(palomitas);
        }
        //Calcular datos estadísticos

        //Determinar si compra palomitas o sale del sistema
        if (clienteServido.getPalomitas() != 0) {
            //Compra palomitas

        }
    }

//  ESTO ES POSIBLE QUE NO HAYA QUE HACERLO
//    /**
//     * Evento de salida de un cliente del sistema
//     *
//     * @param taquilla Taquilla de la que sale el cliente
//     * @warning No está terminado
//     */
//    private void salida(Client cliente) {
//        //Aquí se actualiza el estado de las variables
//        /*
//         * ALGORITMO GENÉRICO (de la teoría)
//         * 
//         *  SI La cola está vacía
//         *      Pone el estado ser servidor desocupado
//         *      Modifica lista de sucesos con infinito en el suceso de salida
//         *  SI NO
//         *      Decrementa en 1 el número de clientes en cola
//         *      Calcula el retardo para el cliente y recalcula los datos estadísticos
//         *      Incrementa en 1 el número de clientes servidos
//         *      Calcula el tiempo de salida y actualiza la lista de sucesos
//         *  FIN SI
//         */
//    }
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
     * Devuelve el puesto de palomitas que menos cola tiene
     */
    private PopcornStand getPalomitasMenosOcupada() {
        PopcornStand palomitasReturn = null;
        for (PopcornStand p : this.puestosPalomitas) {
            if (palomitasReturn != null) {
                if (p.getColaSize() < palomitasReturn.getColaSize()) {
                    palomitasReturn = p;
                }
            } else {
                palomitasReturn = p;
            }
            if (palomitasReturn.isLibre()) {
                return palomitasReturn;
            }
        }
        return palomitasReturn;
    }

    /**
     * Genera resultados globales de la simulación
     *
     * @return Lista con los valores resultado
     * @warning No está relleno
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
    private Pair<Integer, Integer> siguienteSuceso() {
        Pair<Integer, Integer> resultado = new Pair(0, LLEGADATICKET);
        for (int i = 0; i < sucesos.size(); ++i) {
            for (int j = 0; j < sucesos.get(i).size(); ++j) {
                if (sucesos.get(i).get(j) < sucesos.get(resultado.posicion).get(resultado.tipoSuceso)
                        && sucesos.get(i).get(j) > reloj.getSeconds()) {
                    resultado.posicion = i;
                    resultado.tipoSuceso = j;
                }
            }
        }
        return resultado;
    }

    /**
     * Función aleatoria para calcular el número de tickets a comprar (lineal)
     *
     * @return Número de tickets a comprar
     */
    private int numTickets() throws ExcepcionGeneradorIncorrecto {
        /*
         * Multiplicamos el aleatorio generado por el MAX_ENTRADAS * 27 para
         * tener un número grande al que poder hacerle el módulo por
         * MAX_ENTRADAS. Así nos aseguramos que el número por el que multiplica
         * el aleatorio es siempre mayor que MAX_ENTRADAS
         */
        return (int) (((27 * MAX_ENTRADAS * this.randomLehmer.getRandom()) % MAX_ENTRADAS) + 1);
    }

    /**
     * Función aleatoria para calcular cuántas palomitas compra (lineal)
     *
     * @return Número de palomitas que compra [0, MAX_PALOMITAS]
     */
    private int comprarPalomitas() throws ExcepcionGeneradorIncorrecto {
        /*
         * Multiplicamos el aleatorio generado por el MAX_PALOMITAS * 27 para
         * tener un número grande al que poder hacerle el módulo por
         * MAX_PALOMITAS. Así nos aseguramos que el número por el que multiplica
         * el aleatorio es siempre mayor que MAX_PALOMITAS
         */
        if (this.randomLehmer.getRandom() >= 0.5) {
            return 0;
        } else {
            return (int) (((27 * MAX_PALOMITAS * this.randomLehmer.getRandom()) % MAX_PALOMITAS) + 1);
        }
    }

    /**
     * Cálculo del siguiente cliente en llegar
     *
     */
    private void calculoLlegadaSiguienteCliente() throws ExcepcionGeneradorIncorrecto {
        /*
         * Multiplicamos el aleatorio generado por el MAX_TIEMPO_LLEGADA * 27
         * para tener un número grande al que poder hacerle el módulo por
         * MAX_TIEMPO_LLEGADA. Así nos aseguramos que el número por el que
         * multiplica el aleatorio es siempre mayor que MAX_TIEMPO_LLEGADA
         */
        int tiempoLlegada = (int) (((27 * MAX_TIEMPO_LLEGADA * this.randomLehmer.getRandom()) % MAX_TIEMPO_LLEGADA) + 1);
        this.sucesos.get(0).set(LLEGADATICKET, this.reloj.getSeconds() + tiempoLlegada);
    }

    /**
     * Cálculo del tiempo de servicio del cliente en taquilla
     */
    private void calculoSalidaSiguienteCliente(TicketOffice taquilla) {
        this.sucesos.get(taquilla.getId()).set(SALIDATICKET, this.reloj.getSeconds() + taquilla.getTiempoServicio());
    }

    /**
     * Cálculo del tiempo de servicio del cliente en puesto de palomitas
     *
     * @warning No testeado
     */
    private void calculoSalidaSiguienteCliente(PopcornStand palomitas) {
        this.sucesos.get(palomitas.getId()).set(SALIDAPALOMITAS, this.reloj.getSeconds() + palomitas.getTiempoServicio());
    }
}
