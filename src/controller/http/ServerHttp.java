package controller.http;

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

    private final int porta;

    public ServerHttp(int porta) {
        this.porta = porta;
    }

    /**
     * Aguarda até que haja alguma conexão.
     * @throws IOException
     */
    public void conectar() throws IOException {

        ServerSocket servidor = new ServerSocket(porta);

        System.out.println("Servidor conectado na porta " + porta + "! \nAguardando conexão...\n");

        Onibus onibus = new Onibus();
        ArrayList<Reserva> reservas = new ArrayList<>();

        while (true) {
            Socket conexao = servidor.accept();
            String cliente = conexao.getInetAddress().getHostAddress();

            new Thread(new ConnectionHttp(conexao, onibus, reservas)).start();
        }
    }
}
