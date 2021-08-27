package http;

import java.io.IOException;
import java.io.InputStream;

public class RequestHttp {

    private String metodo;
    private String recurso;
    private String protocolo;

    public static RequestHttp lerRequisicao(InputStream entrada)
            throws IOException {

        RequestHttp requisicao = new RequestHttp();
        byte[] buffer = new byte[2048];
        int tam_buffer = entrada.read(buffer);
        String dadosRequisicao = new String(buffer, 0, tam_buffer);
        String[] linhaRequisicao = dadosRequisicao.split("\n");
        String[] linha = linhaRequisicao[0].split(" ");

        requisicao.metodo = linha[0];
        requisicao.recurso = linha[1];
        requisicao.protocolo = linha[2];

        return requisicao;
    }

    public String getRecurso() {
        return recurso;
    }

    @Override
    public String toString() {
        return "DADOS DA REQUISIÇÃO:" +
                "\n-----------------------------\n" +
                "Método..........: " + metodo + "\n" +
                "Recurso.........: " + recurso + "\n" +
                "Protocolo.......: " + protocolo + "\n";
    }
}
