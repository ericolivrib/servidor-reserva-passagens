package http;

import controller.EscreveLog;
import model.Onibus;
import model.Reserva;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHttp {

    public static void main(String[] args) throws IOException {
        new ServerHttp(8081).conectar();
    }

    private final int porta;

    public ServerHttp(int porta) {
        this.porta = porta;
    }

    public void conectar() throws IOException {
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Aguardando conexão...\n");
        Onibus onibus = new Onibus();
        ArrayList<Reserva> reservas = new ArrayList<>();
        new Thread(new EscreveLog.Consumidor()).start();

        while (true) {
            Socket conexao = servidor.accept();
            new Thread(new ConnectionHttp(conexao, onibus, reservas)).start();
        }
    }
}
