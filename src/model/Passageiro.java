package model;

public class Passageiro {

    private String nome;
    private String ip;

    public Passageiro() {
    }

    public Passageiro (String ip) {
        this.ip = ip;
    }

    public Passageiro(String nome, String ip) {
        this.nome = nome;
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "INFORMAÇÕES DO PASSAGEIRO:" +
                "\n-----------------------------\n" +
                "Nome......: " + nome + "\n" +
                "IP........: " + ip + "\n";
    }
}
