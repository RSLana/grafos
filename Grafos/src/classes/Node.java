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
public class Node {
    private String id;
    private static int digito = 0;
    private String rotulo = null;

    public Node() {
        this.id = "n"+(digito++);
    }

    public static void setDigito(int digito) {
        Node.digito = digito;
    }
    
    public Node(String idNode) {
        this.id = idNode;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
