import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.spec.SecretKeySpec;

public class Cliente {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            // Solicitar al usuario que se registre
            Scanner scanner = new Scanner(System.in);
            System.out.print("Por favor, ingrese su nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            registrarUsuario(socket, nombreUsuario);

            // Recibir la clave secreta del servidor
            SecretKey claveSecreta = recibirClaveSecreta(socket.getInputStream());

            // Crear un hilo para recibir mensajes cifrados del servidor
            Thread recibirThread = new Thread(() -> {
                try (Scanner scanner1 = new Scanner(socket.getInputStream())) {
                    while (true) {
                        String mensajeCifrado = scanner1.nextLine();
                        String mensajeDescifrado = descifrarMensaje(mensajeCifrado, claveSecreta);
                        System.out.println("Mensaje del servidor: " + mensajeDescifrado);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recibirThread.start();

            // Enviar mensajes cifrados al servidor desde la consola del cliente
            while (true) {
                System.out.print("Escribe un mensaje: ");
                String mensaje = scanner.nextLine();
                enviarMensaje(socket, cifrarMensaje(mensaje, claveSecreta));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registrarUsuario(Socket socket, String nombreUsuario) {
        try {
            // Enviar el nombre de usuario al servidor
            socket.getOutputStream().write(("registro:" + nombreUsuario + "\n").getBytes());

            // Esperar confirmación del servidor (puedes mejorar esto según tus necesidades)
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String respuesta = new String(buffer, 0, bytesRead);
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SecretKey recibirClaveSecreta(InputStream inputStream) throws IOException {
        byte[] claveSecretaBytes = new byte[16];  // Asumiendo una clave de 128 bits
        inputStream.read(claveSecretaBytes);
        return new SecretKeySpec(claveSecretaBytes, "AES");
    }

    private static void enviarMensaje(Socket socket, String mensaje) {
        try {
            socket.getOutputStream().write((mensaje + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String cifrarMensaje(String mensaje, SecretKey claveSecreta) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte[] mensajeCifradoBytes = cipher.doFinal(mensaje.getBytes());
            return Base64.getEncoder().encodeToString(mensajeCifradoBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String descifrarMensaje(String mensajeCifrado, SecretKey claveSecreta) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] mensajeCifradoBytes = Base64.getDecoder().decode(mensajeCifrado.getBytes());
            byte[] mensajeDescifradoBytes = cipher.doFinal(mensajeCifradoBytes);
            return new String(mensajeDescifradoBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
//JAX CODE