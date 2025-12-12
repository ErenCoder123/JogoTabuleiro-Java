package Jogo;

import estrutura.No;
import estrutura.TabuleiroListaCircular;
import modelo.*;
import java.util.Random;
import java.util.Scanner;

public class MotorJogo {
    private TabuleiroListaCircular tabuleiro;
    private Jogador[] jogadores;
    private int qtdJogadores;

    private double salarioRodada;
    private double saldoInicial;
    private int maxRodadas;
    private Scanner scanner;

    public MotorJogo() {
        this.tabuleiro = new TabuleiroListaCircular();
        this.jogadores = new Jogador[6];
        this.qtdJogadores = 0;
        this.scanner = new Scanner(System.in);
    }

    public void configurarJogo() {
        System.out.println("=== CONFIGURAÇÃO DO JOGO ===");
        boolean configurando = true;

        while (configurando) {
            System.out.println("\nMENU:");
            System.out.println("1. Adicionar Imóvel (Manual)");
            System.out.println("2. Adicionar Imposto");
            System.out.println("3. Adicionar Restituição");
            System.out.println("4. Carregar APENAS Tabuleiro Padrão");
            System.out.println("5. >> MODO RÁPIDO (Tabuleiro + Jogadores + Regras) <<");
            System.out.println("0. Finalizar Configuração Manual e Ir para Jogadores");
            System.out.print("Escolha: ");

            int op = lerInteiro();

            switch (op) {
                case 1:
                    if (tabuleiro.getTamanho() >= 40) {
                        System.out.println("Limite atingido.");
                        break;
                    }
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = lerDouble();
                    System.out.print("Aluguel: ");
                    double aluguel = lerDouble();
                    tabuleiro.adicionarCasa(new CasaImovel(nome, preco, aluguel));
                    break;
                case 2:
                    tabuleiro.adicionarCasa(new CasaImposto());
                    System.out.println("Imposto adicionado.");
                    break;
                case 3:
                    tabuleiro.adicionarCasa(new CasaRestituicao());
                    System.out.println("Restituição adicionada.");
                    break;
                case 4:
                    carregarTabuleiroPadrao();
                    System.out.println("Tabuleiro padrão carregado.");
                    break;

                case 5:
                    configurarJogoCompletoAutomatico();
                    configurando = false;
                    break;

                case 0:
                    if (tabuleiro.getTamanho() < 10) {
                        System.out.println("Erro: Mínimo 10 casas no tabuleiro.");
                    } else {
                        if (!tabuleiro.possuiCasaInicio()) {
                            System.out.println("Adicionando 'Início' automaticamente...");
                            tabuleiro.adicionarCasa(new CasaInicio());
                        }
                        configurarJogadoresManual();
                        configurando = false;
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método novo: Preenche TUDO para começar na hora
    private void configurarJogoCompletoAutomatico() {
        System.out.println("\n--- Carregando Configurações Automáticas ---");

        if (tabuleiro.getTamanho() == 0) {
            carregarTabuleiroPadrao();
        }

        this.saldoInicial = 2500.00;
        this.salarioRodada = 500.00;
        this.maxRodadas = 20;

        this.qtdJogadores = 0;

        jogadores[qtdJogadores] = new Jogador("Player 1 (Bot)", saldoInicial);
        qtdJogadores++;

        jogadores[qtdJogadores] = new Jogador("Player 2 (Bot)", saldoInicial);
        qtdJogadores++;

        jogadores[qtdJogadores] = new Jogador("Player 3 (Bot)", saldoInicial);
        qtdJogadores++;

        System.out.println(">> Jogadores criados: Player 1, Player 2 e Player 3.");
        System.out.println(">> Saldo: " + saldoInicial + " | Salário: " + salarioRodada);
        System.out.println(">> Tudo pronto! Iniciando...");
    }

    private void configurarJogadoresManual() {
        System.out.print("\nSaldo Inicial: ");
        saldoInicial = lerDouble();
        System.out.print("Salário por volta: ");
        salarioRodada = lerDouble();
        System.out.print("Máximo de Rodadas: ");
        maxRodadas = lerInteiro();

        System.out.print("Quantos jogadores (2 a 6)? ");
        int qtdDesejada = lerInteiro();
        while (qtdDesejada < 2 || qtdDesejada > 6) {
            System.out.print("Inválido. Digite entre 2 e 6: ");
            qtdDesejada = lerInteiro();
        }

        for (int i = 0; i < qtdDesejada; i++) {
            System.out.print("Nome do Jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine();

            jogadores[qtdJogadores] = new Jogador(nome, saldoInicial);
            qtdJogadores++;
        }
    }

    private void carregarTabuleiroPadrao() {
        tabuleiro.adicionarCasa(new CasaInicio());
        tabuleiro.adicionarCasa(new CasaImovel("Av. Brasil", 200, 20));
        tabuleiro.adicionarCasa(new CasaImovel("Rua das Flores", 150, 15));
        tabuleiro.adicionarCasa(new CasaImposto()); // 5% patrimonio
        tabuleiro.adicionarCasa(new CasaImovel("Centro", 400, 40));
        tabuleiro.adicionarCasa(new CasaRestituicao()); // 10% salario
        tabuleiro.adicionarCasa(new CasaImovel("Praça da Sé", 350, 35));
        tabuleiro.adicionarCasa(new CasaImovel("Jardins", 600, 60));
        tabuleiro.adicionarCasa(new CasaImposto());
        tabuleiro.adicionarCasa(new CasaImovel("Av. Paulista", 1000, 100));
        System.out.println("... Tabuleiro preenchido com 10 casas.");
    }

    public void iniciarJogo() {
        if (qtdJogadores < 2) {
            System.out.println("Erro: Jogadores insuficientes para iniciar.");
            return;
        }

        No ponteiroInicio = encontrarNoInicio();

        for (int i = 0; i < qtdJogadores; i++) {
            jogadores[i].setPosicaoAtual(ponteiroInicio);
        }

        System.out.println("\n=== JOGO INICIADO ===");
        Random dados = new Random();
        int rodada = 1;

        while (rodada <= maxRodadas && contarJogadoresAtivos() > 1) {
            System.out.println("\n--- RODADA " + rodada + " ---");

            for (int i = 0; i < qtdJogadores; i++) {
                Jogador j = jogadores[i];

                if (j.isFalido()) continue;

                System.out.println("\n>> Vez de " + j.getNome() + " | Saldo: R$ " + String.format("%.2f", j.getSaldo()));
                System.out.println("[ENTER] jogar dados...");
                scanner.nextLine();

                int d1 = dados.nextInt(6) + 1;
                int d2 = dados.nextInt(6) + 1;
                int soma = d1 + d2;
                System.out.println("Dados: " + d1 + " + " + d2 + " = " + soma);

                andarComJogador(j, soma);
                tratarCasaAtual(j);

                if (j.getSaldo() < 0) {
                    System.out.println("X_X " + j.getNome() + " FALIU!");
                    j.setFalido(true);
                }
            }
            rodada++;
        }
        encerrarJogo();
    }

    private void andarComJogador(Jogador j, int passos) {
        No atual = j.getPosicaoAtual();
        for (int i = 0; i < passos; i++) {
            atual = atual.getProximo();
            if (atual.getConteudo() instanceof CasaInicio) {
                System.out.println("Passou pelo Início! +R$ " + salarioRodada);
                j.receber(salarioRodada);
            }
        }
        j.setPosicaoAtual(atual);
        System.out.println("Parou em: " + atual.getConteudo().getNome());
    }

    private void tratarCasaAtual(Jogador j) {
        Casa casa = j.getPosicaoAtual().getConteudo();

        if (casa instanceof CasaImovel) {
            CasaImovel imovel = (CasaImovel) casa;
            if (imovel.getDono() == null) {
                if (j.getSaldo() >= imovel.getPrecoCompra()) {
                    System.out.print("Comprar " + imovel.getNome() + " por R$ " + imovel.getPrecoCompra() + "? (1-Sim/2-Não): ");
                    int op = lerInteiro();
                    if (op == 1) {
                        j.pagar(imovel.getPrecoCompra());
                        imovel.setDono(j);
                        j.adicionarPropriedade(imovel);
                        System.out.println("Compra realizada!");
                    }
                } else {
                    System.out.println("Saldo insuficiente para compra.");
                }
                return;
            }
        }
        casa.realizarAcao(j, salarioRodada, j.calcularPatrimonio());
    }
    private void encerrarJogo() {
        System.out.println("\n=== FIM DE JOGO ===");
        Jogador vencedor = null;
        double maiorPatrimonio = -Double.MAX_VALUE;

        for (int i = 0; i < qtdJogadores; i++) {
            Jogador j = jogadores[i];
            if (!j.isFalido()) {
                double p = j.calcularPatrimonio();
                System.out.println(j.getNome() + " | Patrimônio Final: R$ " + String.format("%.2f", p));
                if (p > maiorPatrimonio) {
                    maiorPatrimonio = p;
                    vencedor = j;
                }
            }
        }

        if (vencedor != null) {
            System.out.println("\n*** VENCEDOR: " + vencedor.getNome().toUpperCase() + " ***");
        } else {
            System.out.println("Sem vencedores.");
        }
    }

    private No encontrarNoInicio() {
        if (tabuleiro.getTamanho() == 0) return null;
        No atual = tabuleiro.getInicio();
        do {
            if (atual.getConteudo() instanceof CasaInicio) return atual;
            atual = atual.getProximo();
        } while (atual != tabuleiro.getInicio());
        return tabuleiro.getInicio();
    }

    private int contarJogadoresAtivos() {
        int count = 0;
        for (int i = 0; i < qtdJogadores; i++) {
            if (!jogadores[i].isFalido()) count++;
        }
        return count;
    }

    private int lerInteiro() {
        try { int i = scanner.nextInt(); scanner.nextLine(); return i; }
        catch (Exception e) { scanner.nextLine(); return -1; }
    }

    private double lerDouble() {
        try { double d = scanner.nextDouble(); scanner.nextLine(); return d; }
        catch (Exception e) { scanner.nextLine(); return 0.0; }
    }
}