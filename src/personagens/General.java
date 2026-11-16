package personagens;

import armas.Fuzil;
import comportamentos.CorreNormal;
import comportamentos.FalaNormal;
import comportamentos.NaoVoa;

public class General extends Personagem {
    public General(String nome) {
        super(nome, 130);
        this.arma = new Fuzil();
        this.comportamentoFala = new FalaNormal("Mantenham a formacao e avancem!");
        this.comportamentoCorrer = new CorreNormal();
        this.comportamentoVoar = new NaoVoa();
    }

    @Override
    public String desenhar() {
        return "[General] " + nome + " | HP: " + vida + " | Arma: " + arma.getNome();
    }
}