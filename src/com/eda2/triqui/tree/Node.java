package com.eda2.triqui.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author oarcila
 * @param <T>
 */
public class Node<T>
{
   
    
 
    private Node padre;
    private T posicion;
    private int peso;
    
    
    public Node (T posicion, Node<T> padre, int peso){
        this.posicion = posicion;
        this.peso = peso;
        this.padre = padre;
    }

    public int getPeso() {
        return peso;
    }

    public T getPosicion() {
        return posicion;
    }
    
    
}
