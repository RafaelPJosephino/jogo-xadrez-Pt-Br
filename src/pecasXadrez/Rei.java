package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei( Tabuleiro tabuleiro,Cor cor) {
        super(tabuleiro,cor );
    }


    @Override
    public String toString() {
        return "R";
    }

    public boolean podeMover(Posicao posicao){
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez == null || pecaXadrez.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentoPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao aux = new Posicao(0,0);

        // cima
        aux.setValores(posicao.getLinha()-1,posicao.getColuna());
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // baixo
        aux.setValores(posicao.getLinha()+1,posicao.getColuna());
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // esquerda
        aux.setValores(posicao.getLinha(),posicao.getColuna()-1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // direita
        aux.setValores(posicao.getLinha(),posicao.getColuna()+1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // cima-esquerda
        aux.setValores(posicao.getLinha()-1,posicao.getColuna()-1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // cima-direita
        aux.setValores(posicao.getLinha()-1,posicao.getColuna()+1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // biaxo-esquerda
        aux.setValores(posicao.getLinha()+1,posicao.getColuna()-1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }
        // baixo-direita
        aux.setValores(posicao.getLinha()+1,posicao.getColuna()+1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
            mat[aux.getLinha()][aux.getColuna()] =true;
        }






        return  mat;
    }

}
