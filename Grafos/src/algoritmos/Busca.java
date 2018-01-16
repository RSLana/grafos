/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import classes.Graph;
import classes.Node;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author rafael
 */
public abstract class Busca {

    public static void emProfundidade(Graph grafo, Node origem) {
        Stack<Node> pilha = new Stack<>();
        grafo.getNodes().values();
        
        ArrayList<Node> nosVisitados = new ArrayList<>();
        pilha.push(origem);
        nosVisitados.add(origem);
        while (!pilha.isEmpty()) {
            Node v = pilha.pop();
            for (int i = 0; i < grafo.getNodeAdjacentes(v).size(); i++) {
                Node adj = grafo.getNodeAdjacentes(v).get(i);
                if (!nosVisitados.contains(adj)) {
                    System.out.println(v.getId() + " >> " + adj.getId());
                    pilha.push(adj);
                    nosVisitados.add(adj);
                }
            }
        }
    }

    static void emLargura() {

    }
}
