package Practica5y6;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorP6 {
    private final ArrayList<Integer> coleccion = new ArrayList<>();
    private final int MAX_ELEM = 10;
    private Lock lock = new ReentrantLock();
    private Condition hayHueco = lock.newCondition();
    private Condition hayPar = lock.newCondition();
    private Condition hayImpar = lock.newCondition();

    public synchronized void addInt(Integer dato){

    }

    public synchronized Integer getPar()throws InterruptedException{

    }

    public synchronized Integer getImpar()throws InterruptedException{

    }

    public void imprimirLista(){
        System.out.println("lista actual: "+coleccion.toString());
    }
}
