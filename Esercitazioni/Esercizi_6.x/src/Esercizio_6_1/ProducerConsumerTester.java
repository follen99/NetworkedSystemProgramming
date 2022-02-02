package Esercizio_6_1;

import java.util.Scanner;

public class ProducerConsumerTester {
    public static void main(String[] args) {
        BoundedBuffer commonBuffer = new BoundedBuffer();
        ThreadGroup commonGroup = new ThreadGroup("CommonThreadGroup");

        Producer producer = new Producer(commonGroup, "Producer1", commonBuffer);
        Consumer consumer = new Consumer(commonGroup, "Consumer1", commonBuffer);

        producer.start();
        consumer.start();

        // come terminare l'applicazione
        Scanner inFromUser = new Scanner(System.in);
        String answer;

        do {
            System.out.print("Terminare l'applicazione? (yes/no)");
            answer = inFromUser.nextLine();
        }while (!answer.toLowerCase().equals("yes"));

        commonGroup.interrupt();    // grazie al gruppo interrompiamo tutti i thread nel gruppo
        inFromUser.close();
    }
}
