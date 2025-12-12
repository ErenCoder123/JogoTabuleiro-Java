package modelo;

public class CasaInicio extends Casa {

    public CasaInicio() {
        super("Início");
    }

    @Override
    public void realizarAcao(Jogador jogador, double salarioRodada, double totalPatrimonio) {
        System.out.println("--- Casa Início ---");
        System.out.println("Você parou no ponto de partida. (Salário é pago ao passar por aqui, não ao parar).");
    }
}