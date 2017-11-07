/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rafael
 */
public class Graph {
    private String id;
    private static int digito;
    private String directed;
    private HashMap<String, Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(int directed) {
        this.id = "G" + digito++;
        if (directed == 1) {
            this.directed = "directed";
        } else {
            this.directed = "undirected";
        }

        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public Graph(String id, String directed) {
        this.id = id;
        this.directed = directed;
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getDirected() {
        return directed;
    }

    public void setNodes(Node node) {
        nodes.put(node.getId(), node);
    }

    public void setEdges(Edge edge) {
        edges.add(edge);
    }

    public Node getNode(String idNode) {
        if (nodes.containsKey(idNode)) {
            return nodes.get(idNode);
        } else {
            return null;
        }
    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int ordem() {
        return nodes.size();
    }

    public void grauVertice() {
        int cont;
        for (Node n : nodes.values()) {
            cont = 0;
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).getSource() == n) {
                    cont++;
                }
                if (edges.get(i).getTarget() == n) {
                    cont++;
                }
            }
            System.out.println(n.getId() + " tem grau: " + cont);
        }
    }

    public int grauVertice2(Node node) {
        int cont = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource() == node) {
                cont++;
            }
            if (edges.get(i).getTarget() == node) {
                cont++;
            }
        }
        return cont;
    }
}
