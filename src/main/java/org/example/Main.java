package org.example;
import practicas_pca.TesterPracticas;
/**
 * Hello world!
 *
 */
public class Main
{//prueba
    public static void main( String[] args ) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSortExecutor());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_25000000, 6);
    }
}
