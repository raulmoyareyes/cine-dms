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

    ///Tiempo acumulado de espera de los clientes en cola
    int tiempoClientesCola;

    ///Tiempo que tarda en ofrecer el servicio
    int tiempoServicio;

    ///Cola de clientes
    List<Client> cola;

    ///Cliente sirviéndose
    Client clienteSirviendose;

    ///Tamaño medio de la cola hasta el tiempo en funcionamiento
    Float tamMedioCola;

    ///Tiempo en funcionamiento (desde que abrió hasta el momento actual)
    int tiempoDeSimulacion;

    ///Tiempo de inicio del sistema
    int tiempoInicioSistema;

    /**
     * Constructor por defecto
     *
     * @param tiempoInicioSistema Tiempo de inicio del sistema
     */
    public TicketOffice(int tiempoInicioSistema) {
        id = nexId;
        ++nexId;
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = 30;
        this.cola = new ArrayList();
        this.clienteSirviendose = null;
        this.tamMedioCola = 0f;
        this.tiempoDeSimulacion = 0;
        this.tiempoInicioSistema = tiempoInicioSistema;
    }

    /**
     * Constructor
     *
     * @param tiempoInicioSistema Tiempo de inicio del sistema
     * @param tiempoServicio Tiempo de servicio
     */
    public TicketOffice(int tiempoInicioSistema, int tiempoServicio) {
        id = nexId;
        ++nexId;
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = tiempoServicio;
        this.cola = new ArrayList();
        this.clienteSirviendose = null;
        this.tamMedioCola = 0f;
        this.tiempoDeSimulacion = 0;
        this.tiempoInicioSistema = tiempoInicioSistema;
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
    public Integer getClientesServidos() {
        return clientesServidos;
    }

    /**
     * Añade un cliente servido a la lista
     */
    public void addClientesServidos() {
        ++this.clientesServidos;
    }

    /**
     * Calcula el tamaño medio de la cola hasta el momento actual
     *
     * @param horaActual
     * @return Tamaño medio de la cola hasta el momento actual
     */
    public Float getTamMedioCola(int horaActual) {
        //Espacio de tiempo que hay que sumar al valor del tamaño medio de la cola
        Integer tiempoGap = this.tiempoDeSimulacion + horaActual - this.tiempoInicioSistema;

        Integer sumatoria = (int) (this.tamMedioCola * this.tiempoDeSimulacion) + (tiempoGap * this.getColaSize());

        //Actualizar valores de la taquilla
        this.tiempoDeSimulacion += tiempoGap;
        this.tamMedioCola = sumatoria.floatValue() / this.tiempoDeSimulacion;

        //Devolver tamaño medio de cola
        return this.tamMedioCola;
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

    /**
     * Obtiene el tiempo acumulado de los clientes servidos en cola
     *
     * @return tiempo de clientes en cola
     */
    public Integer getTiempoClientesCola() {
        return tiempoClientesCola;
    }

    /**
     * Añade el tiempo a la acumulación del resto de tiempos de clientes en cola
     *
     * @param tiempoClientesCola tiempo para acumular
     */
    public void addTiempoClientesCola(int tiempoClientesCola) {
        this.tiempoClientesCola += tiempoClientesCola;
    }
}
