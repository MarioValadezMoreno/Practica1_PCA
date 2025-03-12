package Practica5y6;

import java.util.ArrayList;

public class Monitor {
    private final ArrayList<Integer> coleccion = new ArrayList<>();

    public synchronized void addInt(Integer dato){
        /* @TODO: COMPLETAR EL MÉTODO QUE AÑADE UN ELEMENTO */

        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ AÑADIDO */
    }
    public synchronized Integer getPar()throws InterruptedException{
        /* @TODO: COMPLETAR EL MÉTODO QUE TOMA UN ELEMENTO PAR*/

        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ COGIDO  */
    }
    public synchronized Integer getImpar()throws InterruptedException{
        /* @TODO: COMPLETAR EL MÉTODO QUE TOMA UN ELEMENTO IMPAR*/

        /* QUE NO SE TE OLVIDE LLAMAR A imprimir_lista() UNA VEZ COGIDO  */
    }
    public void imprimirLista(){
        System.out.println("lista actual: "+coleccion.toString());
    }
}
