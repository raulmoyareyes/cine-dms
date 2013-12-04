package cine.dms.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class TicketOffice {

    ///Estado de la taquilla (0 = libre, 1 = ocupada)
    private char estado;

    ///Clientes servidos
    int clientesServidos;

    ///Tiempo de servicio
    Double tiempoServicio;

    ///Cola de clientes
    List<Client> cola;

    /**
     * Constructor por defecto
     */
    public TicketOffice() {
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = 3.0;
        this.cola = new ArrayList();
    }

    /**
     * Constructor
     *
     * @param tiempoServicio Tiempo de servicio
     */
    public TicketOffice(Double tiempoServicio) {
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = tiempoServicio;
        this.cola = new ArrayList();
    }

    /**
     * Estado de la taquilla
     *
     * @return true si la taquilla está libre o false en caso contrario
     */
    public boolean isLibre() {
        return (this.estado == 0);
    }

    /**
     * Cambia el estado de la taquilla
     */
    public void switchEstado() {
        if (this.estado == 0) {
            this.estado = 1;
        } else {
            this.estado = 0;
        }
    }

    /**
     * Devuelve los clientes servidor
     *
     * @return Número de clientes servidos
     */
    public int getClientesServidos() {
        return clientesServidos;
    }

    /**
     * Añade un cliente servido a la lista
     */
    public void addClientesServidos() {
        ++this.clientesServidos;
    }

    /**
     * Obtener tiempo de servicio
     *
     * @return Tiempo de servicio
     */
    public Double getTiempoServicio() {
        return tiempoServicio;
    }

    /**
     * Cambiar tiempo de servicio
     *
     * @param tiempoServicio
     * @warning Cuidado al cambiar el tiempo para hacer cálculos
     */
    public void setTiempoServicio(Double tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    /**
     * Devuelve el siguiente cliente de la cola y lo elimina de la misma
     *
     * @return Siguiente cliente de la cola
     * @warning No testeado
     */
    public Client getSiguienteCliente() {
        Client clientReturn = this.cola.get(0);
        this.cola.remove(0);
        return clientReturn;
    }

    /**
     * Añade un cliente a la cola
     *
     * @param client
     */
    public void addClienteEnCola(Client client) {
        this.cola.add(client);
    }

    /**
     * Devuelve el tamaño de la cola
     *
     * @return Tamaño de la cola
     */
    public int getColaSize() {
        return this.cola.size();
    }
}
