//Codigo de Geminis Α-Ω/Jax para el evento de hackeo del varista en Java organizado por Nikorasu / Discord MD: geminisao
import java.io.File; //LIBRERIA PARA IMPORTAR EL DOCUMENTO O ARCHIVO CON TERMINACION .TXT
import java.io.FileNotFoundException; //LIBRERIA PARA QUE NO SE TRABE CUANDO LO ENCUENTRE
import java.text.ParseException; //ESCEPECION DE ARCHIVO
import java.text.SimpleDateFormat; //FORMATO DE LA FECHA
import java.util.*; //ESCRIBIR NOMBRES COMPLETOS

public class DecodificaneitorCafenation {

    public static void main(String[] args) {
        try {
//---------------------IMPORTA EL ARCHIVO, REGISTROS_PERDIDOS.TXT O ALGUN OTRO ARCHIVO---------------------//
            File archivo = new File("RUTA_ARCHIVO");//EJEMPLO: C:\Users\USUARIO\Carpeta\registros_perdidos.txt
            if (!archivo.exists()) {
                System.out.println("¡El archivo no se encontró!");
                return;
            }
//---------------------LEE EL ARCHIVO CON LOS SISTEMAS DE ENCRIPTACION---------------------//
            List<String> lineas = new ArrayList<>();

            try (Scanner lector = new Scanner(archivo)) {
                while (lector.hasNextLine()) {
                    lineas.add(lector.nextLine());
                }
            }

//---------------------ORDENA LAS FECHAS SEGUN EL DIA---------------------//
            Collections.sort(lineas, new Comparator<String>() {
                @Override
                public int compare(String linea1, String linea2) {
                    String fecha1 = obtenerFecha(linea1);
                    String fecha2 = obtenerFecha(linea2);
                    return fecha1.compareTo(fecha2); // ORDEN ASCENDENTE
                }
            });

            // Imprimir las líneas ordenadas
            for (String linea : lineas) {
//---------------------SEPARA LA LINEA POR CADA , (EN TOTAL 3 BLOQUES---------------------//
                String[] partes = linea.split(","); //SEPARA LA LINEA EN CADA BLOQUE POR CADA ,
                String codigoHex = partes[0]; //PRIMER BLOQUE CANTIDAD
                String mensajeBinario = partes[1].replace(" ", ""); //SEGUNDO BLOQUE FECHA
                String texto = partes[2]; // TERCERO TEXTO
//---------------------YA DIVIDIDA EN BLOQUES DESENCRIPA LA FECHA Y EL SOBRE EN HEXADECIMAL Y DECIMAL EN BASE UTF-8---------------------//
                int cantidadSobres = Integer.parseInt(codigoHex, 16);
                String fechaCodificada = decodificarBinario(mensajeBinario);
                String fechaFormateada = obtenerFechaFormateada(fechaCodificada);
//---------------------IMPRESION---------------------//
                System.out.println("\t Cant.Sobres: " + cantidadSobres + "\tFecha: " + fechaFormateada + "\tTipo.Grano: " + texto);
            }
//---------------------AL MOMENTO DE NO ENCONTRAR EL ARCHIVO SE ENVIA ESTE MENSAJE---------------------//
        } catch (FileNotFoundException e) {
            System.out.println("¡El archivo no se encontró!");
            e.printStackTrace();
        }
    }
//---------------------FUNCION PARA DECODIFICAR LA FECHA---------------------//
    public static String decodificarBinario(String msjBinario) {
        StringBuilder msjDecodificado = new StringBuilder();
        String[] bytes = msjBinario.split("(?<=\\G.{8})");

        for (String byteBinario : bytes) {
            int ascii = Integer.parseInt(byteBinario, 2);
            char caracter = (char) ascii;
            msjDecodificado.append(caracter);
        }

        return msjDecodificado.toString();
    }
//---------------------SEPARA LA FECHA EN VEZ "20230313" A "2023/03/13"---------------------//
    public static String obtenerFechaFormateada(String fechaCodificada) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date fecha = formatoEntrada.parse(fechaCodificada);
            return formatoSalida.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error de formato"; //EN CASO DE NO CUMPLIR CON EL FORMATO AUTOMATICAMENTE SE BLOQUEA Y DICE FORMATO NO COMPATIBLE
        }
    }
//---------------------REEMPLAZA LOS ESPACIOS POR NO ESPACIOS PARA QUE EL ALGEROTMO LO CONVIERTA A "CARACTERES"---------------------//
    public static String obtenerFecha(String linea) {
        String[] partes = linea.split(",");
        String msjBinario = partes[1].replace(" ", "");
        String fechaCodificada = decodificarBinario(msjBinario);
        return fechaCodificada;
    }
}
