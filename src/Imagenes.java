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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Imagenes();
            }
        });
    }
    
}
