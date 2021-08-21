package xadrez;

import jogoTabuleiro.Tabuleiro;
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

    private void colocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {

        tabuleiro.colocarPecas(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicao());

    }

    private void configucaoInicial() {
        colocarNovaPeca('a', 1, new Torre(Cor.PRETO, tabuleiro));
    }

}
