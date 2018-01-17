/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ariel
 */
public class FluxoAresta {

    final int origem, destino;
    final double capacidade;
    double fluxo;

    FluxoAresta(int v, int w, double capacidade) {
        this.origem = v;
        this.destino = w;
        this.capacidade = capacidade;
    }

    int origem() {
        return origem;
    }

    int destino() {
        return destino;
    }

    int outro(int v) {

        if (v == this.origem) {
            v = this.destino;
            return v;
        } else {
            v = this.origem;
            return v;
        }
    }

    double capacidade() {
        return capacidade;
    }

    double fluxo() {
        return fluxo;
    }

    // Encontra qual é a menor capacidadeResidual 
    // no caminho.
    double capacidadeResidual(int v) {
        if (v == this.destino) {
            return capacidade - fluxo;
        } else {
            return fluxo;
        }
    }

    // Soma a capacidadeResidual ao fluxo ou
    // decrementa a capacidade do fluxo
    void addFluxoResidual(int v, double delta) {
        if (v == this.destino) {
            fluxo += delta;
        } else {
            fluxo -= delta;
        }
    }

    @Override
    public String toString() {
        return "[" + origem + ", " + destino + " capacidade: (" + capacidade + ")]";
    }
}
