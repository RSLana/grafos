/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import classes.Edge;
import classes.Graph;
import classes.Node;
import adicionais.EdgeComparator;
import java.util.HashMap;

/**
 *
 * @author rafael
 */
public class Prim {
    private ArrayList<Edge> edges;
    private ArrayList<Edge> arestasAdicionadas;
    private HashMap<String,Node> nodes;
    private ArrayList<Node> nodesAdicionados;
    private ArrayList<String> nodesIdAdicionados;
    private ArrayList<String> nodesId;

    private Graph graphml;

    public Prim() {
        this.edges = new ArrayList();
        this.arestasAdicionadas = new ArrayList();
        this.nodes = new HashMap();
        this.nodesAdicionados = new ArrayList();
        this.nodesIdAdicionados = new ArrayList();
        this.nodesId = new ArrayList();
    }

    public Graph getPrim(Graph graph) {
        graphml = graph;
        nodes = graphml.getNodes();
        String index = "n"+0;
        for (int i = 0; i < nodes.size(); i++) {
            nodesId.add(graphml.getNodes().get(index).getId());
        }

        EdgeComparator c = new EdgeComparator();
        edges = (ArrayList<Edge>) graphml.getEdges().clone();
        Collections.sort(edges, c);

        nodesAdicionados.add(nodes.get(index));
        nodesIdAdicionados.add(nodes.get(index).getId());

        while (nodesAdicionados.size() != nodes.size()) {
            for (int j = 0; j < edges.size(); j++) {
                // nosAdicionados.add(nos.get(i));
                if (nodesIdAdicionados.contains(edges.get(j).getSource().getId())
                        && !nodesIdAdicionados.contains(edges.get(j).getTarget().getId())) {
                    arestasAdicionadas.add(edges.get(j));
                    nodesAdicionados.add(edges.get(j).getTarget());
                    nodesIdAdicionados.add(edges.get(j).getTarget().getId());
                    edges.remove(j);
                    break;
                }
                if (nodesIdAdicionados.contains(edges.get(j).getTarget().getId())
                        && !nodesIdAdicionados.contains(edges.get(j).getSource().getId())) {
                    arestasAdicionadas.add(edges.get(j));
                    nodesAdicionados.add(edges.get(j).getSource());
                    nodesIdAdicionados.add(edges.get(j).getSource().getId());
                    edges.remove(j);
                    break;
                }
            }
        }
        Graph grafoPrim = new Graph(graphml.getId(), graphml.getDirected(), nodes, arestasAdicionadas);
        return grafoPrim;
    }
}
