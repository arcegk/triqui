package com.eda2.triqui.tree;

import com.eda2.triqui.game.Posicion;
import com.eda2.triqui.game.Tablero;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author oarcila
 * @param <T>
 */
public class Tree<T> {

    private Node<T> root;
    private LinkedList<Node<T>> nodosList;
    private HashMap<T, Void> marked;

    public Tree() {
        root = new Node<>(null, null, 100);
        nodosList = new LinkedList<>();
        marked = new HashMap<>();
        nodosList.add(root);
        iniciarArbol();
    }

    public Node<T> getRoot() {
        return root;
    }

    public T mejorMovimiento() {
        ListIterator<Node<T>> iterador = nodosList.listIterator();
        Node<T> toReturn = nodosList.getFirst();
        LinkedList<Node<T>> listaAuxiliar = new LinkedList<>();

        //recorre la lista de nodos, buscando los nodos con menor peso
        while (iterador.hasNext()) {
            Node<T> aux = iterador.next();
            // si encuentra un nodo con menor peso y no está marcado, 
            //crea una nueva lista agregando dicho nodo
            if (aux.getPeso() < toReturn.getPeso()) {
                if (!isMarked(aux.getPosicion())) {
                    listaAuxiliar = new LinkedList<>();
                    listaAuxiliar.add(aux);
                    toReturn = aux;
                }

                //Mete en la lista a los nodos con el mismo peso
            } else if (aux.getPeso() == toReturn.getPeso()) {
                if (!isMarked(aux.getPosicion())) {
                    listaAuxiliar.add(aux);
                }
            }
        }
        int size = listaAuxiliar.size();
        //Si hay más de un nodo con el mismo peso, escoge uno al azar
        if (size > 1) {
            int x = (int) (Math.random() * size);
            return listaAuxiliar.get(x).getPosicion();
            //sino, retorna la posicion del único nodo
        } else {
            return listaAuxiliar.getFirst().getPosicion();
        }
    }

    //Marca un nodo para que no tenga encuenta dicha jugada (posición)
    public void marcar(T toMark) {
        marked.put(toMark, null);

    }

    //verifica si un nodo ya no puede ser jugado
    public boolean isMarked(T s) {
        return marked.containsKey(s);
    }

    public void insert(T posicion, int peso) {
        Node node = new Node(posicion, root, peso);
        nodosList.add(node);

    }
    
    //Marca un nodo para que no tenga encuenta dicha jugada (posición)
    public void marcar(int i, int j){
        ListIterator<Node<T>> iterador = nodosList.listIterator();
        boolean encontrado = false;
        
        while (!encontrado) {
            Node<T> next = iterador.next();
            if(next!=root){
            Posicion d = (Posicion)next.getPosicion();
            if(i==d.getFila()&&j==d.getColumna()){
                marcar((T)d);
                encontrado = true;
            }
            }
            
        }
    }

    public void iniciarArbol() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int peso = calcularPeso(i, j);
                insert((T) new Posicion(i, j), peso);
            }
        }


    }

    /**
     * reglas para asignar el peso el centro es la posición que menos peso tiene
     * seguido por las esquinas el resto de los movimientos, tienen el mayor
     * peso
     *
     * @param i fila
     * @param j columna
     * @return
     */
    private int calcularPeso(int i, int j) {
        if (i == 1 && j == 1) {
            return 1;
        } else if (i == j) {
            return 2;
        } else if (j - i == 2 || i - j == 2) {
            return 2;
        } else {
            return 5;
        }
    }

}
