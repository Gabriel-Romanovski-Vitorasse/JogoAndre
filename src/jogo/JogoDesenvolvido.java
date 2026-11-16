package jogo;

import armas.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        StringBuilder sb = new StringBuilder("Escolha o número do personagem:\n");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(i).append(" - ").append(lista.get(i).desenhar()).append("\n");
        }

        int idx = InOut.leInt(sb.toString(), icone);
        if (idx < 0 || idx >= lista.size()) {
            throw new IndexOutOfBoundsException("Índice de personagem inválido!");
        }

        Personagem escolhido = lista.get(idx);

        String menuArmas = "Escolha a nova arma para " + escolhido.getNome() + ":\n"
                + "1 - Revólver (Dano 25)\n"
                + "2 - Fuzil (Dano 45)\n"
                + "3 - Faca (Dano 15)\n"
                + "4 - Magia (Dano 40)\n"
                + "5 - Sopro de Fogo (Dano 55)\n"
                + "6 - Desarmado (Dano 5)";

        int opArma = InOut.leInt(menuArmas, icone);
        Arma_IF novaArma;

        switch (opArma) {
            case 1: novaArma = new Revolver(); break;
            case 2: novaArma = new Fuzil(); break;
            case 3: novaArma = new Faca(); break;
            case 4: novaArma = new Magia(); break;
            case 5: novaArma = new SoproDeFogo(); break;
            case 6: novaArma = new Desarmado(); break;
            default:
                throw new IllegalArgumentException("Opção de arma inválida!");
        }

        escolhido.setArma(novaArma);
        InOut.msgDeInformacao("Arma Equipada", escolhido.getNome() + " agora está equipado com: " + novaArma.getNome(), icone);
    }

    public static void simularBatalha(List<Personagem> lista, ImageIcon icone) {
        StringBuilder sb = new StringBuilder("Selecione os combatentes pelo número:\n");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(i).append(" - ").append(lista.get(i).desenhar()).append("\n");
        }

        int idx1 = InOut.leInt(sb.toString() + "\nDigite o número do Desafiante 1:", icone);
        int idx2 = InOut.leInt(sb.toString() + "\nDigite o número do Desafiante 2:", icone);

        if (idx1 < 0 || idx1 >= lista.size() || idx2 < 0 || idx2 >= lista.size()) {
            throw new IndexOutOfBoundsException("Um dos personagens selecionados não existe!");
        }
        if (idx1 == idx2) {
            throw new IllegalArgumentException("Um personagem não pode batalhar contra si mesmo!");
        }

        Personagem p1 = lista.get(idx1);
        Personagem p2 = lista.get(idx2);

        if (!p1.estaVivo() || !p2.estaVivo()) {
            throw new IllegalStateException("Ambos os personagens precisam estar vivos (HP > 0) para lutar!");
        }

        Random dado = new Random();
        StringBuilder logBatalha = new StringBuilder("=== INÍCIO DO COMBATE ===\n")
                .append(p1.getNome()).append(" (HP: ").append(p1.getVida()).append(") VS ")
                .append(p2.getNome()).append(" (HP: ").append(p2.getVida()).append(")\n\n");

        int turno = 1;
        while (p1.estaVivo() && p2.estaVivo()) {
            logBatalha.append("--- Turno ").append(turno).append(" ---\n");

            int rolagem1 = dado.nextInt(20) + 1;
            if (rolagem1 >= 8) {
                int dano = p1.getArma().getDano();
                if (rolagem1 == 20) dano = (int) (dano * 1.5);
                p2.receberDano(dano);
                logBatalha.append("[D20: ").append(rolagem1).append("] ").append(p1.arma())
                          .append(" -> Causou ").append(dano).append(" de dano! (HP ").append(p2.getNome()).append(": ").append(p2.getVida()).append(")\n");
            } else {
                logBatalha.append("[D20: ").append(rolagem1).append("] ").append(p1.getNome()).append(" errou o ataque!\n");
            }

            if (!p2.estaVivo()) break;

            if (p2.getVida() < 25 && dado.nextBoolean()) {
                logBatalha.append(p2.getNome()).append(" está com vida baixa e tentou fugir:\n   ")
                          .append(p2.correr()).append("\n   ").append(p2.voar()).append("\n");
            }

            int rolagem2 = dado.nextInt(20) + 1;
            if (rolagem2 >= 8) {
                int dano = p2.getArma().getDano();
                if (rolagem2 == 20) dano = (int) (dano * 1.5);
                p1.receberDano(dano);
                logBatalha.append("[D20: ").append(rolagem2).append("] ").append(p2.arma())
                          .append(" -> Causou ").append(dano).append(" de dano! (HP ").append(p1.getNome()).append(": ").append(p1.getVida()).append(")\n");
            } else {
                logBatalha.append("[D20: ").append(rolagem2).append("] ").append(p2.getNome()).append(" errou o ataque!\n");
            }

            turno++;
        }

        Personagem vencedor = p1.estaVivo() ? p1 : p2;
        logBatalha.append("\n=== VENCEDOR: ").append(vencedor.getNome().toUpperCase()).append(" ===");

        InOut.msgDeInformacao("Resultado da Batalha", logBatalha.toString(), icone);
    }
}