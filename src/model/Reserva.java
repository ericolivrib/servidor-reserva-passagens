package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reserva {

    private Passageiro passageiro;
    private Poltrona poltrona;
    private LocalDate data;
    private LocalTime hora;

    public Reserva(Passageiro passageiro, Poltrona poltrona, LocalDateTime dataHora) {
        this.passageiro = passageiro;
        this.poltrona = poltrona;
        this.data = dataHora.toLocalDate();
        this.hora = dataHora.toLocalTime();
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Poltrona getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(Poltrona poltrona) {
        this.poltrona = poltrona;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "INFORMAÇÕES DE RESERVA:" +
                "\n-----------------------------\n" +
                "Passageiro......: " + passageiro.getNome() + " | " + passageiro.getIp() + "\n" +
                "Poltrona........: " + poltrona.getNumero() + "\n" +
                "Data e hora.....: " + data + " " + hora + "\n";
    }
}
