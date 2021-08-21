package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Dama extends PecaXadrez {


    public Dama(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public boolean[][] movimentoPossiveis() {


        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao aux = new Posicao(0, 0);

        //cima
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setLinha(aux.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        //esquerda
        aux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setColuna(aux.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        //direita
        aux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setColuna(aux.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }

        //cima
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setLinha(aux.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        //cima-esquerda
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setValores(aux.getLinha() - 1, aux.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        //cima-direita
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setValores(aux.getLinha() - 1, aux.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        //baixo-esquerda
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setValores(aux.getLinha() + 1, aux.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }

        //baixo-direita
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setValores(aux.getLinha() + 1, aux.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }

        return mat;

    }
}
