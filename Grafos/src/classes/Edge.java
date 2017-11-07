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
    private String rotulo = null;

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
        this.id = digito++;
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
}
