package http;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHttp {

    private String protocolo;
    private int codigo;
    private String status;
    private byte[] conteudo;
    private byte[] cabecalho;

    public ResponseHttp(String protocolo, int codigo, String status) {
        this.protocolo = protocolo;
        this.codigo = codigo;
        this.status = status;
    }

    public void escreverSaida(OutputStream saida) throws IOException {
        saida.write(cabecalho);
        saida.write(conteudo);
        saida.flush();
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public byte[] getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(byte[] cabecalho) {
        this.cabecalho = cabecalho;
    }

    @Override
    public String toString() {
        return "DADOS DA RESPOSTA" +
                "\n------------------\n" +
                "Código...........: " + codigo + "\n" +
                "Status...........: " + status + "\n";
    }
}
