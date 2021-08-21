package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import pecasXadrez.*;

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
    private PecaXadrez enPassantVuneravel;


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

    public PecaXadrez getEnPassantVuneravel() {
        return enPassantVuneravel;
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

        PecaXadrez pecamovida = (PecaXadrez) tabuleiro.peca(destino);

        check = testarCheck(oponente(corJogadorAtual)) ? true : false;
        if (testarCheckMate(oponente(corJogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }
        // Jogada En passant
        if (pecamovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2   )) {
            enPassantVuneravel = pecamovida;
        } else {
            enPassantVuneravel = null;
        }


        return (PecaXadrez) pecaCapturada;

    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.paraPosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentoPossiveis();


    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.removePeca(origem);
        pecaMovida.incrementarContagemMovimento();
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.colocarPecas(pecaMovida, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        // Jogada Rock
        // Rock pequeno
        if (pecaMovida instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
            tabuleiro.colocarPecas(torre, destinoTorre);
            torre.incrementarContagemMovimento();
        }
        //Rock grande
        if (pecaMovida instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
            tabuleiro.colocarPecas(torre, destinoTorre);
            torre.incrementarContagemMovimento();
        }

        //Jogada En passant
        if (pecaMovida instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
                Posicao posicaoPeao;
                if (pecaMovida.getCor() == Cor.BRANCO) {
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                pecaCapturada = tabuleiro.removePeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasTabuleiro.remove(pecaCapturada);


            }

        }

        return pecaCapturada;
    }

    private void defazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.removePeca(destino);
        pecaMovida.decrementarContagemMovimento();
        tabuleiro.colocarPecas(pecaMovida, origem);
        if (pecaCapturada != null) {
            tabuleiro.colocarPecas(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasTabuleiro.add(pecaCapturada);
        }
        //Defazendo Rock pequeno
        if (pecaMovida instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
            tabuleiro.colocarPecas(torre, origemTorre);
            torre.decrementarContagemMovimento();
        }
        //Defazendo Rock grande
        if (pecaMovida instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
            tabuleiro.colocarPecas(torre, origemTorre);
            torre.decrementarContagemMovimento();
        }

        //Jogada En passant
        if (pecaMovida instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVuneravel) {
                PecaXadrez peao = (PecaXadrez) tabuleiro.removePeca(destino);
                Posicao posicaoPeao;
                if (pecaMovida.getCor() == Cor.BRANCO) {
                    posicaoPeao = new Posicao(3, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.colocarPecas(peao,posicaoPeao);


            }

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
        colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
    }

}
