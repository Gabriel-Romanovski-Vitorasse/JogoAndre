package jogo;

import java.util.Random;
import personagens.Personagem;

/**
 * Regras do combate por turnos (D20). Nao depende de interface grafica:
 * recebe os dois combatentes e devolve o relato da luta em texto.
 */
public class Batalha {

    private static final int FACES_DADO = 20;
    private static final int MINIMO_PARA_ACERTAR = 8;
    private static final int MINIMO_PARA_FUGIR = 11;
    private static final int VIDA_BAIXA = 25;
    private static final double MULTIPLICADOR_CRITICO = 1.5;

    private final Random dado;
    private Personagem fugitivo;

    public Batalha() {
        this(new Random());
    }

    public Batalha(Random dado) {
        this.dado = dado;
    }

    public String simular(Personagem p1, Personagem p2) {
        if (p1 == p2) {
            throw new IllegalArgumentException("Um personagem não pode batalhar contra si mesmo!");
        }
        if (!p1.estaVivo() || !p2.estaVivo()) {
            throw new IllegalStateException("Ambos os personagens precisam estar vivos (HP > 0) para lutar!\n"
                    + "Use o botão \"Restaurar Vidas\" para curar os personagens.");
        }

        fugitivo = null;
        StringBuilder log = new StringBuilder("=== INÍCIO DO COMBATE ===\n")
                .append(p1.getNome()).append(" (HP: ").append(p1.getVida()).append(") VS ")
                .append(p2.getNome()).append(" (HP: ").append(p2.getVida()).append(")\n");

        // Sorteio de iniciativa: quem ataca primeiro nao depende da ordem de escolha.
        Personagem primeiro = dado.nextBoolean() ? p1 : p2;
        Personagem segundo = (primeiro == p1) ? p2 : p1;
        log.append("Iniciativa: ").append(primeiro.getNome()).append(" age primeiro!\n\n");

        int turno = 1;
        while (true) {
            log.append("--- Turno ").append(turno).append(" ---\n");
            if (agir(primeiro, segundo, log) || agir(segundo, primeiro, log)) {
                break;
            }
            turno++;
        }

        Personagem vencedor;
        if (fugitivo != null) {
            vencedor = (fugitivo == p1) ? p2 : p1;
            log.append("\n").append(fugitivo.getNome()).append(" fugiu do combate!");
        } else {
            vencedor = p1.estaVivo() ? p1 : p2;
        }
        log.append("\n=== VENCEDOR: ").append(vencedor.getNome().toUpperCase()).append(" ===");
        return log.toString();
    }

    /**
     * Executa a acao de um combatente no turno: tentar fugir (se estiver com vida baixa) ou atacar.
     * Retorna true se a batalha terminou.
     */
    private boolean agir(Personagem atacante, Personagem defensor, StringBuilder log) {
        if (atacante.getVida() < VIDA_BAIXA && dado.nextBoolean()) {
            return tentarFugir(atacante, log);
        }
        atacar(atacante, defensor, log);
        return !defensor.estaVivo();
    }

    // Quem tenta fugir gasta a acao do turno: se falhar, fica sem atacar.
    private boolean tentarFugir(Personagem personagem, StringBuilder log) {
        int rolagem = rolarDado();
        log.append(personagem.getNome()).append(" está com vida baixa e tentou fugir:\n   ")
           .append(personagem.correr()).append("\n   ")
           .append(personagem.voar()).append("\n   ")
           .append("[D20: ").append(rolagem).append("] ");
        if (rolagem >= MINIMO_PARA_FUGIR) {
            log.append("Conseguiu escapar!\n");
            fugitivo = personagem;
            return true;
        }
        log.append("Não conseguiu escapar e perdeu a vez!\n");
        return false;
    }

    private void atacar(Personagem atacante, Personagem defensor, StringBuilder log) {
        int rolagem = rolarDado();
        log.append("[D20: ").append(rolagem).append("] ");
        if (rolagem < MINIMO_PARA_ACERTAR) {
            log.append(atacante.getNome()).append(" errou o ataque!\n");
            return;
        }
        int dano = atacante.getArma().getDano();
        if (rolagem == FACES_DADO) {
            dano = (int) (dano * MULTIPLICADOR_CRITICO);
        }
        defensor.receberDano(dano);
        log.append(atacante.arma())
           .append(" -> Causou ").append(dano).append(" de dano! (HP ")
           .append(defensor.getNome()).append(": ").append(defensor.getVida()).append(")\n");
    }

    private int rolarDado() {
        return dado.nextInt(FACES_DADO) + 1;
    }
}
