/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidorudp;

import java.net.*;
import java.io.*;

/**
 *
 * @author matheus
 */
public class ServidorUdp {

    private static BasedeDados bd = null;

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        bd = new BasedeDados();

        try {
            // ================== Cliente Recebendo Mensagem
            aSocket = new DatagramSocket(6789);
            while (true) {
                byte[] buffer = new byte[600];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                // =================== Servidor Processa a mensagem
                String mensagem = new String(request.getData());
                String resposta ="";

                String[] partes = mensagem.split(";");
                int tipo, filme, cliente, nota, resp = 0;
                tipo = Integer.parseInt(partes[0].trim());

                switch (tipo) {
                    case 1 -> {
                        cliente = Integer.parseInt(partes[1].trim());
                        resp = bd.solicitarFIlme(cliente);
                        resposta = String.valueOf(resp);
                    }
                    case 2 -> {
                        filme = Integer.parseInt(partes[2].trim());
                        cliente = Integer.parseInt(partes[1].trim());
                        nota = Integer.parseInt(partes[3].trim());
                        bd.inserirNota(cliente, filme, nota);
                        bd.imprimirMatriz();
                        resposta = String.valueOf(resp);
                    }
                    case 3 -> {
                        // blabla
                    }
                    case 4 -> {
                        cliente = Integer.parseInt(partes[1].trim());
                        resposta = bd.listaRecomendacao(cliente).trim();
                    }
                    default ->
                        throw new AssertionError();
                }

                byte[] todasMSg = resposta.getBytes();

                // =================== Envio da Mensagem Resposta
                DatagramPacket reply = new DatagramPacket(todasMSg, todasMSg.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException ex) {
            System.out.println("Servidor - Socket: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Servidor - Input OutPut: " + ex.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }

}
