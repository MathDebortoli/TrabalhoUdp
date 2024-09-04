/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clienteudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class ClienteUdp {

    private String nomeDNS;
    private int serverPort;
    private byte[] meuIP;

    public ClienteUdp() {
        try {
            InetAddress endereco = InetAddress.getLocalHost();
            nomeDNS = endereco.getHostName();
            meuIP = endereco.getAddress();
        } catch (UnknownHostException e) {
            System.out.println("Host Desconhecido: " + e.getMessage());
        }
        serverPort = 6789;
    }

    public ClienteUdp(String nomeDNSservidor) {
        nomeDNS = "matheus-pc"; //nome do computador que o servidor é executado
        meuIP = null;
        serverPort = 6789;
    }

    public String enviaMensagem(String mensagem) {
        DatagramSocket aSocket = null;
        String resposta = new String("");

        try {
            aSocket = new DatagramSocket();
            byte[] m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName(nomeDNS);

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[600];

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            aSocket.receive(reply);

            resposta = new String(reply.getData());
            resposta = resposta + "\n";

        } catch (UnknownHostException ex) {
            System.out.println("Socket: " + ex.getMessage());
        } catch (SocketException ex) {
            System.out.println("Input outPut: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
        return resposta;
    }

    public String getNomeDNS() {
        return nomeDNS;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getMeuIP() {
        String s = new String(meuIP);
        return s;
    }

}
