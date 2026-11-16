package personagens;

import armas.Arma_IF;
import armas.Desarmado;
import comportamentos.Correr_IF;
import comportamentos.Falar_IF;
import comportamentos.NaoCorre;
import comportamentos.NaoFala;
import comportamentos.NaoVoa;
import comportamentos.Voar_IF;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected final int vidaMaxima;
    protected Arma_IF arma;
    protected Falar_IF comportamentoFala;
    protected Correr_IF comportamentoCorrer;
    protected Voar_IF comportamentoVoar;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        // Padroes seguros: uma subclasse que nao definir algum comportamento nao causa NullPointerException.
        this.arma = new Desarmado();
        this.comportamentoFala = new NaoFala();
        this.comportamentoCorrer = new NaoCorre();
        this.comportamentoVoar = new NaoVoa();
    }

    public abstract String desenhar();

    /** Texto padrao de exibicao, reutilizado pelo desenhar() de cada subclasse. */
    protected String descricao(String tipo) {
        return "[" + tipo + "] " + nome + " | HP: " + vida + "/" + vidaMaxima + " | Arma: " + arma.getNome();
    }

    public String falar() {
        return nome + ": " + comportamentoFala.falar();
    }

    public String arma() {
        return nome + " -> " + arma.usarArma();
    }

    public String correr() {
        return nome + ": " + comportamentoCorrer.correr();
    }

    public String voar() {
        return nome + ": " + comportamentoVoar.voar();
    }

    public void setArma(Arma_IF arma) {
        if (arma == null) {
            throw new IllegalArgumentException("A arma atribuida nao pode ser nula!");
        }
        this.arma = arma;
    }

    public void setComportamentoFala(Falar_IF comportamentoFala) {
        if (comportamentoFala == null) {
            throw new IllegalArgumentException("O comportamento de fala nao pode ser nulo!");
        }
        this.comportamentoFala = comportamentoFala;
    }

    public void setComportamentoCorrer(Correr_IF comportamentoCorrer) {
        if (comportamentoCorrer == null) {
            throw new IllegalArgumentException("O comportamento de corrida nao pode ser nulo!");
        }
        this.comportamentoCorrer = comportamentoCorrer;
    }

    public void setComportamentoVoar(Voar_IF comportamentoVoar) {
        if (comportamentoVoar == null) {
            throw new IllegalArgumentException("O comportamento de voo nao pode ser nulo!");
        }
        this.comportamentoVoar = comportamentoVoar;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public void restaurarVida() {
        this.vida = this.vidaMaxima;
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

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public Arma_IF getArma() {
        return arma;
    }
}
