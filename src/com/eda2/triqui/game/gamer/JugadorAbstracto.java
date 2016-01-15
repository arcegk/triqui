package com.eda2.triqui.game.gamer;

import com.eda2.triqui.game.Posicion;

/**
 * Esta clase tiene las cosas basicas que necesita un jugador para poder participar en una partida.
 * @author oarcila
 */
public abstract class JugadorAbstracto
{
    private String marca;
    private String nombre;
    
    /**
     * Constructor con parametros. Se asegura que el jugador tenga nombre y una marca asignada.
     * @param nombre
     * @param marca 
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: JugadorAbstracto(String, String)">
    public JugadorAbstracto(String nombre, String marca)
    {
        this.nombre = nombre;
        this.marca = marca;
    }
    //</editor-fold>
    
    /**
     * Constructor por defecto.
     * Al definirlo privado evita que alguien cree un jugador sin nombre ni marca.
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: JugadorAbstracto()">
    private JugadorAbstracto()
    {
        this("", "");
    }
    //</editor-fold>

    /**
     * Obtiene la marca con que esta jugando
     * @return marca
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: getMarca()">
    public String getMarca()
    {
        return marca;
    }
    //</editor-fold>
    
    /**
     * Obtiene el nombre del jugador
     * @return nombre del jugador
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: getNombre()">
    public String getNombre()
    {
        return nombre;
    }
    //</editor-fold>
    
    /**
     * En este metodo se determina la logica del juego con la cual 
     * @return 
     */
    public abstract Posicion seleccionarCasilla ();
}
