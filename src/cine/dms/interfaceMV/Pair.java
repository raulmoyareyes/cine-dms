/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cine.dms.interfaceMV;

/**
 * Par posicion-tipo suceso
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
