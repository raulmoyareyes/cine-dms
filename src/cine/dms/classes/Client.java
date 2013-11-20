/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.dms.classes;

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

    /**
     * Constructor con todos los parámetros.
     *
     * @param numTicket
     * @param palomitas
     */
    public Client(int numTicket, int palomitas) {
        this.numTicket = numTicket;
        this.palomitas = palomitas;
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

}
