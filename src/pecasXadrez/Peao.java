package pecasXadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
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
                mat[aux2.getLinha()][aux2.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }

            //Jogada En passant Branca
            if (posicao.getLinha() ==3 ){
                Posicao esqueda = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
                if (getTabuleiro().posicaoExistente(esqueda) && existePecaOponente(esqueda) && getTabuleiro().peca(esqueda) == partidaXadrez.getEnPassantVuneravel()){
                    mat[esqueda.getLinha()-1][esqueda.getColuna()]=true;
                }
                if (getTabuleiro().posicaoExistente(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVuneravel()){
                    mat[direita.getLinha()-1][direita.getColuna()]=true;
                }
            }

        } else {
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao aux2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(aux) && !getTabuleiro().temPeca(aux) && getTabuleiro().posicaoExistente(aux2) && !getTabuleiro().temPeca(aux2) && getContagemMovimento() == 0) {
                mat[aux.getLinha()][aux.getColuna()] = true;
                mat[aux2.getLinha()][aux2.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }
            aux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(aux) && existePecaOponente(aux)) {
                mat[aux.getLinha()][aux.getColuna()] = true;
            }

            //Jogada En passant Preta
            if (posicao.getLinha() == 4 ){
                Posicao esqueda = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
                if (getTabuleiro().posicaoExistente(esqueda) && existePecaOponente(esqueda) && getTabuleiro().peca(esqueda) == partidaXadrez.getEnPassantVuneravel()){
                    mat[esqueda.getLinha()+1][esqueda.getColuna()]=true;
                }
                if (getTabuleiro().posicaoExistente(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVuneravel()){
                    mat[direita.getLinha()+1][direita.getColuna()]=true;
                }
            }

        }


        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
