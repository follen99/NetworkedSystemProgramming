package Esercizio_5_2.Synchronized;

import Esercizio_5_2.Unsynchronized.SimpleBuffer;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Tester {
    public static void main(String[] args) {
        SimpleSyncBuffer simpleBuffer = new SimpleSyncBuffer();

        Producer p1 = new Producer(simpleBuffer, 1);
        Consumer c1 = new Consumer(simpleBuffer, 2);

        p1.start();
        c1.start();
    }
}
