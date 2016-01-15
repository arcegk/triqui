package com.eda2.triqui.game.gamer;

import com.eda2.triqui.game.Posicion;

/**
 * Esta clase es un jugador el cual de forma aleatorio selecciona la casilla en la que va a hacer
 * la proxima jugada.
 * @author oarcila
 */
public class JugadorAleatorio extends JugadorAbstracto
{

    /**
     * Constructor de la clase Jugador Aleatorio.
     * @param nombre
     * @param marca 
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: JugadorAleatorio(String, String)">
    public JugadorAleatorio(String nombre, String marca)
    {
        super(nombre, marca);
    }
    //</editor-fold>

    /**
     * Metodo que determina la logica de juego que utiliza el jugador.
     * @return Posicion de la casilla en la que coloca su marca el jugador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: seleccionarCasilla()">
    @Override
    public Posicion seleccionarCasilla()
    {
        int fila = darValorAleatorio(3);
        int columna = darValorAleatorio(3);
        return new Posicion(fila, columna);
    }
    //</editor-fold>

    /**
     * Genera un valor entre 0 y 3 excluyendo al 3 para la siguiente jugada.
     * @param max
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: darValorAleatorio(int)">
    private int darValorAleatorio(int max)
    {
        return (int) (Math.random() * max);
    }
    //</editor-fold>
    
}
