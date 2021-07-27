package model;

import java.util.ArrayList;

public class Onibus {

    private ArrayList<Poltrona> poltronas = new ArrayList<>(40);

    public Onibus() {

        for (int i = 1; i <= getPoltronas().size(); i++) {
            Poltrona poltrona = new Poltrona(i, true);
            getPoltronas().add(poltrona);
        }
    }

    public ArrayList<Poltrona> getPoltronas() {
        return poltronas;
    }
}
