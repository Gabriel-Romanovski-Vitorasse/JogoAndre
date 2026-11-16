package armas;

public class SoproDeFogo implements Arma_IF {
    @Override
    public String usarArma() {
        return "Cuspiu uma labareda intensa de Fogo! (Dano: " + getDano() + ")";
    }

    @Override
    public int getDano() {
        return 55;
    }

    @Override
    public String getNome() {
        return "Sopro de Fogo";
    }
}