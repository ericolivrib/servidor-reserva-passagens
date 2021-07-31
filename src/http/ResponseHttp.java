package http;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHttp {

    private final int codigo;
    private final String status;
    private byte[] conteudo;
    private byte[] cabecalho;

    public ResponseHttp(int codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public void escreverSaida(OutputStream saida) throws IOException {
        saida.write(cabecalho);
        saida.write(conteudo);
        saida.flush();
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
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
