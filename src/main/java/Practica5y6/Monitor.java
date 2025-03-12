package Practica5y6;

import java.util.ArrayList;

public class Monitor {
    private final ArrayList<Integer> coleccion = new ArrayList<>();

    public synchronized void addInt(Integer dato){
        /* @TODO: COMPLETAR EL MÉTODO QUE AÑADE UN ELEMENTO */
        coleccion.add(dato); //Añade el dato a la coleccion
        imprimirLista(); //imprime la lista
        notify(); //Despierta al primer elemento de la cola de espera

        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ AÑADIDO */
    }
    public synchronized Integer getPar()throws InterruptedException{
        /* @TODO: COMPLETAR EL MÉTODO QUE TOMA UN ELEMENTO PAR*/
        while (true) {
            if (coleccion.isEmpty()) {
                return 0; //Devuelve 0 si vacio
            } else {
                if (coleccion.get(coleccion.size()-1) % 2 == 0) { //Mira si es par
                    int x = coleccion.remove(coleccion.size()-1);
                    imprimirLista();
                    notify();
                    return x;
                } else {
                    wait();
                }
            }
        }
        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ COGIDO  */
    }
    public synchronized Integer getImpar()throws InterruptedException{
        /* @TODO: COMPLETAR EL MÉTODO QUE TOMA UN ELEMENTO IMPAR*/
        while (true) {
            if (coleccion.isEmpty()) {
                return 0; //Devuelve 0 si vacio
            } else {
                if (coleccion.get(coleccion.size()-1) % 2 == 1) { //Mira si es impar
                    int x = coleccion.remove(coleccion.size()-1);
                    imprimirLista();
                    notify();
                    return x;
                } else {
                    wait();
                }
            }
        }
        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ COGIDO  */
    }
    public void imprimirLista(){
        System.out.println("lista actual: "+coleccion.toString());
    }
}
