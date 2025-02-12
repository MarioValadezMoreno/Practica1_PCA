package org.example;
import java.lang.*;
import practicas_pca.TesterPracticas;
/**
 * Hello world!
 *
 */
public class TestPractica1 {
    public static void main( String[] args )
    {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_2500000, 6);
    }
}
