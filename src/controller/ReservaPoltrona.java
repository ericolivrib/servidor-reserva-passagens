package controller;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservaPoltrona implements Runnable {

    private Onibus onibus;
    private Reserva reserva;
    private ArrayList<Reserva> reservas;
    private Passageiro passageiro;
    private int numeroPoltrona;
    private String registro;

    public ReservaPoltrona(Onibus onibus, ArrayList<Reserva> reservas, int numeroPoltrona, Passageiro passageiro) {
        this.onibus = onibus;
        this.reservas = reservas;
        this.numeroPoltrona = numeroPoltrona;
        this.passageiro = passageiro;
    }

    @Override
    public void run() {

        synchronized (onibus.getPoltronas()) {

            if (poltronaLivre(getNumeroPoltrona(), getOnibus())) {

                System.out.println("Reservando a poltrona " + numeroPoltrona + " para " + passageiro.getNome() + "...");

                for (Poltrona poltrona : onibus.getPoltronas()) {

                    if (poltrona.getNumero() == numeroPoltrona) {

                        onibus.getPoltronas().get(numeroPoltrona - 1).setLivre(false);
                        poltrona.setLivre(false);

                        reserva = new Reserva(passageiro, poltrona, LocalDateTime.now());
                        reservas.add(reserva);

                        System.out.println("Reserva efetuada!");
                    }
                }

                registro = "" +
                        "-----------------------------------------\n" +
                        "NOME: " + passageiro.getNome() + "\n" +
                        "IP: " + passageiro.getIp() + "\n" +
                        "POLTRONA: " + numeroPoltrona + "\n" +
                        "DATA: " + reserva.getData() + "\n" +
                        "HORA: " + reserva.getHora() + "\n" +
                        "STATUS: Conseguiu concluir a reserva!\n" +
                        "-----------------------------------------";
            }

            else {
                registro = "" +
                        "-----------------------------------------\n" +
                        "NOME: " + passageiro.getNome() + "\n" +
                        "IP: " + passageiro.getIp() + "\n" +
                        "POLTRONA: " + numeroPoltrona + "\n" +
                        "DATA: " + reserva.getData() + "\n" +
                        "HORA: " + reserva.getHora() + "\n" +
                        "STATUS: Falhou na tentativa de reserva!\n" +
                        "-----------------------------------------";

                System.out.println("A reserva não foi efetuada!!!\n");
            }

            new EscreveLog().gravarRegistro(registro);
        }
    }

    /**
     * Verifica se a poltrona recebida por parâmetro está livre
     * @param numeroPoltrona
     * @param onibus
     * @return
     */
    private boolean poltronaLivre(int numeroPoltrona, Onibus onibus) {

        for (Poltrona poltrona : getOnibus().getPoltronas()) {
            if (poltrona.getNumero() == numeroPoltrona) {
                if (!poltrona.isLivre()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public int getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public void setNumeroPoltrona(int numeroPoltrona) {
        this.numeroPoltrona = numeroPoltrona;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
