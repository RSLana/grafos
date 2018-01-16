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
            if (grafo.getNodes().get(chave).getRotulo() != null) {
                gravarArquivo.printf("      <node id='" + grafo.getNodes().get(chave).getId() + "'>\n");
                gravarArquivo.printf("          <data key='d0'>" + grafo.getNodes().get(chave).getRotulo() + "</data>\n");
                gravarArquivo.printf("      </node>\n");
            } else {
                gravarArquivo.printf("      <node id='" + grafo.getNodes().get(chave).getId() + "'/>\n");
            }

        }

        ArrayList<Edge> edges = grafo.getEdges();
        for (Edge edge : edges) {
            if (edge.getValor() != Integer.MAX_VALUE) {
                gravarArquivo.printf("      <edge id='" + edge.getId() + "' source='" + edge.getSource().getId() + "' target='" + edge.getTarget().getId() + "'>\n");
                gravarArquivo.printf("          <data key='d1'>" + edge.getValor() + "</data>\n");
                gravarArquivo.printf("      </edge>\n");
            } else {
                gravarArquivo.printf("      <edge id='" + edge.getId() + "' source='" + edge.getSource().getId() + "' target='" + edge.getTarget().getId() + "'/>\n");
            }

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
            NodeList listaData = doc.getElementsByTagName("data");

            int tamanhoListaNode = listaNode.getLength();
            int tamanhoListaEdge = listaEdge.getLength();
            int tamanhoListaData = listaData.getLength();

            org.w3c.dom.Node noGraph = listaGraph.item(0);

            if (noGraph.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element elementoGraph = (Element) noGraph;
                String id = elementoGraph.getAttribute("id");
                String edgeDefault = elementoGraph.getAttribute("edgedefault");

                grafo = new Graph(id, edgeDefault);
            }

            ArrayList<org.w3c.dom.Node> listaDataEdge = new ArrayList<>();
            ArrayList<org.w3c.dom.Node> listaDataNode = new ArrayList<>();
            for (int i = 0; i < tamanhoListaData; i++) {
                org.w3c.dom.Node dataNode = listaData.item(i);
                if (dataNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element elemento = (Element) dataNode;
                    if (elemento.getAttribute("key").equals("d0")) {
                        listaDataNode.add(dataNode);
                    } else if (elemento.getAttribute("key").equals("d1")) {
                        listaDataEdge.add(dataNode);
                    }
                }
            }

            for (int i = 0; i < tamanhoListaNode; i++) {
                org.w3c.dom.Node noNode = listaNode.item(i);
                Node no = null;

                if (noNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element elementoNode = (Element) noNode;
                    String id = elementoNode.getAttribute("id");

                    if (!listaDataNode.isEmpty()) {
                        String rotulo = listaDataNode.get(i).getTextContent();
                        no = new Node(id, rotulo);
                    } else {
                        no = new Node(id);
                    }
                    grafo.setNodes(no);
                    System.out.println("Valor nó: " + no.getRotulo());
                }
            }

            for (int i = 0; i < tamanhoListaEdge; i++) {
                org.w3c.dom.Node noEdge = listaEdge.item(i);
                Edge aresta = null;

                if (noEdge.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element elementoEdge = (Element) noEdge;
                    int id = Integer.parseInt(elementoEdge.getAttribute("id"));
                    String source = elementoEdge.getAttribute("source");
                    String target = elementoEdge.getAttribute("target");

                    if (!listaDataEdge.isEmpty()) {
                        int valor = Integer.parseInt(listaDataEdge.get(i).getTextContent());
                        aresta = new Edge(id, grafo.getNode(source), grafo.getNode(target), valor);
                    } else {
                        aresta = new Edge(id, grafo.getNode(source), grafo.getNode(target));
                    }
                    grafo.setEdges(aresta);
                    System.out.println("Valor aresta: " + aresta.getValor());
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
