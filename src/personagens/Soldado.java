package personagens;

import armas.Revolver;
import comportamentos.CorreNormal;
import comportamentos.FalaNormal;
import comportamentos.NaoVoa;

public class Soldado extends Personagem {
    public Soldado(String nome) {
        super(nome, 100);
        this.arma = new Revolver();
        this.comportamentoFala = new FalaNormal("Soldado pronto para o combate, senhor!");
        this.comportamentoCorrer = new CorreNormal();
        this.comportamentoVoar = new NaoVoa();
    }

    @Override
    public String desenhar() {
        return "[Soldado] " + nome + " | HP: " + vida + " | Arma: " + arma.getNome();
    }
}