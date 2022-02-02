package Esercizio_6_1;

public class BoundedBuffer {
    private static final int n = 10;    // 10 elementi
    private int[] buf = new int[10];
    private int front, rear, count;

    public synchronized int get() throws InterruptedException {
        while (count == 0) wait();

        int temp = buf[front];
        front = (front + 1) % n;

        count--;
        notifyAll();
        return temp;
    }

    public synchronized void put(int element) throws InterruptedException {
        while (count == n) wait();

        buf[rear] = element;
        rear = (rear + 1) % n;

        count++;
        notifyAll();
    }
}
