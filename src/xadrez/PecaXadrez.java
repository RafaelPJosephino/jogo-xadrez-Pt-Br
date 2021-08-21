package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;

    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.dePosicao(posicao);
    }

    public Cor getCor() {
        return cor;
    }

    protected boolean existePecaOponente(Posicao posicao){

        PecaXadrez pecaXadrez =(PecaXadrez)getTabuleiro().peca(posicao);

        return pecaXadrez != null && pecaXadrez.getCor() != cor;

    }

}
