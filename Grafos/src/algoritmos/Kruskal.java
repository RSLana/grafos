/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import classes.Graph;
import classes.Edge;
import classes.Node;
import adicionais.EdgeComparator;
import java.util.HashMap;

/**
 *
 * @author rafael
 */
public class Kruskal {

    private ArrayList<ArrayList<String>> listaPais = new ArrayList<>();
    private ArrayList<Edge> edges;
    private HashMap<String,Node> nodes;
    private ArrayList<String> nodesId = new ArrayList<>();

    private Graph grafo;

    public Kruskal(ArrayList<Edge> edges, HashMap<String,Node> nodes, Graph grafo) {
        this.edges = edges;
        this.nodes = nodes;
        this.grafo = grafo;
    }

    public Kruskal() {
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> arestas) {
        this.edges = arestas;
    }

    public HashMap<String,Node> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String,Node> nodes) {
        this.nodes = nodes;
    }

    public Graph getKruskal() {
        for (int i = 0; i < this.nodes.size(); i++) {
            String e = this.grafo.getNodes().get("n"+i).getId();
            this.nodesId.add(e);
        }

        ArrayList<Edge> arvoreAresta = new ArrayList<>();

        for (int i = 0; i < this.nodes.size(); i++) {
            ArrayList<String> listaAux = new ArrayList<>();
            listaAux.add(grafo.getNodes().get("n"+i).getId());
            this.listaPais.add(listaAux);
        }

        EdgeComparator comparador = new EdgeComparator();

        this.edges = (ArrayList<Edge>) this.grafo.getEdges().clone();
        Collections.sort(this.edges, comparador);

        for (int i = 0; i < this.edges.size(); i++) {

            if (comparaPais(this.edges.get(i).getSource().getId(), this.edges.get(i).getTarget().getId())) {
                arvoreAresta.add(this.edges.get(i));
                unirNos(this.edges.get(i).getSource(), this.edges.get(i).getTarget());
                unirNos(this.edges.get(i).getTarget(), this.edges.get(i).getSource());
            }
        }

        return arestasParaArvore(arvoreAresta);
    }

    private boolean comparaPais(String origem, String destino) {
        return Collections.disjoint(noPai(origem), noPai(destino));
    }

    private ArrayList<String> noPai(String idNo) {
        return this.listaPais.get(this.nodesId.indexOf(idNo));
    }

    private void unirNos(Node origem, Node destino) {
        int tamanhoLista = this.listaPais.get(this.nodesId.indexOf(destino.getId())).size();

        for (int i = 0; i < tamanhoLista; i++) {
            String x = this.listaPais.get(this.nodesId.indexOf(destino.getId())).get(i);
            if (!(this.listaPais.get(this.nodesId.indexOf(origem.getId())).contains(x))) {
                this.listaPais.get(this.nodesId.indexOf(origem.getId())).add(x);
            }
        }
    }

    private Graph arestasParaArvore(ArrayList<Edge> arvore) {
        Graph arvoreMinima = new Graph(this.grafo.getId(), this.grafo.getDirected(), this.nodes, arvore);
        return arvoreMinima;
    }
}
