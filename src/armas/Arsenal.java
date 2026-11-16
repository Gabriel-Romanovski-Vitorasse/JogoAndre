package armas;

import java.util.List;

/**
 * Catalogo com todas as armas disponiveis no jogo.
 * Para adicionar uma arma nova, basta inclui-la nesta lista.
 */
public final class Arsenal {

    private static final List<Arma_IF> ARMAS = List.of(
            new Revolver(),
            new Fuzil(),
            new Faca(),
            new Magia(),
            new SoproDeFogo(),
            new Desarmado()
    );

    private Arsenal() {
    }

    public static List<Arma_IF> todas() {
        return ARMAS;
    }
}
