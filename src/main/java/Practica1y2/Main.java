package Practica1y2;
import practicas_pca.TesterPracticas;
public class Main
{
    public static void main( String[] args ) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSortExecutor());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_25000000, 6);
    }
    //Tuvimos una tutoría con el profesor de prácticas, comprobamos que el código estaba bien, coincidía con lo que había que hacer y ordenaba bien los números,
    // pero los tiempos de ejecución eran muy largos, esto podía deberse al dispositivo (ordenador usado).
}
