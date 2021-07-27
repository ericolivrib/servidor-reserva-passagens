package http;

import java.io.IOException;
import java.io.InputStream;

public class RequestHttp {

    private String metodo;
    private String recurso;
    private String protocolo;

    /**
     * Recebe um input de entrada e retorna a primeira linha da requisição.
     * @param entrada
     * @return
     * @throws IOException
     */
    public static RequestHttp lerRequisicao(InputStream entrada) throws IOException {

        RequestHttp requisicao = new RequestHttp();

        byte[] buffer = new byte[2048];

        int tam_buffer = entrada.read(buffer);

        String dadosRequisicao = new String(buffer, 0, tam_buffer);

        String[] linhaRequisicao = dadosRequisicao.split("\n");

        String[] linha = linhaRequisicao[0].split(" ");

        requisicao.setMetodo(linha[0]);
        requisicao.setRecurso(linha[1]);
        requisicao.setProtocolo(linha[2]);

        System.out.println(requisicao);

        return requisicao;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public String toString() {
        return "DADOS DA REQUISIÇÃO:" +
                "\n-----------------------------\n" +
                "Método..........: " + getMetodo() + "\n" +
                "Recurso.........: " + getRecurso() + "\n" +
                "Protocolo.......: " + getProtocolo() + "\n";
    }
}
