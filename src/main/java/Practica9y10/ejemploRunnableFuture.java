package Practica9y10;

import java.util.concurrent.*;

public class ejemploRunnableFuture {
    public static void main(String[] args) {
        Runnable tarea = () ->{
            try{
                System.out.println(Thread.currentThread().getName()+": ejecutando tarea runnable...");
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+": ...fin de la tarea runnable");
            }
        };
        // FIRST PART: RunnableFuture with Thread
        // Create a FutureTask with the previous Runnable task

        // Create a thread with the previous FutureTask


        System.out.println(Thread.currentThread().getName()+": lanzando tarea 1 en hilo");
        // Execute the thread


        // SECOND PART: RunnableFuture with the main thread
        // Create a second FutureTask with the Runnable task


        System.out.println(Thread.currentThread().getName()+": ejecutando tarea 2");
        // Execute the futureTask


        // print the thread name and the result of the future



        // THIRD PART: RunnableFuture with executor
        // Create an executor with the method Executors.newSingleThreadExecutor()
        // Executes a third futuretask
        // Get the result of the future
        // Don't forget shutdown the executor

    }
}
