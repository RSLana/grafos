/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import main.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ariel
 */
public abstract class Xml {

    public static void gerarXmlGrafo(String nomeGrafo, Graph grafo) throws IOException {
        FileWriter arquivo = new FileWriter(nomeGrafo + ".xml");
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

    public static Graph lerXmlGrafo(String nomeArquivo) {
        Graph grafo = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File arquivo = new File(nomeArquivo + ".xml");

            if (!arquivo.exists()) {
                return null;
            }

            Document doc = builder.parse(nomeArquivo + ".xml");

            NodeList listaGraph = doc.getElementsByTagName("graph");
            NodeList listaNode = doc.getElementsByTagName("node");
            NodeList listaEdge = doc.getElementsByTagName("edge");

            int tamanhoListaNode = listaNode.getLength();
            int tamanhoListaEdge = listaEdge.getLength();

            org.w3c.dom.Node noGraph = listaGraph.item(0);
            
            if (noGraph.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element elementoGraph = (Element) noGraph;
                String id = elementoGraph.getAttribute("id");
                String edgeDefault = elementoGraph.getAttribute("edgedefault");
                
                grafo = new Graph(id,edgeDefault);
            }

            for (int i = 0; i < tamanhoListaNode; i++) {
                org.w3c.dom.Node noNode = listaNode.item(i);

                if (noNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element elementoNode = (Element) noNode;
                    String id = elementoNode.getAttribute("id");

                    Node no = new Node(id);
                    grafo.setNodes(no);
                }
            }
            
            for (int i = 0; i < tamanhoListaEdge; i++) {
                org.w3c.dom.Node noEdge = listaEdge.item(i);

                if (noEdge.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element elementoEdge = (Element) noEdge;
                    String source = elementoEdge.getAttribute("source");
                    String target = elementoEdge.getAttribute("target");
                    
                    Edge aresta = new Edge(grafo.getNode(source), grafo.getNode(target));
                    grafo.setEdges(aresta);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grafo;
    }
}
