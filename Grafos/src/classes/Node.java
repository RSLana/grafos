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

    public Node() {
        this.id = "n"+(digito++);
    }

    public Node(String idNode) {
        this.id = idNode;
    }
   
    public String getId() {
        return id;
    }
}
