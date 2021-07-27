package model;

public class Passageiro {

    private String nome;
    private int ip;

    public Passageiro(String nome, int ip) {
        this.nome = nome;
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public int getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "INFORMAÇÕES DO PASSAGEIRO:" +
                "\n-----------------------------\n" +
                "Nome......: " + getNome() + "\n" +
                "IP........: " + getIp() + "\n";
    }
}
