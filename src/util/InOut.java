package util;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InOut {

    private static final int MAX_LINHAS_SEM_ROLAGEM = 20;

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
        JOptionPane.showMessageDialog(null, conteudo(frase), cabecalho, JOptionPane.INFORMATION_MESSAGE, icone);
    }

    public static void msgDeErro(String cabecalho, String frase) {
        JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Le um texto. Lanca OperacaoCanceladaException se o usuario cancelar ou fechar o dialogo.
     */
    public static String leString(String frase, ImageIcon icone) {
        Object entrada = JOptionPane.showInputDialog(null, frase, "Entrada de Dados", JOptionPane.QUESTION_MESSAGE, icone, null, "");
        if (entrada == null) {
            throw new OperacaoCanceladaException();
        }
        return entrada.toString();
    }

    /**
     * Le um inteiro, repetindo a pergunta enquanto o valor digitado for invalido.
     * Lanca OperacaoCanceladaException se o usuario cancelar ou fechar o dialogo.
     */
    public static int leInt(String frase, ImageIcon icone) {
        while (true) {
            try {
                return Integer.parseInt(leString(frase, icone).trim());
            } catch (NumberFormatException e) {
                msgDeErro("Erro de Entrada", "Por favor, digite um numero inteiro valido!");
            }
        }
    }

    // Textos longos (ex.: log de batalha) vao para uma area com rolagem, para o dialogo caber na tela.
    private static Object conteudo(String frase) {
        if (frase.lines().count() <= MAX_LINHAS_SEM_ROLAGEM) {
            return frase;
        }
        JTextArea area = new JTextArea(frase);
        area.setEditable(false);
        area.setCaretPosition(0);
        JScrollPane rolagem = new JScrollPane(area);
        rolagem.setPreferredSize(new Dimension(650, 400));
        return rolagem;
    }
}
