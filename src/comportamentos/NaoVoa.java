package comportamentos;

public class NaoVoa implements Voar_IF {
    @Override
    public String voar() {
        return "Este personagem esta preso ao chao e nao pode voar.";
    }
}