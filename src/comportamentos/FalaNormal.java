package comportamentos;

public class FalaNormal implements Falar_IF {
    private String frase;

    public FalaNormal(String frase) {
        this.frase = frase;
    }

    @Override
    public String falar() {
        return "\"" + frase + "\"";
    }
}