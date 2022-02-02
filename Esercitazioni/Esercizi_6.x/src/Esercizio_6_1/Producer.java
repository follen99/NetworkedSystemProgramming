package Esercizio_6_1;

public class Producer extends Thread{
    private BoundedBuffer buffer;

    public Producer(ThreadGroup threadGroup, String name, BoundedBuffer boundedBuffer){
        super(threadGroup, name);
        this.buffer = boundedBuffer;
    }

    public void run(){
        int i = 0;
        try {
            while (!isInterrupted()){
                buffer.put(i++);
                sleep(500); // sleep mezzo secondo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Il produttore ha terminato.");
    }
}
