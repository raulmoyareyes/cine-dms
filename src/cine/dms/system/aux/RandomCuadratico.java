package cine.dms.system.aux;

import cine.dms.system.exceptions.ExcepcionGeneradorIncorrecto;
import java.util.Random;

/**
 * Generador Cuadrático
 *
 * x_(n+1) = ( a · (x_n)^2 + b · x_n) / m
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class RandomCuadratico {

    private double a;
    private double b;
    private double m;
    private double reductor;
    private RandomLehmer randomLehmer;
    
    /**
     * Constructor
     *
     * @param a Multiplicador cuadrático
     * @param b Multiplicador lineal
     * @param m Factor de escala
     */
    public RandomCuadratico(double a, double b, double m) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.reductor = this.a+this.b;
        randomLehmer = new RandomLehmer(0.84641, 0.645, 1);
    }

    /**
     * @return Multiplicador cuadrático
     */
    public double getA() {
        return this.a;
    }

    /**
     * @return Multiplicador lineal
     */
    public double getB() {
        return this.b;
    }

    /**
     * @return Factor de escala (valor máximo)
     */
    public double getM() {
        return this.m;
    }

    /**
     * @return Valor aleatorio cuadrático entre 0 y m
     * @throws ExcepcionGeneradorIncorrecto Se lanza cuando el constructor no es
     * correcto
     */
    public double getRandom() throws ExcepcionGeneradorIncorrecto {
        Double random = this.randomLehmer.getRandom();
        return ((this.a * random * random) + (this.b * random)) / this.reductor * this.m;
    }
}
