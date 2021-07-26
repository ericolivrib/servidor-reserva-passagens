package model;

import java.util.ArrayList;

public class Onibus {

    private ArrayList<Poltrona> poltronas = new ArrayList<>(40);

    public Onibus() {

        for (int i = 1; i <= poltronas.size(); i++) {
            Poltrona poltrona = new Poltrona(i, true);
            poltronas.add(poltrona);
        }
    }
}
