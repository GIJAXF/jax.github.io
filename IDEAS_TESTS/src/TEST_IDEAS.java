/* ESTE PROYECTO ES UNA RECOPILACION DE TODOS LOS TESTS PARA LA VERSION FINAL TODAS LAS FUNCIONES PUBLICAS
SE LE ASIGNA FUNCIONES (NOMBRES)  */

/*
//RELOJ TEST
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TEST_IDEAS extends JFrame {
    private JLabel relojLabel;

    public TEST_IDEAS() {
        setTitle("Reloj");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        relojLabel = new JLabel("", JLabel.CENTER);
        relojLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(relojLabel);

        // Actualiza el reloj cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date ahora = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
                relojLabel.setText(formato.format(ahora));
            }
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new TEST_IDEAS();
    }
}
*/

/*
//TEST PIANO TILES 4*4 Bloques
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TEST_IDEAS extends JFrame implements ActionListener {
    private JButton[] tiles;

    public TEST_IDEAS() {
        setTitle("Piano Tiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4));

        tiles = new JButton[16];
        for (int i = 0; i < 16; i++) {
            tiles[i] = new JButton();
            tiles[i].setBackground(Color.white);
            add(tiles[i]);
            tiles[i].addActionListener(this);
        }

        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < 16; i++) {
            if (source == tiles[i]) {
                if (i >= 12) {
                    // Game over logic
                    JOptionPane.showMessageDialog(this, "Game Over");
                    // You can add more game over actions here
                } else {
                    tiles[i].setBackground(Color.black);
                }
            }
        }
    }

    public static void main(String[] args) {
        new TEST_IDEAS();
    }
}
 */

//LISTA DE TAREAS
import java.util.ArrayList;
import java.util.Scanner;

public class TEST_IDEAS {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1. Agregar tarea");
            System.out.println("2. Editar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la nueva tarea: ");
                    scanner.nextLine(); // Limpiar el buffer
                    String nuevaTarea = scanner.nextLine();
                    tareas.add(nuevaTarea);
                    
                    System.out.println("Tarea agregada");
                    break;
                case 2:
                    System.out.print("Ingrese el índice de la tarea a editar: ");
                    int indiceEditar = scanner.nextInt();
                    if (indiceEditar >= 0 && indiceEditar < tareas.size()) {
                        System.out.print("Ingrese la nueva descripción de la tarea: ");
                        scanner.nextLine(); // Limpiar el buffer
                        String nuevaDescripcion = scanner.nextLine();
                        tareas.set(indiceEditar, nuevaDescripcion);
                        System.out.println("Tarea editada");
                    } else {
                        System.out.println("Índice inválido");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el índice de la tarea a eliminar: ");
                    int indiceEliminar = scanner.nextInt();
                    if (indiceEliminar >= 0 && indiceEliminar < tareas.size()) {
                        tareas.remove(indiceEliminar);
                        System.out.println("Tarea eliminada");
                    } else {
                        System.out.println("Índice inválido");
                    }
                    break;
                case 4:
                    if (tareas.isEmpty()) {
                        System.out.println("No hay tareas en la lista");
                    } else {
                        System.out.println("Lista de tareas:");
                        for (int i = 0; i < tareas.size(); i++) {
                            System.out.println(i + ". " + tareas.get(i));
                        }
                    }
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo de la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TEST_IDEAS {
    private ArrayList<String> tasks = new ArrayList<>();
    private JTextArea taskTextArea;

    public TEST_IDEAS() {
        JFrame frame = new JFrame("Task List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Task List");
        titleLabel.setBounds(160, 10, 80, 25);
        panel.add(titleLabel);

        JTextField taskInput = new JTextField(20);
        taskInput.setBounds(50, 40, 200, 25);
        panel.add(taskInput);

        JButton addButton = new JButton("Add Task");
        addButton.setBounds(260, 40, 100, 25);
        panel.add(addButton);

        JButton editButton = new JButton("Edit Task");
        editButton.setBounds(50, 70, 100, 25);
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setBounds(160, 70, 100, 25);
        panel.add(deleteButton);

        JButton showButton = new JButton("Show Tasks");
        showButton.setBounds(260, 70, 100, 25);
        panel.add(showButton);

        taskTextArea = new JTextArea();
        taskTextArea.setBounds(50, 100, 310, 120);
        panel.add(taskTextArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTask = taskInput.getText();
                tasks.add(newTask);
                taskTextArea.append(newTask + "\n");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasks.remove();
                taskTextArea.append(taskInput + "\n");
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskTextArea.setText("");
                for (String task : tasks) {
                    taskTextArea.append(task + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        new TEST_IDEAS();
    }
}

 */

/*
//MUESTRA EL USO DE LA GPU Y CPU ASI COMO LA RAM
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEST_IDEAS {
    private JLabel processorsLabel;
    private JLabel memoryLabel;
    private Timer timer;

    public TEST_IDEAS() {
        JFrame frame = new JFrame("Monitor de Recursos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        processorsLabel = new JLabel("Número de procesadores disponibles: ");
        frame.add(processorsLabel);

        memoryLabel = new JLabel("Memoria total:  bytes | Memoria libre:  bytes | Memoria utilizada:  bytes");
        frame.add(memoryLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateResourceInfo();
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private void updateResourceInfo() {
        // Uso del procesador
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        processorsLabel.setText("Número de procesadores disponibles: " + availableProcessors);

        // Uso de la memoria RAM
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        memoryLabel.setText("Memoria total: " + totalMemory + " bytes | Memoria libre: " + freeMemory + " bytes | Memoria utilizada: " + usedMemory + " bytes");

        // Información de la GPU (depende del sistema y la configuración)
        // Puedes incluir la lógica específica para obtener información de la GPU aquí
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TEST_IDEAS();
            }
        });
    }
}
 */
/*
import java.security.MessageDigest;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TEST_IDEAS {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Ingrese la clave a Encriptar:");
        String selectedAlgorithm = (String) JOptionPane.showInputDialog(null, "Seleccione el algoritmo de encriptación",
                "Selección de algoritmo", JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"SHA-256", "Otro algoritmo"}, "SHA-256");

        if (selectedAlgorithm.equals("SHA-256")) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(input.getBytes());
                // Convertir el byte array a representación hexadecimal
                StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
                for (int i = 0; i < encodedhash.length; i++) {
                    String hex = Integer.toHexString(0xff & encodedhash[i]);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                JOptionPane.showMessageDialog(null, "La clave encriptada es: " + hexString.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Aquí puedes agregar la lógica para otros algoritmos de encriptación
            JOptionPane.showMessageDialog(null, "Otro algoritmo seleccionado. Lógica de desencriptación no implementada.");
        }
        String encryptedKey = "CLAVE_ENCRIPTADA";
        String decryptedKey = sha256(encryptedKey);
        System.out.println("Clave desencriptada en SHA-256: " + decryptedKey);
    }

    public static String sha256(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/
