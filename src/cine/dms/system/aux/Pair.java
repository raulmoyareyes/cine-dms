package cine.dms.system.aux;

/**
 * Par posicion-tipo suceso
 *
 * @param <T0> Cualquier tipo (indicará la posición)
 * @param <T1> Cualquier tipo (indicará el tipo de suceso)
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class Pair<T0, T1> {

    public T0 posicion;
    public T1 tipoSuceso;

    public Pair(T0 posicion, T1 tipoSuceso) {
        this.posicion = posicion;
        this.tipoSuceso = tipoSuceso;
    }

}
