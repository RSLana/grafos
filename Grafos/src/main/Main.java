/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import classes.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author rafael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Grafo Direcional [1] / Grafo NÃO Direcional [2]: ");
        int orientacao = teclado.nextInt();

        Graph grafo = new Graph(orientacao);

        System.out.print("Digite a quantidade de nós: ");
        int quantNodes = teclado.nextInt();

        for (int i = 0; i < quantNodes; i++) {
            Node node = new Node();
            grafo.setNodes(node);
        }

        System.out.print("Digite a quantidade de arestas: ");
        int quantEdges = teclado.nextInt();
        System.out.println();

        int nEdge = 1;

        System.out.println("--------------------------------------");
        while (quantEdges >= nEdge) {
            System.out.println(nEdge + "º Aresta: ");

            System.out.print("Origem [0 até " + (quantNodes - 1) + "]: ");
            String source = teclado.next();
            source = "n" + source;

            System.out.print("Destino [0 até " + (quantNodes - 1) + "]: ");
            String target = teclado.next();
            target = "n" + target;

            if (grafo.getNode(source) != null && grafo.getNode(target) != null) {
                Edge edge = new Edge(grafo.getNode(source), grafo.getNode(target));
                grafo.setEdges(edge);
                nEdge++;
            } else {
                System.out.println("Origem ou Destino inválido!");
            }
            System.out.println("--------------------------------------");
        }
        FileWriter arquivo = new FileWriter("grafo.xml");
        PrintWriter gravarArquivo = new PrintWriter(arquivo);

        gravarArquivo.printf("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        gravarArquivo.printf("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n"
                + "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n");

        gravarArquivo.printf("  <graph id='" + grafo.getId() + "' edgedefault='" + grafo.getDirected() + "'>\n");

        Set<String> chaves = grafo.getNodes().keySet();

        for (String chave : chaves) {
            gravarArquivo.printf("      <node id='" + grafo.getNodes().get(chave).getId() + "'/>\n");
        }

        ArrayList<Edge> edges = grafo.getEdges();
        for (Edge edge : edges) {
            gravarArquivo.printf("      <edge source='" + edge.getSource().getId() + "' target='" + edge.getTarget().getId() + "'/>\n");
        }

        gravarArquivo.printf("  </graph>\n");
        gravarArquivo.printf("</graphml>");

        arquivo.close();
    }
}

