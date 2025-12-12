package estrutura;

import modelo.Casa;

public class No {
    private Casa conteudo;
    private No proximo;

    public No(Casa conteudo) {
        this.conteudo = conteudo;
        this.proximo = null;
    }

    public Casa getConteudo() {
        return conteudo;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}