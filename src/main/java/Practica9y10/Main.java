package Practica9y10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static boolean isPalindrome(int number) {
        String str = Integer.toString(number);
        int str_len = str.length();

        for (int i = 0; i < str_len / 2; i++) {
            if (str.charAt(i) != str.charAt(str_len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        // Create your own Callable class that implements Callable
                    // Inside the Callable class: add a private List<Integer> attribute
                    // Inside the Callable class: add the constructor of the class
                    // Inside the Callable class: override the call method

        final int MAX_RANGE = 1000000;
        final int NUM_THREADS = 10;
        long time_ini = System.currentTimeMillis();
        long time_end;
        List<Integer> list = IntStream.rangeClosed(1, MAX_RANGE).boxed().collect(Collectors.toList());
        // Create an executor with 5 threads



        // Execute each thread with a sublist of integers inside a loop and get the futures





        // Get the results from the futures and print them





        // Shutdown the executor


        time_end = System.currentTimeMillis();
        System.out.println("El tiempo que ha tardado es: "+(time_end-time_ini)+" milisegundos.");
    }
}
