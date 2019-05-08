package Controle;

import ServidorVisao.ServidorVisao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorControle extends Thread {

    ServidorVisao server;
    DatagramSocket serverSocket;
    DatagramPacket receivePacket;
    DatagramPacket sendPacket;
    int porta;
    String lista;

    public ServidorControle(int porta) {
        this.porta = porta;
        server = new ServidorVisao();
        server.setTitle("Servidor recebendo conexões na porta: "+porta);
        server.mostraMsg("Servidor recebendo conexões na porta: "+porta);
        server.setVisible(true);
        this.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new DatagramSocket(porta);
            
            while (true) {
                byte[] receiveData = new byte[1000];
                byte[] sendData = new byte[1000];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                serverSocket.receive(receivePacket);
                String mensagem = new String(receivePacket.getData()).trim();
                String IP = receivePacket.getAddress().toString().replace("/", "");
                int portaReceive = receivePacket.getPort();
                
                server.mostraMsg("Servidor recebe: "+mensagem);
                
                String [] x = mensagem.split("#");
                int op = Integer.parseInt(x[0]);

                switch (op) {
                    case 1:
                        //recebe 1#NICK
                        lista = server.adicionaLista(mensagem+"#"+IP+"#"+portaReceive);
                        broadcast(lista,null);
                    break;

                    case 3:
                        //RECEBE 3#IPCLIENTEDESTINO#PORTACLIENTEDESTINO#MENSAGEM  OU  3#999.999.999.999#99999#mensagem para broadcast
                        //retorna 4#IPCLIENTEORIGEM#PORTACLIENTEORIGEM#MENSAGEM
                        String protocolo = ("4#"+IP+"#"+portaReceive+"#"+x[3]);
                        
                        if(x[1].equals("999.999.999.999") && x[2].equals("99999")){
                            lista = server.atualizaLista();
                            protocolo = ("4#"+IP+"#"+portaReceive+"#(broadcast) "+x[3]);
                            broadcast(lista,protocolo);
                        }
                        else{
                            InetAddress IPdestino = InetAddress.getByName(x[1]);
                            int portaDestino = Integer.parseInt(x[2]);
                            protocolo = ("4#"+IP+"#"+portaReceive+"#(privado) "+x[3]);
                            sendData = protocolo.getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPdestino, portaDestino);
                            serverSocket.send(sendPacket);
                            server.mostraMsg("Servidor envia: "+protocolo);
                        }
                    break;

                    case 5:
                        //recebe 5#
                        server.mostraMsg("Servidor envia: "+mensagem);
                        
                        sendData = mensagem.getBytes();
                        sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(IP), portaReceive);
                        serverSocket.send(sendPacket);
                        
                        lista = server.removeCliente(IP,portaReceive);
                        if(!"2".equals(lista)){
                            broadcast(lista,null);
                        }
                    break;

                    default:
                        System.out.println("Servidor: opçao invalida");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: "+e.getMessage());
        }
    }
    
    
    public void broadcast(String list,String msg) throws IOException{
        byte[] sendData = new byte[1000];
        
        if (msg!=null){
            sendData = msg.getBytes();
            server.mostraMsg("Servidor envia broadcast: "+msg);
        }
        else{
            sendData = list.getBytes();
            server.mostraMsg("Servidor envia broadcast: "+list);
        }
        
        String [] x = list.split("#");
        int tam = x.length;
        String cliente[] = new String[tam];
        
        int i=1;
        while(i<tam){
            for(int j=0; j<3; j++){
                cliente[j] = x[i];
                i++;
            }
            InetAddress IP = InetAddress.getByName(cliente[0].replace("/", "")); //ou (cliente[0].replace("/", ""))
            int portaBroad = Integer.parseInt(cliente[1]);
            sendPacket = new DatagramPacket(sendData, sendData.length, IP, portaBroad);
            serverSocket.send(sendPacket);
        }
    }
   
}
