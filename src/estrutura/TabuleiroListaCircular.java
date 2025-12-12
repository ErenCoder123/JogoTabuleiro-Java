package estrutura;

import modelo.Casa;
import modelo.CasaInicio;

public class TabuleiroListaCircular {
    private No inicio;
    private No fim;
    private int tamanho;

    public TabuleiroListaCircular() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void adicionarCasa(Casa casa) {
        No novoNo = new No(casa);

        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
            fim.setProximo(inicio);
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
            fim.setProximo(inicio);
        }
        tamanho++;
    }

    public No getInicio() {
        return inicio;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void listarCasas() {
        if (inicio == null) {
            System.out.println("Tabuleiro vazio.");
            return;
        }
        No atual = inicio;
        System.out.print("Tabuleiro: ");
        do {
            System.out.print("[" + atual.getConteudo().getNome() + "] -> ");
            atual = atual.getProximo();
        } while (atual != inicio);
        System.out.println("(volta ao Início)");
    }

    public boolean possuiCasaInicio() {
        if (inicio == null) return false;
        No atual = inicio;
        do {
            if (atual.getConteudo() instanceof CasaInicio) return true;
            atual = atual.getProximo();
        } while (atual != inicio);
        return false;
    }
}