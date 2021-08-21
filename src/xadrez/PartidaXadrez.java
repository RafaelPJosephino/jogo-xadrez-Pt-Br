package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;


    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        configucaoInicial();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] pecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {

                pecas[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }

        return pecas;
    }

    private void  configucaoInicial(){
        tabuleiro.colocarPecas(new Torre(Cor.BRANCO,tabuleiro),new Posicao(0,0));
        tabuleiro.colocarPecas(new Torre(Cor.BRANCO,tabuleiro),new Posicao(0,7));
        tabuleiro.colocarPecas(new Torre(Cor.PRETO,tabuleiro),new Posicao(7,0));
        tabuleiro.colocarPecas(new Torre(Cor.PRETO,tabuleiro),new Posicao(7,7));
        tabuleiro.colocarPecas(new Rei(Cor.BRANCO,tabuleiro),new Posicao(0,4));
        tabuleiro.colocarPecas(new Rei(Cor.BRANCO,tabuleiro),new Posicao(7,4));
    }

}
