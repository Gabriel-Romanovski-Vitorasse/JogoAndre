package util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class InOut {

    
    public static ImageIcon carregarIcone(String caminho) {
        if (caminho == null || caminho.isEmpty()) {
            return null;
        }
        java.net.URL imgURL = InOut.class.getResource(caminho);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        return null;
    }

    public static void msgDeInformacao(String cabecalho, String frase, ImageIcon icone) {
        JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.INFORMATION_MESSAGE, icone);
    }

    public static void msgDeErro(String cabecalho, String frase) {
        JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.ERROR_MESSAGE);
    }

    public static String leString(String frase, ImageIcon icone) {
        Object entrada = JOptionPane.showInputDialog(null, frase, "Entrada de Dados", JOptionPane.QUESTION_MESSAGE, icone, null, "");
        return (entrada != null) ? entrada.toString() : "";
    }

    public static int leInt(String frase, ImageIcon icone) {
        while (true) {
            try {
                String valor = leString(frase, icone);
                if (valor.isEmpty()) {
                    throw new NumberFormatException("Campo vazio.");
                }
                return Integer.parseInt(valor.trim());
            } catch (NumberFormatException e) {
                msgDeErro("Erro de Entrada", "Por favor, digite um numero inteiro valido!");
            }
        }
    }
}