package cine.dms.system.classes;

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

    //Id
    private final int id;

    //NextID
    private static int nexId = 0;

    ///Clientes servidos
    int clientesServidos;

    ///Tiempo de servicio
    int tiempoServicio;

    ///Cola de clientes
    List<Client> cola;

    ///Cliente sirviéndose
    Client clienteSirviendose;

    /**
     * Constructor por defecto
     */
    public TicketOffice() {
        id = nexId;
        ++nexId;
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = 30;
        this.cola = new ArrayList();
        this.clienteSirviendose = null;
    }

    /**
     * Constructor
     *
     * @param tiempoServicio Tiempo de servicio
     */
    public TicketOffice(int tiempoServicio) {
        id = nexId;
        ++nexId;
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = tiempoServicio;
        this.cola = new ArrayList();
        this.clienteSirviendose = null;
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
     * Cambia el estado de la taquilla a libre
     */
    public void libre() {
        this.estado = 0;
    }

    /**
     * Cambia el estado de la taquilla a ocupado
     */
    public void ocupado() {
        this.estado = 1;
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
    public int getTiempoServicio() {
        return tiempoServicio;
    }

    /**
     * Cambiar tiempo de servicio
     *
     * @param tiempoServicio
     * @warning Cuidado al cambiar el tiempo para hacer cálculos
     */
    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    /**
     * Devuelve el siguiente cliente de la cola y lo elimina de la misma
     *
     * @return Siguiente cliente de la cola
     * @warning No testeado
     */
    public Client getSiguienteCliente() {
        return this.cola.remove(0);
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

    /**
     * Devuelve el cliente que se está sirviendo
     *
     * @return Cliente que se está sirviendo (o null si no existe)
     */
    public Client getClienteSirviendose() {
        return clienteSirviendose;
    }

    /**
     * Guarda el cliente que se está sirviendo
     *
     * @param clienteSirviendose Cliente que se está sirviendo
     */
    public void setClienteSirviendose(Client clienteSirviendose) {
        this.clienteSirviendose = clienteSirviendose;
    }

    /**
     * Reinicia el valor para calcular los identificadores
     */
    public static void resetId() {
        nexId = 0;
    }

    /**
     * Obtiene el id del puesto de palomitas
     *
     * @return Id del puesto de palomitas
     */
    public int getId() {
        return id;
    }
}
