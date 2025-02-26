package Practica3y4;

import java.util.concurrent.Semaphore;

public class Semaforo2 {
    private final Semaphore semaforo = new Semaphore(1);

    public void ejecutarHilos() {
        Runnable tarea = () -> {
            String nombreHilo = Thread.currentThread().getName();
            System.out.println(nombreHilo + " intentando entrar en la zona compartida...");

            try {
                semaforo.acquire(); //  Bloquea el semáforo (mutex)
                System.out.println(nombreHilo + " ha entrado en la zona compartida.");
                Thread.sleep(2000); // Simula trabajo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(nombreHilo + " ha salido de la zona compartida.");
                semaforo.release(); //  Libera el semáforo
            }
        };


        Thread hilo1 = new Thread(tarea, "Hilo 1");
        Thread hilo2 = new Thread(tarea, "Hilo 2");
        Thread hilo3 = new Thread(tarea, "Hilo 3");
        //COMPLETAR EL CÓDIGO
        //lanzar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    } //Con permits=1, tanto el hilo1 como el hilo2 pueden entrar primero, prioridad no fija, el que entra
    //primero acaba primero, entra en la zona compartida ya que sí hay permit, y el segundo hilo se queda
    //esperando al realease (el primer hilo entra y sale antes que el segundo).
    //Si hay tres hilos, se ejecutan uno por uno, con orden indefinido.

    //Con 0 permits hay inanicion, ya que ambos hilos (o los que sean) esperan a un permit que nunca va a llegar
    //Con 2 permits ambos hilos pueden estar en la zona compartida al mismo tiempo. Si hay tres hilos
    //uno se queda bloqueado hasta que otro salga

}

