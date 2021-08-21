package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez  {





        public Cavalo(Tabuleiro tabuleiro, Cor cor) {
            super(tabuleiro,cor );
        }


        @Override
        public String toString() {
            return "C";
        }

        public boolean podeMover(Posicao posicao){
            PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
            return pecaXadrez == null || pecaXadrez.getCor() != getCor();
        }

        @Override
        public boolean[][] movimentoPossiveis() {
            boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
            Posicao aux = new Posicao(0,0);

            // direita2-cima1
            aux.setValores(posicao.getLinha()-1,posicao.getColuna()-2);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // cima2-esquerda1
            aux.setValores(posicao.getLinha()-2,posicao.getColuna()-1);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // cima2-direita1
            aux.setValores(posicao.getLinha()-2,posicao.getColuna()+1);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // direita2-cima1
            aux.setValores(posicao.getLinha()-1,posicao.getColuna()+2);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // direita2-baixo1
            aux.setValores(posicao.getLinha()+1,posicao.getColuna()+2);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // baixo2-direita1
            aux.setValores(posicao.getLinha()+2,posicao.getColuna()+1);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // baixo2-esquerda1
            aux.setValores(posicao.getLinha()+2,posicao.getColuna()-1);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }
            // esquerda2-baixo1
            aux.setValores(posicao.getLinha()+1,posicao.getColuna()-2);
            if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)){
                mat[aux.getLinha()][aux.getColuna()] =true;
            }






            return  mat;
        }





}
