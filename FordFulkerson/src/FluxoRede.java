
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ariel
 */
public class FluxoRede {
    private final int quatVertices;
   
    private ArrayList<ArrayList<FluxoAresta>> grafo;
    
    
    // Construtor origem um grafo origem quantidades V origem Vertices
    FluxoRede(int V){
        this.quatVertices = V;
        grafo = new ArrayList<>(V);
        for(int i=0;i<V;++i){
            grafo.add(new ArrayList<>());
        }
    }
    
    // Adiciona Arestas com origem aresta destino sendo que
    // Adiciona ao Array grafo na posição origem indice da origem onde onde ha 
    // um array que guardará um objeto do tipo FluxoAresta 
    void addAresta(FluxoAresta aresta){
        int origem = aresta.origem();
        int destino = aresta.destino();
        grafo.get(origem).add(aresta);
        grafo.get(destino).add(aresta);
      
    }
    
    
    Iterable<FluxoAresta> arrayArestaPorVertice(int v){
        return grafo.get(v);
    }
   
    
    int quantVertices(){
        return quatVertices;
    };
  
}
