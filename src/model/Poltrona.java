package model;

public class Poltrona {

    private int numero;
    private boolean livre;

    public Poltrona(int numero, boolean livre) {
        this.numero = numero;
        this.livre = livre;
    }

    public Poltrona(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "INFORMAÇÕES DA POLTRONA:" +
                "\n-----------------------------\n" +
                "Número......: " + numero + "\n" +
                "Livre.......? " + livre + "\n";
    }
}
