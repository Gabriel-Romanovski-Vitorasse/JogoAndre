package jogo;

import armas.Arma_IF;
import armas.Arsenal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import personagens.*;
import util.InOut;

public class JogoDesenvolvido {

    public static void main(String[] args) {
        ImageIcon iconeJogo = InOut.carregarIcone("/imagens/icone_jogo.png");

        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Soldado("Cabo Silva"));
        personagens.add(new General("General Costa"));
        personagens.add(new LutSUMO("Honda"));
        personagens.add(new Mago("Merlin"));
        personagens.add(new DragaoAlado("Smaug"));

        SwingUtilities.invokeLater(() -> {
            TelaJogo tela = new TelaJogo(personagens, iconeJogo);
            tela.setVisible(true);
        });
    }

    public static void testarPersonagens(List<Personagem> lista, ImageIcon icone) {
        StringBuilder sb = new StringBuilder("=== STATUS E COMPORTAMENTOS ===\n\n");
        for (Personagem p : lista) {
            sb.append(p.desenhar()).append("\n")
              .append("  • Fala: ").append(p.falar()).append("\n")
              .append("  • Ação Arma: ").append(p.arma()).append("\n")
              .append("  • Perigo (Correr): ").append(p.correr()).append("\n")
              .append("  • Perigo (Voar): ").append(p.voar()).append("\n")
              .append("--------------------------------------------------\n");
        }
        InOut.msgDeInformacao("Personagens do Jogo", sb.toString(), icone);
    }

    public static void trocarArma(List<Personagem> lista, ImageIcon icone) {
        Personagem escolhido = lerPersonagem(lista, "Escolha o número do personagem:", icone);

        List<Arma_IF> armas = Arsenal.todas();
        StringBuilder menuArmas = new StringBuilder("Escolha a nova arma para " + escolhido.getNome() + ":\n");
        for (int i = 0; i < armas.size(); i++) {
            menuArmas.append(i + 1).append(" - ").append(armas.get(i).getNome())
                     .append(" (Dano ").append(armas.get(i).getDano()).append(")\n");
        }

        int opArma = InOut.leInt(menuArmas.toString(), icone);
        if (opArma < 1 || opArma > armas.size()) {
            throw new IllegalArgumentException("Opção de arma inválida!");
        }

        Arma_IF novaArma = armas.get(opArma - 1);
        escolhido.setArma(novaArma);
        InOut.msgDeInformacao("Arma Equipada", escolhido.getNome() + " agora está equipado com: " + novaArma.getNome(), icone);
    }

    public static void simularBatalha(List<Personagem> lista, ImageIcon icone) {
        Personagem p1 = lerPersonagem(lista, "Digite o número do Desafiante 1:", icone);
        Personagem p2 = lerPersonagem(lista, "Digite o número do Desafiante 2:", icone);

        String logBatalha = new Batalha().simular(p1, p2);
        InOut.msgDeInformacao("Resultado da Batalha", logBatalha, icone);
    }

    public static void restaurarVidas(List<Personagem> lista, ImageIcon icone) {
        for (Personagem p : lista) {
            p.restaurarVida();
        }
        InOut.msgDeInformacao("Vidas Restauradas", "Todos os personagens voltaram com a vida cheia!", icone);
    }

    private static Personagem lerPersonagem(List<Personagem> lista, String pergunta, ImageIcon icone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(i).append(" - ").append(lista.get(i).desenhar()).append("\n");
        }
        sb.append("\n").append(pergunta);

        int idx = InOut.leInt(sb.toString(), icone);
        if (idx < 0 || idx >= lista.size()) {
            throw new IndexOutOfBoundsException("Índice de personagem inválido!");
        }
        return lista.get(idx);
    }
}
