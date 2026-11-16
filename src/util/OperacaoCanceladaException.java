package util;

/**
 * Lancada quando o usuario cancela ou fecha um dialogo de entrada.
 */
public class OperacaoCanceladaException extends RuntimeException {

    public OperacaoCanceladaException() {
        super("Operacao cancelada pelo usuario.");
    }
}
