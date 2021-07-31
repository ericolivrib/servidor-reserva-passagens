package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class EscreveLog {

    private static final ArrayList<String> fila = new ArrayList<>();

    public void gravarRegistro(String registro) {

        synchronized (fila) {
            fila.add(registro);
            fila.notify();
            System.out.println("[LOG] Gravou o registro. \"" + registro + "\"");

            new Thread(new Consumidor()).start();
        }
    }

    public static class Consumidor implements Runnable {

        @Override
        public void run() {

            while (true) {
                String reg;

                synchronized (fila) {
                    if (fila.isEmpty()) {
                        try {
                            fila.wait();
                        } catch (InterruptedException ignored) { }
                    }

                    reg = fila.remove(0);
                    System.out.println("[LOG] Item gravado: " + reg +"");

                    try {
                        FileHandler alimentador = new FileHandler("Log.txt", true);
                        Logger logger = Logger.getLogger(reg);
                        logger.addHandler(alimentador);
                    } catch (IOException ignored) { }
                }
            }
        }
    }
}
