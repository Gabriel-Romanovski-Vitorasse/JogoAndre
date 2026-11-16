package personagens;

import armas.Arma_IF;
import armas.Desarmado;
import comportamentos.Correr_IF;
import comportamentos.Falar_IF;
import comportamentos.Voar_IF;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected Arma_IF arma;
    protected Falar_IF comportamentoFala;
    protected Correr_IF comportamentoCorrer;
    protected Voar_IF comportamentoVoar;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.arma = new Desarmado(); 
    }

    public abstract String desenhar();

    public String falar() {
        return nome + ": " + comportamentoFala.falar();
    }

    public String arma() {
        return nome + " -> " + arma.usarArma();
    }

    public void setArma(Arma_IF arma) {
        if (arma == null) {
            throw new IllegalArgumentException("A arma atribuida nao pode ser nula!");
        }
        this.arma = arma;
    }

    public String correr() {
        return nome + ": " + comportamentoCorrer.correr();
    }

    public String voar() {
        return nome + ": " + comportamentoVoar.voar();
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public Arma_IF getArma() {
        return arma;
    }
}