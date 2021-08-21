package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;


    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }


    @Override
    public String toString() {
        return "R";
    }

    public boolean podeMover(Posicao posicao) {
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez == null || pecaXadrez.getCor() != getCor();
    }

    private boolean testeTorreRock(Posicao posicao) {
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez != null && pecaXadrez instanceof Torre && pecaXadrez.getCor() == getCor() && pecaXadrez.getContagemMovimento() == 0;

    }

    @Override
    public boolean[][] movimentoPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao aux = new Posicao(0, 0);

        // cima
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // baixo
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // esquerda
        aux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // direita
        aux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // cima-esquerda
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // cima-direita
        aux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // biaxo-esquerda
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }
        // baixo-direita
        aux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(aux) && podeMover(aux)) {
            mat[aux.getLinha()][aux.getColuna()] = true;
        }

        //Jogada Rock

        if (getContagemMovimento() == 0 && !partidaXadrez.getCheck()) {
            //Roque pequeno
            Posicao auxTorre = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeTorreRock(auxTorre)) {
                Posicao aux1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao aux2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(aux1) == null && getTabuleiro().peca(aux2) == null) {
                    mat[aux2.getLinha()][aux2.getColuna()] = true;
                }
            }
            //Roque grande
            Posicao auxTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testeTorreRock(auxTorre)) {
                Posicao aux1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao aux2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao aux3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(aux1) == null && getTabuleiro().peca(aux2) == null && getTabuleiro().peca(aux3) == null) {
                    mat[aux2.getLinha()][aux2.getColuna()] = true;
                }

            }

        }
        return mat;
    }
}
