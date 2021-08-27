package model;

public class Passageiro {

    private final String nome;
    private final String ip;

    public Passageiro(String nome, String ip) {
        this.nome = nome;
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "INFORMAÇÕES DO PASSAGEIRO:" +
                "\n-----------------------------\n" +
                "Nome......: " + nome + "\n" +
                "IP........: " + ip + "\n";
    }
}
