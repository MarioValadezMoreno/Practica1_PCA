package org.example;

import practicas_pca.TesterRun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class AlgoritmoBucketSortExecutor implements TesterRun {
    public AlgoritmoBucketSortExecutor(){

    }

    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads){
        ArrayList<Integer> listaOrdenada = new ArrayList<>();
        //Maximo, mínimo y el tamaño
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        int rango = (max - min) / num_threads;

        List<List<Integer>> buckets = new ArrayList<>(num_threads);
        for (int i = 0; i < num_threads; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : numbers) {
            int index = Math.min(num_threads-1, Math.abs((num - min) / rango));
            buckets.get(index).add(num);
        }

        ExecutorService executor = Executors.newFixedThreadPool(num_threads);

        ArrayList<Future<List<Integer>>> futures = new ArrayList<>();

        for (List<Integer> bucket : buckets){

            Callable<List<Integer>> tarea = () -> {
                Collections.sort(bucket);
                return bucket;
            };
            Future<List<Integer>> future = executor.submit(tarea);
            futures.add(future);

        }

        for (Future<List<Integer>> future : futures) {
            try {
                List<Integer> bucket = future.get();
                listaOrdenada.addAll(bucket);
                bucket.clear();
                bucket = null;
            } catch (InterruptedException e){
                System.out.println("Interrupted Exception");
            } catch (ExecutionException executionException){
                System.out.println("ExecutionException");
            }
        }

        executor.shutdown();

        return listaOrdenada;
    }
}