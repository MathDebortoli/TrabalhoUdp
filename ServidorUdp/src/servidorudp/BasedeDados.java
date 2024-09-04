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
        for (int i = 0; i < 10; i++) {
            if (matriz[cliente][i] == 0) {
                return i;
            }
        }
        return 10;
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