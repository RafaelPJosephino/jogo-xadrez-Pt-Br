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
        PecaXadrez[][] pecaxadrez = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {

                pecaxadrez[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }

        return pecaxadrez;
    }

    public PecaXadrez movimentacaoXadrez( PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.paraPosicao();
        Posicao destino = posicaoDestino.paraPosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem,destino);
        Peca pecaCapturada = movimentoFeito(origem,destino);
        return (PecaXadrez) pecaCapturada;

    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.paraPosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentoPossiveis();


    }

    private Peca movimentoFeito(Posicao  origem,Posicao destino){
        Peca pecaMovida = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.colocarPecas(pecaMovida,destino);
        return pecaCapturada;
    }

    public void validarPosicaoOrigem( Posicao posicao){
        if (!tabuleiro.temPeca(posicao)){
         throw new XadrezException("Nao existe peca na posicao origem");
        }
        if (!tabuleiro.peca(posicao).exiteMovimentoPossivel()){

          throw new XadrezException("Nao exitem movimentos possiveis para a peca escolhida ");
        }

    }

    public  void validarPosicaoDestino(Posicao origem,Posicao destino){

        if (!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new XadrezException("Peca escolhida nao pode se mover para "+ destino);

        }

    }


    private void colocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {

        tabuleiro.colocarPecas(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicao());

    }

    private void configucaoInicial() {
        colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));}

}
