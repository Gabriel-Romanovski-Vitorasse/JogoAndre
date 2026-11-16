package personagens;

import armas.Desarmado;
import comportamentos.CorreNormal;
import comportamentos.FalaNormal;
import comportamentos.NaoVoa;

public class LutSUMO extends Personagem {
    public LutSUMO(String nome) {
        super(nome, 160);
        this.arma = new Desarmado();
        this.comportamentoFala = new FalaNormal("Dosukoi! Ninguem me tira deste ringue!");
        this.comportamentoCorrer = new CorreNormal();
        this.comportamentoVoar = new NaoVoa();
    }

    @Override
    public String desenhar() {
        return "[Lutador de Sumo] " + nome + " | HP: " + vida + " | Arma: " + arma.getNome();
    }
}