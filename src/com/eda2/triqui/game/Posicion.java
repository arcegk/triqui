package com.eda2.triqui.game;

/**
 *
 * @author oarcila
 */
public class Posicion
{
    
    private int fila;
    private int columna;

    /**
     * Constructor con parametros
     * @param fila
     * @param columna 
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: Posicion(int fila, int columna)">
    public Posicion(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
    }
    //</editor-fold>

    /**
     * Constructor por defecto de posiciones.
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: Posicion()">
    private Posicion()
    {
        this(0, 0);
    }
    //</editor-fold>
    
    /**
     * Get the value of columna
     *
     * @return the value of columna
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: getColumna()">
    public int getColumna()
    {
        return columna;
    }
    //</editor-fold>

    /**
     * Set the value of columna
     *
     * @param columna new value of columna
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: setColumna(int)">
    public void setColumna(int columna)
    {
        this.columna = columna;
    }
    //</editor-fold>

    /**
     * Get the value of fila
     *
     * @return the value of fila
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo getFila()">
    public int getFila()
    {
        return fila;
    }
    //</editor-fold>

    /**
     * Set the value of fila
     *
     * @param fila new value of fila
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: setFila(int)">
    public void setFila(int fila)
    {
        this.fila = fila;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Posicion{" + "fila=" + fila + ", columna=" + columna + '}';
    }
    
    

}
