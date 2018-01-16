/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author rafael
 */
public class Edge {
    private int id;
    private static int digito = 0;
    private Node source = null;
    private Node target = null;
    private int valor = 0;

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
        this.id = digito++;
        this.valor = Integer.MAX_VALUE;
    }
    
    public Edge(int id, Node source, Node target) {
        this.source = source;
        this.target = target;
        this.id = id;
        this.valor = Integer.MAX_VALUE;
    }
    
    public Edge(Node source, Node target, int valor) {
        this.source = source;
        this.target = target;
        this.valor = valor;
        this.id = digito++;
    }
    
    public Edge(int id, Node source, Node target, int valor) {
        this.source = source;
        this.target = target;
        this.valor = valor;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {

        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
