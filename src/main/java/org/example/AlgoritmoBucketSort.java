package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import practicas_pca.TesterRun;

public class AlgoritmoBucketSort implements TesterRun {

    public AlgoritmoBucketSort(){
    }
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads){
        ArrayList<Integer> listaOrdenada = new ArrayList<>();
        //Maximo, mínimo y el tamaño
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        int rango = (max - min) / num_threads;

        //Creamos lista de buckets
        List<List<Integer>> buckets = new ArrayList<>(num_threads);
        for (int i = 0; i < num_threads; i++) {
            buckets.add(new ArrayList<>());
        }

        //Asignamos cada número de la lista a un bucket
        //Este bucle es el que lo hace mal, hay que asignar DIRECTAMENTE cada numero a un bucket, si no, es demasiado complejo y tarda demasiado
        for (int num : numbers) {
            int index = Math.min(num_threads-1, Math.abs((num - min) / rango));
            buckets.get(index).add(num);
        }


        //Creamos lista de Threads y en cada uno ordenamos con Collections.sort() cada bucket
        List<Thread> threads = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            Thread thread = new Thread(() -> Collections.sort(bucket));
            threads.add(thread);
            thread.start();
        }

        //Recorremos la lista de Threads y hacemos join
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        //Unimos todos los buckets, borramos elementos y ponemos a null los buckets para no saturar al Heap
        for (List<Integer> bucket : buckets) {
            listaOrdenada.addAll(bucket);
            bucket.clear();
            bucket=null;
        }

        return listaOrdenada;
    }
}
