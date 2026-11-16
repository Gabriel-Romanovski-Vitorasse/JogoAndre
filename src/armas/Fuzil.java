package armas;

public class Fuzil implements Arma_IF {
    @Override
    public String usarArma() {
        return "Rajada de Fuzil disparada! RATATA! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 45;
    }

    @Override
    public String getNome() {
        return "Fuzil";
    }
}