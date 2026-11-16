package personagens;

import armas.SoproDeFogo;
import comportamentos.NaoCorre;
import comportamentos.NaoFala;
import comportamentos.VoaComAsas;

public class DragaoAlado extends Personagem {
    public DragaoAlado(String nome) {
        super(nome, 220);
        this.arma = new SoproDeFogo();
        this.comportamentoFala = new NaoFala();     // Dragão não fala
        this.comportamentoCorrer = new NaoCorre();   // Dragão não sai na carreira
        this.comportamentoVoar = new VoaComAsas();   // Dragão voa
    }

    @Override
    public String desenhar() {
        return descricao("Dragão Alado");
    }
}