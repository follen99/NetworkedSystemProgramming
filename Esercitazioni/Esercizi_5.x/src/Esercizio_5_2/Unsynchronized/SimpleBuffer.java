package Esercizio_5_2.Unsynchronized;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class SimpleBuffer {
    private int buff;

    public int get(){
        return this.buff;
    }
    public void put(int buff){
        this.buff = buff;
    }
}
