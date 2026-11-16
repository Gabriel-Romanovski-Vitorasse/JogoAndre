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
import util.OperacaoCanceladaException;

public class TelaJogo extends JFrame {

    private Image imagemFundo;
    private List<Personagem> personagens;
    private ImageIcon iconeJogo;

    public TelaJogo(List<Personagem> personagens, ImageIcon iconeJogo) {
        this.personagens = personagens;
        this.iconeJogo = iconeJogo;

        URL urlFundo = getClass().getResource("/imagens/fundo.jpg");
        if (urlFundo != null) {
            this.imagemFundo = new ImageIcon(urlFundo).getImage();
        }
        if (iconeJogo != null) {
            setIconImage(iconeJogo.getImage());
        }

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
                }
            }
        };
        painelFundo.setBackground(new Color(30, 30, 40));
        painelFundo.setLayout(new BorderLayout());

        
        JLabel titulo = new JLabel("ARENA DE COMBATE", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        // Faixa escura atras do titulo para ele continuar legivel sobre qualquer imagem de fundo.
        titulo.setOpaque(true);
        titulo.setBackground(new Color(40, 44, 52));
        painelFundo.add(titulo, BorderLayout.NORTH);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setOpaque(false); 

        JButton btnStatus = criarBotao("1. Status e Comportamentos");
        JButton btnArmas = criarBotao("2. Trocar Arma");
        JButton btnBatalha = criarBotao("3. Iniciar Batalha (D20)");
        JButton btnRestaurar = criarBotao("4. Restaurar Vidas");
        JButton btnSair = criarBotao("Sair");

        btnStatus.addActionListener(e -> executar("Erro",
                () -> JogoDesenvolvido.testarPersonagens(personagens, iconeJogo)));
        btnArmas.addActionListener(e -> executar("Erro de Validação",
                () -> JogoDesenvolvido.trocarArma(personagens, iconeJogo)));
        btnBatalha.addActionListener(e -> executar("Erro na Batalha",
                () -> JogoDesenvolvido.simularBatalha(personagens, iconeJogo)));
        btnRestaurar.addActionListener(e -> executar("Erro",
                () -> JogoDesenvolvido.restaurarVidas(personagens, iconeJogo)));
        btnSair.addActionListener(e -> System.exit(0));

        painelBotoes.add(btnStatus);
        painelBotoes.add(btnArmas);
        painelBotoes.add(btnBatalha);
        painelBotoes.add(btnRestaurar);
        painelBotoes.add(btnSair);

        painelFundo.add(painelBotoes, BorderLayout.SOUTH);
        add(painelFundo);
    }

    // Executa a acao de um botao: cancelar um dialogo apenas volta ao menu; outros erros sao exibidos.
    private void executar(String tituloErro, Runnable acao) {
        try {
            acao.run();
        } catch (OperacaoCanceladaException ex) {
            // Usuario cancelou: nada a fazer.
        } catch (Exception ex) {
            InOut.msgDeErro(tituloErro, ex.getMessage());
        }
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