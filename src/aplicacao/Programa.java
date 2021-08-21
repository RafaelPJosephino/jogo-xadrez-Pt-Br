package aplicacao;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;

public class Programa {
    public static void main(String[] args) {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.mostrarTabuleiro(partidaXadrez.getPecas());
    }
}
