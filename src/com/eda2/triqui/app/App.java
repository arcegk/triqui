package com.eda2.triqui.app;

import com.eda2.triqui.game.Arbitro;
import com.eda2.triqui.game.Posicion;
import com.eda2.triqui.game.gamer.JugadorAbstracto;
import com.eda2.triqui.game.gamer.JugadorAleatorio;
import com.eda2.triqui.game.gamer.JugadorHumano;
import com.eda2.triqui.game.gamer.JugadorWAI;
import com.eda2.triqui.tree.Tree;
import com.eda2.triqui.util.ExceptionBadLabelAsign;
import com.eda2.triqui.util.ExceptionEqualLabelAsign;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oarcila
 * editedby : kevin.arce , ian.vasco , julian.ibarra
 */
public class App
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //se crea el arbol para los jugadores inteligentes
        /**
         * Considerabamos que el arbol se creara e inicializara en la clase JugadorWAI
         * pero al haber dos jugadores inteligentes, resultó ser más eficiente
         * que ambos compartieran un arbol, así no repetían acciones.
         */
        Tree<Posicion> tree = new Tree<>();
        // Se crean los jugadores
        JugadorAbstracto j1 = new JugadorHumano("Un simple mortal","X");
        JugadorWAI j2 = new JugadorWAI("MEGATRIQUINADOR 3000 :v","O", tree);
        JugadorWAI j3 = new JugadorWAI("MEGATRIQUINADOR 3000-2 :v","X", tree);
        // Se crea el árbitro. 
        // El arbitro esta encargado de verificar que los jugadores usen marcas diferentes.
        // Crea el tablero sin marcas.
        Arbitro arbitro = new Arbitro(j2, j3);
        
        //esto se hace solo cuando se quiere jugar máquina vs máquina --
        j2.setValor(arbitro.asignarValorAdecuado(j2.getMarca()));
        j3.setValor(arbitro.asignarValorAdecuado(j3.getMarca()));

        
        // la máquina debe conocer como va el tablero -- 
        j2.setT(arbitro.getT());
        j3.setT(arbitro.getT());
        
        // Aqui el arbitro asigna el primer turno al jugador que tenga el "O" asignado.
        try
        {
            arbitro.iniciarJuego();
        }
        catch (ExceptionBadLabelAsign | ExceptionEqualLabelAsign ex)
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // En este metodo se hace la logica del juego
        arbitro.jugar();

        // Aqui el juego ha finalizado y el arbitro designa si hay un ganador o la partida quedo empatada.
        JugadorAbstracto ganador = arbitro.obtenerGanador();
        if ( ganador == null)
        {
            System.out.println("Esta partida quedo empatada");
        }
        else
        {
            System.out.println("El ganador de la partida es " + ganador.getNombre());
        }
    }
    
}
