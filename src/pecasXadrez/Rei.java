package pecasXadrez;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei( Tabuleiro tabuleiro,Cor cor) {
        super(cor, tabuleiro);
    }


    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentoPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return  mat;
    }

}
