import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kobayashi
 */
public class Imagenes extends JFrame{
    public Imagenes() {
        setTitle("Ver más");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel verMasLink = new JLabel("<html><u>Ver más</u></html>");
        verMasLink.setForeground(Color.BLUE);
        verMasLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        verMasLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Aquí va tu imagen");
                // Para abrir una imagen desde una URL
                // Puedes usar algo como:
                ImageIcon icon = null;
                try {
                    icon = new ImageIcon(new URL(""));
                } catch (MalformedURLException ex) {
                    System.out.println("NO se encontro la imagen");
                }
                JOptionPane.showMessageDialog(null, new JLabel(icon));
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(verMasLink);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
         String imagePath = "./imagenes/ImgGramaticas/BloqueInicioFin.jpeg"; // Ruta de la imagen en tu computadora

        try {
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                // Sistema operativo Windows
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", imagePath);
                processBuilder.start();
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
                // Sistemas operativos Unix (Linux, macOS)
                ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", imagePath);
                processBuilder.start();
            } else {
                System.out.println("Sistema operativo no soportado para abrir imágenes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
