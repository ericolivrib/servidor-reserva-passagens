package http;

import model.Onibus;
import model.Reserva;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHttp {

    public static void main(String[] args) throws IOException {
        new ServerHttp(80).conectar();
    }

    private int porta;

    public ServerHttp(int porta) {
        setPorta(porta);
    }

    /**
     * Aguarda até que haja alguma conexão.
     * @throws IOException
     */
    public void conectar() throws IOException {

        ServerSocket servidor = new ServerSocket(getPorta());

        System.out.println("Servidor conectado na porta " + getPorta() + "! \nAguardando conexão...\n");

        while (true) {
            Socket conexao = servidor.accept();

            System.out.println("O cliente " + conexao.getInetAddress().getHostAddress() + " se conectou!\n");
            new Thread(new ConnectionHttp(conexao, new Onibus(), new ArrayList<Reserva>())).start();
        }
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
