package aplicacao;

import jogoTabuleiro.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            try {
                UI.limparTela();
                UI.mostrarTabuleiro(partidaXadrez.getPecas());

                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.LerPosicaoXadrez(scanner);
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.LerPosicaoXadrez(scanner);

                PecaXadrez pecaCapturada = partidaXadrez.movimentacaoXadrez(origem, destino);
            }catch (XadrezException e ){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }

}
