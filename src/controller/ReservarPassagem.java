package controller;

import log.Log;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservarPassagem implements Runnable {

    private Onibus onibus;
    private Reserva reserva;
    private ArrayList<Reserva> reservas;
    private Passageiro passageiro;
    private int numeroPoltrona;
    private String registro;

    public ReservarPassagem(Onibus onibus, ArrayList<Reserva> reservas, int numeroPoltrona, Passageiro passageiro) {
        setOnibus(onibus);
        setPassageiro(passageiro);
        setNumeroPoltrona(numeroPoltrona);
        setReservas(reservas);
    }

    @Override
    public void run() {

        synchronized (getOnibus().getPoltronas()) {

            if (poltronaLivre(getNumeroPoltrona(), getOnibus())) {

                System.out.println("Reservando a poltrona " + getNumeroPoltrona() + " para " + getPassageiro().getNome() + "...");

                for (Poltrona poltrona : getOnibus().getPoltronas()) {
                    if (poltrona.getNumero() == getNumeroPoltrona()) {
                        poltrona.setLivre(false);

                        setReserva(new Reserva(getPassageiro(), poltrona, LocalDateTime.now()));
                        reservas.add(reserva);

                        System.out.println("Reserva efetuada!");
                    }
                }

                setRegistro(getPassageiro().getNome() + "\n" +
                        getPassageiro().getIp() + "\n" +
                        getReserva().getData() + "\n" +
                        getReserva().getHora() + "\n" +
                        "STATUS: Conseguiu concluir a reserva!");
            }

            else {
                setRegistro(getPassageiro().getNome() + "\n" +
                        getPassageiro().getIp() + "\n" +
                        getReserva().getData() + "\n" +
                        getReserva().getHora() + "\n" +
                        "STATUS: Falhou na tentativa de reserva!");

                System.out.println("A reserva não foi efetuada!!!\n");
            }

            new Log(getRegistro());
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
