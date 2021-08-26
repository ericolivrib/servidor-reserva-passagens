package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class EscreveLog {

    static File arquivo;
    static FileWriter escritorArquivo;
    static BufferedWriter escritorBuffer;

    private static final ArrayList<String> fila = new ArrayList<>();

    public void gravarRegistro(String registro) {

        synchronized (fila) {
            fila.add(registro);
            fila.notify();
            System.out.println("[LOG] Gravou o registro da reserva.\n");
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
                    System.out.println("[LOG] Salvou o registro.\n");

                    try {
                        arquivo = new File("log.txt");

                        if (!arquivo.exists()) {
                            arquivo.createNewFile();
                        }

                        escritorArquivo = new FileWriter(arquivo, true);
                        escritorBuffer = new BufferedWriter(escritorArquivo);

                        escritorBuffer.write(reg);
                        escritorBuffer.newLine();

                        escritorBuffer.flush();
                        escritorArquivo.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
