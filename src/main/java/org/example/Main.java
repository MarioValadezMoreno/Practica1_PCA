package org.example;
import practicas_pca.TesterPracticas;
public class Main
{
    public static void main( String[] args ) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_2500000, 6);
    }
    //Tuvimos una tutoría con el profesor de prácticas, comprobamos que el código estaba bien, coincidía con lo que había que hacer y ordenaba bien los números,
    // pero los tiempos de ejecución eran muy largos, esto podía deberse al dispositivo (ordenador usado).
}
