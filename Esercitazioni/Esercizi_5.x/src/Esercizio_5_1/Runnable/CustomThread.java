package Esercizio_5_1.Runnable;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class CustomThread implements Runnable{
    private String name;    // la stringa viene costantemente modificata dai thread che la usano

    public CustomThread(String threadName) {
        this.name = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println((i + 1) +") Stampo Thread: " + name);
        }
    }
}
