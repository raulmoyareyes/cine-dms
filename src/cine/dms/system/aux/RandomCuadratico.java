package cine.dms.system.aux;

/**
 * Generador Cuadrático
 *
 * x_(n+1) = ( a · (x_n)^2 + b · x_n) / m
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class RandomCuadratico {

    private final double a;
    private final double b;
    private final double m;
    private final double reductor;
    private final RandomLehmer randomLehmer;

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
        this.reductor = this.a + this.b;
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
     */
    public double getRandom() {
        Double random = this.randomLehmer.getRandom();
        return ((this.a * random * random) + (this.b * random)) / this.reductor * this.m;
    }
}
