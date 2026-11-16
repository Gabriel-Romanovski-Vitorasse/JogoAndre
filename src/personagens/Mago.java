package personagens;

import armas.Magia;
import comportamentos.CorreNormal;
import comportamentos.FalaNormal;
import comportamentos.NaoVoa;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 90);
        this.arma = new Magia();
        this.comportamentoFala = new FalaNormal("Pelos poderes arcanos, eu me defendo!");
        this.comportamentoCorrer = new CorreNormal();
        this.comportamentoVoar = new NaoVoa();
    }

    @Override
    public String desenhar() {
        return descricao("Mago");
    }
}