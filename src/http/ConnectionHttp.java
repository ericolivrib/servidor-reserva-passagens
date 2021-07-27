package http;

import model.Onibus;
import model.Reserva;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ConnectionHttp implements Runnable {

    private Socket conexao;
    private Onibus onibus;
    private ArrayList<Reserva> reservas;

    public ConnectionHttp(Socket conexao, Onibus onibus, ArrayList<Reserva> reservas) {
        setConexao(conexao);
        setOnibus(onibus);
        setReservas(reservas);
    }

    @Override
    public void run() {

        try (InputStream entrada = getConexao().getInputStream()) {

            RequestHttp req = new RequestHttp().lerRequisicao(entrada);

            ResponseHttp resp;

            if (req.getRecurso().equals("/")) {
                resp = new ResponseHttp(req.getProtocolo(), 200, "OK");

                String html = "<html><body><h1>Conectado!</h1></body></html>";

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));

                System.out.println(resp);
            }

            else {
                resp = new ResponseHttp(req.getProtocolo(), 404, "Not Found");

                String html = "<html><body><h1>Página não encontrada!</h1></body></html>";

                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));
                resp.setCabecalho(("HTTP/1.1 404 Not Found\n\n" + html).getBytes(StandardCharsets.UTF_8));
            }

            OutputStream saida = getConexao().getOutputStream();
            resp.escreverSaida(saida);
        } catch (IOException e) {
            try {
                getConexao().close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Socket getConexao() {
        return conexao;
    }

    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
}
