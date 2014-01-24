package cine.dms.system.facade;

import cine.dms.system.classes.PopcornStand;
import cine.dms.system.classes.Client;
import cine.dms.system.classes.TicketOffice;
import cine.dms.system.classes.Clock;
import cine.dms.system.aux.Pair;
import cine.dms.system.aux.RandomExponential;
//import cine.dms.system.aux.RandomCuadratico;
import cine.dms.system.aux.RandomLehmer;
import cine.dms.system.exceptions.ExcepcionGeneradorIncorrecto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    //public static final int MAX_ENTRADAS = 5;
    ///Cantidad máxima de palomitas que puede comprar un cliente
    //public static final int MAX_PALOMITAS = 5;
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
    private Float frecuenciaClientes;
    private Integer tiempoServicioTaquilla;
    private Integer tiempoServicioPalomitas;
    private Float probabilidadTicketMultiple;
    private Float probabilidadPalomitas;
    /// Lista de sucesos (0 - llegada, 1 - salida, 2 - fin)
    private List<List<Integer>> sucesos;
    ///Generador de número aleatorios [0,1] (parámetros provisionales)
    RandomLehmer randomLehmer = new RandomLehmer(16807, 0, 2147483647);
    //RandomCuadratico randomCuadratico = new RandomCuadratico(0.6, 0.84641, 20); //Este 20 está a pelo
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

        Object[][] arraySucesos = new Object[4][this.sucesos.size()];
        for (int i = 0; i < this.sucesos.size(); ++i) {
            for (int j = 0; j < 4; ++j) {
                if (this.sucesos.get(i).get(j).equals(INFINITO)) {
                    arraySucesos[j][i] = "INFINITO";
                } else {
                    arraySucesos[j][i] = getTime(this.sucesos.get(i).get(j));
                }
            }
        }

        //Sólo hay una entrada al sistema
        for (int i = 1; i < this.sucesos.size(); ++i) {
            arraySucesos[0][i] = "";
        }

        //Eliminar los campos que no procedan de taquillas o puestos de palomitas
        int numTaquillas = this.taquillas.size();
        int numPalomitas = this.puestosPalomitas.size();
        if (numTaquillas != numPalomitas) {
            if (numTaquillas < numPalomitas) {
                //Hay menos taquillas que puestos de palomitas
                for (int i = numTaquillas; i < numPalomitas; ++i) {
                    arraySucesos[0][i] = arraySucesos[1][i] = "";
                }
            } else {
                //Hay más taquillas que puestos de palomitas
                for (int i = numPalomitas; i < numTaquillas; ++i) {
                    arraySucesos[2][i] = arraySucesos[3][i] = "";
                }
            }
        }

        this.tablaListaEventos.addColumn("Entrada Sistema", arraySucesos[0]);
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
    public void initialize(int numTicketOffice, int numPopcornStand, Float frecuenciaClientes,
            Integer tiempoServicioTaquilla, Integer tiempoServicioPalomitas, Float probabilidadTicketMultiple,
            Float probabilidadPalomitas, List<String> log, javax.swing.table.TableModel tablaListaEventos) {

        reloj = new Clock(8 * 60 * 60); // Hora inicial 08:00:00
        tiempoFin = new Clock(22 * 60 * 60); // Hora inicial 22:00:00

        TicketOffice.resetId();
        taquillas = new ArrayList();
        for (int i = 0; i < numTicketOffice; ++i) {
            taquillas.add(new TicketOffice(reloj.getSeconds(), tiempoServicioTaquilla));
        }

        PopcornStand.resetId();
        puestosPalomitas = new ArrayList();
        for (int i = 0; i < numPopcornStand; ++i) {
            puestosPalomitas.add(new PopcornStand(reloj.getSeconds(), tiempoServicioPalomitas));
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

        log.add("\tNúmero de tickets que va a comprar = " + cliente.getNumTicket() + "\n");
        TicketOffice taquilla = this.getTaquillaMenosOcupada();

        if (taquilla.isLibre()) { //Servidor libre
            taquilla.setClienteSirviendose(cliente);
            this.entradaTicket(taquilla);
            log.add("\tEntra directamente a taquilla " + taquilla.getId() + "\n");
        } else { //Servidor ocupado
            cliente.setTiempoLlegadaCola(this.reloj.getSeconds());
            taquilla.addClienteEnCola(cliente);
            log.add("\tSe pone en cola en taquilla " + taquilla.getId() + " (tamaño cola: " + taquilla.getColaSize() + ")\n");
        }
    }

    private void entradaTicket(TicketOffice taquilla) {
        log.add("\tTipo de suceso: LLEGADATICKET (" + taquilla.getId() + ")\n");

        //Poner la taquilla en estado ocupado
        taquilla.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(taquilla);
    }

    private void salidaTicket(TicketOffice taquilla) {
        log.add("\tTipo de suceso: SALIDATICKET (" + taquilla.getId() + ")\n");

        Client clienteServido = taquilla.getClienteSirviendose();
        taquilla.libre();
        taquilla.addClientesServidos();

        if (taquilla.getColaSize() != 0) { //La cola tiene clientes
            Client cliente = taquilla.getSiguienteCliente();
            log.add("\tEntra siguiente cliente en cola de la taquilla (" + taquilla.getId() + ")\n");
            cliente.setTiempoSalidaCola(reloj.getSeconds());
            taquilla.addTiempoClientesCola(cliente.getTiempoCola());
            taquilla.setClienteSirviendose(cliente);
            this.entradaTicket(taquilla);
        } else {
            //Actualizar lista de sucesos
            this.sucesos.get(taquilla.getId()).set(SALIDATICKET, INFINITO);
        }

//        taquilla.addTiempoClientesCola(clienteServido.getTiempoCola());

        //Determinar si compra palomitas o sale del sistema
        if (clienteServido.getPalomitas() != 0) {
            //Compra palomitas
            log.add("\tVa a comprar palomitas (" + clienteServido.getPalomitas() + ")\n");
            asignacionPop(clienteServido);
        } else {
            log.add("\tNo va a comprar palomitas\n");
        }
    }

    private void asignacionPop(Client cliente) {

        PopcornStand palomitas = this.getPalomitasMenosOcupada();

        if (palomitas.isLibre()) { //Servidor libre
            palomitas.setClienteSirviendose(cliente);
            this.entradaPop(palomitas);
        } else { //Servidor ocupado
            cliente.setTiempoLlegadaCola(this.reloj.getSeconds());
            palomitas.addClienteEnCola(cliente);
        }
    }

    private void entradaPop(PopcornStand palomitas) {
        log.add("[" + this.reloj.getTime() + "] ");
        log.add("\tTipo de suceso: LLEGADAPALOMITAS (" + palomitas.getId() + ")\n");

        //Poner la taquilla en estado ocupado
        palomitas.ocupado();
        //Tiempo de servicio del cliente
        this.calculoSalidaSiguienteCliente(palomitas);
    }

    private void salidaPop(PopcornStand palomitas) {
        log.add("\tTipo de suceso: SALIDAPALOMITAS (" + palomitas.getId() + ")\n");

        Client clienteServido = palomitas.getClienteSirviendose();
        palomitas.libre();
        palomitas.addClientesServidos();

        if (palomitas.getColaSize() != 0) { //La cola tiene clientes
            Client cliente = palomitas.getSiguienteCliente();
            cliente.setTiempoSalidaCola(reloj.getSeconds());
            palomitas.addTiempoClientesCola(cliente.getTiempoCola());
            palomitas.setClienteSirviendose(cliente);
            this.entradaPop(palomitas);
        } else {
            //Actualizar lista de sucesos
            this.sucesos.get(palomitas.getId()).set(SALIDAPALOMITAS, INFINITO);
        }
        //Calcular datos estadísticos
//        palomitas.addTiempoClientesCola(clienteServido.getTiempoCola());
    }

    private void finSimulacion() {
        fin = true;
    }

    /* ********************* OBTENCION DE RESULTADOS ********************* */
    /**
     * Devuelve el tamaño medio de las colas de las taquillas
     *
     * @return Tamaño medio de colas de las Taquillas
     */
    public Float tamMedioColasTaquillas() {
        Float tam = 0.0f;
        for (TicketOffice t : taquillas) {
            tam += t.getColaSize();
        }
        tam = tam / taquillas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tam));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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
     */
    public Float tamMedioColasPuestosPalomitas() {
        Float tam = 0.0f;
        for (PopcornStand t : puestosPalomitas) {
            tam += t.getColaSize();
        }
        tam = tam / puestosPalomitas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tam));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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
     */
    public Float numMedioAtendidosTaquilla() {
        float tam = 0;
        for (TicketOffice t : taquillas) {
            tam += t.getClientesServidos();
        }
        tam = tam / taquillas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tam));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Devuelve el número medio de clientes atendidos por puesto de palomitas
     *
     * @return Número medio de atendidos por puesto de palomitas
     */
    public Float numMedioAtendidosPuestoPalomitas() {
        Float tam = 0.0f;
        for (PopcornStand t : puestosPalomitas) {
            tam += t.getClientesServidos();
        }
        tam = tam / puestosPalomitas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tam));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * @return Tiempo medio en cola para n clientes en las taquillas
     */
    public Float tiempoMedioColasTaquillas() {
        Float tiempoMedio = 0.0f;
        for (TicketOffice taquilla : this.taquillas) {
            if (taquilla.getClientesServidos() > 0) {
                tiempoMedio += taquilla.getTiempoClientesCola().floatValue() / taquilla.getClientesServidos().floatValue();
            }
        }
        tiempoMedio = tiempoMedio / this.taquillas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tiempoMedio));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * @return Tiempo medio en cola para n clientes en los puestos de palomitas
     */
    public Float tiempoMedioColasPalomitas() {
        Float tiempoMedio = 0.0f;
        for (PopcornStand palomitas : this.puestosPalomitas) {
            if (palomitas.getClientesServidos() > 0) {
                tiempoMedio += palomitas.getTiempoClientesCola().floatValue() / palomitas.getClientesServidos().floatValue();
            }
        }
        tiempoMedio = tiempoMedio / this.puestosPalomitas.size();
        BigDecimal bd = new BigDecimal(Float.toString(tiempoMedio));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * @return Total de personas atendidas en taquillas de tickets
     */
    public Integer totalPersonasAtendidasTicket() {
        Integer suma = 0;
        for (TicketOffice taquilla : this.taquillas) {
            suma += taquilla.getClientesServidos();
        }
        return suma;
    }

    /**
     * @return Total de personas atendidas en puestos de palomitas
     */
    public Integer totalPersonasAtendidasPalomitas() {
        Integer suma = 0;
        for (PopcornStand palomitas : this.puestosPalomitas) {
            suma += palomitas.getClientesServidos();
        }
        return suma;
    }

    public Float totalTamMedioColasTaquillas() {
        Float media = 0f;
        Integer tiempoActual = this.reloj.getSeconds();
        for(TicketOffice taquilla:this.taquillas){
            media+=taquilla.getTamMedioCola(tiempoActual);
        }
        media = media / this.taquillas.size();
        //Redondeo
        BigDecimal bd = new BigDecimal(Float.toString(media));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public Float totalTamMedioColasPalomitas() {
        Float media = 0.0f;
        Integer tiempoActual = this.reloj.getSeconds();
        for(PopcornStand palomitas:this.puestosPalomitas){
            media+=palomitas.getTamMedioCola(tiempoActual);
        }
        media = media / this.puestosPalomitas.size();
        //Redondeo
        BigDecimal bd = new BigDecimal(Float.toString(media));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * @return Grado de ocupación de las taquillas de tickets
     */
    public Float gradoOcupacionTicket() {
        Integer totalOcupadas = 0;
        for (TicketOffice taquilla : this.taquillas) {
            if (!taquilla.isLibre()) {
                ++totalOcupadas;
            }
        }
        Float gradoOcupacion = totalOcupadas.floatValue() / this.taquillas.size();
        BigDecimal bd = new BigDecimal(Float.toString(gradoOcupacion));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * @return Grado de ocupación de los puestos de palomitas
     */
    public Float gradoOcupacionPalomitas() {
        Integer totalOcupadas = 0;
        for (PopcornStand taquilla : this.puestosPalomitas) {
            if (!taquilla.isLibre()) {
                ++totalOcupadas;
            }
        }
        Float gradoOcupacion = totalOcupadas.floatValue() / this.puestosPalomitas.size();
        BigDecimal bd = new BigDecimal(Float.toString(gradoOcupacion));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public Integer tiempoMedioColaClientes() {
        return 0;
    }

    /* *********************** MÉTODOS AUXILIARES *********************** */
    /**
     * Devuelve la taquilla que menos cola tiene
     *
     * @return Taquilla con menos cola
     */
    private TicketOffice getTaquillaMenosOcupada() {
        TicketOffice taquillaReturn = this.taquillas.get(0);
        for (TicketOffice t : this.taquillas) {
            if (t.isLibre())
                return t;
            else if (t.getColaSize() < taquillaReturn.getColaSize()) 
                    taquillaReturn = t;
        }
        return taquillaReturn;
    }

    /**
     * Devuelve el puesto de palomitas que menos cola tiene
     *
     * @return Puesto de palomitas con menos cola
     */
    private PopcornStand getPalomitasMenosOcupada() {
        PopcornStand palomitasReturn = this.puestosPalomitas.get(0);
        for (PopcornStand p : this.puestosPalomitas) {
            if (p.isLibre())
                return p;
            else if (p.getColaSize() < palomitasReturn.getColaSize()) 
                    palomitasReturn = p;
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
                        && sucesos.get(i).get(j) >= reloj.getSeconds()) {
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
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
     */
    private int numTickets() {
        if (this.randomLehmer.getRandomUnidad() >= this.probabilidadTicketMultiple) {
            return 1;
        } else {
            double rand = this.randomLehmer.getRandomUnidad();
            if(rand<0.6 && rand>0){
                return 2;
            } else if(rand<0.85 && rand>=0.6){
                return 3;
            } else if(rand<0.95 && rand>=0.85){
                return 4;
            } else if(rand<1 && rand>=0.95){
                return 5;
            } else{
                return 1;
            }
        }
    }

    /**
     * Función aleatoria para calcular cuántas palomitas compra (lineal)
     *
     * @return Número de palomitas que compra [0, MAX_PALOMITAS]
     */
    private int comprarPalomitas() {
        if (this.randomLehmer.getRandomUnidad() >= this.probabilidadPalomitas) {
            return 0;
        } else {
            double rand = this.randomLehmer.getRandomUnidad();
            if(rand<0.6 && rand>0){
                return 1;
            } else if(rand<0.85 && rand>=0.6){
                return 2;
            } else if(rand<0.95 && rand>=0.85){
                return 3;
            } else if(rand<1 && rand>=0.95){
                return 4;
            } else{
                return 0;
            }
        }
    }

    /**
     * Función aleatoria para calcular el tiempo de llegada del siguiente
     * cliente (cuadrática)
     *
     * @warnig Deberia usar el parametro de probabilidad de la interfaz
     */
    private void calculoLlegadaSiguienteCliente() {
        RandomExponential r = new RandomExponential(this.frecuenciaClientes);
        Double tiempoLlegada = r.getExponential();
        
//        Integer tiempoLlegada = this.frecuenciaClientes.intValue();
//        if (randomLehmer.getRandomUnidad() < 0.5) {
//            tiempoLlegada += aleatorio.intValue();
//        } else {
//            tiempoLlegada -= aleatorio.intValue();
//        }

        //Si es negativo que se quede a cero
//        if (tiempoLlegada < 0) {
//            tiempoLlegada = 0;
//        }

        this.sucesos.get(0).set(LLEGADATICKET, this.reloj.getSeconds() + tiempoLlegada.intValue());
        this.log.add("\tSiguiente llegada en " + tiempoLlegada.toString() + " segundos (" + this.getTime(this.reloj.getSeconds() + tiempoLlegada.intValue()) + ")\n");
    }

    /**
     * Función aleatoria para calcular el tiempo de servicio en taquilla del
     * cliente (lineal)
     *
     * @param taquilla
     */
    private void calculoSalidaSiguienteCliente(TicketOffice taquilla) {
        RandomExponential r = new RandomExponential(this.tiempoServicioTaquilla);
        Double tiempoServicioActual = r.getExponential();
//        Integer tiempoServicioActual;
//        if (this.randomLehmer.getAnteriorUnidad() < 0.5) {
//            tiempoServicioActual = (int) (taquilla.getTiempoServicio() + this.randomCuadratico.getRandom());
//        } else {
//            tiempoServicioActual = (int) (taquilla.getTiempoServicio() - this.randomCuadratico.getRandom());
//            if (tiempoServicioActual < 1) {
//                tiempoServicioActual = 1;
//            }
//        }
        this.sucesos.get(taquilla.getId()).set(SALIDATICKET, this.reloj.getSeconds() + tiempoServicioActual.intValue());

        //Sacar el log
        log.add("\tTiempo de servicio en la taquilla " + taquilla.getId() + ": " + tiempoServicioActual + ". Salida prevista: " + this.getTime(this.reloj.getSeconds() + tiempoServicioActual.intValue()) + " (en caso de entrar ahora)\n");
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
        RandomExponential r = new RandomExponential(this.tiempoServicioPalomitas);
        Double tiempoServicioActual = r.getExponential();
        //this.sucesos.get(palomitas.getId()).set(SALIDAPALOMITAS, this.reloj.getSeconds() + palomitas.getTiempoServicio());
//        Integer tiempoServicioActual;
//        if (this.randomLehmer.getAnteriorUnidad() < 0.5) {
//            tiempoServicioActual = (int) (palomitas.getTiempoServicio() + this.randomCuadratico.getRandom());
//        } else {
//            tiempoServicioActual = (int) (palomitas.getTiempoServicio() - this.randomCuadratico.getRandom());
//            if (tiempoServicioActual < 1) {
//                tiempoServicioActual = 1;
//            }
//        }
        this.sucesos.get(palomitas.getId()).set(SALIDAPALOMITAS, this.reloj.getSeconds() + tiempoServicioActual.intValue());

        //Sacar el log
        log.add("\tTiempo de servicio en el puesto de palomitas " + palomitas.getId() + ": " + tiempoServicioActual + ". Salida prevista: " + this.getTime(this.reloj.getSeconds() + tiempoServicioActual.intValue()) + " (en caso de entrar ahora)\n");
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
}
