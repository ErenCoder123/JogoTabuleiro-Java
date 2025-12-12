VIDEO NOS ARQUIVOS DO PROJETO


# 🎲 Projeto Tabuleiro - Simulação de Jogo Estratégico

Este projeto consiste em uma aplicação Java (Console) que simula a lógica de um jogo de tabuleiro estratégico (inspirado em "Banco Imobiliário"). 

O objetivo principal do desenvolvimento foi a **prática de Estruturas de Dados**, implementando soluções manuais sem o uso de Collections prontas do Java (como `ArrayList` ou `LinkedList`).

## 🚀 Destaques Técnicos

O diferencial deste projeto é a abstenção de frameworks ou bibliotecas auxiliares para armazenamento de dados, focando na lógica algorítmica pura:

- **Lista Ligada Circular (Manual):** O tabuleiro é gerido por uma estrutura de nós (`Node`) onde o último elemento aponta para o primeiro, permitindo loops infinitos de rodadas sem necessidade de tratamento de índices de array.
- **Vetores Primitivos (Arrays):** Todo o gerenciamento de jogadores e propriedades é feito utilizando arrays nativos (`[]`) com controle manual de índices e redimensionamento lógico, **sem o uso de `java.util.ArrayList`**.
- **Polimorfismo:** Uso de classes abstratas para definir os diferentes tipos de casas (Imóvel, Imposto, Restituição, Início), permitindo que o motor do jogo trate interações de forma genérica.

## 📂 Arquitetura do Projeto

O código está organizado seguindo boas práticas de separação de responsabilidades:

- **`src/modelo`**: Classes de domínio (Jogador, Casa e suas especializações).
- **`src/estrutura`**: Implementação "from scratch" das estruturas de dados (Classe `No` e `TabuleiroListaCircular`).
- **`src/jogo`**: Lógica de controle (`MotorJogo`), regras de negócio, turnos, dados e transações financeiras.

## ⚙️ Funcionalidades

- **Gerenciamento de Tabuleiro:** Criação dinâmica de casas (Imóveis, Impostos, Restituição).
- **Sistema Econômico:** Compra de imóveis, pagamento de aluguel, recolhimento de impostos e recebimento de salário ao completar voltas.
- **Modo Rápido:** Opção de configuração automática que gera um tabuleiro padrão e cria bots para testar a lógica do jogo imediatamente.
- **Condição de Vitória:** O jogo encerra por limite de rodadas ou falência dos oponentes, declarando vencedor quem tiver o maior patrimônio (Saldo + Valor dos Imóveis).
