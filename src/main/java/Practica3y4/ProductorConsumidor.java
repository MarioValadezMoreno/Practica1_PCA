package Practica3y4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProductorConsumidor {
    private static final int TAM_BUFFER = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();
    
    // Semáforos para gestionar el acceso al buffer
    private static final Semaphore espacioDisponible = new Semaphore(TAM_BUFFER);
    private static final Semaphore elementosDisponibles = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    public void ejecutarHilos() {
        // Crear y ejecutar productores y consumidores
        Thread productor1 = new Thread(new Productor(), "Productor 1");
        Thread productor2 = new Thread(new Productor(), "Productor 2");
        //COMPLETAR EL CÓDIGO
        // Crear y ejecutar "Consumidor1" y "Consumidor2"
        Thread consumidor1 = new Thread(new Consumidor(),"Consumidor 1");
        Thread consumidor2 = new Thread(new Consumidor(),"Consumidor 2");
        productor1.start();
        productor2.start();
        //COMPLETAR EL CÓDIGO
        //Arrancar los consumidores
        consumidor1.start();
        consumidor2.start();
        
    }

    // Clase Productor
    static class Productor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int item = (int) (Math.random() * 100); // Producir un elemento
                    espacioDisponible.acquire(); // Esperar espacio disponible
                    mutex.acquire(); // acceso exclusivo
                    buffer.add(item); // Introducir el elemento al buffer
                    System.out.println(Thread.currentThread().getName() + " produjo: " + item);
                    mutex.release(); // Liberar acceso
                    elementosDisponibles.release(); // Indicar que hay un nuevo elemento
                    Thread.sleep(1000); // Simular tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // COMPLETAR CÖDIGO
    // Implementar la Clase Consumidor, de modo que imprima el valor consumido
    static class Consumidor implements Runnable {
        @Override
        public void run(){
            try {
                while (true){
                    int item;
                    elementosDisponibles.acquire(); //Espera a que haya un elemento en el buffer
                    mutex.acquire(); //Espera el semaforo
                    item=buffer.poll(); //Saca el elemento del buffer
                    System.out.println(Thread.currentThread().getName() + " obtuvo: "+item);
                    mutex.release(); //Libera el semaforo
                    espacioDisponible.release(); //Libera espacio del buffer
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

