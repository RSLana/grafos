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
    
    /**
     *
     * @return grau da regularidade
     */
    public int isRegular() {
        int grau = -1;
        for (Node n : nodes.values()) {
            if (grau == -1) {
                grau = grauVertice2(n);
            } else if (grau != grauVertice2(n)) {
                return 0;
            }
        }
        return grau;
    }

    /**
     *
     * @return ordem do grafo
     */
    public int isCompleto() {
        if (isRegular() == ordem() - 1) {
            return ordem();
        }
        return 0;
    }

    /**
     *
     * @param source
     * @param target
     * @return quantidade de ajacências
     */
    public int verticeAdjacente(String source, String target) {
        int cont = 0;
        for (Edge edge : edges) {
            if ((edge.getSource() == getNode(source) && edge.getTarget() == getNode(target))
                    || (edge.getSource() == getNode(target) && edge.getTarget() == getNode(source))) {
                cont++;
            }
        }
        return cont;
    }
    
    public boolean isMultigrafo() {
        for (Node n1 : nodes.values()) {
            for (Node n2 : nodes.values()) {
                if (verticeAdjacente(n1.getId(), n2.getId()) > 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean existeTarget(String destino) {
        for (Edge edge : edges) {
            if (edge.getTarget() == getNode(destino)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeSource(Node inicio) {
        for (int k = 0; k < edges.size(); k++) {
            if (inicio == edges.get(k).getSource()) {
                return true;
            }
        }
        return false;
    }

    public boolean existeArestaDireta(Node inicio, Node destino) {
        for (int k = 0; k < edges.size(); k++) {
            if (destino == edges.get(k).getSource() && inicio == edges.get(k).getTarget()) {
                return true;
            }
        }
        return false;
    }

    public boolean caminho(String inicio, String destino) {
        if (existeTarget(destino)) {
            ArrayList<Edge> edgesSource = new ArrayList<>();

            for (Edge edge : edges) {
                if (edge.getSource() == getNode(inicio) && getNode(inicio) != edge.getTarget()) {
                    edgesSource.add(edge);
                }
            }

            while (edgesSource.size() >= 1) {
                ArrayList<Edge> newEdgesSource = new ArrayList<>();
                for (Edge edgesSource1 : edgesSource) {
                    for (Edge edge : edges) {
                        if (edgesSource1.getTarget() == edge.getSource() && edgesSource1.getTarget() != getNode(inicio)) {
                            newEdgesSource.add(edge);
                        } else if (edgesSource1.getTarget() == getNode(destino)) {
                            return true;
                        }
                    }
                }
                edgesSource = newEdgesSource;
            }
        }
        return false;
    }

    public boolean cadeia(String inicio, String destino) {
        ArrayList<Edge> edgesFound = new ArrayList<>();
        ArrayList<Node> nodesFound = new ArrayList<>();
        ArrayList<Node> newNodesFound = new ArrayList<>();

        for (Edge edge : edges) {
            if (!nodesFound.contains(edge.getSource()) && !nodesFound.contains(edge.getTarget())) {
                if (edge.getSource() == getNode(inicio)) {
                    edgesFound.add(edge);
                    newNodesFound.add(edge.getTarget());
                }
                if (edge.getTarget() == getNode(inicio)) {
                    edgesFound.add(edge);
                    newNodesFound.add(edge.getSource());
                }
            }
        }

        nodesFound.add(getNode(inicio));
        for (Node n : newNodesFound) {
            if (!nodesFound.contains(n)) {
                nodesFound.add(n);
            }
        }

        while (edgesFound.size() >= 1) {
            ArrayList<Edge> newEdgesFound = new ArrayList<>();
            newNodesFound = new ArrayList<>();
            int nodesFoundSize = nodesFound.size();
            for (Edge edgeFound : edgesFound) {
                for (Edge edge : edges) {
                    if (edgeFound.getTarget() == getNode(destino) || edgeFound.getSource() == getNode(destino)) {
                        return true;
                    }
                    if (edgeFound.getTarget() == edge.getSource()) {
                        newEdgesFound.add(edge);
                        newNodesFound.add(edge.getTarget());
                    }
                    if (edgeFound.getTarget() == edge.getTarget()) {
                        newEdgesFound.add(edge);
                        newNodesFound.add(edge.getSource());
                    }
                    if (edgeFound.getSource() == edge.getSource()) {
                        newEdgesFound.add(edge);
                        newNodesFound.add(edge.getTarget());
                    }
                    if (edgeFound.getSource() == edge.getTarget()) {
                        newEdgesFound.add(edge);
                        newNodesFound.add(edge.getSource());
                    }
                }
            }
            for (Node n : newNodesFound) {
                if (!nodesFound.contains(n)) {
                    nodesFound.add(n);
                }
            }
            edgesFound = newEdgesFound;
            if (nodesFoundSize == nodesFound.size()) {
                return false;
            }
        }
        return false;
    }

    public boolean verificaVerticeFonte(Node node) {
        for (Edge edge : edges) {
            if (edge.getTarget() == node) {
                return false;
            }
        }
        return true;
    }

    public boolean verificaVerticeSumidouro(Node node) {
        for (Edge edge : edges) {
            if (edge.getSource() == node) {
                return false;
            }
        }
        return true;
    }

}
