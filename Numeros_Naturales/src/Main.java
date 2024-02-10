import java.util.Scanner;
import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {
        float n1=0, n2=1, n3=0, contador=0, vueltas=0;
        System.out.println("Numero de vueltas: ");
            Scanner scanner= new Scanner(System.in);
            vueltas = scanner.nextInt();
        for (contador=0; contador<=vueltas; contador++){
            String resultado = format("%.0f", n1);
            String numeros = format("%.0f", contador);
            System.out.println("\t Numero " + numeros +":  \t" + resultado);
            n3=n1+n2;
            n1=n2;
            n2=n3;
        }
    }
}
