package cine.dms.system.facade;

import cine.dms.system.classes.PopcornStand;
import cine.dms.system.classes.Client;
import cine.dms.system.classes.TicketOffice;
import cine.dms.system.classes.Clock;
import cine.dms.system.aux.Pair;
import cine.dms.system.aux.RandomLehmer;
import cine.dms.system.exceptions.ExcepcionGeneradorIncorrecto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

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
    /// Log
    private List<String> log;
    /// Tabla Lista de Eventos
    DefaultTableModel tablaListaEventos;
    /// Fin de simulacion
    private boolean fin = false;

    /// Constructor
    public CinemaSystem() {
    }

    public Clock getReloj() {
        return reloj;
    }

    public void refrescarListaEventos() {
        //Limpiar la tabla
        this.tablaListaEventos.setColumnCount(0);

        //Recoger los eventos y pasarlos a un array
        Object[][] arraySucesos = new Object[4][];
        for (int j = 0; j < arraySucesos.length; ++j) {
            arraySucesos[j] = this.sucesos.get(j).toArray();
            for (int i = 0; i < arraySucesos[j].length; ++i) {
                if (arraySucesos[j][i].equals(INFINITO)) {
                    arraySucesos[j][i] = "INFINITO";
                } else {
                    arraySucesos[j][i] = getTime((Integer) arraySucesos[j][i]);
                }
            }
        }

        this.tablaListaEventos.addColumn("Entrada Taquilla", arraySucesos[0]);
        this.tablaListaEventos.addColumn("Salida Taquilla", arraySucesos[1]);
        this.tablaListaEventos.addColumn("Entrada Palomitas", arraySucesos[2]);
        this.tablaListaEventos.addColumn("Salida Palomitas", arraySucesos[3]);
    }

    public boolean fin() {
        return fin;
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
     * @param log
     * @param tablaListaEventos
     */
    public void initialize(int numTicketOffice, int numPopcornStand, float frecuenciaClientes,
            int tiempoServicioTaquilla, int tiempoServicioPalomitas, float probabilidadTicketMultiple,
            float probabilidadPalomitas, List<String> log, javax.swing.table.TableModel tablaListaEventos) {

        reloj = new Clock(8 * 60 * 60); // Hora inicial 08:00:00
        tiempoFin = new Clock(22 * 60 * 60); // Hora inicial 22:00:00

        TicketOffice.resetId();
        taquillas = new ArrayList();
        for (int i = 0; i < numTicketOffice; ++i) {
            taquillas.add(new TicketOffice(tiempoServicioTaquilla));
        }

        PopcornStand.resetId();
        puestosPalomitas = new ArrayList();
        for (int i = 0; i < numPopcornStand; ++i) {
            puestosPalomitas.add(new PopcornStand(tiempoServicioPalomitas));
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

        //Llegada del primer cliente
        this.log = log; //log = new ArrayList();
        log.add("[" + this.reloj.getTime() + "] ");
        this.llegadaCliente();

        this.tablaListaEventos = (DefaultTableModel) tablaListaEventos;
    }

    public void run() {

        while (!fin) {
            this.temporizacion();
        }

    }

    public void temporizacion() {

        Pair<Integer, Integer> sS = this.siguienteSuceso();
        if (this.reloj.getSeconds() < this.tiempoFin.getSeconds()
                && this.sucesos.get(sS.posicion).get(sS.tipoSuceso) < this.tiempoFin.getSeconds()) {

            //Avanza el reloj con el valor de this.siguienteSuceso()
            /* REVISAR LA ACTUALIZACION DEL RELOJ */
            this.reloj.advance(this.sucesos.get(sS.posicion).get(sS.tipoSuceso));
            log.add("[" + this.reloj.getTime() + "] ");

            //Comprobamos cuál es el siguiente evento
            switch (sS.tipoSuceso) {
                case LLEGADATICKET:
                    llegadaCliente();
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
        } else {
            this.finSimulacion();
        }

    }

    /**
     * Suceso de llegada de un nuevo cliente
     *
     * @param cliente Cliente
     * @warning No está terminado
     */
    private void llegadaCliente() {
        //Aquí se determina el tipo de cliente que llega
        Client cliente = null;
        try {
            cliente = new Client(this.numTickets(), this.comprarPalomitas());

            //Calcular el tiempo de la siguiente llegada y actualizar la lista de sucesos
            this.calculoLlegadaSiguienteCliente();
        } catch (ExcepcionGeneradorIncorrecto ex) {
            //
        }

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
        log.add("Tipo de suceso: LLEGADATICKET (" + taquilla.getId() + ")\n");

        //Poner la taquilla en estado ocupado
        taquilla.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(taquilla);
    }

    private void salidaTicket(TicketOffice taquilla) {
        log.add("Tipo de suceso: SALIDATICKET (" + taquilla.getId() + ")\n");

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
        log.add("[" + this.reloj.getTime() + "] ");
        log.add("Tipo de suceso: LLEGADAPALOMITAS (" + palomitas.getId() + ")\n");

        //Poner la taquilla en estado ocupado
        palomitas.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(palomitas);
    }

    private void salidaPop(PopcornStand palomitas) {
        log.add("Tipo de suceso: SALIDAPALOMITAS (" + palomitas.getId() + ")\n");

        Client clienteServido = palomitas.getClienteSirviendose();
        palomitas.libre();
        palomitas.addClientesServidos();

        if (palomitas.getColaSize() != 0) { //La cola tiene clientes
            Client cliente = palomitas.getSiguienteCliente();
            palomitas.setClienteSirviendose(cliente);
            this.entradaPop(palomitas);
        }
        //Calcular datos estadísticos

    }

    private void finSimulacion() {
        fin = true;
    }

    /* ********************* OBTENCION DE RESULTADOS ********************* */
    /**
     * Devuelve el tamaño medio de las colas de las taquillas
     *
     * @return Tamaño medio de colas de las Taquillas
     * @warning no implementado
     */
    public Integer tamMedioColasTaquillas() {

        return 0;
    }

    /**
     * Devuelve el número de taquillas ocupadas
     *
     * @return Número de taquillas ocupadas
     */
    public Integer numTaquillasOcupadas() {
        Integer resultado = 0;
        for (TicketOffice t : this.taquillas) {
            if (!t.isLibre()) {
                ++resultado;
            }
        }
        return resultado;
    }

    /**
     * Devuelve el tamaño medio de las colas de los puesto de palomitas
     *
     * @return Tamaño medio de colas de puestos palomitas.
     * @warning no implementado
     */
    public Integer tamMedioColasPuestosPalomitas() {
        return 0;
    }

    /**
     * Devuelve el número de puestos de palomitas ocupados
     *
     * @return Número de puestos de palomitas ocupados
     */
    public Integer numPuestosPalomitasOcupados() {
        Integer resultado = 0;
        for (PopcornStand p : this.puestosPalomitas) {
            if (!p.isLibre()) {
                ++resultado;
            }
        }
        return resultado;
    }

    /**
     * Devuelve el número medio de clientes atendidos por taquilla
     *
     * @return Número medio de atendidos por taquilla
     * @warning no implementado
     */
    public Integer numMedioAtendidosTaquilla() {
        return 0;
    }

    /**
     * Devuelve el número medio de clientes atendidos por puesto de palomitas
     *
     * @return Número medio de atendidos por puesto de palomitas
     * @warning no implementado
     */
    public Integer numMedioAtendidosPuestoPalomitas() {
        return 0;
    }

    /**
     * Devuelve el tiempo medio que los clientes están en una cola.
     *
     * @return Tiempo medio de clientes en cola
     * @warning no implementado
     */
    public Integer tiempoMedioCola() {
        return 0;
    }

    /* *********************** MÉTODOS AUXILIARES *********************** */
    /**
     * Devuelve la taquilla que menos cola tiene
     *
     * @return Taquilla con menos cola
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
     *
     * @return Puesto de palomitas con menos cola
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

    /* ********************** MÉTODOS EXTADÍSTICOS ********************** */
    /**
     * Función aleatoria para calcular el número de tickets a comprar (lineal)
     *
     * @return Número de tickets a comprar
     * @throws ExcepcionGeneradorIncorrecto
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
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
     * @throws ExcepcionGeneradorIncorrecto
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
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
     * Función aleatoria para calcular el tiempo de llegada del siguiente
     * cliente (lineal)
     *
     * @throws ExcepcionGeneradorIncorrecto
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
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
     * Función aleatoria para calcular el tiempo de servicio en taquilla del
     * cliente (lineal)
     *
     * @param taquilla
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
     */
    private void calculoSalidaSiguienteCliente(TicketOffice taquilla) {
        this.sucesos.get(taquilla.getId()).set(SALIDATICKET, this.reloj.getSeconds() + taquilla.getTiempoServicio());
    }

    /**
     * Función aleatoria para calcular el tiempo de servicio en puesto de
     * palomitas del cliente (lineal)
     *
     * @param palomitas
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
     * @warnig no testeada
     */
    private void calculoSalidaSiguienteCliente(PopcornStand palomitas) {
        this.sucesos.get(palomitas.getId()).set(SALIDAPALOMITAS, this.reloj.getSeconds() + palomitas.getTiempoServicio());
    }

    public List<List<Integer>> getListaSucesos() {
        return this.sucesos;
    }

    public String getTime(int seconds) {

        String s = (seconds % 60 < 10) ? "0" + String.valueOf(seconds % 60) : String.valueOf(seconds % 60);
        int min = seconds / 60;
        String m = (min % 60 < 10) ? "0" + String.valueOf(min % 60) : String.valueOf(min % 60);
        String h = (min / 60 < 10) ? "0" + String.valueOf(min / 60) : String.valueOf(min / 60);

        return h + ":" + m + ":" + s;

    }
//
//    public Object getLog() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void initialize(int parseInt, int parseInt0, float parseFloat, int parseInt1, int parseInt2, float parseFloat0, float parseFloat1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
