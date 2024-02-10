import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsoRecursosUI {
    private JLabel processorsLabel;
    private JLabel memoryLabel;
    private Timer timer;

    public UsoRecursosUI() {
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
                new UsoRecursosUI();
            }
        });
    }
}
