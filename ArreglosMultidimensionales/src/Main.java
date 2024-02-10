public class Main {
    public static void main(String[] args) {
        /*
        int[][] numeros = new int[3][3];
        int i, j; //i=filas, j=columnas

        //recorrer filas
        for (i = 0; i < numeros.length; i++) {
            System.out.println();
            //recorrer columnas
            for (j = 0; j < numeros.length; j++) {
                //Imprime el valor de la matris en el lugar actual
                System.out.print(numeros[i][j] + " ");
            }
        }

         */

        int [][] matriz = new int[4][4];
        matriz[0][0]=45;
        matriz[0][1]=4;
        matriz[1][0]=78;
        matriz[1][1]=65;
        int i,j;//filas columnas

        //recorrer filas
        for (i=0; i < matriz.length; i++){
            //recorrer columas
            System.out.println();
            for (j=0; j< matriz.length; j++){
                System.out.printf(matriz[i][j]+" ");
            }
        }
    }
}