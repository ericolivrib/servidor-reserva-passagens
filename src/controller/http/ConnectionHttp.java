package controller.http;

import controller.ReservaPoltrona;
import model.Onibus;
import model.Passageiro;
import model.Poltrona;
import model.Reserva;
import view.PaginaWeb;

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
        this.conexao = conexao;
        this.onibus = onibus;
        this.reservas = reservas;
    }

    @Override
    public void run() {

        try (InputStream entrada = conexao.getInputStream()) {

            RequestHttp req = new RequestHttp().lerRequisicao(entrada);
            System.out.println(req);

            ResponseHttp resp;

            String html;

            if (req.getRecurso().equals("/")) {

                resp = new ResponseHttp(req.getProtocolo(), 200, "OK");

                html = new PaginaWeb().getHtml(onibus, reservas, "");

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));

                System.out.println(resp);
            }

            else if (req.getRecurso().contains("/reservar")) {

                String[] dadosReserva = req.getRecurso().split("[?,=,&]");

                Passageiro passageiro = new Passageiro(dadosReserva[2], conexao.getInetAddress().toString());
                Poltrona poltrona = new Poltrona(Integer.parseInt(dadosReserva[4]));

                new Thread(new ReservaPoltrona(onibus, reservas, poltrona.getNumero(), passageiro)).start();

                String retorno;
                boolean falhou = poltrona.isLivre();

                if (falhou) {
                    retorno = "Ops";
                } else {
                    retorno = "OK";
                }

                html = new PaginaWeb().getHtml(onibus, reservas, retorno);
                retorno = "";

                resp = new ResponseHttp(req.getProtocolo(), 200, "OK");

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));
            }

            else {
                resp = new ResponseHttp(req.getProtocolo(), 404, "Not Found");

                html = "<html><body><h1>Página não encontrada!</h1></body></html>";

                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));
                resp.setCabecalho(("HTTP/1.1 404 Not Found\n\n" + html).getBytes(StandardCharsets.UTF_8));
            }

            OutputStream saida = conexao.getOutputStream();
            resp.escreverSaida(saida);
        } catch (IOException e) {
            try {
                conexao.close();
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
