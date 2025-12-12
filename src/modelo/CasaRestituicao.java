package modelo;

public class CasaRestituicao extends Casa {

    public CasaRestituicao() {
        super("Restituição");
    }

    @Override
    public void realizarAcao(Jogador jogador, double salarioRodada, double totalPatrimonio) {
        double valor = salarioRodada * 0.10; // 10% do salário da rodada
        System.out.println("--- Restituição ---");
        System.out.println("Você recebeu uma restituição do banco!");
        jogador.receber(valor);
        System.out.println("Valor recebido: R$ " + String.format("%.2f", valor));
    }
}