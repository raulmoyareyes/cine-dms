package cine.dms.classes;

/**
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class PopcornStand {

    ///Estado del puesto de palomitas (0 = libre, 1 = ocupada)
    private char estado;

    ///Clientes servidos
    int clientesServidos;

    ///Tiempo de servicio
    Double tiempoServicio;

    /**
     * Constructor por defecto
     */
    public PopcornStand() {
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = 3.0;
    }

    /**
     * Constructor
     *
     * @param tiempoServicio Tiempo de servicio
     */
    public PopcornStand(Double tiempoServicio) {
        this.estado = 0;
        this.clientesServidos = 0;
        this.tiempoServicio = tiempoServicio;
    }

    /**
     * Estado del puesto de palomitas
     *
     * @return true si el puesto de palomitas está libre o false en caso
     * contrario
     */
    public boolean isLibre() {
        return (this.estado == 0);
    }

    /**
     * Cambia el estado del puesto de palomitas
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
}
