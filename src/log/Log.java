package log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    private String registro;
    private final ArrayList<String> fila = new ArrayList<>(registro.length());

    public Log(String registro) {
        this.registro = registro;

        new Thread(new Produtor()).start();
        new Thread(new Consumidor()).start();
    }

    private class Produtor implements Runnable {

        @Override
        public void run() {

            while (true) {
                synchronized (fila) {
                    if (fila.size() == registro.length()) {
                        try {
                            fila.wait();
                        } catch (InterruptedException ignored) { }
                    }

                    fila.add(registro);
                    fila.notify();

                    System.out.println("[LOG] Gravou um registro");
                }
            }
        }
    }

    private class Consumidor implements Runnable {

        @Override
        public void run() {

            while (true) {
                synchronized (this) {
                    if (fila.size() == 0) {
                        try {
                            fila.wait();
                        } catch (InterruptedException ignored) { }
                    }

                    String reg = fila.remove(0);

                    try {
                        FileHandler alimentador = new FileHandler("log.txt", true);
                        Logger logger = Logger.getLogger(reg);
                        logger.addHandler(alimentador);

                        System.out.println("[LOG] Adicionou o registro no arquivo de log.");
                        fila.notify();
                    } catch (IOException ignored) { }
                }
            }
        }
    }
}
