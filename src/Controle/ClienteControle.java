package Controle;

import ClienteVisao.ClienteVisao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ClienteControle extends Thread {
    
    ClienteVisao cv;
    DatagramSocket clienteSocket;
    DatagramPacket receivePacket;
    String nick;
    InetAddress IP; 
    int porta;
    boolean loop = true;

    public ClienteControle(String nick, InetAddress IP, int porta) {
        this.nick = nick;
        this.IP = IP;
        this.porta = porta;
        cv = new ClienteVisao(this,nick,IP,porta);
        cv.setVisible(true);
        this.start();
    }
    
    public void run() {
        try {
            clienteSocket = new DatagramSocket();
            enviarMsg("1#"+nick);
            receberMsg();
        } catch (SocketException ex) {
            System.out.println("Erro no cliente: "+ex.getMessage());
        }
    }

    public void enviarMsg(String op) {
        try{
            byte[] sendData = new byte[1000];
            sendData = op.getBytes();
            System.out.println("Cliente envia: "+op);
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, porta);
            clienteSocket.send(sendPacket);
        }catch(Exception e){
            System.out.println("Erro no cliente: "+e.getMessage());
        }
    }

    private void receberMsg() {
        try{
            while(loop){
                byte[] receiveData = new byte[1000];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                clienteSocket.receive(receivePacket);
                String mensagem = new String(receivePacket.getData()).trim();
                System.out.println("Cliente recebe: "+mensagem);
                
                String [] x = mensagem.split("#");
                int op = Integer.parseInt(x[0]);
                
                switch (op) {
                    case 2:
                        //RECEBE 2#IPCLIENTE1#PORTACLIENTE1#NOMECLIENTE1#IPCLIENTE2#PORTACLIENTE2#NOMECLIENTE2...
                        cv.atualizarLista(mensagem);
                    break;

                    case 4:
                        //RECEBE 4#IPCLIENTEORIGEM#PORTACLIENTEORIGEM#MENSAGEM
                        cv.adicionarMensagem(mensagem);
                    break;

                    case 5:
                        //RECEBE 5#
                        loop = false;
                        clienteSocket.close();
                    break;

                    default:
                        System.out.println("Cliente: opçao invalida");
                    break;
                }
            }
        }catch(IOException ioe){
            System.out.println("Erro no cliente: "+ioe.getMessage());
        }
    }
    
}
