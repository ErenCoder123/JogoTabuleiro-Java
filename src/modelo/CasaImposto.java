package modelo;

public class CasaImposto extends Casa {

    public CasaImposto() {
        super("Imposto");
    }

    @Override
    public void realizarAcao(Jogador jogador, double salarioRodada, double totalPatrimonio) {
        double imposto = totalPatrimonio * 0.05; //
        System.out.println("--- Receita Federal ---");
        System.out.println("Taxa de 5% sobre seu patrimônio (R$ " + totalPatrimonio + ")");
        System.out.println("Valor a pagar: R$ " + String.format("%.2f", imposto));
        jogador.pagar(imposto);
    }
}