
import compilerTools.Directory;
import compilerTools.Functions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JTextPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author kobayashi
 */
public class subirCodigoArduino extends javax.swing.JFrame {

    private Directory directorio;
    private String title;

    /**
     * Creates new form subirCodigoArduino
     */
    public subirCodigoArduino() {
        initComponents();
        title = "Codex_Music";// Titulo de la ventana
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(title);
        directorio = new Directory(this, jtpMostrarAntesSubir, title, ".ino");
        //Mostrar los numeros de linea en la ventana jtpCode
        Functions.setLineNumberOnJTextComponent(jtpMostrarAntesSubir);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscarCodigo = new javax.swing.JButton();
        btnSubirCodigo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpMostrarAntesSubir = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBuscarCodigo.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        btnBuscarCodigo.setText("BUSCAR CÓDIGO");
        btnBuscarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCodigoActionPerformed(evt);
            }
        });

        btnSubirCodigo.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        btnSubirCodigo.setText("SUBIR CODIGO");
        btnSubirCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirCodigoActionPerformed(evt);
            }
        });

        jtpMostrarAntesSubir.setEditable(false);
        jtpMostrarAntesSubir.setFont(new java.awt.Font("Iosevka Nerd Font", 0, 24)); // NOI18N
        jScrollPane2.setViewportView(jtpMostrarAntesSubir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSubirCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(btnSubirCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCodigoActionPerformed
        if (directorio.Open()) {
            System.out.println("Se pudo abrir");
        }
    }//GEN-LAST:event_btnBuscarCodigoActionPerformed

    private void btnSubirCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirCodigoActionPerformed
        uploadArduinoCodeFromJTextPane(jtpMostrarAntesSubir, this.getTitle().substring(0, this.getTitle().length() - 4));
    }//GEN-LAST:event_btnSubirCodigoActionPerformed

    public static void uploadArduinoCodeFromJTextPane(JTextPane textPane, String carpeta) {
        String code = textPane.getText(); // Obtener el código del JTextPane

        // Crear un nuevo directorio para el proyecto Arduino
        File projectDirectory = new File("/home/kobayashi/Desktop/Semestre7/4-Automatas2/todoxd/Compilador-main/src/compilarYSubir/" + carpeta);

        // Verificar si la carpeta del proyecto existe
        if (projectDirectory.exists() && projectDirectory.isDirectory()) {
            // Si existe, eliminarla junto con sus contenidos
            deleteDirectory(projectDirectory);
        }

        // Crear un nuevo directorio para el proyecto Arduino
        projectDirectory.mkdir();

        // Crear un archivo .ino y escribir el código en él
        File inoFile = new File(projectDirectory, carpeta + ".ino");
        try (PrintWriter writer = new PrintWriter(inoFile)) {
            writer.println(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Ejecutar comandos de arduino-cli
        executeCommand("/home/kobayashi/Desktop/Semestre7/4-Automatas2/todoxd/Arduino/arduino-cli compile --fqbn esp32:esp32:esp32 " + projectDirectory.toString()); // Compilar el proyecto
        executeCommand("/home/kobayashi/Desktop/Semestre7/4-Automatas2/todoxd/Arduino/arduino-cli upload -p /dev/ttyUSB0 --fqbn esp32:esp32:esp32 " + projectDirectory.toString()); // Subir el código a la placa
    }

    public static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        if (!directory.delete()) {
            System.out.println("No se pudo eliminar: " + directory);
        }
    }

    public static void executeCommand(String command) {
        try {
            // Ejecutar el comando en la máquina real
            Process proceso = Runtime.getRuntime().exec(command);

            // Capturar la salida del comando
            BufferedReader entrada = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea);
            }
            entrada.close();

            // Esperar a que el comando finalice
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(subirCodigoArduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subirCodigoArduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subirCodigoArduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subirCodigoArduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subirCodigoArduino().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCodigo;
    private javax.swing.JButton btnSubirCodigo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jtpMostrarAntesSubir;
    // End of variables declaration//GEN-END:variables
}
