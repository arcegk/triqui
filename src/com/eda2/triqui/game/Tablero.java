package com.eda2.triqui.game;

import com.eda2.triqui.util.ExceptionCellBussy;
import com.eda2.triqui.util.ExceptionExpectedValue;

/**
 * Clase que sirve para definir un tablero de 3x3 el cual es la base para jugar el triqui.
 * En cada casilla del tablero se va a asignar un valor que puede ser el siguiente.
 *   - 0: Para indicar que la casilla esta vacía.
 *   - 1: Para inidcar que la casilla esta marcada con "O".
 *   - 2: Para indicar que la casilla esta marcada con "X".
 * @author oarcila
 */
public class Tablero
{
    private int t[][];
    
    /**
     * Constructor por defecto de la clase Tablero.
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: Tablero()">
    public Tablero()
    {
        t = new int[3][3];
    }
    //</editor-fold>
    
    /**
     * Metodo que permite asigar valores en las casillas de tablero.
     * @param f indice de la fila a marcar
     * @param c indice de la columna a marcar
     * @param v valor con el cual se marca la celda.
     * @throws ExceptionExpectedValue Excepcion que se lanza cuando el valor de la marca no es uno de los valores permitidos.
     * @throws ExceptionCellBussy  Excepcion que se lanza cuando se trata de escribir sobre una celda que ya esta ocupada, marcada previamente.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: asignarJugada(int, int, int)">
    public void asignarJugada(int f, int c, int v) throws ExceptionExpectedValue, ExceptionCellBussy
    {
        if ( v == 1 || v == 2)
        {
            if ( t[f][c] == 0)
            {
                t[f][c] = v;
            }
            else
            {
                throw new ExceptionCellBussy(f,c);
            }
        }
        else
        {
            throw new ExceptionExpectedValue(v);
        }
    }
    //</editor-fold>

    /**
     * Este metodo permite revisar por filas para ver si hay el numero de marcas requeridas para que alguno de los jugadores
     * gane la partida.
     * @param index indice de la fila a revisar.
     * @return un valor que indica si hay o no un ganador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: hayGanadorFila(int)">
    public boolean hayGanadorFila(int index)
    {
        int c1 = 0;
        int c2 = 0;
        
        for (int i = 0; i < 3; i++)
        {
            if ( t[index][i] == 1)
            {
                c1++;
            }
            if ( t[index][i] == 2)
            {
                c2++;
            }
        }
        
        return c1 == 3 || c2 == 3;
    }
    //</editor-fold>
    
    /**
     * Este metodo permite revisar por columnas para ver si hay el numero de marcas requeridas para que alguno de los jugadores
     * gane la partida.
     * @param index indice de la columna a revisar.
     * @return un valor que indica si hay o no un ganador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: hayGanadorColumna(int)">
    boolean hayGanadorColumna(int index)
    {
        int c1 = 0;
        int c2 = 0;
        
        for (int i = 0; i < 3; i++)
        {
            if ( t[i][index] == 1)
            {
                c1++;
            }
            if ( t[i][index] == 2)
            {
                c2++;
            }
        }
        
        return c1 == 3 || c2 == 3;
    }
    //</editor-fold>

    /**
     * Este metodo permite revisar la diagonal principal para ver si hay el numero de marcas requeridas para que alguno de los jugadores
     * gane la partida.
     * @return un valor que indica si hay o no un ganador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: revisarDiagonalPrincipal()">
    public boolean revisarDiagonalPrincipal()
    {
        int c1 = 0;
        int c2 = 0;
        
        for (int i = 0; i < 3; i++)
        {
            if ( t[i][i] == 1)
            {
                c1++;
            }
            if ( t[i][i] == 2)
            {
                c2++;
            }
        }
        
        return c1 == 3 || c2 == 3;
    }
    //</editor-fold>
    
    /**
     * Este metodo permite revisar la diagonal inversa para ver si hay el numero de marcas requeridas para que alguno de los jugadores
     * gane la partida.
     * @return un valor que indica si hay o no un ganador.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: revisarDiagonalInversa()">
    public boolean revisarDiagonalInversa()
    {
        int c1 = 0;
        int c2 = 0;
        
        for (int i = 0; i < 3; i++)
        {
            if ( t[2-i][i] == 1)
            {
                c1++;
            }
            if ( t[2-i][i] == 2)
            {
                c2++;
            }
        }
        
        return c1 == 3 || c2 == 3;
    }
    //</editor-fold>

    //Este metodo retorna el valor del tablero, para que poder tomar decisiones
    public int getValue(int i, int j){
        return t[i][j];
    }
    
    /**
     * Metodo que busca por todo el tablero contando el numero de casillas libres para ver si se acaba la partida
     * sin ganadores.
     * @return numero de casillas libres encontradas.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: contadorCasillasLibre()">
    public int contadorCasillasLibres()
    {
        int c = 0;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if ( t[i][j] == 0)
                {
                    c++;
                }
            }
        }
        
        return c;
    }
    //</editor-fold>

    /**
     * Metodo que convierte un valor entero en una marca reconocida por el juego.
     * @param v valor de la celda
     * @return marca para la celda respectiva
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: eval(int)">
    private String eval ( int v)
    {
        return v == 1 ? "O" : v == 2 ? "X" : " ";
    }
    //</editor-fold>

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("|-|-|-|\n");
        
        for (int i = 0; i < 3; i++)
        {
            sb.append("|");
            for (int j = 0; j < 3; j++)
            {
                sb.append(eval(t[i][j])).append("|");
            }
            sb.append("\n|-|-|-|\n");
        }

        return sb.toString();
    }

}
