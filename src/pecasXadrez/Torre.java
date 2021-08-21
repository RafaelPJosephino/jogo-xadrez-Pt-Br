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

        Posicao aux = new Posicao(0, 0);

         //cima
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setLinha(aux.getLinha()-1);
        }
        if (getTabuleiro().posicaoExistente(aux)&& existePecaOponente(aux)){
            mat[aux.getLinha()][aux.getColuna()]=true;
        }
        //esquerda
        aux.setValores(posicao.getLinha(), posicao.getColuna()-1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setColuna(aux.getColuna()-1);
        }
        if (getTabuleiro().posicaoExistente(aux)&& existePecaOponente(aux)){
            mat[aux.getLinha()][aux.getColuna()]=true;
        }
        //direita
        aux.setValores(posicao.getLinha(), posicao.getColuna()+1);
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setColuna(aux.getColuna()+1);
        }
        if (getTabuleiro().posicaoExistente(aux)&& existePecaOponente(aux)){
            mat[aux.getLinha()][aux.getColuna()]=true;
        }

        //cima
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
            aux.setLinha(aux.getLinha()+1);
        }
        if (getTabuleiro().posicaoExistente(aux)&& existePecaOponente(aux)){
            mat[aux.getLinha()][aux.getColuna()]=true;
        }


        return mat;
    }
}
