package Practica3y4;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Pan {
    //COMPLETAR CÖÓIGO
    private static final int TAM_BUFFER = 2; // Tamaño del buffer
    // Colas para los ingredientes (una cola por ingrediente)
    private static final Queue<Integer> bufferHarina = new LinkedList<>();
    private static final Queue<Integer> bufferLevadura = new LinkedList<>();
    private static final Queue<Integer> bufferSal = new LinkedList<>();

    // Semáforos para comprobar si hay disponibilidad de ingredientes (un semáforo por ingrediente)
    private static final Semaphore harinaDisponible = new Semaphore(0);
    private static final Semaphore levaduraDisponible = new Semaphore(0);
    private static final Semaphore salDisponible = new Semaphore(0);

    //Semárforos para comprobar si hay espacio para los ingredientes (un semáforo por ingrediente)
    private static final Semaphore espacioHarina = new Semaphore(TAM_BUFFER);
    private static final Semaphore espacioLevadura = new Semaphore(TAM_BUFFER);
    private static final Semaphore espacioSal = new Semaphore(TAM_BUFFER);


    //semáforos para controlar el acceso exclusivo al buffer de ingredientes (un semáforo por ingrediente)
    private static final Semaphore mutexHarina = new Semaphore(1);
    private static final Semaphore mutexLevadura = new Semaphore(1);
    private static final Semaphore mutexSal = new Semaphore(1);


    //semáforos para controlar la entrada en orden de los ingredientes (un semáforo por ingrediente)

    private static final Semaphore ordenHarina = new Semaphore(1);
    private static final Semaphore ordenLevadura = new Semaphore(0);
    private static final Semaphore ordenSal = new Semaphore(0);


    public void ejecutarHilos() {
        //COMPLETAR CÓDIGO
        // Crear y ejecutar productores y consumidores
        Thread productorHarina = new Thread(new Productor(), "Productor Harina");
        Thread productorLevadura = new Thread(new Productor(), "Productor Levadura");
        Thread productorSal = new Thread(new Productor(), "Productor Sal");

        Thread panadero1 = new Thread(new Panadero(), "Panadero 1");
        Thread panadero2 = new Thread(new Panadero(), "Panadero 2");

        productorHarina.start();
        productorLevadura.start();
        productorSal.start();

        panadero1.start();
        panadero2.start();

    }

    // Clase Productor
    static class Productor implements Runnable {
        @Override
        public void run() {

            int i = 1;
            int j = 1;
            int k = 1;

            while (i <= 10 && j <= 10 && k <= 10) {
                try {
                    //COMPLETAR CÓDIGO
                    // Bucle para almacenar ingredientes
                    String nombreHilo = Thread.currentThread().getName();
                    if (nombreHilo.equals("Productor Harina")) {
                        //COMPLETAR EL CÓDIGO
                        //comprobar bloqueo
                        ordenHarina.acquire(); //Garantiza el orden
                        espacioHarina.acquire(); //Espera hasta que haya espacio
                        mutexHarina.acquire(); //Acceso exclusivo a la despensa

                        System.out.println(nombreHilo + " depositando harina...");
                        bufferHarina.add(i);
                        Thread.sleep(1000);
                        System.out.println(nombreHilo + " ha depositado harina "+i);
                        i++;
                        mutexHarina.release();

                    } else if (nombreHilo.equals("Productor Levadura")) {
                        //COMPLETAR EL CÓDIGO
                        //comprobar bloqueo
                        ordenLevadura.acquire();
                        espacioLevadura.acquire();
                        mutexLevadura.acquire();

                        System.out.println(nombreHilo + " depositando levadura...");
                        bufferLevadura.add(j);
                        Thread.sleep(1000);
                        System.out.println(nombreHilo + " ha depositado levadura "+j);
                        j++;
                        mutexLevadura.release();

                    } else if (nombreHilo.equals("Productor Sal")) {
                        //COMPLETAR EL CÓDIGO
                        //comprobar bloqueo
                        ordenSal.acquire();
                        espacioSal.acquire();
                        mutexSal.acquire();

                        System.out.println(nombreHilo + " depositando sal...");
                        bufferSal.add(k);
                        Thread.sleep(1000);
                        System.out.println(nombreHilo + " ha depositado sal "+k);
                        k++;
                        mutexSal.release();
                    }


                    if (nombreHilo.equals("Productor Harina")) {
                        //COMPLETAR EL CÓDIGO
                        //desbloquear
                        harinaDisponible.release(); //Harina disponible para el panadero +1
                        ordenLevadura.release(); //Se hace a la siguiente ya que las siguientes se inician a 0
                         //Asì logramos que se haga en el orden correcto

                    } else if (nombreHilo.equals("Productor Levadura")) {
                        //COMPLETAR EL CÓDIGO
                        //desbloquear
                        levaduraDisponible.release();
                        ordenSal.release();
                    } else if (nombreHilo.equals("Productor Sal")) {
                        //COMPLETAR EL CÓDIGO
                        //desbloquear
                        salDisponible.release();
                        ordenHarina.release();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


        // Clase Panadero
        //COMPLETAR CÓDIGO
        //Crear clase Panadero que implemente Runnable, consuma ingredientes y produzca pan
        static class Panadero implements Runnable {
            @Override
            public void run() {
                int i = 1;
                int j = 1;
                try {

                    while (i <= 5 || j <= 5) {
                        String nombreHilo = Thread.currentThread().getName();

                        if (i>5 && Thread.currentThread().getName().equals("Panadero 1")){
                            Thread.currentThread().interrupt();
                        }

                        if (j>5 && Thread.currentThread().getName().equals("Panadero 2")){
                            Thread.currentThread().interrupt();
                        }

                        harinaDisponible.acquire();
                        espacioHarina.release();
                        mutexHarina.acquire();
                        int harina=bufferHarina.poll();
                        mutexHarina.release();

                        levaduraDisponible.acquire();
                        espacioLevadura.release();
                        mutexLevadura.acquire();
                        int levadura=bufferLevadura.poll();
                        mutexLevadura.release();

                        salDisponible.acquire();
                        espacioSal.release();
                        mutexSal.acquire();
                        int sal=bufferSal.poll();
                        mutexSal.release();

                        System.out.println(nombreHilo + " horneando pan...");
                        Thread.sleep(5000);
                        System.out.println(nombreHilo + " produjo pan con harina " + harina +", levadura " + levadura + " y sal "+sal);

                        if (nombreHilo.equals("Panadero 1")){
                            i++;
                        } else j++;
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }

    }


