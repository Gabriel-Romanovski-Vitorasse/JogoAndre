package armas;

public class Magia implements Arma_IF {
    @Override
    public String usarArma() {
        return "Lancou uma esfera arcana de Magia! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 40;
    }

    @Override
    public String getNome() {
        return "Magia";
    }
}