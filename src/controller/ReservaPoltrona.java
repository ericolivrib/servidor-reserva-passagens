package controller;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReservaPoltrona implements Runnable {

    private final Onibus onibus;
    private Reserva reserva;
    private final ArrayList<Reserva> reservas;
    private final Passageiro passageiro;
    private final int numeroPoltrona;

    public ReservaPoltrona(Onibus onibus, ArrayList<Reserva> reservas, int numeroPoltrona, Passageiro passageiro) {
        this.onibus = onibus;
        this.reservas = reservas;
        this.numeroPoltrona = numeroPoltrona;
        this.passageiro = passageiro;
    }

    @Override
    public void run() {

        synchronized (onibus.getPoltronas().get(numeroPoltrona - 1)) {

            String registro;

            if (poltronaLivre(numeroPoltrona, onibus)) {

                System.out.println("Reservando a poltrona " + numeroPoltrona + " para " + passageiro.getNome() + "...\n");

                for (Poltrona poltrona : onibus.getPoltronas()) {

                    if (poltrona.getNumero() == numeroPoltrona) {

                        onibus.getPoltronas().get(numeroPoltrona - 1).setLivre(false);
                        poltrona.setLivre(false);

                        reserva = new Reserva(passageiro, poltrona, LocalDateTime.now());
                        reservas.add(reserva);

                        System.out.println("Reserva efetuada!\n");
                    }
                }

                registro = "" +
                    "-----------------------------------------\n" +
                    "NOME...........: " + reserva.getPassageiro().getNome() +"\n" +
                    "IP.............: " + reserva.getPassageiro().getIp() + "\n" +
                    "POLTRONA.......: " + reserva.getPoltrona().getNumero() + "\n" +
                    "DATA...........: " + reserva.getData() + "\n" +
                    "HORA...........: " + reserva.getHora() + "\n" +
                    "STATUS.........: Conseguiu concluir a reserva!\n" +
                    "-----------------------------------------";
            }

            else {
                registro = "" +
                    "-----------------------------------------\n" +
                    "NOME...........: " + passageiro.getNome() +"\n" +
                    "IP.............: " + passageiro.getIp() + "\n" +
                    "POLTRONA.......: " + numeroPoltrona + "\n" +
                    "DATA...........: " + LocalDate.now() + "\n" +
                    "HORA...........: " + LocalTime.now() + "\n" +
                    "STATUS.........: Falhou na tentativa de reserva!\n" +
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

        for (Poltrona poltrona : onibus.getPoltronas()) {
            if (poltrona.getNumero() == numeroPoltrona) {
                if (!poltrona.isLivre()) {
                    return false;
                }
            }
        }
        return true;
    }
}
