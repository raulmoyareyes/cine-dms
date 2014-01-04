package cine.dms.system.classes;

/**
 * Clase cliente para controlar los parametros de este.
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class Client {

    /// Número de entradas que va a comprar
    private int numTicket;

    /// Número de bolsas de palomitas que va a comprar
    private int palomitas;

    /// Tiempo de llegada a la cola
    private int tiempoLlegadaCola;

    /// Tiempo de salida a la cola
    private int tiempoSalidaCola;

    /**
     * Constructor con todos los parámetros.
     *
     * @param numTicket
     * @param palomitas
     */
    public Client(int numTicket, int palomitas) {
        this.numTicket = numTicket;
        this.palomitas = palomitas;
        this.tiempoLlegadaCola = 0;
        this.tiempoSalidaCola = 0;
    }

    /**
     * Constructor por defecto.
     */
    public Client() {
    }

    /**
     * @return Devuelve el número de entradas que va a comprar.
     */
    public int getNumTicket() {
        return numTicket;
    }

    /**
     * @return Devuelve el número de paquetes de palomitas que va a comprar.
     */
    public int getPalomitas() {
        return palomitas;
    }

    public int getTiempoCola() {
        return tiempoSalidaCola - tiempoLlegadaCola;
    }

    public void setTiempoLlegadaCola(int tiempoLlegadaCola) {
        this.tiempoLlegadaCola = tiempoLlegadaCola;
    }

    public void setTiempoSalidaCola(int tiempoSalidaCola) {
        this.tiempoSalidaCola = tiempoSalidaCola;
    }
}
