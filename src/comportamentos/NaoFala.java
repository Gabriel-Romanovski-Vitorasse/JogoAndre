package comportamentos;

public class NaoFala implements Falar_IF {
    @Override
    public String falar() {
        return "[Este personagem nao fala, apenas emite rugidos/sons!]";
    }
}