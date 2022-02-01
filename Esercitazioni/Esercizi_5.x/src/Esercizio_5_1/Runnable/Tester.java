package Esercizio_5_1.Runnable;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Tester {
    public static void main(String[] args) {
        CustomThread thread1 = new CustomThread("Thread 1");
        CustomThread thread2 = new CustomThread("Thread 2");

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
