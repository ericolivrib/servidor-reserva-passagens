package model;

import java.util.ArrayList;

public class Onibus {

    private final int LUGARES = 46;
    private final ArrayList<Poltrona> poltronas = new ArrayList<>(LUGARES);

    public Onibus() {

        for (int i = 1; i <= LUGARES; i++) {
            Poltrona p = new Poltrona(i, true);
            poltronas.add(p);
        }
    }

    public ArrayList<Poltrona> getPoltronas() {
        return poltronas;
    }
}
