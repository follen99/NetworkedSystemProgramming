package Esercizio_5_2.Unsynchronized;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class Consumer extends Thread{
    private SimpleBuffer buffer;
    private int id;

    public Consumer(SimpleBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        int readValue = 0;
        for (int i = 0; i < 10; i++){
            readValue = buffer.get();
            System.out.println("consumer #" + this.id + "got: " + readValue);

            try {
                sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Consumer woke up!");
            }
        }
    }
}
