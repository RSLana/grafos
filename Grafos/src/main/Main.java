/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algoritmos.Busca;
import algoritmos.Dijkstra;
import algoritmos.Kruskal;
import algoritmos.Prim;
import classes.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        while (true) {
            int resp = menu();
            if (resp == 1) {
                System.out.print("Grafo Direcional [1] / Grafo não Direcional [0]: ");
                int orientacao = teclado.nextInt();

                Graph grafo = new Graph(orientacao);

                System.out.print("Nó valorado [1] / Nó não valorado [0]: ");
                int nodeValorado = teclado.nextInt();

                System.out.print("Digite a quantidade de nós: ");
                int quantNodes = teclado.nextInt();

                for (int i = 0; i < quantNodes; i++) {
                    Node node = new Node();
                    if (nodeValorado == 1) {
                        System.out.print("Valor do nó n" + i + ": ");
                        String rotulo = teclado.next();
                        node.setRotulo(rotulo);
                    }
                    grafo.setNodes(node);
                }

                System.out.print("Aresta valorada [1] / Aresta não valorada [0]: ");
                int arestaValorada = teclado.nextInt();

                System.out.print("Digite a quantidade de arestas: ");
                int quantEdges = teclado.nextInt();
                System.out.println();

                inclusaoVertices(grafo, quantEdges, arestaValorada);

                gerarXML(grafo);

                informacoesGrafo(grafo);
                String opcao = "";
                menu3(opcao, grafo, arestaValorada, nodeValorado);

            } else if (resp == 2) {
                lerXML();
            } else {
                break;
            }
        }
        System.out.println("\n# Para o caso de não nos vermos mais, Bom Dia, Boa Tarde e Boa Noite.\n");
        System.out.println("-------------------------------------------------------------------------");
    }

    private static int menu() {
        System.out.println("-------------------------------------------------");
        System.out.println("# 1) Criar Grafo");
        System.out.println("# 2) Ler Grafo");
        System.out.println("# 3) Sair");
        System.out.print("> ");
        return teclado.nextInt();
    }

    private static String menu2() {
        System.out.println("# 1) Caminho de dois vértices");
        System.out.println("# 2) Cadeia de dois vértices");
        System.out.println("# 3) Vértices adjacentes");
        System.out.println("# 4) Vértices fonte");
        System.out.println("# 5) Vértices sumidouro");
        System.out.println("# 6) Inserir Vértice");
        System.out.println("# 7) Remover Vértice");
        System.out.println("# 8) Inserir Aresta");
        System.out.println("# 9) Remover Aresta");
        System.out.println("# 10) Exibir imagem do grafo");
        System.out.println("# 11) Prim");
        System.out.println("# 12) Kruskal");
        System.out.println("# 13) Dijkstra");
        System.out.println("# 14) Busca por profundidade");
        System.out.println("# i) Informações sobre o grafo");
        System.out.println("# 0) Voltar ao menu principal");
        System.out.print("> ");
        return teclado.next();
    }

    private static void menu3(String opcao, Graph grafo, int arestaValorada, int nodeValorado) {
        while (!opcao.equals("0")) {
            System.out.println("-------------------------------------------------");
            opcao = menu2();
            switch (opcao) {
                case "1": {
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String source = teclado.next();
                    source = "n" + source;
                    System.out.print("Destino [0 até " + (grafo.ordem() - 1) + "]: ");
                    String target = teclado.next();
                    target = "n" + target;
                    System.out.println();
                    if (grafo.caminho(source, target)) {
                        System.out.println("# Tem caminho");
                    } else {
                        System.out.println("# Não tem caminho");
                    }
                    break;
                }
                case "2": {
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String source = teclado.next();
                    source = "n" + source;
                    System.out.print("Destino [0 até " + (grafo.ordem() - 1) + "]: ");
                    String target = teclado.next();
                    target = "n" + target;
                    System.out.println();
                    if (grafo.cadeia(source, target)) {
                        System.out.println("# Tem cadeia");
                    } else {
                        System.out.println("# Não tem cadeia");
                    }
                    break;
                }
                case "3": {
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String source = teclado.next();
                    source = "n" + source;
                    System.out.print("Destino [0 até " + (grafo.ordem() - 1) + "]: ");
                    String target = teclado.next();
                    target = "n" + target;
                    System.out.println();
                    if (grafo.verticeAdjacente(source, target) > 0) {
                        System.out.println("Adjacente");
                    } else {
                        System.out.println("Não é adjacente");
                    }
                    break;
                }
                case "4": {
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String node = teclado.next();
                    node = "n" + node;
                    if (grafo.verificaVerticeFonte(grafo.getNode(node))) {
                        System.out.println("O vértice é fonte");
                    } else {
                        System.out.println("O vértice não é fonte");
                    }
                    break;
                }
                case "5": {
                    System.out.print("\nVértice [0 até " + (grafo.ordem() - 1) + "]: ");
                    String node = teclado.next();
                    node = "n" + node;
                    if (grafo.verificaVerticeSumidouro(grafo.getNode(node))) {
                        System.out.println("O vértice é sumidouro");
                    } else {
                        System.out.println("O vértice é sumidouro");
                    }
                    break;
                }
                case "6": {
                    Node node = new Node();
                    if (nodeValorado == 1) {
                        System.out.print("Valor do nó: ");
                        String rotulo = teclado.next();
                        node.setRotulo(rotulo);
                    }
                    grafo.adicionaNode(node);
                    break;
                }
                case "7": {
                    for (Node node : grafo.getNodes().values()) {
                        System.out.println("[" + node.getId() + "]");
                    }
                    grafo.excluirNode(teclado.next());

                    break;
                }
                case "8": {
                    inclusaoVertices(grafo, 1, arestaValorada);
                    //grafo.adicionaEdge(edge);

                    break;
                }
                case "9": {
                    for (Edge edge : grafo.getEdges()) {
                        System.out.println("[" + edge.getId() + "][" + edge.getSource().getId() + " - " + edge.getTarget().getId() + "]");
                    }
                    System.out.print("> ");
                    grafo.excluirEdge(teclado.nextInt());

                    break;
                }
                case "10": {
                    grafo.exibirImagemGrafo();
                    break;
                }
                case "11": {
                    Prim grafoPrim = new Prim();
                    Graph grafoOtimizado = grafoPrim.getPrim(grafo);
                    String nome = grafo.getNome() + "_Prim";
                    grafoOtimizado.setNome(nome);
                    try {
                        Xml.gerarXmlGrafo(nome, grafoOtimizado);
                        System.out.println("-------------------------------------------------");
                        System.out.println("XML GERADO COM SUCESSO!!!");
                        grafoOtimizado.exibirImagemGrafo();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "12": {
                    Kruskal kruskal = new Kruskal(grafo.getEdges(), grafo.getNodes(), grafo);
                    Graph grafoKruskal = kruskal.getKruskal();
                    String nome = grafo.getNome() + "_Kruskal";
                    grafoKruskal.setNome(nome);
                    try {
                        Xml.gerarXmlGrafo(nome, grafoKruskal);
                        System.out.println("-------------------------------------------------");
                        System.out.println("XML GERADO COM SUCESSO!!!");
                        grafoKruskal.exibirImagemGrafo();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "13": {
                    Dijkstra dijkstra = new Dijkstra(grafo);
                    System.out.println("# Dijkstra. Busca de menor caminho #");
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String noRaiz = teclado.next();

                    int i = dijkstra.getGraph().getNodeIndice("n" + noRaiz);
                    dijkstra.buscarCaminhos(dijkstra.getGraph().getNode("n" + i));

                    System.out.print("\nDestino [0 até " + (grafo.ordem() - 1) + "]: ");
                    String noMenor = teclado.next();
                    int j = dijkstra.getGraph().getNodeIndice("n" + noMenor);

                    float menor = dijkstra.menorDistancia(dijkstra.getGraph().getNodes().get("n" + j));
                    if (menor == Float.MAX_VALUE) {
                        System.out.println("infinito");
                    } else {
                        System.out.println("O menor caminho é: " + Float.toString(menor));
                    }
                    break;
                }
                case "14": {
                    System.out.println("# Busca por profundidade #");
                    System.out.print("\nOrigem [0 até " + (grafo.ordem() - 1) + "]: ");
                    String origem = teclado.next();
                    Busca.emProfundidade(grafo, grafo.getNode("n"+origem));
                    break;
                }
                
                case "i":
                    informacoesGrafo(grafo);
                default:
                    break;
            }
        }
    }

    private static void gerarXML(Graph grafo) throws IOException {
        System.out.print("Deseja gerar o XML do Grafo? SIM->[1] / NÃO->[0]: ");
        int resposta = teclado.nextInt();
        if (resposta == 1) {
            System.out.print("Digite o nome do XML que será gerado: ");
            String nome = teclado.next();
            grafo.setNome(nome);
            Xml.gerarXmlGrafo(nome, grafo);
            System.out.println("-------------------------------------------------");
            System.out.println("XML GERADO COM SUCESSO!!!");
        } else {
            System.out.println("-------------------------------------------------");
            System.out.println("XML NÃO GERADO.");
        }
    }

    private static void inclusaoVertices(Graph grafo, int quantEdges, int arestaValorada) {
        int nEdge = 1;

        System.out.println("-------------------------------------------------");
        while (quantEdges >= nEdge) {
            System.out.println(nEdge + "º Aresta: ");

            System.out.print("Origem [0 até " + (grafo.ordem() - 1) + "]: ");
            String source = teclado.next();
            source = "n" + source;

            System.out.print("Destino [0 até " + (grafo.ordem() - 1) + "]: ");
            String target = teclado.next();
            target = "n" + target;

            if (grafo.getNode(source) != null && grafo.getNode(target) != null) {
                Edge edge = null;
                if (arestaValorada == 1) {
                    System.out.print("\nValor: ");
                    int valor = teclado.nextInt();
                    edge = new Edge(grafo.getNode(source), grafo.getNode(target), valor);
                } else {
                    edge = new Edge(grafo.getNode(source), grafo.getNode(target));
                }

                grafo.setEdges(edge);
                nEdge++;
            } else {
                System.out.println("Origem ou Destino inválido!");
            }
            System.out.println("-------------------------------------------------");
        }
        //Node.setDigito(0);
    }

    private static void lerXML() {
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o nome do XML que será lido: ");
        String nomeGrafoXml = teclado.next();

        System.out.println("-------------------------------------------------");
        Graph grafo = Xml.lerXmlGrafo(nomeGrafoXml);

        if (grafo == null) {
            System.out.println("Arquivo não encontrado!");
        } else {
            grafo.setNome(nomeGrafoXml);
            Set<String> chaves = grafo.getNodes().keySet();

            System.out.println("# ID do Grafo: " + grafo.getId());
            System.out.println("# Tipo de Grafo: " + grafo.getDirected() + "\n");
            System.out.println("# Nós: ");

            int nodeValorado = 0;
            for (String chave : chaves) {
                if (grafo.getNodes().get(chave).getRotulo() != null) {
                    nodeValorado = 1;
                }
            }

            int arestaValorada = 0;
            ArrayList<Edge> edges = grafo.getEdges();
            for (Edge edge : edges) {
                if (edge.getValor() != Integer.MAX_VALUE) {
                    arestaValorada = 1;
                }
            }
            System.out.println("-------------------------------------------------");
            System.out.println("XML LIDO COM SUCESSO!!!");

            menu3("1", grafo, arestaValorada, nodeValorado);

        }
//        System.out.println("-------------------------------------------------");
    }

    private static void informacoesGrafo(Graph grafo) {
        System.out.println("-------------------------------------------------");
        System.out.println("# INFORMAÇÕES SOBRE O GRAFO");
        System.out.println("-------------------------------------------------");
        System.out.println("A ordem do grafo é: " + grafo.ordem());

        grafo.grauVertice();

        System.out.println("\n# Matriz de Adjacências: ");
        grafo.matrizAdjacencias();

        System.out.println("\n# Lista de Adjacências: ");
        grafo.listaAdjacencias();

        System.out.println("\n# Matriz de Incidência: ");
        grafo.matrizIncidencia();

        System.out.println();
        if (grafo.isRegular() > 0) {
            System.out.println("Grafo Regular");
        } else {
            System.out.println("Grafo não é regular");
        }

        int ordemGrafo = grafo.isCompleto();
        if (ordemGrafo > 0) {
            System.out.println("Grafo Completo K" + ordemGrafo);
        } else {
            System.out.println("Grafo não é completo");
        }

        if (grafo.isMultigrafo()) {
            System.out.println("Grafo é multigrafo");
        } else {
            System.out.println("Grafo não é multigrafo");
        }

    }
}
