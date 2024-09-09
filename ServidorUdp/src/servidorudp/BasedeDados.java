//Leandro de Souza Amorim e Matheus Francisco Debortoli Silva
//SAlve
package servidorudp;

import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class BasedeDados {

    private int matriz[][] = null;

    public BasedeDados() {
        matriz = new int[10][20];

        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 20; p++) {
                matriz[i][p] = 0;
            }
        }
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
    int pessoaMaisProxima = -1;
    double menorDistancia = Double.MAX_VALUE; // Inicializa com o maior valor possível
    double distanciaEuclidiana;
    double diferenca;

    for (int i = 0; i < 10; i++) {
        if (i == cliente) {
            continue; // Pula o cálculo para o próprio cliente
        }

        boolean pessoaTemAvaliacoes = false;
        distanciaEuclidiana = 0;

        // Calcula a distância euclidiana apenas para pessoas com avaliações
        for (int j = 0; j < 20; j++) {
            if (matriz[i][j] != 0) { // Verifica se a pessoa tem uma avaliação
                pessoaTemAvaliacoes = true;
                diferenca = matriz[cliente][j] - matriz[i][j];
                distanciaEuclidiana += diferenca * diferenca;
            }
        }

        // Se a pessoa tem avaliações suficientes, calcula a raiz quadrada e compara
        if (pessoaTemAvaliacoes) {
            distanciaEuclidiana = Math.sqrt(distanciaEuclidiana);
            System.out.println("A distância euclidiana entre cliente " + cliente + " e pessoa " + i + " foi: " + distanciaEuclidiana);

            if (distanciaEuclidiana < menorDistancia) {
                menorDistancia = distanciaEuclidiana;
                pessoaMaisProxima = i;
            }
        } else {
            System.out.println("Pessoa " + i + " não tem avaliações suficientes.");
        }
    }

    if (pessoaMaisProxima == -1) {
        System.out.println("Nenhuma pessoa com avaliações suficientes foi encontrada.");
    } else {
        System.out.println("Pessoa mais próxima: " + pessoaMaisProxima);
    }
    System.out.println("Cliente: " + cliente);
    return receberRecomendacao(cliente, pessoaMaisProxima);
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
                msg += i + ";";
                msg += matriz[cliente][i] + ";";
            }
        }
        if (!msg.isEmpty()) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
