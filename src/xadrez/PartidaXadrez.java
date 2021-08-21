package xadrez;

import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;


    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
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
}
