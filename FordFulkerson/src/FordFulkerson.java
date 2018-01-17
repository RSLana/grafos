
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ariel
 */
public class FordFulkerson {
   private boolean[] marcado;
    private FluxoAresta[] aresta;
    private double valor;
   
    public FordFulkerson(FluxoRede Grafo, int fonte, int sumidouro){
        valor = 0;
        
        // Enquanto temCaminhoFluxoPossivel retornar true
        while(temCaminhoFluxoPossivel(Grafo, fonte, sumidouro)){
            // Define uma variável com um valor muito grande
            double baseComparacao = Double.POSITIVE_INFINITY;
            
            // Imprime na tela um array com as areasta dos caminhos  
            System.out.println("Possivel Caminho com aumento de fluxo: "+ Arrays.toString(aresta));
            
            
             // Acha a menor capacidadeResidual entre as arestas do caminho
            for(int v = sumidouro;v != fonte; v = aresta[v].outro(v)){ 
                baseComparacao = Math.min(baseComparacao, aresta[v].capacidadeResidual(v));
            }
            
            
            // Redistribui a menor capacidadeResidual para os fluxos do caminho
            for(int v = sumidouro;v!=fonte;v = aresta[v].outro(v))
                aresta[v].addFluxoResidual(v, baseComparacao);
            
            
            //Soma ao valor a capacidade residual desse ciclo
            valor += baseComparacao;
        }
    }
        
    public final boolean temCaminhoFluxoPossivel(FluxoRede G, int fonte, int sumidouro){
        
            // Vetor do tipo FluxoAreta com o tamanho da quantidade 
            // de vertices
            aresta = new FluxoAresta[G.quantVertices()];
            
            // Vetor do tipo boolean com o tamanho da quantidade 
            // de vertices
            marcado = new boolean[G.quantVertices()];
            
            // Pilha do tipo inteiro 
            Queue<Integer> q = new LinkedList<>();
            
            // Adiciona fonte a pilha 
            q.add(fonte);
            
            // Adciona true na posição que tem o indici igual ao da fonte
            marcado[fonte] = true;
            
            // Enquanto q não estiver vazio
            while(!q.isEmpty()){
                
                // Retira o topo da fila e atribui a v
                int v = q.poll();
                
                //Percorre um Array de retornado por arrayArestaPorVertice(v)
                for(FluxoAresta a:G.arrayArestaPorVertice(v)){
                    
                    // Adiciona a w o retorno de a.outro(v) que pode 
                    // ser a origem ou o destino da aresta a
                    int w = a.outro(v);
                    
                    // SE capacidadeResidual da aresta a for Maior que 
                    // a
                    if(a.capacidadeResidual(w) > 0 && !marcado[w]){
                        aresta[w] = a;
                        
                        marcado[w] = true;
                        q.add(w);
                    }
                }
            }
            return marcado[sumidouro];
    }
       
    public double valor(){
        return valor;
    }
    
    public boolean corte(int v){
        return marcado[v];
    } 
    
    public static void main(String[] args){
        
        
        FluxoRede rede = new FluxoRede(4);
        
        // resultado 23
//        rede.addAresta(new FluxoAresta(0, 1, 16));
//        rede.addAresta(new FluxoAresta(0,2,13));
//        rede.addAresta(new FluxoAresta(2,1,4));
//        rede.addAresta(new FluxoAresta(1,3,12));
//        rede.addAresta(new FluxoAresta(3,2,9));
//        rede.addAresta(new FluxoAresta(2,4,14));
//        rede.addAresta(new FluxoAresta(4,3,7));
//        rede.addAresta(new FluxoAresta(3,5,20));
//        rede.addAresta(new FluxoAresta(4,5,4));



                //Resultado 50;

//        rede.addAresta(new FluxoAresta(0, 1, 20));
//        rede.addAresta(new FluxoAresta(0,2,10));
//        rede.addAresta(new FluxoAresta(0,3,35));
//        rede.addAresta(new FluxoAresta(1,4,15));
//        rede.addAresta(new FluxoAresta(1,5,10));
//        rede.addAresta(new FluxoAresta(2,4,5));
//        rede.addAresta(new FluxoAresta(2,6,10));
//        rede.addAresta(new FluxoAresta(3,4,10));
//        rede.addAresta(new FluxoAresta(3,5,5));
//        rede.addAresta(new FluxoAresta(3,6,5));
//        rede.addAresta(new FluxoAresta(4,7,25));
//        rede.addAresta(new FluxoAresta(5,7,20));
//        rede.addAresta(new FluxoAresta(6,7,20));


          rede.addAresta(new FluxoAresta(0,1,10));
          rede.addAresta(new FluxoAresta(0,2,20));
          rede.addAresta(new FluxoAresta(1,3,15));
          rede.addAresta(new FluxoAresta(2,1,5));
          rede.addAresta(new FluxoAresta(2,3,10));


        
        FordFulkerson ff = new FordFulkerson(rede, 0, 3);
        System.out.println("Maximo Fluxo = "+ff.valor());
        
    
    }
}
