package Esercizio_5_1.Sottoclasse;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Tester {
    public static void main(String[] args) {
        SimpleThread myThread1 = new SimpleThread("thread 1");
        SimpleThread myThread2 = new SimpleThread("thread 2");

        myThread1.start();
        myThread2.start();
    }
}
