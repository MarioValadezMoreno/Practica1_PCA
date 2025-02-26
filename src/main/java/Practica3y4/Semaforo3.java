package Practica3y4;

import java.util.concurrent.Semaphore;

public class Semaforo3 {
    //COMPLETAR EL CÓDIGO
    //CONFIGURAR LOS SEMÁFOROS
    private final Semaphore semaforo1 = new Semaphore(1,true); // Bloquea el hilo 1
    private final Semaphore semaforo2 = new Semaphore(0,true); // Bloquea el hilo 2
    private final Semaphore semaforo3 = new Semaphore(0,true); // Bloquea el hilo 3

    private final Runnable tarea = () -> {
        String nombreHilo = Thread.currentThread().getName();

        try {
            if (nombreHilo.equals("Hilo 1")) {
                //COMPLETAR EL CÓDIGO
                //comprobar bloqueo
                semaforo1.acquire();

            } else if (nombreHilo.equals("Hilo 2")) {
                //COMPLETAR EL CÓDIGO
                //comprobar bloqueo
                semaforo2.acquire();

            } else if (nombreHilo.equals("Hilo 3")) {
                //COMPLETAR EL CÓDIGO
                //comprobar bloqueo
                semaforo3.acquire();
            }

            System.out.println(nombreHilo + " iniciando...");
            Thread.sleep(1000); // Simula trabajo
            System.out.println(nombreHilo + " terminado.");

            if (nombreHilo.equals("Hilo 1")) {
                //COMPLETAR EL CÓDIGO
                //desbloquear
                semaforo2.release();
            } else if (nombreHilo.equals("Hilo 2")) {
                //COMPLETAR EL CÓDIGO
                //desbloquear
                semaforo3.release();
            } else if (nombreHilo.equals("Hilo 3")){
                //COMPLETAR EL CÓDIGO
                //desbloquear
                semaforo1.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    };

    public void ejecutarHilos() {
        for (int i = 0; i < 10; i++) {
            Thread hilo1 = new Thread(tarea, "Hilo 1");
            Thread hilo2 = new Thread(tarea, "Hilo 2");
            Thread hilo3 = new Thread(tarea, "Hilo 3");

            hilo1.start();
            hilo2.start();
            hilo3.start();
        }
    }

    }//Ponemos permit = 1 al semaforo 1 para que solo este se ejecute y hacemos release en el semaforo siguiente cada vez
    //que liberemos un semaforo, para que el orden sea correcto

    //Una mala inicialización de los semáforos puede provocar inanición por ejemplo si se crean los semáforos con 0 permits
    //o hacer que el orden de ejecución sea indefinido o varios hilos se ejecuten a la vez si hay más de un permit en
    //cualquier hilo

    //Si se puede



