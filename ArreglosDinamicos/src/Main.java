import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Array de tipo String
        /*
        List<String> animales = new ArrayList<>();

        //Agregar elementos al Array
        animales.add("Leon");
        animales.add("Tigre");
        animales.add("Gato");
        animales.add("Perro");
        System.out.println("Primer array " + animales);
        animales.add(2, "Elefante");
        System.out.println("Segundo array" + animales);

         */
        List<String> lenguajeProgra = new ArrayList<>();
        lenguajeProgra.add("C++");
        lenguajeProgra.add("Python");
        lenguajeProgra.add("Java");
        lenguajeProgra.add("Ruby");
        System.out.println("Arreglo uno " + lenguajeProgra);
        //Remover elemento indice 3
        lenguajeProgra.remove(3);
        System.out.println("Lista sin el indice 3 " + lenguajeProgra)
    }
}