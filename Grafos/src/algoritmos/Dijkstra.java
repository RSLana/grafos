/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import classes.Graph;
import classes.Node;

/**
 *
 * @author rafael
 */
public class Dijkstra {

    private Graph graph;
    private HashMap<String, Float> distancias;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.distancias = new HashMap();
    }

    public Dijkstra() {

    }

    private float calcularDistancia(String inicioId, String alvoId) {
        int i;
        for (i = 0; i < this.graph.getEdges().size(); i++) {
            if (this.graph.getDirected().equals("directed")) {
                if (this.graph.getEdges().get(i).getSource().getId().equals(inicioId) && this.graph.getEdges().get(i).getTarget().getId().equals(alvoId)) {
                    return this.graph.getEdges().get(i).getValor();
                }
            } else if ((this.graph.getEdges().get(i).getSource().getId().equals(inicioId) && this.graph.getEdges().get(i).getTarget().getId().equals(alvoId))
                    || (this.graph.getEdges().get(i).getSource().getId().equals(alvoId) && this.graph.getEdges().get(i).getTarget().getId().equals(inicioId))) {
                return this.graph.getEdges().get(i).getValor();
            }
        }
        return Float.MAX_VALUE;
    }

    public void buscarCaminhos(Node source) {
        ArrayList<Node> nosJaVisitados = new ArrayList();
        int index = this.graph.getNodeIndice(source.getId());

        for (int j = 0; j < this.graph.getNodes().size(); j++) {
            this.distancias.put(graph.getNodes().get("n" + j).getId(), Float.MAX_VALUE);
        }
        System.out.println(index);
        if (index != -1) {
            this.distancias.put(this.graph.getNodes().get("n" + index).getId(), 0F);
            nosJaVisitados.add(this.graph.getNodes().get("n" + index));
            visitarTodosNos(this.graph.getNodes().get("n" + index).getId(), 0, nosJaVisitados);
        }
    }

    private void visitarTodosNos(String noId, float distanciaAnterior, ArrayList<Node> nosJaVisitados) {

        ArrayList<Node> alcancaveis;
        int indice, i;
        indice = this.graph.getNodeIndice(noId);
        alcancaveis = this.graph.getNodeAdjacentes(this.graph.getNodes().get("n"+indice));
        alcancaveis.removeAll(nosJaVisitados);

        for (i = 0; i < alcancaveis.size(); i++) {
            float distancia = calcularDistancia(noId, alcancaveis.get(i).getId());

            if (distancia + distanciaAnterior < this.distancias.get(alcancaveis.get(i).getId())) {
                this.distancias.put(alcancaveis.get(i).getId(), distancia + distanciaAnterior);
            }
        }

        for (i = 0; i < alcancaveis.size(); i++) {
            nosJaVisitados.add(alcancaveis.get(i));
            visitarTodosNos(alcancaveis.get(i).getId(), this.distancias.get(alcancaveis.get(i).getId()), nosJaVisitados);
            nosJaVisitados.remove(alcancaveis.get(i));
        }
    }

    public float menorDistancia(Node destino) {
        if (this.distancias.get(destino.getId()) != null) {
            return this.distancias.get(destino.getId());
        } else {
            return -1;
        }
    }

    public Graph getGraph() {
        return this.graph;
    }
}
