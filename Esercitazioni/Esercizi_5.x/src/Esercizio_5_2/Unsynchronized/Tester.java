package Esercizio_5_2.Unsynchronized;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Tester {
    public static void main(String[] args) {
        SimpleBuffer simpleBuffer = new SimpleBuffer();

        Producer p1 = new Producer(simpleBuffer, 1);
        Consumer c1 = new Consumer(simpleBuffer, 1);

        p1.start();
        c1.start();
    }
}
