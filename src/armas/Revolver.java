package armas;

public class Revolver implements Arma_IF {
    @Override
    public String usarArma() {
        return "Disparou com o Revolver! BANG! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 25;
    }

    @Override
    public String getNome() {
        return "Revolver";
    }
}