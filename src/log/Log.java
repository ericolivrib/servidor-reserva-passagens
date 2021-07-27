package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    private String registro;
    private Logger logger;
    private FileHandler arquivo;
    private final Object monitor = new Log(getRegistro());

    public Log(String registro) {
        setRegistro(registro);
        new Thread(new ProduzLog(getRegistro())).start();
        new Thread(new ConsomeLog(getLogger())).start();
    }

    /**
     * Cria o registro.
     */
    private class ProduzLog implements Runnable {

        public ProduzLog(String registro) {
            setRegistro(registro);
        }

        @Override
        public void run() {

            synchronized (monitor) {
                System.out.println("Criando registro...\n");
                setLogger(Logger.getLogger(registro));
                getMonitor().notify();
            }
        }
    }

    /**
     * Armazena o registro em um arquivo de log
     */
    private class ConsomeLog implements Runnable {

        public ConsomeLog(Logger logger) {
            setLogger(logger);
        }

        @Override
        public void run() {

            synchronized (monitor) {
                try {
                    setArquivo(new FileHandler("Log.txt", true));
                    getLogger().addHandler(getArquivo());
                    getMonitor().wait();

                    System.out.println("Armazenou o registro no log!\n");
                } catch (IOException | InterruptedException ignored) { }
            }
        }
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public FileHandler getArquivo() {
        return arquivo;
    }

    public void setArquivo(FileHandler arquivo) {
        this.arquivo = arquivo;
    }

    public Object getMonitor() {
        return monitor;
    }
}
