package cine.dms.system.aux;

import cine.dms.system.exceptions.ExcepcionGeneradorIncorrecto;
import java.util.Random;

/**
 * Generador congruencial lineal multiplicativo de Lehmer
 *
 * x_(n+1) = ( a · x_n + c) mod m
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class RandomLehmer {

    private double anterior;
    private double a;
    private double c;
    private double m;
    private final boolean generadorOK;

    /**
     * Constructor
     *
     * @param a Valor de a (multiplicador)
     * @param c Valor de c (incremento)
     * @param m Valor de m (módulo)
     * @pre m <= 0 ; a >= m ; c >= m ; a < 0 ; c < 0
     */
    public RandomLehmer(double a, double c, double m) {
        if (m <= 0 || a >= m || c >= m || a < 0 || c < 0) {
            this.generadorOK = false;
        } else {
            this.generadorOK = true;
            Random rand = new Random();
            this.anterior = rand.nextDouble();
            this.a = a;
            this.c = c;
            this.m = m;
        }
    }

    /**
     * @return Valor anterior (entre 0 y m)
     */
    public double getAnterior() {
        return anterior;
    }

    /**
     * @return Valor anterior (entre 0 y 1)
     */
    public double getAnteriorUnidad() {
        return anterior / this.m;
    }

    /**
     * @return Multiplicador
     */
    public double getA() {
        return a;
    }

    /**
     * @return Incremento
     */
    public double getC() {
        return c;
    }

    /**
     * @return Módulo
     */
    public double getM() {
        return m;
    }

    /**
     * Indica si el generador es correcto o no según sus parámetros
     *
     * @return true si el generador es correcto o false en caso contrario
     */
    public boolean isGeneradorOK() {
        return generadorOK;
    }

    /**
     *
     * @return Valor aleatorio entre 0 y m
     */
    public double getRandom() {
        if (generadorOK) {
            this.anterior = (this.a * this.anterior + this.c) % this.m;
            return this.anterior;
        } else {
            throw new ExcepcionGeneradorIncorrecto();
//            return 0;
        }
    }
    
    /**
     *
     * @return Valor aleatorio entre 0 y 1
     */
    public double getRandomUnidad() {
        return this.getRandom() / this.m;
    }
}
