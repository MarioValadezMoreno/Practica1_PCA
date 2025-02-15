package org.example;
import practicas_pca.TesterPracticas;
/**
 * Hello world!
 *
 */
public class Main
{//prueba
    public static void main( String[] args ) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_2500000, 6);
    }
}
