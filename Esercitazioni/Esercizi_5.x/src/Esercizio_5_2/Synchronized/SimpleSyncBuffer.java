package Esercizio_5_2.Synchronized;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class SimpleSyncBuffer {
    private int buf;
    private boolean dataAvailable = false;

    public synchronized int get() throws InterruptedException {
        while (!dataAvailable) wait();

        // quando i dati saranno disponibili
        dataAvailable = false;
        notifyAll();
        return buf;
    }

    public synchronized void put(int val) throws InterruptedException {
        while (dataAvailable) wait();

        // quando i dati saranno letti
        dataAvailable = true;
        notifyAll();

        this.buf = val;
    }
}
