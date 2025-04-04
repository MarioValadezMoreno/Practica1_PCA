package com.ejemplo.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear el sistema de actores 

        // Crear el actor printerActor 

        // Crear hundredActor  

        // Crear evenActor   


        int[] ints = {10, 2, 3, 100, 245, 102, 234};
        // enviar cada cifra de la secuencia al actor evenActor



        // esperar 5 segundos

        // enviar el mensaje "stop" a cada actor



        //  terminar el sistema

        System.out.println("Actor system terminated");
    }
}
