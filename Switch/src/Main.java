public class Main {
    public static void main(String[] args) {
        //Forma antigua del Switch Case
        /*
        char vocal='U';

        switch (vocal){
            case 'A':
                System.out.println("Vocal A");
                break;
            case 'E':
                System.out.println("Vocal E");
                break;
            case 'I':
                System.out.println("Vocal I");
                break;
            case 'O':
                System.out.println("Vocal O");
                break;
            case 'U':
                System.out.println("Vocal U");
                break;
            default:
                System.out.println("No se conoce o reconoce la vocal");
                break;

         */
        //Forma nueva del Switch
        int mes=4;
        String nombre_mes = "";

        switch (mes){
            //Instrucciones
            case 1 -> nombre_mes = "Enero";
            case 2 -> nombre_mes = "Febrero";
            case 3 -> nombre_mes = "Marzo";
            case 4 -> nombre_mes = "Abril";
            case 5 -> nombre_mes = "Mayo";
            case 6 -> nombre_mes = "Junio";
            case 7 -> nombre_mes = "Julio";
            case 8 -> nombre_mes = "Agosto";
            case 9 -> nombre_mes = "Septiembre";
            case 10 -> nombre_mes = "Octubre";
            case 11 -> nombre_mes = "Noviembre";
            case 12 -> nombre_mes = "Diciembre";
        }
        System.out.println("Mes:" + nombre_mes);

    }
}