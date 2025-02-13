package org.example;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import practicas_pca.TesterRun;
//Commit
public class AlgoritmoBucketSort implements TesterRun{
    private ArrayList<ArrayList<Integer>> buckets;//Lista que contendrá listas de hilos

    public AlgoritmoBucketSort(){
        buckets = new ArrayList<>();
    }
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads) {
       //1.
        for(int i = 0; i< num_threads; i++){
            buckets.add(new ArrayList<Integer>());
        }
        //2.
        //LLamo dos métodos auxiliares para conseguir el máximo y mínimo de esta lista
        int max = getMaxList(numbers);
        int min = getMinList(numbers);
        // Calculo el rango de números que va a tener cada cubo
        int rango = (int) Math.ceil((double)(max-min+1)/num_threads);
        for(int number : numbers) {
            int bucketIndex = (number - min) / rango;
            if (bucketIndex >= num_threads) {
                bucketIndex = num_threads - 1; // Asegurar que el índice no exceda el número de buckets
            }
            buckets.get(bucketIndex).add(number);
        }
        //3 y 4.
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i< num_threads; i++){
            ArrayList<Integer> bucket = buckets.get(i);
            Thread th = new Thread();
            Collections.sort(bucket);
            threads.add(th);
            th.start();
        }
        //5.
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //6 y 7
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (ArrayList<Integer> bucket : buckets) {
            sortedList.addAll(bucket);
            bucket.clear();
            bucket = null; // Permitir que el GC lo elimine
        }
        // Limpiar las listas auxiliares
        buckets.clear();
        buckets = null;
        threads.clear();
        threads = null;

        // 8.
        return sortedList;
    }

    private int getMinList(List<Integer> numbers) {
        int min = Integer.MAX_VALUE;
        for(Integer n: numbers){
            if(n < min){
                min = n;
            }
        }
        return min;
    }

    private int getMaxList(List<Integer> numbers) {
        int max = Integer.MIN_VALUE;
        for(Integer n: numbers){
            if(n> max){
                max = n;
            }
        }
        return max;
    }


}
