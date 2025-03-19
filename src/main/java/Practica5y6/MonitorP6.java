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

    public void addInt(Integer dato){
        lock.lock();
        if(coleccion.size() >= MAX_ELEM){
            try {
                hayHueco.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        coleccion.add(dato); //Añade el dato a la coleccion
        hayImpar.signal();
        hayPar.signal();
        imprimirLista(); //imprime la lista
        lock.unlock();
    }

    public Integer getPar()throws InterruptedException{
            lock.lock();
            if (coleccion.isEmpty()) {
                hayPar.await();
                hayImpar.signal();
                return 0;
            }
                if (coleccion.get(coleccion.size()-1) % 2 == 0) { //Mira si es par
                    int x = coleccion.remove(coleccion.size()-1);
                    hayHueco.signal();
                    hayImpar.signal();
                    imprimirLista();
                    lock.unlock();
                    return x;

                }else{
                    hayPar.await();
                    lock.unlock();
                    return 0;
                }

    }

    public Integer getImpar()throws InterruptedException{
            lock.lock();
            if (coleccion.isEmpty()) {
                hayImpar.await();
                return 0;
            }
                if (coleccion.get(coleccion.size()-1) % 2 == 1) { //Mira si es impar
                    int x = coleccion.remove(coleccion.size()-1);
                    hayHueco.signal();
                    hayPar.signal();
                    imprimirLista();
                    lock.unlock();
                    return x;
                }else{
                    hayImpar.await();
                    lock.unlock();
                    return 0;
                }

    }

    public void imprimirLista(){
        System.out.println("lista actual: "+coleccion.toString());
    }
}