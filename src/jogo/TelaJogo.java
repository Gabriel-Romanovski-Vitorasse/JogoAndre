package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import personagens.Personagem;
import util.InOut;

public class TelaJogo extends JFrame {

    private Image imagemFundo;
    private List<Personagem> personagens;
    private ImageIcon iconeJogo;

    public TelaJogo(List<Personagem> personagens, ImageIcon iconeJogo) {
        this.personagens = personagens;
        this.iconeJogo = iconeJogo;

        
        URL urlFundo = getClass().getResource("imagens/fundo.jpg");
        if (urlFundo != null) {
            this.imagemFundo = new ImageIcon(urlFundo).getImage();
        }
        /*String caminhoDaCaverna = "/src/imagens/fundo.jpg";
        ImageIcon fundo = new ImageIcon(caminhoDaCaverna);
        JLabel painel = new JLabel(fundo, JLabel.CENTER);
        painel.setHorizontalTextPosition(JLabel.CENTER);
        painel.setVerticalTextPosition(JLabel.CENTER);*/

        configurarJanela();
    }

    private void configurarJanela() {
        setTitle("Jogo Desenvolvido - UVV");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagemFundo != null) {
                    
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                } else {
                    
                    setBackground(new Color(30, 30, 40));
                }
            }
        };
        painelFundo.setLayout(new BorderLayout());

        
        JLabel titulo = new JLabel("ARENA DE COMBATE", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        painelFundo.add(titulo, BorderLayout.NORTH);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setOpaque(false); 

        JButton btnStatus = criarBotao("1. Status e Comportamentos");
        JButton btnArmas = criarBotao("2. Trocar Arma");
        JButton btnBatalha = criarBotao("3. Iniciar Batalha (D20)");
        JButton btnSair = criarBotao("Sair");

        
        btnStatus.addActionListener(e -> {
            try {
                JogoDesenvolvido.testarPersonagens(personagens, iconeJogo);
            } catch (Exception ex) {
                InOut.msgDeErro("Erro", ex.getMessage());
            }
        });

        btnArmas.addActionListener(e -> {
            try {
                JogoDesenvolvido.trocarArma(personagens, iconeJogo);
            } catch (Exception ex) {
                InOut.msgDeErro("Erro de Validação", ex.getMessage());
            }
        });

        btnBatalha.addActionListener(e -> {
            try {
                JogoDesenvolvido.simularBatalha(personagens, iconeJogo);
            } catch (Exception ex) {
                InOut.msgDeErro("Erro na Batalha", ex.getMessage());
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        painelBotoes.add(btnStatus);
        painelBotoes.add(btnArmas);
        painelBotoes.add(btnBatalha);
        painelBotoes.add(btnSair);

        painelFundo.add(painelBotoes, BorderLayout.SOUTH);
        add(painelFundo);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setBackground(new Color(40, 44, 52));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }
}