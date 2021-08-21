package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao( Tabuleiro tabuleiro,Cor cor) {
        super(tabuleiro,cor);
    }


    @Override
    public boolean[][] movimentoPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao aux = new Posicao(0, 0);
        if (getCor() == Cor.BRANCO) {

            aux.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao aux2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux) && getTabuleiro().posicaoExistente(aux2) && !getTabuleiro().temPeca(aux2) && getContagemMovimento() == 0) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }

        }else {
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao aux2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux) && getTabuleiro().posicaoExistente(aux2) && !getTabuleiro().temPeca(aux2) && getContagemMovimento() == 0) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
        }



        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
