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
                int tipo = Character.getNumericValue(mensagem.charAt(0));
                System.out.println(" oi");
                int filme = Character.getNumericValue(mensagem.charAt(2));
                int resp;
                
                switch (tipo) {
                    case 1 -> {
                        resp = bd.solicitarFIlme(filme);
                    }
                    default ->
                        throw new AssertionError();
                }

                String resposta = String.valueOf(resp);
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
