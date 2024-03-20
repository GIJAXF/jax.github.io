//Codigo de Geminis Α-Ω/Jax para el evento de hackeo del varista en Java organizado por Nikorasu / Discord MD: geminisao
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DecodificaneitorCafenation {

    public static void main(String[] args) {
        try {   //C:\Users\Jax\IdeaProjects\HACKEO_DEL_VARISTA\src\registros_perdidos.txt
            File archivo = new File("RUTA_ARCHIVO");//EJEMPLO: C:\Users\USUARIO\Carpeta\registros_perdidos.txt
            if (!archivo.exists()) {
                System.out.println("¡El archivo no se encontró!");
                return;
            }

            List<String> lineas = new ArrayList<>();

            try (Scanner lector = new Scanner(archivo)) {
                while (lector.hasNextLine()) {
                    lineas.add(lector.nextLine());
                }
            }

            // Ordenar las líneas según la fecha
            Collections.sort(lineas, new Comparator<String>() {
                @Override
                public int compare(String linea1, String linea2) {
                    String fecha1 = obtenerFecha(linea1);
                    String fecha2 = obtenerFecha(linea2);
                    return fecha1.compareTo(fecha2); // Orden ascendente
                }
            });

            // Imprimir las líneas ordenadas
            for (String linea : lineas) {
                String[] partes = linea.split(","); //Separa el texto en bloques por cada ,
                String codigoHex = partes[0]; //Primer bloque cantidad
                String mensajeBinario = partes[1].replace(" ", ""); //Segundo bloque fecha
                String texto = partes[2]; // Tercer bloque texto

                int cantidadSobres = Integer.parseInt(codigoHex, 16);

                String fechaCodificada = decodificarBinario(mensajeBinario);
                String fechaFormateada = obtenerFechaFormateada(fechaCodificada);

                System.out.println("\t Cant.Sobres: " + cantidadSobres + "\tFecha: " + fechaFormateada + "\tTipo.Grano: " + texto);
            }

        } catch (FileNotFoundException e) {
            System.out.println("¡El archivo no se encontró!");
            e.printStackTrace();
        }
    }

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

    public static String obtenerFechaFormateada(String fechaCodificada) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date fecha = formatoEntrada.parse(fechaCodificada);
            return formatoSalida.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error de formato";
        }
    }

    public static String obtenerFecha(String linea) {
        String[] partes = linea.split(",");
        String msjBinario = partes[1].replace(" ", "");
        String fechaCodificada = decodificarBinario(msjBinario);
        return fechaCodificada;
    }
}
