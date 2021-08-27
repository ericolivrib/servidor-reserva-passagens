package http;

import controller.ReservaPoltrona;
import model.*;
import view.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static http.RequestHttp.lerRequisicao;

public class ConnectionHttp implements Runnable {

    private final Socket conexao;
    private final Onibus onibus;
    private final ArrayList<Reserva> reservas;

    public ConnectionHttp(Socket conexao, Onibus onibus, ArrayList<Reserva> reservas) {
        this.conexao = conexao;
        this.onibus = onibus;
        this.reservas = reservas;
    }

    @Override
    public void run() {
        try (InputStream entrada = conexao.getInputStream()) {
            RequestHttp req = lerRequisicao(entrada);

            System.out.println(req);

            ResponseHttp resp;
            String html;

            if (req.getRecurso().equals("/")) {
                System.out.println("[CLIENTE] Se conectou à página.\n");

                resp = new ResponseHttp(200, "OK");
                html = new PaginaWeb().getHtml(onibus, reservas);

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));

                System.out.println(resp);
            }

            else if (req.getRecurso().contains("/reservar")) {
                System.out.println("[CLIENTE] Requisitou uma reserva.\n");

                String[] dadosReserva = req.getRecurso().split("[?,=,&]");
                // retira o simbolo "+" que separa o nome do sobrenome
                String[] nome = dadosReserva[2].split("\\+");
                StringBuilder nomeSeparado = new StringBuilder();

                // adiciona espaços entre o nome e sobrenome
                for (String s : nome) {
                    nomeSeparado.append(s).append(" ");
                }

                Passageiro passageiro = new Passageiro(nomeSeparado.toString(), conexao.getInetAddress().toString());
                Poltrona poltrona = new Poltrona(Integer.parseInt(dadosReserva[4]));

                new Thread(new ReservaPoltrona(onibus, reservas, poltrona.getNumero(), passageiro)).start();
                Reserva reserva = new Reserva(passageiro, poltrona, LocalDateTime.now());
                html = new PaginaWeb().head + new PaginaWeb().nav + new MensagemResposta().getHtml(reserva, passageiro.getNome(), poltrona.getNumero());
                resp = new ResponseHttp(200, "OK");

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));
            }

            else if (req.getRecurso().equals("/index?")) {
                System.out.println("[CLIENTE] A mensagem de resposta foi mostrada.\n");

                resp = new ResponseHttp(200, "OK");
                html = new PaginaWeb().getHtml(onibus, reservas);

                resp.setCabecalho(("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=UTF-8\n\n").getBytes(StandardCharsets.UTF_8));
                resp.setConteudo(html.getBytes(StandardCharsets.UTF_8));
            }

            else {
                System.out.println("[CLIENTE] Acessou uma página inválida.\n");

                resp = new ResponseHttp(404, "Not Found");
                String notFound = new NotFound().getHtml();
                html = new PaginaWeb().head + new PaginaWeb().nav + notFound;

                resp.setCabecalho(("HTTP/1.1 404 Not Found\n\n" + html).getBytes(StandardCharsets.UTF_8));
            }

            OutputStream saida = conexao.getOutputStream();
            resp.escreverSaida(saida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
