package armas;

public class Desarmado implements Arma_IF {
    @Override
    public String usarArma() {
        return "Atacou com os proprios punhos! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 5;
    }

    @Override
    public String getNome() {
        return "Desarmado";
    }
}