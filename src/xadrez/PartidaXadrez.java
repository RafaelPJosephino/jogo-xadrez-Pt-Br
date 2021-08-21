package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import pecasXadrez.Peao;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PartidaXadrez {

    private int turno;
    private Cor corJogadorAtual;
    private Tabuleiro tabuleiro;
    private List<Peca> pecasTabuleiro = new ArrayList<>();
    ;
    private List<Peca> pecasCapturadas = new ArrayList<>();
    private boolean check;
    private boolean checkMate;


    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        corJogadorAtual = Cor.BRANCO;

        configucaoInicial();
    }

    public int getTurno() {
        return turno;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public boolean getCheck() {
        return check;
    }

    public Cor getCorJogadorAtual() {
        return corJogadorAtual;
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

    public PecaXadrez movimentacaoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.paraPosicao();
        Posicao destino = posicaoDestino.paraPosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        if (testarCheck(corJogadorAtual)) {
            defazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Nao e possivel se colocar em check.");
        }
        check = testarCheck(oponente(corJogadorAtual)) ? true : false;
        if (testarCheckMate(oponente(corJogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }
        return (PecaXadrez) pecaCapturada;

    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.paraPosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentoPossiveis();


    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca pecaMovida = tabuleiro.removePeca(origem);
        ((PecaXadrez)pecaMovida).incrementarContagemMovimento();
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.colocarPecas(pecaMovida, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }

    private void defazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca pecaMovida = tabuleiro.removePeca(destino);
        ((PecaXadrez)pecaMovida).decrementarContagemMovimento();
        tabuleiro.colocarPecas(pecaMovida, origem);
        if (pecaCapturada != null) {
            tabuleiro.colocarPecas(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasTabuleiro.add(pecaCapturada);
        }
    }

    public void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.temPeca(posicao)) {
            throw new XadrezException("Nao existe peca na posicao origem");
        }
        if (corJogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new XadrezException("Peca escolhida e do adversario");
        }
        if (!tabuleiro.peca(posicao).exiteMovimentoPossivel()) {

            throw new XadrezException("Nao exitem movimentos possiveis para a peca escolhida ");
        }

    }

    public void validarPosicaoDestino(Posicao origem, Posicao destino) {

        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("Peca escolhida nao pode se mover para " + destino);

        }

    }

    private void proximoTurno() {
        turno++;
        corJogadorAtual = (corJogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> pecas = pecasTabuleiro.stream().filter(peca -> ((PecaXadrez) peca).getCor() == cor).collect(Collectors.toList());

        for (Peca peca : pecas) {
            if (peca instanceof Rei) {
                return (PecaXadrez) peca;
            }
        }
        throw new IllegalStateException("nao existe o rei da cor: " + cor);
    }

    private boolean testarCheck(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
        List<Peca> pecasOponentes = pecasTabuleiro.stream().filter(peca -> ((PecaXadrez) peca).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca peca : pecasOponentes) {
            boolean[][] mat = peca.movimentoPossiveis();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()] == true) {
                return true;
            }
        }
        return false;
    }

    private boolean testarCheckMate(Cor cor) {
        if (!testarCheck(cor)) {
            return false;
        }
        List<Peca> pecaList = pecasTabuleiro.stream().filter(peca -> ((PecaXadrez) peca).getCor() == cor).collect(Collectors.toList());
        for (Peca peca : pecaList) {
            boolean[][] mat = peca.movimentoPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez) peca).getPosicaoXadrez().paraPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = fazerMovimento(origem, destino);
                        boolean testarCheck = testarCheck(cor);
                        defazerMovimento(origem, destino, pecaCapturada);
                        if (!testarCheck) {
                            return false;
                        }
                    }

                }
            }


        }
        return true;


    }


    private void colocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {

        tabuleiro.colocarPecas(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicao());
        pecasTabuleiro.add(pecaXadrez);

    }

    private void configucaoInicial() {
        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
    }

}
