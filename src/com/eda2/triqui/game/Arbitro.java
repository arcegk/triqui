package com.eda2.triqui.game;

import com.eda2.triqui.util.ExceptionCellBussy;
import com.eda2.triqui.game.gamer.JugadorAbstracto;
import com.eda2.triqui.util.ExceptionEqualLabelAsign;
import com.eda2.triqui.util.ExceptionBadLabelAsign;
import com.eda2.triqui.util.ExceptionExpectedValue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oarcila
 */
public class Arbitro
{
    private Tablero t;
    private JugadorAbstracto jugador1;
    private JugadorAbstracto jugador2;
    private JugadorAbstracto jugadorEnTurno;
    
    private boolean fin;
    
    /**
     * Constructor con parametros de la clase arbitro.
     * @param jugador1
     * @param jugador2 
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: Arbitro(JugadorAbstracto, JugadorAbstracto)">
    public Arbitro (JugadorAbstracto jugador1, JugadorAbstracto jugador2)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        
        t = new Tablero();
        fin = false;
    }
    //</editor-fold>
    
    /**
     * Constructor por defecto, se define privado para que no se pueda crear un objeto
     * sin parametros.
     */
    //<editor-fold defaultstate="collapsed" desc="Constructor :: Arbitro ()">
    private Arbitro()
    {
        this(null, null);
    }
    //</editor-fold>

    /**
     * Permite asignar un jugador para que inicie el juego, dependiendo de la marca "O".
     * @throws ExceptionBadLabelAsign
     * @throws ExceptionEqualLabelAsign 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: iniciarJuego()">
    public void iniciarJuego() throws ExceptionBadLabelAsign, ExceptionEqualLabelAsign
    {
        // Se asigna al jugador 1 para el primer turno si tiene la marca "O".
        if ( jugador1.getMarca().equals("O"))
        {
            jugadorEnTurno = jugador1;
        }
        // Se asigna al jugador 2 para el primer turno si tiene la marca "O".
        if ( jugador2.getMarca().equals("O"))
        {
            jugadorEnTurno = jugador2;
        }
        
        // Se verifica que los dos jugadores tengan marcas diferentes.
        if ( jugador1.getMarca().equals(jugador2.getMarca()))
        {
            throw new ExceptionEqualLabelAsign(jugador1.getMarca());
        }
        // Se verifica que el jugador 1 tengan marcas adecuadas "O" o "X".
        if ( !jugador1.getMarca().equals("X")&&!jugador1.getMarca().equals("O"))
        {
            throw new ExceptionBadLabelAsign(jugador1.getNombre());
        }
        // Se verifica que el jugador 2 tengan marcas adecuadas "O" o "X".
        if ( !jugador2.getMarca().equals("X")&&!jugador2.getMarca().equals("O"))
        {
            throw new ExceptionBadLabelAsign(jugador2.getNombre());
        }
    }
    //</editor-fold>

    /**
     * Este metodo tiene la logica del juego, permite que el jugador realice su jugada, que repita si escogio mal la celda en la que
     * jugo.
     * Intercambia el jugador para asignar el nuevo turno.
     * Determina si el juego termina ya sea por que empataron los jugadores o hay un ganador.
     */
    public void jugar()
    {
        boolean repiteTiro;
        System.out.println("El juego lo va a iniciar el jugador " + jugadorEnTurno.getNombre());
        while (!fin)
        {
            repiteTiro = false;
            System.out.println("Ahora juega " + jugadorEnTurno.getNombre());
            // Permite que el jugador que tiene el turno seleccione la casilla donde quiere colocar su marca.
            Posicion p = jugadorEnTurno.seleccionarCasilla();
            // Obtiene la marca del jugador en turno.
            int valor = asignarValorAdecuado(jugadorEnTurno.getMarca());
            try
            {
                // Asigna la marca en la casilla adecuada, teniendo en cuenta que la casilla este libre.
                t.asignarJugada(p.getFila(), p.getColumna(), valor);
                System.out.println(t);
            }
            catch (ExceptionExpectedValue ex)
            {
                Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ExceptionCellBussy ex)
            {
                // Cuando la casilla esta ocupada, permite que el jugador repita el turno con otros datos.
                repiteTiro = true;
                System.out.println(ex.getMessage());
            }
            // Verifica si despues de la jugada hay ganador.
            if(hayGanador())
            {
                fin = true;
            }
            // Verifica si hay casillas libres para seguir jugando o si queda el juego empatado.
            if(!fin&&hayEmpate())
            {
                fin = true;
                jugadorEnTurno = null;
            }
            // Cambia el jugador en turno cuando corresponde.
            if (!fin && !repiteTiro)
            {
                cambiarJugador();
            }
        }
    }

    /**
     * Metodo que permite obtener el ganador al final del juego.
     * Este valor puede ser <code>null</code> en caso de empate.
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: obtenerGanador()">
    public JugadorAbstracto obtenerGanador()
    {
        return jugadorEnTurno;
    }
    //</editor-fold>

    /**
     * Metodo que permite cambiar el jugador que tiene el turno.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: cambiarJugador()">
    private void cambiarJugador()
    {
        jugadorEnTurno = jugadorEnTurno == jugador1 ? jugador2 : jugador1;
    }
    //</editor-fold>
    
    /**
     * Metodo que permite decidir si hay un empate en el juego.
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: hayEmpate()">
    private boolean hayEmpate()
    {
        return t.contadorCasillasLibres() == 0;
    }
    //</editor-fold>
    
    /**
     * Metodo que permite saber si hay un ganador en el juego, buscando si hay una linea con tres marcas iguales.
     * Verifica primero todas la filas, luego todas la columnas, finalmente verifica las diagonales principales del
     * tablero.
     * @return Un valor que indica si hay un ganador o no lo hay.
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: hayGanador()">
    private boolean hayGanador()
    {
        boolean f = false;
        // Verifica todas la filas
        for(int i = 0; !f && i < 3; i++)
        {
            f = t.hayGanadorFila(i);
        }
        // Verifica todas la columnas
        for(int i = 0; !f && i < 3; i++)
        {
            f = t.hayGanadorColumna(i);
        }
        // Verificas las diagonales del tablero.
        if( !f)
        {
            f = t.revisarDiagonalPrincipal() || t.revisarDiagonalInversa();;
        }
        return f;
    }
    //</editor-fold>

    public Tablero getT() {
        return t;
    }
    

    /**
     * Metodo que permite convertir una marca en valor adecuado para asignar en la matriz que forma el tablero.
     * @param marca
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: asignarValorAdecuado(String)">
    public int asignarValorAdecuado(String marca)
    {
        return marca.equals("O") ? 1 : 2;
    }
    //</editor-fold>

    @Override
    public String toString()
    {
        return "Arbitro{" + "t=" + t + '}';
    }

}
