package Esercizio_6_1;

public class Consumer extends Thread{
    private BoundedBuffer boundedBuffer;

    public Consumer(ThreadGroup threadGroup, String name, BoundedBuffer buffer){
        super(threadGroup, name);

        this.boundedBuffer = buffer;
    }

    public void run(){
        try {
            while (!isInterrupted()){
                System.out.println(boundedBuffer.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("il consumatore ha terminato");
    }
}
