package com.eda2.triqui.game.gamer;

import com.eda2.triqui.game.Posicion;
import javax.swing.JOptionPane;

/**
 * Esta clase es un jugador el cual esta dominado por un jugador humano el cual toma las decisiones sobre
 * las casillas en las que va a jugar.
 * @author oarcila
 */
public class JugadorHumano extends JugadorAbstracto
{

    /**
     * Constructor de Jugador Humano.
     * @param nombre
     * @param marca 
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: JugadorHumano(String, String)">
    public JugadorHumano(String nombre, String marca)
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
        int fila = leerCasilla("Fila", 3);
        int columna = leerCasilla("Columna", 3);
        return new Posicion(fila, columna);
    }
    //</editor-fold>

    /**
     * Permite que el jugador seleccione un valor entre 1 y 3. Pues el juego tiene tres filas y tres columnas.
     * @param l Texto que explica que se esta leyendo fila o columna.
     * @param max Numero maximo de casillas disponibles.
     * @return Numero de la casilla que selecciono el jugador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: leerCasilla(String, int)">
    private int leerCasilla(String l, int max)
    {
        int v = -1;
        do
        {
            try
            {
                v = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor de la " + l + "\nUn valor entre 1 y " + max));
                v--;
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Valor de casilla erroneo");
            }
        }
        while (v <= -1 || v > max);
        return v;
    }
    //</editor-fold>

}
