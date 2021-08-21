package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentoPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

         //cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha()-1);
        }
        if (getTabuleiro().posicaoExistente(p)&& existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()]=true;
        }
        //esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna()-1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExistente(p)&& existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()]=true;
        }
        //direita
        p.setValores(posicao.getLinha(), posicao.getColuna()+1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna()+1);
        }
        if (getTabuleiro().posicaoExistente(p)&& existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()]=true;
        }

        //cima
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha()+1);
        }
        if (getTabuleiro().posicaoExistente(p)&& existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()]=true;
        }


        return mat;
    }
}
