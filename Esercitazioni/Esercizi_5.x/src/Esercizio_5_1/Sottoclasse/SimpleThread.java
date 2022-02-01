package Esercizio_5_1.Sottoclasse;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class SimpleThread extends Thread{
    public SimpleThread(String str){
        super(str); // do un nome al thread
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println((i+1) + ") Stampo Thread " + getName());
        }
        System.out.println("Il thread " + getName() + " ha concluso.");
    }
}
