package modelo;

import estrutura.No;

public class Jogador {
    private String nome;
    private double saldo;
    private No posicaoAtual;
    private boolean falido;

    private CasaImovel[] propriedades;
    private int qtdPropriedades;

    public Jogador(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.falido = false;

        this.propriedades = new CasaImovel[40];
        this.qtdPropriedades = 0;
    }

    public void receber(double valor) {
        this.saldo += valor;
    }

    public boolean pagar(double valor) {
        if (saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            System.out.println("ALERTA: " + nome + " não tem saldo suficiente (Necessário: " + valor + ")");
            this.saldo -= valor;
            return false;
        }
    }

    public double calcularPatrimonio() {
        double valorImoveis = 0;
        for (int i = 0; i < qtdPropriedades; i++) {
            valorImoveis += propriedades[i].getPrecoCompra();
        }
        return saldo + valorImoveis;
    }

    public void adicionarPropriedade(CasaImovel imovel) {
        if (qtdPropriedades < 40) {
            propriedades[qtdPropriedades] = imovel;
            qtdPropriedades++;
        } else {
            System.out.println("Erro: Limite de propriedades atingido.");
        }
    }

    public String getNome() { return nome; }
    public double getSaldo() { return saldo; }
    public No getPosicaoAtual() { return posicaoAtual; }
    public void setPosicaoAtual(No posicaoAtual) { this.posicaoAtual = posicaoAtual; }
    public boolean isFalido() { return falido; }
    public void setFalido(boolean falido) { this.falido = falido; }
}