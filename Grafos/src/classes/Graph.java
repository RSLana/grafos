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
    private HashMap<String,Node> nodes;
    private ArrayList<Edge> edges;
    
    public Graph(int directed){
        this.id = "G"+digito++;
        if(directed == 1){
            this.directed = "directed";
        }else{
            this.directed = "undirected";
        }
        
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }
    public String getId(){
        return id;
    }
    public String getDirected(){
        return directed;
    }
    public void setNodes (Node node){
        nodes.put(node.getId(), node);
    }
    public void setEdge(Edge edge){
        if (nodes.containsKey(edge.getSource().getId())&& nodes.containsKey(edge.getTarget().getId())){
            edges.add(edge);
        }else{
            System.out.println("Este nó não existe");
        }
    }
    public Node getNode(String idNode){
        if (nodes.containsKey(idNode)){
            return nodes.get(idNode);
        }else{
            return null;
        }
    }
    public HashMap<String,Node> getNodes(){
        return nodes;
    }
    public ArrayList<Edge> getEdges(){
        return edges;
    }
}
