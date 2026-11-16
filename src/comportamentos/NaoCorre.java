package comportamentos;

public class NaoCorre implements Correr_IF {
    @Override
    public String correr() {
        return "Este personagem nao corre (nao sai na carreira)!";
    }
}