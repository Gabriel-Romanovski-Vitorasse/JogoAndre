package armas;

public class Faca implements Arma_IF {
    @Override
    public String usarArma() {
        return "Golpeou rapidamente com a Faca! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 15;
    }

    @Override
    public String getNome() {
        return "Faca";
    }
}