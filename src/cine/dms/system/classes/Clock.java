package cine.dms.system.classes;

/**
 * Clase rejol
 *
 * @author Raúl Moya Reyes <rmr00021@red.ujaen.es>
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class Clock {

    // Hora actual del rejol en seconds
    int seconds;

    /**
     * Constructor para iniciar el reloj a una hora.
     *
     * @param seconds Hora de inicio del reloj en seconds.
     */
    public Clock(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return Devuelve la hora actual en seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @return Devuelve la hora actual en formato HH:MM:SS en un String
     */
    public String getTime() {

        String s = (seconds % 60 < 10) ? "0" + String.valueOf(seconds % 60) : String.valueOf(seconds % 60);
        int min = seconds / 60;
        String m = (min % 60 < 10) ? "0" + String.valueOf(min % 60) : String.valueOf(min % 60);
        String h = (min / 60 < 10) ? "0" + String.valueOf(min / 60) : String.valueOf(min / 60);

        return h + ":" + m + ":" + s;

    }

    /**
     * Avanza el rejol hasta la hora indicada
     *
     * @param seconds hora a la que avanzar el reloj
     */
    public void advance(int seconds) {
        this.seconds = seconds;
    }

}
