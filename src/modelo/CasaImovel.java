package modelo;

public class CasaImovel extends Casa {
    private double precoCompra;
    private double valorAluguel;
    private Jogador dono;

    public CasaImovel(String nome, double precoCompra, double valorAluguel) {
        super(nome);
        this.precoCompra = precoCompra;
        this.valorAluguel = valorAluguel;
        this.dono = null;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    @Override
    public void realizarAcao(Jogador jogador, double salarioRodada, double totalPatrimonio) {
        if (dono == null) {
            System.out.println("Imóvel '" + getNome() + "' está à venda por R$ " + precoCompra);
        } else if (dono != jogador) {
            System.out.println("Esta propriedade pertence a " + dono.getNome());
            System.out.println("Valor do aluguel: R$ " + valorAluguel);

            boolean pagou = jogador.pagar(valorAluguel);
            if (pagou) {
                dono.receber(valorAluguel);
                System.out.println("Você pagou o aluguel.");
            } else {
                System.out.println("Você não conseguiu pagar o aluguel integralmente.");
            }
        } else {
            System.out.println("Você está em sua própria propriedade (" + getNome() + "). Relaxe.");
        }
    }
}