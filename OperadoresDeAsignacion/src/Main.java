public class Main {
    public static void main(String[] args) {
        //Ejemplo 1
        /*
    int a = 5;
    int b = 10;
    int c = 3;
    int d = 8;
    a = a + 4;
    b = b - 5;
    c = c * 3;
    d = d / 4;
        System.out.println("a="+a);
        System.out.println("b="+b);
        System.out.println("c="+c);
        System.out.println("d="+d);
         */


        //Ejemplo 2
        /*
        int a = 5, b = 10, c = 3, d = 8;
        a += 4;
        b -= 5;
        c *= 3;
        d /= 4;
        System.out.println("a="+a);
        System.out.println("b="+b);
        System.out.println("c="+c);
        System.out.println("d="+d);
         */


//Operadores relacionales
        /*
        //variables
        int a = 10;
        int b = 5;
        //Impresion de consola donde colocamos si a es = < > ! de b
        System.out.println("a==b:" + (a==b));
        System.out.println("a<b:" + (a<b));
        System.out.println("a<=b:" + (a<=b));
        System.out.println("a>b:" + (a>b));
        System.out.println("a>=b:" + (a>=b));
        System.out.println("a!=b:" + (a!=b));
         */
        //Operador Logico
        boolean valor1=true;
        boolean valor2=true;
        boolean valor3 =true;
        boolean valor4=false;
//Operador Logico &&
        System.out.println("Primer resultado:" + (valor1 && valor2)); //true
        System.out.println("Segundo resultado;" + (valor3 && valor4)); //false

        //Operador logico ||
        System.out.println("Tercer resultado:" + (valor1 || valor2)); //true
        System.out.println("Cuarto resultado:" + (valor1 || valor4)); //true

        //Operador logico !
        System.out.println("Quinto resultado:" + (!valor1)); //False
        System.out.println("Sexto resultado:" + (!valor4)); //True

    }
}