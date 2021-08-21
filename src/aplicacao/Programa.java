package aplicacao;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            UI.mostrarTabuleiro(partidaXadrez.getPecas());

            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.LerPosicaoXadrez(scanner);
            System.out.println();
            System.out.print("Destino: ");
            PosicaoXadrez destino = UI.LerPosicaoXadrez(scanner);

            PecaXadrez pecaCapturada = partidaXadrez.movimentacaoXadrez(origem,destino);


        }
    }

}
