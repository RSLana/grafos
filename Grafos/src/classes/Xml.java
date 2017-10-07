/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author ariel
 */
public abstract class Xml {
    public static void gerarXmlGrafo(Graph grafo) throws IOException{
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
