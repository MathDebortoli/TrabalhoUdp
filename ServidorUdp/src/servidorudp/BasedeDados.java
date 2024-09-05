/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidorudp;

import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class BasedeDados {

    private ArrayList lista = null;
    private int matriz[][] = null;

    public BasedeDados() {
        lista = new ArrayList();
        matriz = new int[10][20];

        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 20; p++) {
                matriz[i][p] = 0;
            }
        }
    }

    public void insere(String msg) {
        lista.add(msg.trim());
    }

    public int solicitarFIlme(int cliente) {
        for (int i = 0; i < 20; i++) {
            if (matriz[cliente][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public void inserirNota(int cliente, int filme, int nota) {
        matriz[cliente][filme] = nota;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 20; p++) {
                System.out.print(matriz[i][p] + "|");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public int calcularDistEucl(int cliente) {
        int pessoa = -1, i;
        double soma = 0, menor = 0;

        for (i = 0; i < 10; i++) {
            if (i == cliente) {
                continue;
            }
            for (int j = 0; j < 20; j++) {
                soma += Math.pow(2, matriz[cliente][j] - matriz[i][j]); //ELeva todos ao quadrado e soma
            }
            soma = Math.sqrt(soma); //Tira raiz quadrada
            if (menor == 0 || soma < menor) {
                menor = soma;
                soma = 0;
                pessoa = i;
            }
        }
        return receberRecomendacao(cliente,pessoa);
    }

    public int receberRecomendacao(int cliente, int pessoaParecida) {
        int notamaior = 0, filme = -1;

        if (pessoaParecida == -1) {
            return -1;
        }

        for (int i = 0; i < 20; i++) {
            if (matriz[cliente][i] == 0 && matriz[pessoaParecida][i] != 0) {
                if (matriz[pessoaParecida][i] > notamaior) {
                    notamaior = matriz[pessoaParecida][i];
                    filme = i;
                }
            }
        }
        return filme;
    }

    public String listaRecomendacao(int cliente) {
        String msg = "";
        for (int i = 0; i < 20; i++) {
            if (matriz[cliente][i] != 0) {
                msg += i;
                msg += matriz[cliente][i];
            }
        }
        return msg;
    }

    public String le() {
        String s = "\n";
        int fim = lista.size();
        for (int pos = 0; pos < fim; pos++) {
            s = s + "[" + (pos + 1) + "] " + (String) lista.get(pos) + "\n";
        }
        return s;
    }

}
