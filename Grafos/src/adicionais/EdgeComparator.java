/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adicionais;

import classes.Edge;
import java.util.Comparator;

/**
 *
 * @author rafael
 */
public class EdgeComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Edge aresta1 = (Edge) o1;
        Edge aresta2 = (Edge) o2;
        if (aresta1.getValor() > aresta2.getValor()) {
            return 1;
        }
        if (aresta1.getValor() < aresta2.getValor()) {
            return -1;
        }
        return 0;
    }
}
