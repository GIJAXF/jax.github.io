import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Servidor {

    private static Map<String, ClienteHandler> clientes = new HashMap<>();

    public static void main(String[] args) {
        try {
            SecretKey claveSecreta = generarClaveSecreta();

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Enviar la clave secreta al cliente
                enviarClaveSecreta(clienteSocket.getOutputStream(), claveSecreta);

                // Crear un nuevo manejador de cliente y agregarlo al mapa
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket, claveSecreta);
                clientes.put(clienteHandler.getNombre(), clienteHandler);

                // Iniciar el hilo para el nuevo cliente
                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private SecretKey claveSecreta;
        private String nombre;
        private Scanner scanner;
        private OutputStream outputStream;

        public ClienteHandler(Socket clienteSocket, SecretKey claveSecreta) {
            this.clienteSocket = clienteSocket;
            this.claveSecreta = claveSecreta;
            try {
                this.scanner = new Scanner(clienteSocket.getInputStream());
                this.outputStream = clienteSocket.getOutputStream();
                // Solicitar el nombre del cliente
                this.nombre = obtenerNombreCliente();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String mensajeCifrado = scanner.nextLine();
                    String mensajeDescifrado = descifrarMensaje(mensajeCifrado, claveSecreta);

                    // Verificar si es un mensaje privado y enviarlo al destinatario específico
                    if (mensajeDescifrado.startsWith("/privado")) {
                        String[] partes = mensajeDescifrado.split(" ", 3);
                        if (partes.length == 3) {
                            String destinatario = partes[1];
                            String mensajePrivado = partes[2];
                            enviarMensajePrivado(destinatario, mensajePrivado);
                        }
                    } else {
                        // Si no es un mensaje privado, imprimirlo en la consola del servidor con el nombre del usuario
                        System.out.println("[" + nombre + "]: " + mensajeDescifrado);

                        // Enviar el mensaje a todos los clientes conectados
                        enviarMensajeATodos("[" + nombre + "]: " + mensajeDescifrado);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String obtenerNombreCliente() {
            try {
                // Solicitar el nombre de usuario al cliente
                outputStream.write("registro".getBytes());
                byte[] buffer = new byte[1024];
                int bytesRead = clienteSocket.getInputStream().read(buffer);
                return new String(buffer, 0, bytesRead);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void enviarMensajeATodos(String mensaje) {
            for (ClienteHandler otroCliente : clientes.values()) {
                try {
                    if (otroCliente != this) {
                        String mensajeCifrado = cifrarMensaje(mensaje, otroCliente.claveSecreta);
                        otroCliente.outputStream.write((mensajeCifrado + "\n").getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void enviarMensajePrivado(String destinatario, String mensaje) {
            ClienteHandler destinatarioHandler = clientes.get(destinatario);
            if (destinatarioHandler != null) {
                try {
                    String mensajeCifrado = cifrarMensaje("(Mensaje privado de " + nombre + "): " + mensaje, destinatarioHandler.claveSecreta);
                    destinatarioHandler.outputStream.write((mensajeCifrado + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("El destinatario '" + destinatario + "' no está conectado.");
            }
        }

        public String getNombre() {
            return nombre;
        }
    }

    private static SecretKey generarClaveSecreta() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private static void enviarClaveSecreta(OutputStream outputStream, SecretKey claveSecreta) throws IOException {
        byte[] claveSecretaBytes = claveSecreta.getEncoded();
        outputStream.write(claveSecretaBytes);
    }

    private static String cifrarMensaje(String mensaje, SecretKey claveSecreta) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte[] mensajeCifradoBytes = cipher.doFinal(mensaje.getBytes());
            return new String(Base64.getEncoder().encode(mensajeCifradoBytes));
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