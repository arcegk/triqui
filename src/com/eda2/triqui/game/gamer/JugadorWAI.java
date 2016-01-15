/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eda2.triqui.game.gamer;

import com.eda2.triqui.game.Posicion;
import com.eda2.triqui.game.Tablero;
import com.eda2.triqui.tree.Tree;

/**
 *
 * @author koombea
 */
public class JugadorWAI extends JugadorAbstracto {

    private Tablero t;
    private Tree<Posicion> tree;
    private int valorAsignado;

    public JugadorWAI(String name, String marca , Tree<Posicion> tree) {
        super(name, marca);
        this.tree = tree;
        valorAsignado = 1;
    }

    public void setT(Tablero t) {
        this.t = t;
    }

    public void setValor(int value){
        this.valorAsignado = value;
    }
            
    @Override
    public Posicion seleccionarCasilla() {
        //revisa si en la próxima jugada puede ganar, 
        //retorna coordenadas de la posicion para ganar
        int[][] canWin = canIWin();
        //revisa si en la próxima jugada puede bloquear un win, 
        //retorna coordenadas de la posicion para bloquear
        int[][] canBlock = canIBlock();
        //si puede ganar, gana
        if(canWin!=null){
            tree.marcar(canWin[0][0],canWin[1][0]);
            Posicion aux = new Posicion(canWin[0][0],canWin[1][0]);
            return aux;
        //si no puede ganar, pero puede bloquear, bloquea
        }else if(canBlock!=null){
            tree.marcar(canBlock[0][0],canBlock[1][0]);
            Posicion aux = new Posicion(canBlock[0][0],canBlock[1][0]);
            return aux;
        // si no puede ganar, ni bloquear, escoge uno de sus mejores movimientos    
        }else {
            Posicion move = tree.mejorMovimiento();
            tree.marcar(move);
            return move;
            
         }
 
    }
   
    //metodo que revisa si puede ganar
    private int[][] canIWin() {

        int fila = 0;
        int diag = 0;
        int contra = 0;
        int column = 0;
        int[][] coorFila = new int[2][2];
        int[][] coorDiagonal = new int[2][2] ;
        int[][] coorContradiagonal = new int[2][2] ;
        int[][] coorColumn = new int[2][2];    

        for (int i = 0; i < 3; i++) {
            fila = 0;
            column = 0;
            for (int j = 0; j < 3; j++) {
                int d = t.getValue(i, j);
                int g = t.getValue(j, i);

                if (j == i) {
                    if (d == valorAsignado) {
                        diag++;
                    } else if (d == 0) {
                        coorDiagonal[0][0] = i;
                        coorDiagonal[1][0] = j;
                    }else {
                        diag--;
                    }
                }

                if (d == valorAsignado) {
                    fila++;
                }else if (d==0){
                    coorFila[0][0] = i;
                    coorFila[1][0] = j;
                }else {
                    fila--;
                    tree.marcar(new Posicion(i, j));
                }

                if (i - j == 2 || j - i == 2 || (j == 1 && i == 1)) {
                    if (d == valorAsignado) {
                        contra++;
                    }else if(d==0){
                        coorContradiagonal[0][0] = i;
                        coorContradiagonal[1][0] = j;
                    }else{
                        contra--;
                    }
                }
                
                if(g==valorAsignado){
                    column++;
                }else if(g==0){
                    coorColumn[0][0] = j;
                    coorColumn[1][0] = i;
                }else{
                    column--;
                }

                if (j == 2) {
                    if (fila == 2) {
                        return coorFila;
                    }else if(diag==2 && i==2){
                        return coorDiagonal;
                    }else if(contra==2 && i ==2){
                        return coorContradiagonal;
                    }else if(column==2){
                        return coorColumn;
                    }
                }
            }
    }
    return null;
}
    //metodo que revisa si puede bloquear
    private int[][] canIBlock() {

        int fila = 0;
        int diagonal = 0;
        int contraDiagonal = 0;
        int column = 0;
        int[][] coorFila = new int[2][2];
        int[][] coorDiagonal = new int[2][2] ;
        int[][] coorContraDiago = new int[2][2] ;
        int[][] coorColumn = new int[2][2];
             

        for (int i = 0; i < 3; i++) {
            fila = 0;
            column = 0;
            for (int j = 0; j < 3; j++) {
                int d = t.getValue(i, j);
                int g = t.getValue(j, i);
                
                if (j == i) {
                    if (d == valorAsignado) {
                        diagonal--;
                    } else if(d==0){
                        coorDiagonal[0][0] = i;
                        coorDiagonal[1][0] = j;
                    } else {
                        diagonal++;
                }
                }

                if (d == valorAsignado) {
                    fila--;
                }else if (d==0){
                    coorFila[0][0] = i;
                    coorFila[1][0] = j;
                }else {
                    fila++;
                }

                if (i - j == 2 || j - i == 2 || (j == 1 && i == 1)) {
                    if(d==valorAsignado){
                        contraDiagonal--;
                    }else if (d==0){
                        coorContraDiago[0][0] = i;
                        coorContraDiago[1][0] = j;
                    }else {
                        contraDiagonal++;
                    }
                }
                if(g==valorAsignado){
                    column--;
                }else if(g==0){
                    coorColumn[0][0] = j;
                    coorColumn[1][0] = i;
                }else{
                    column++;
                }
                if (j == 2) {
                    if (fila == 2) {
                        return coorFila;
                    }else if(diagonal==2 && i==2){
                        return coorDiagonal;
                    }else if(contraDiagonal==2 && i==2){
                        return coorContraDiago;
                    }else if(column==2){
                        return coorColumn;
                    }
                }
            }
        }
    return null;
}
    
    
}
