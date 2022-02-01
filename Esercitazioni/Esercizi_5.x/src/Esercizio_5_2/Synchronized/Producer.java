package Esercizio_5_2.Synchronized;

import Esercizio_5_2.Unsynchronized.SimpleBuffer;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Producer extends Thread{
    private SimpleSyncBuffer buffer;
    private int id;

    public Producer(SimpleSyncBuffer sb, int id){
        this.buffer = sb;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++){
                buffer.put(i);
                System.out.println("Producer #" + this.id + "put: " + i);

                try {
                    sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.err.println("Producer woke up!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
