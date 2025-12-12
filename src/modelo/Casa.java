package modelo;

public abstract class Casa {
    private String nome;

    public Casa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void realizarAcao(Jogador jogador, double salarioRodada, double totalPatrimonio);
}