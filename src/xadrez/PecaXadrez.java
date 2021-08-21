package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;
    protected  int contagemMovimento;



    public PecaXadrez(Tabuleiro tabuleiro,Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public int getContagemMovimento() {
        return contagemMovimento;
    }

    public  void incrementarContagemMovimento(){
        contagemMovimento++;
    }

    public  void decrementarContagemMovimento(){
        contagemMovimento--;
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
