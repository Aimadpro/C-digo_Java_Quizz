public class Jugador implements Comparable<Jugador> {
    private String nombre;
    private int puntuacion;
    private int intentos;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public  void respuestaCorrecta(){
        this.puntuacion=this.puntuacion+1;
    }

    public int compareTo(Jugador otro) {
        return Integer.compare(otro.getPuntuacion(), this.puntuacion);
    }



}
