package jogoTabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {

        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("Erro na criacao do tabuleiro: Necessariominimo 1 Linha e 1 coluna ");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];

    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna) {
        if (!posicaoExistente(linha,coluna)){
            throw new TabuleiroException("Posicao nao existente no tabuleiro");
        }
        return pecas[linha][coluna];

    }

    public Peca peca(Posicao posicao) {
        if (!posicaoExistente(posicao)){
            throw new TabuleiroException("Posicao nao existente no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];

    }

    public void colocarPecas(Peca peca, Posicao posicao) {
        if (temPeca(posicao)){
            throw new TabuleiroException("Ja exite uma peca na posicao: "+posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public Peca removePeca( Posicao posicao){
        if (!posicaoExistente(posicao)){
            throw new TabuleiroException("Posicao nao existente no tabuleiro");
        }
        if (peca(posicao)== null){
            return null;
        }
        Peca peca = peca(posicao);
        peca.posicao =null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return peca;

    }

    private boolean posicaoExistente(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExistente(Posicao posicao) {

        return posicaoExistente(posicao.getLinha(), posicao.getColuna());
    }

    public boolean temPeca(Posicao posicao) {
        if (!posicaoExistente(posicao)){
            throw new TabuleiroException("Erro: Posicao nao existente no tabuleiro!");
        }
        return peca(posicao) != null;

    }


}
