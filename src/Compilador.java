
import Analisis.ErroresSintacticos;
import Analisis.TablaSimbolos;
import Analisis.CodigoIntermedio;
import Analisis.IntermedioOptimizado;
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.Directory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
//import static generadorIntermedio.optimizadorDeClave;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author codex_404
 */
public class Compilador extends javax.swing.JFrame {

    // Variables del editor
    private String title;
    private Directory directorio;

    // Variables para analisis lexico
    private ArrayList<Token> tokens;

    // Manejador de errores
    private ArrayList<ErrorLSSL> errors;
    private Map<Integer, String> diccionarioErroresImagenes;

    // Color en los tokens
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;

    // Variables para el analisis sintactico
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private Map<String, String> reemplazoFunciones;

    // Codigo compilado
    private boolean codeHasBeenCompiled = true;

    // Variables analisis semantico
    private AnalisisSemantico analizadorSemantico;

    // Variables codigo intermedio
    private generadorIntermedio codIntermedio;
    private String codigoIntermedioSinOptimizar;
    private String codigoIntermedioOptimizado;
    private codObjetoPiano funcionesPiano;
    private codObjetoBuzzer buzzerCod;

    // Variables codigo objeto
    private String notasxd = "";
    private String funcionesxd = "";
    private String nombreArchivo = "";
    private int TEMPO_BUZZER = 0;
    private int TEMPO_PIANO = 0;

    // Variables mostrar resultado de los analisis
    private TablaSimbolos TablaDSimbolos;
    private ErroresSintacticos ErroresSintac;
    private CodigoIntermedio mostrarCodigoIntermedio;
    private IntermedioOptimizado mostarIntermedioOptimizado;
    private subirCodigoArduino subirCodArduino;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
        //this.setResizable(false);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        jPanel1.setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Acultar los analizadores
        btnAnalizadores.setVisible(false);
        btnGramaticas.setVisible(false);
        btnIntermedioOptimizado.setVisible(false);
        btnIntermedio.setVisible(false);
        btnSubirArduino.setVisible(false);
        btnTablaSimbolos.setVisible(false);

        // Mostrar tabla de simbolos
        TablaDSimbolos.setLocationRelativeTo(this);
        ErroresSintac.setLocationRelativeTo(this);
    }

    private void init() {
        title = "Codex_Music";// Titulo de la ventana
        setLocationRelativeTo(null);
        setTitle(title);
        //btnEjecutar.setVisible(false);
        // algun nombre de extebtnEjecutarnsion???
        directorio = new Directory(this, jtpCode, title, ".cmx");

        //Asegurarse de que al salir podamos guardar los cambios en el archivo
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });

        //Mostrar los numeros de linea en la ventana jtpCode
        Functions.setLineNumberOnJTextComponent(jtpCode);

        //Colores en el editor de texto cada 300 ms
        timerKeyReleased = new Timer(300, (ActionEvent e) -> {
            timerKeyReleased.stop();
            colores();
        });

        //Idicador de texto modificado en el editor jtpCode
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });

        //Arrays de elementos
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        TablaDSimbolos = new TablaSimbolos();
        ErroresSintac = new ErroresSintacticos();
        mostrarCodigoIntermedio = new CodigoIntermedio();
        funcionesPiano = new codObjetoPiano();
        buzzerCod = new codObjetoBuzzer();
        mostarIntermedioOptimizado = new IntermedioOptimizado();
        subirCodArduino = new subirCodigoArduino();
        reemplazoFunciones = new HashMap<>();

        //Autocompletado de codigo
        Functions.setAutocompleterJTextComponent(new String[]{/*UTILIZAR AL FINAL*/}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnTablaSimbolos = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnGuardarC1 = new javax.swing.JButton();
        btnAnalizadores = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIntermedio = new javax.swing.JButton();
        btnIntermedioOptimizado = new javax.swing.JButton();
        btnGramaticas = new javax.swing.JButton();
        btnSubirArduino = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblErrores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        rootPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setBackground(new java.awt.Color(255, 153, 51));
        btnNuevo.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/newfile_85903 (1).png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("");
        btnNuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 40));

        btnAbrir.setBackground(new java.awt.Color(153, 153, 255));
        btnAbrir.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/32527 (1).png"))); // NOI18N
        btnAbrir.setText("Abrir");
        btnAbrir.setToolTipText("");
        btnAbrir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 40));

        btnGuardar.setBackground(new java.awt.Color(102, 255, 204));
        btnGuardar.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save_icon_125167 (1).png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, 40));

        btnTablaSimbolos.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnTablaSimbolos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/981402 (1).png"))); // NOI18N
        btnTablaSimbolos.setText("Tabla de simbolos");
        btnTablaSimbolos.setToolTipText("");
        btnTablaSimbolos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnTablaSimbolos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTablaSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaSimbolosActionPerformed(evt);
            }
        });
        jPanel1.add(btnTablaSimbolos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 210, 80));

        btnCompilar.setBackground(new java.awt.Color(204, 255, 204));
        btnCompilar.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/descarga (1).png"))); // NOI18N
        btnCompilar.setText("Compilar");
        btnCompilar.setToolTipText("");
        btnCompilar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnCompilar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompilar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 210, 60));

        jtpCode.setBackground(new java.awt.Color(39, 41, 52));
        jtpCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtpCode.setFont(new java.awt.Font("JetBrainsMono Nerd Font", 0, 24)); // NOI18N
        jtpCode.setForeground(new java.awt.Color(247, 247, 241));
        jtpCode.setCaretColor(new java.awt.Color(255, 255, 255));
        jtpCode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jtpCode);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 820, 480));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("-");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 100, 50));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("+");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 110, 50));

        btnGuardarC1.setBackground(new java.awt.Color(255, 255, 204));
        btnGuardarC1.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnGuardarC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32743 (1).png"))); // NOI18N
        btnGuardarC1.setText("Guardar como");
        btnGuardarC1.setToolTipText("");
        btnGuardarC1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnGuardarC1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarC1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, -1));

        btnAnalizadores.setBackground(new java.awt.Color(153, 204, 255));
        btnAnalizadores.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnAnalizadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/lexico.png"))); // NOI18N
        btnAnalizadores.setText("Analizadores");
        btnAnalizadores.setToolTipText("");
        btnAnalizadores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnAnalizadores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnalizadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizadoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnalizadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 210, 40));

        jLabel1.setFont(new java.awt.Font("Open Sans", 2, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo3.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, -1, 80));

        btnIntermedio.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnIntermedio.setText("Código intermedio");
        btnIntermedio.setToolTipText("");
        btnIntermedio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnIntermedio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntermedioActionPerformed(evt);
            }
        });
        jPanel1.add(btnIntermedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 160, 210, 80));

        btnIntermedioOptimizado.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnIntermedioOptimizado.setText("Intermedio optimizado");
        btnIntermedioOptimizado.setToolTipText("");
        btnIntermedioOptimizado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnIntermedioOptimizado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIntermedioOptimizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntermedioOptimizadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnIntermedioOptimizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 240, 210, 90));

        btnGramaticas.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnGramaticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/GooglePlus_G_icon-icons.com_49945 (1).png"))); // NOI18N
        btnGramaticas.setText("Gramatica");
        btnGramaticas.setToolTipText("");
        btnGramaticas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnGramaticas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGramaticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGramaticasActionPerformed(evt);
            }
        });
        jPanel1.add(btnGramaticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 90, 210, 70));

        btnSubirArduino.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnSubirArduino.setText("Subir código");
        btnSubirArduino.setToolTipText("");
        btnSubirArduino.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnSubirArduino.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubirArduino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirArduinoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSubirArduino, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 330, 210, 80));

        tblErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Error", "Imagen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblErrores);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1240, 230));

        rootPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1252, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirArduinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirArduinoActionPerformed
        subirCodArduino.setVisible(true);
    }//GEN-LAST:event_btnSubirArduinoActionPerformed

    private void btnGramaticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGramaticasActionPerformed
        ErroresSintac.setVisible(true);
    }//GEN-LAST:event_btnGramaticasActionPerformed

    private void btnIntermedioOptimizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntermedioOptimizadoActionPerformed
        mostarIntermedioOptimizado.setVisible(true);
    }//GEN-LAST:event_btnIntermedioOptimizadoActionPerformed

    private void btnIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntermedioActionPerformed
        mostrarCodigoIntermedio.setVisible(true);
    }//GEN-LAST:event_btnIntermedioActionPerformed

    private void btnAnalizadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizadoresActionPerformed
        btnGramaticas.setVisible(true);
        btnIntermedio.setVisible(true);
        btnIntermedioOptimizado.setVisible(true);
        btnSubirArduino.setVisible(true);
        btnTablaSimbolos.setVisible(true);
    }//GEN-LAST:event_btnAnalizadoresActionPerformed

    private void btnGuardarC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarC1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aumentarFuente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        disminuirFuente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compilar();
            }
        } else {
            // Inicializar el codigo intermedio
            codigoIntermedioSinOptimizar = "";
            codigoIntermedioOptimizado = "";

            // Compilar el proyecto
            compilar();
            intermedio();
            nombreArchivo = this.getTitle().substring(0, this.getTitle().length() - 4);
            buzzerCod.generateCodigoBuzzer(notasxd, nombreArchivo, TEMPO_BUZZER);
            funcionesPiano.generateCodigoPiano(funcionesxd, nombreArchivo, "", TEMPO_PIANO);

            mostrarCodigoIntermedio.getTxtIntermedio().setText(codigoIntermedioSinOptimizar);
            mostarIntermedioOptimizado.getTxtIntermedioOptimizado().setText(codigoIntermedioOptimizado);
        }

        // Mostrar los "analisis" realizados
        btnAnalizadores.setVisible(true);
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnTablaSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaSimbolosActionPerformed
        TablaDSimbolos.setVisible(true);
    }//GEN-LAST:event_btnTablaSimbolosActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            limpiarCampos();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colores();
            limpiarCampos();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    public void intermedio() {
        // Primera evaluacion -Encontrar variables y tempo-
        String codLimpio = jtpCode.getText().replace(";", "");
        String codigoLimpioOptimizar = jtpCode.getText().replace(";", "");;

        String[] codIntermdioAmedias = codLimpio.split("\n");
        String[] codIntermedioOP = codigoLimpioOptimizar.split("\n");

        // Codigo intermedio
        for (String linea : codIntermdioAmedias) {
            // Agregar variables al mapa
            if (linea.contains("var")) {
                codIntermedio.agregarVariablesAlMapa(linea, identificadores);
            }

            // Generar codigo intermedio del tempos
            if (linea.startsWith("tempo")) {
                codigoIntermedioSinOptimizar += codIntermedio.codigoIntermedioTempo(linea) + "\n";
                TEMPO_BUZZER = codIntermedio.codigoBuzzerTempo(linea);
            }
        }

        //Codigo intermedio optimzado
        for (String linea : codIntermedioOP) {
            // Agregar variables al mapa
            if (linea.contains("var")) {
                codIntermedio.agregarVariablesAlMapa(linea, identificadores);
            }

            // Generar codigo intermedio del tempos
            if (linea.startsWith("tempo")) {
                codigoIntermedioOptimizado += "(t2, =, , " + codIntermedio.codigoBuzzerTempo(linea) + ")\n";
                TEMPO_PIANO = codIntermedio.obtenerValorOriginal(linea);
            }
        }

        // ELIMINAR CLAVES FALSAS------------------------------------------------
        codigoLimpioOptimizar = codIntermedio.optimizadorDeClave(codigoLimpioOptimizar);
        codIntermedioOP = codigoLimpioOptimizar.split("\n");
        //----------------------------------------------------------------------

        // Limpiar variables
        codLimpio = codIntermedio.eliminarLineasVar(codIntermdioAmedias);
        //  OPTIMIZADO
        codigoLimpioOptimizar = codIntermedio.eliminarLineasVar(codIntermedioOP);

        // Agregar if's intemedios
        codLimpio = codIntermedio.remplazarIF(codLimpio);
        codIntermdioAmedias = codLimpio.split("\n");
        //  OPTIMIZADO
        codigoLimpioOptimizar = codIntermedio.remplazarIF(codigoLimpioOptimizar);
        codIntermedioOP = codigoLimpioOptimizar.split("\n");

        // Reemplazar variables        
        int i = 0;
        for (String linea : codIntermdioAmedias) {
            i++;
            if (codIntermedio.cadenaPerteneceAlMapa(linea, identificadores)) {
                linea = codIntermedio.reemplazarVariables(linea, identificadores);
                if (linea.contains("$")) {
                    linea = linea.replace("$", "");
                }
                codIntermdioAmedias[i - 1] = linea;
            }
        }

        // Reemplazar variables OPTIMIZADO
        i = 0;
        for (String linea : codIntermedioOP) {
            i++;
            if (codIntermedio.cadenaPerteneceAlMapa(linea, identificadores)) {
                linea = codIntermedio.reemplazarVariables(linea, identificadores);
                if (linea.contains("$")) {
                    linea = linea.replace("$", "");
                }
                codIntermedioOP[i - 1] = linea;
            }
        }

        // Analisis completo
        i = 0;
        for (String linea : codIntermdioAmedias) {
            i++;
            // Agregar etiquetas a funciones
            if (linea.contains("fn #")) {
                String salto = codIntermedio.renombrarFuncion(linea);
                if (salto.contains(" ")) {
                    salto = salto.replace(" ", "");
                }
                if (salto.contains("\t")) {
                    salto = salto.replace("\t", "");
                }
                codigoIntermedioSinOptimizar += "\n" + salto + "\n";
                continue;
            }

            // Agregra saltos a condicionales
            if (linea.contains("TRUE") || linea.contains("FALSE") || linea.contains("L")) {
                if (linea.contains("\t")) {
                    linea = linea.replace("\t", "");
                }
                codigoIntermedioSinOptimizar += "\n" + linea + "\n";
            }

            // Agregrar saltos a repeticiones
            if (linea.contains("rep")) {
                codigoIntermedioSinOptimizar += codIntermedio.repsIf(linea) + "\n";
            }

            // Reemplazar compases buzzer
            if (linea.contains("[") && linea.contains("]")) {
                codigoIntermedioSinOptimizar += codIntermedio.codigoIntermedioNotas(linea) + "\n";
            }

            // Generar funciones del piano
            if (linea.contains(".P-")) {
                //funcionesxd += funcionesPiano.generarFuncionesPiano(linea);
            }

            // Crear saltos a funciones
            if (linea.contains("#")) {
                String llamado = codIntermedio.llamarFuncion(linea);
                llamado = llamado.substring(0, llamado.length() - 2);
                if (llamado.contains(" ")) {
                    llamado = llamado.replace(" ", "");
                }
                if (llamado.contains("\t")) {
                    llamado = llamado.replace("\t", "");
                }
                llamado = llamado.replace("#", "jmp ");
                codigoIntermedioSinOptimizar += llamado + "\n";
            }
        }

        // Analisis completo OPTIMIZADO
        i = 0;
        for (String linea : codIntermedioOP) {
            i++;
            // Agregar etiquetas a funciones
            if (linea.contains("fn #")) {
                String salto = codIntermedio.renombrarFuncion(linea);
                if (salto.contains(" ")) {
                    salto = salto.replace(" ", "");
                }
                if (salto.contains("\t")) {
                    salto = salto.replace("\t", "");
                }
                codigoIntermedioOptimizado += "\n" + salto + "\n";
                continue;
            }

            // Agregra saltos a condicionales
            if (linea.contains("TRUE") || linea.contains("L")) {
                if (linea.contains("\t")) {
                    linea = linea.replace("\t", "");
                }
                codigoIntermedioOptimizado += "\n" + linea + "\n";
            }

            // Agregrar saltos a repeticiones
            if (linea.contains("rep")) {
                codigoIntermedioOptimizado += codIntermedio.repsIf(linea) + "\n";
            }

            // Reemplazar compases buzzer
            if (linea.contains("[") && linea.contains("]")) {
                codigoIntermedioOptimizado += codIntermedio.codigoIntermedioNotas(linea) + "\n";
            }

            // Generar funciones del piano
            if (linea.contains(".P-")) {
                //funcionesxd += funcionesPiano.generarFuncionesPiano(linea);
            }

            // Crear saltos a funciones
            if (linea.contains("#")) {
                String llamado = codIntermedio.llamarFuncion(linea);
                llamado = llamado.substring(0, llamado.length() - 2);
                if (llamado.contains(" ")) {
                    llamado = llamado.replace(" ", "");
                }
                if (llamado.contains("\t")) {
                    llamado = llamado.replace("\t", "");
                }
                llamado = llamado.replace("#", "jmp ");
                codigoIntermedioOptimizado += llamado + "\n";
            }
        }

        // Limpiar repeticiones para generar notas completas de ciclos
        codLimpio = funcionesPiano.procesarRepeticiones(codLimpio);
        codLimpio.replace("\t", "");

        // Limpiar repeticiones para generar notas completas de ciclos OPTIMIZADO
        codigoLimpioOptimizar = funcionesPiano.procesarRepeticiones(codigoLimpioOptimizar);
        codigoLimpioOptimizar.replace("\t", "");

        // Reemplazar funciones en el codigo
//        System.out.println("CODIGO LIMPIO ULTIMA REVICION: \n" + codLimpio);
//
//        reemplazoFunciones = codIntermedio.extractFunctions(codLimpio);
//        System.out.println("CODIGO LIMPIO DESPUES COSA 1 VARIABLES:\n" + codLimpio);
//
//        codLimpio = codIntermedio.replaceFunctionCalls(codLimpio, reemplazoFunciones);
//        System.out.println("CODIGO LIMPIO DESPUES COSA 2 VARIABLES:\n" + codLimpio);
        codIntermdioAmedias = codLimpio.split("\n");
        codIntermedioOP = codigoLimpioOptimizar.split("\n");

        // Reemplazar variables otra vez xd
        i = 0;
        for (String linea : codIntermdioAmedias) {
            i++;
            if (codIntermedio.cadenaPerteneceAlMapa(linea, identificadores)) {
                linea = codIntermedio.reemplazarVariables(linea, identificadores);
                if (linea.contains("$")) {
                    linea = linea.replace("$", "");
                }
                codIntermdioAmedias[i - 1] = linea;
            }
        }

        // Reemplazar variables otra vez xd
        i = 0;
        for (String linea : codIntermedioOP) {
            i++;
            if (codIntermedio.cadenaPerteneceAlMapa(linea, identificadores)) {
                linea = codIntermedio.reemplazarVariables(linea, identificadores);
                if (linea.contains("$")) {
                    linea = linea.replace("$", "");
                }
                codIntermedioOP[i - 1] = linea;
            }
        }

        // Generar las notas para el piano y el speaker
        for (String linea : codIntermedioOP) {

            // Reemplazar compases buzzer
            if (linea.contains("[") && linea.contains("]")) {
                if (linea.contains(".P-")) {
                    funcionesxd += funcionesPiano.generarFuncionesPiano(linea);
                    continue;
                }
                notasxd += codIntermedio.codigoIntermedioNotas(linea) + ",\n";
            }
        }
    }

    private void aumentarFuente() {
        float size = jtpCode.getFont().getSize() + 2; // Incremento de tamaño de la fuente
        jtpCode.setFont(jtpCode.getFont().deriveFont(size));
    }

    private void disminuirFuente() {
        float size = jtpCode.getFont().getSize() - 2; // Decremento de tamaño de la fuente
        jtpCode.setFont(jtpCode.getFont().deriveFont(size));
    }

    private void compilar() {
        limpiarCampos();
        analisisLexico();
        rellenarTablaTokens();
        analisisSintactico();
        analisisSemantico();
        mostrarConsola();
    }

    private void analisisLexico() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void analisisSintactico() {
        Grammar gramatica = new Grammar(tokens, errors);
        /* ERRORES LEXICOS*/
        gramatica.delete(new String[]{"ERROR", "ERROR_RESERVADA", "ERROR_IDENTIFICADOR"},
                1, "Error Lexico: NO se esperaba el token [#,%]");

        /* GRUPO de FIGURAS */
        gramatica.group("FIGURA", "(TOKEN_REDONDA|TOKEN_BLANCA|TOKEN_NEGRA|TOKEN_CORCHEA|TOKEN_SEMICORCHEA|TOKEN_FUSA|TOKEN_SEMIFUSA|"
                + "TOKEN_SILENCIO_REDONDA|TOKEN_SILENCIO_BLANCA|TOKEN_SILENCIO_NEGRA|TOKEN_SILENCIO_CORCHEA|TOKEN_SILENCIO_SEMICORCHEA|TOKEN_SILENCIO_FUSA|TOKEN_SILENCIO_SEMIFUSA|"
                + "TOKEN_REDONDA_PIANO|TOKEN_BLANCA_PIANO|TOKEN_NEGRA_PIANO|TOKEN_CORCHEA_PIANO|TOKEN_SEMICORCHEA_PIANO|TOKEN_FUSA_PIANO|TOKEN_SEMIFUSA_PIANO|"
                + "TOKEN_REDONDA_LED|TOKEN_BLANCA_LED|TOKEN_NEGRA_LED|TOKEN_CORCHEA_LED|TOKEN_SEMICORCHEA_LED|TOKEN_FUSA_LED|TOKEN_SEMIFUSA_LED)", true);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* EXPRESION DE CLAVE (IF) */
        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_SELECION_CLAVE TOKEN_DIGITO", true);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION FIGURA CON NOTA ----------------------------------------- */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS? )+");
        });

        //--------------------------------------------------------------------------------------------------------------------------------------------------------
        /* FIGURA NOTA CLAVE */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("FIGURA_NOTA_CLAVE", "(TOKEN_NOTA_CLAVE TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS? )+");
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION DE ESTRUCTURA DE COMPAS (1/2)*/
        gramatica.group("COMPAS", "TOKEN_DIGITO TOKEN_DIVISOR_TEMPO TOKEN_DIGITO");
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* COMPAS NOTAS */
        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS TOKEN_FIN_SENTENCIA", true);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACIÓN COMPAS */
        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION COMPAS TOKEN_FIN_SENTENCIA", true, identProd);

        gramatica.initialLineColumn();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACIÓN TEMPO */
        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_ASIGNACION TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true, identProd);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACIÓN IDENTIFICADOR */
        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION COMPAS_NOTAS", true, identProd);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACIÓN FIGURA CON NOTA */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_NOTAS_TOCAR", "(COMPAS_NOTAS|TOKEN_IDENTIFICADOR TOKEN_FIN_SENTENCIA)* COMPAS_NOTAS|TOKEN_IDENTIFICADOR TOKEN_FIN_SENTENCIA", identProd);
        });

        gramatica.initialLineColumn();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION CLAVE */
        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA TOKEN_SELECION_CLAVE TOKEN_DIGITO", true);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------------------------------------------------
        /* CLAVE DE IF COMPLETA */
        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE CLAVE_IF_EXPRESION TOKEN_CIERRE_CLAVE", true);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION REP */
        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE TOKEN_DIGITO TOKEN_CIERRE_CLAVE", true);

        gramatica.initialLineColumn();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        /* DECLARACION SENTENCIAS */
        gramatica.initialLineColumn();
        gramatica.group("SENTENCIAS", "(BLOQUE_NOTAS_TOCAR | TOKEN_IDENTIFICADOR | COMPAS_NOTAS | TOKEN_IDENTIFICADOR_FUNCION )+ TOKEN_FIN_SENTENCIA", true);

        gramatica.initialLineColumn();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION FUNCION */
        gramatica.group("DECLARACION_FUNCION", "TOKEN_FUNCION TOKEN_IDENTIFICADOR_FUNCION TOKEN_APERTURA_CLAVE TOKEN_CIERRE_CLAVE", true, identProd);

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION FUNCION */
        gramatica.group("FUNCION_EJECUTAR", "TOKEN_IDENTIFICADOR_FUNCION TOKEN_APERTURA_CLAVE TOKEN_CIERRE_CLAVE TOKEN_FIN_SENTENCIA");
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------------------------------------------------------------------------------
        /* ESTRUCTURAS DE CONTROL */
        gramatica.group("ESTRUCTURAS_CONTROL", "(CLAVE_IF | DECLARACION_REP | DECLARACION_FUNCION)", true, identProd);

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* FUNCIONES SALIDA FISICA */
        gramatica.group("FUNCIONES", "TOKEN_PIANO_CONTROL", true);

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("ESTRUCTURA_CONTROL_COMPLETA", "ESTRUCTURAS_CONTROL TOKEN_APERTURA_NOTAS (SENTENCIAS)* TOKEN_CIERRE_NOTAS TOKEN_FIN_SENTENCIA", true);
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* DECLARACION INICIO-FIN */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_INICIO_FIN", "TOKEN_INICIO_PARTITURA TOKEN_FIN_SENTENCIA (SENTENCIAS | ESTRUCTURA_CONTROL_COMPLETA)? TOKEN_FINAL_PARTITURA TOKEN_FIN_SENTENCIA", true);
        });

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_SALIDA_FISICA", "FUNCIONES TOKEN_APERTURA_NOTAS (ESTRUCTURA_CONTROL_COMPLETA | SENTENCIAS)+ TOKEN_CIERRE_NOTAS TOKEN_FIN_SENTENCIA", true);
        });

        gramatica.finalLineColumn();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        /* ERRORES DE SINTACTICOS */
        // Verificar llaves
        verificarLlaves();
        verificarTokens();

        // Continuar con errores sintacticos
        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_NOTA FIGURA )+", true,
                73, "Error sintáctico {}: Hace falta una separacion de figura nota ',' [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE TOKEN_DIGITO", true,
                2, "Error sintáctico {}: No hay una nota de clave selecionada [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_DIGITO", true,
                3, "Error sintáctico {}: Hace falta el token de selección de clave '^' [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_SELECION_CLAVE", true,
                4, "Error sintáctico {}: Hace falta una declaracon de octava en la selección de clave [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_SELECION_CLAVE FIGURA", true,
                5, "Error sintáctico {}: No es podibles asignar una figura una clave [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "FIGURA TOKEN_SELECION_CLAVE TOKEN_DIGITO", true,
                6, "Error sintáctico {}: No es posible asignar un valor numerico a una figura dentro de la selección de clave [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE FIGURA TOKEN_DIGITO", true,
                7, "Error sintáctico {}: NO es posible usar una figura como selección de clave, es necesario un selector de clave '^' [#,%]");

        gramatica.group("COMPAS", "TOKEN_DIVISOR_TEMPO TOKEN_DIGITO", true,
                8, "Error sintáctico {}: Declaracion de compas incompleta (Ejemplo: 3/4) [#,%]");

        gramatica.group("COMPAS", "TOKEN_DIGITO TOKEN_DIVISOR_TEMPO", true,
                9, "Error sintáctico {}: Declaracion de compas incompleta (Ejemplo: 3/4) [#,%]");

        gramatica.group("COMPAS", "TOKEN_DIVISOR_TEMPO", true,
                10, "Error sintáctico {}: Declaracion de compas incompleta (Ejemplo: 3/4) [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_DIGITO", true,
                11, "Error sintáctico {}: Digito fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE", true,
                12, "Error sintáctico {}: Token de selección de clave '^' fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE", true,
                13, "Error sintáctico {}: Nota de clave sin contexto [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS", true,
                14, "Error sintáctico {}: Error en el bloque de notas del compas hace falta un terminador de sentencia ';' [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_FIN_SENTENCIA", true,
                15, "Error sintáctico {}: Hace falta un cierre de compas ']' [#,%]");

        gramatica.group("COMPAS_NOTAS", "(FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS TOKEN_FIN_SENTENCIA", true,
                16, "Error sintáctico {}: Hace falta un inicio de compas '[' [#,%]");

        gramatica.group("COMPAS_NOTAS", "(FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_FIN_SENTENCIA", true,
                17, "Error sintáctico {}: Figuras y notas fuera de compas, hace falta '[' y ']' [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS TOKEN_CIERRE_COMPAS TOKEN_FIN_SENTENCIA", true,
                18, "Error sintáctico {}: Declaración de compas de notas vacio [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ ", true,
                19, "Error sintáctico {}: Hace falta un fin de compas y un terminador de sentencia '];' [#,%]");

        gramatica.group("COMPAS_NOTAS", "(FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS TOKEN_FIN_SENTENCIA", true,
                20, "Error sintáctico {}: Error en el bloque de notas del compas hace falta un terminador de sentencia ';' [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS", true,
                21, "Error sintáctico {}: Declaracion de compas incompleta, solo se ha detectado una apertura de compas [#,%]");

        gramatica.group("COMPAS_NOTAS", "TOKEN_CIERRE_COMPAS", true,
                22, "Error sintáctico {}: Error en el bloque de notas del compas hace falta un terminador de sentencia ';' [#,%]");

        gramatica.group("COMPAS_NOTAS", "(FIGURA_NOTA|FIGURA_NOTA_CLAVE)+", true,
                23, "Error sintáctico {}: Error en el bloque de notas, compas incompleto hace falta '[',']' y ';' (Ejemplo: [F.n, A.b, C.n];) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_FIN_SENTENCIA", true,
                24, "Error sintáctico {}: Falta declarar unidad de compas [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION COMPAS", true,
                25, "Error sintáctico {}: Falta declarar el finalizador de sentencia \';\' [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS COMPAS TOKEN_FIN_SENTENCIA", true,
                26, "Error sintáctico {}: Falta declarar asignación (=) [#,%]");

        gramatica.group("DECLARACION_COMPAS", " TOKEN_ASIGNACION COMPAS TOKEN_FIN_SENTENCIA", true,
                27, "Error sintáctico {}: Falta colocar la variable del compas [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_FIN_SENTENCIA", true,
                28, "Error sintáctico {}: No hay un compás asignado [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION", true,
                29, "Error sintáctico {}: Declaración de compás incompleta, falta especificar el compás (ejemplo: 3/4) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS", true,
                30, "Error sintáctico {}: Declaración de compás incompleta, falta asignar un valor (ejemplo: compas = 3/4;) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_ASIGNACION", true,
                31, "Error sintáctico {}: Token de asignacionde compas fuera de contexto[#,%]");

        gramatica.group("DECLARACION_COMPAS", "COMPAS", true,
                32, "Error sintáctico {}: Declaración de compás incompleta, fuera de contexto [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_ASIGNACION TOKEN_DIGITO+ ", true,
                33, "Error sintáctico {}: Falta declarar el finalizador de sentencia ';' [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_ASIGNACION TOKEN_FIN_SENTENCIA", true,
                34, "Error sintáctico {}: Declaración de tempo incompleta, falta asignar un valor (ejemplo: tempo = 120;) [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true,
                35, "Error sintáctico {}: Declaración de tempo incompleta, falta el simbolo de asignacion '=' [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_FIN_SENTENCIA ", true,
                36, "Error sintáctico {}: NO hay un valor de tempo asignado [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_ASIGNACION TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true,
                37, "Error sintáctico {}: NO se encuentra la palabra clave 'tempo' para hacer la asignación [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true,
                38, "Error sintáctico {}: Digito con final de sentencia fuera de contexto, puede hacer falta una asignación de tempo [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO", true,
                39, "Error sintáctico {}: Token de tempo fuera de contexto [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION COMPAS_NOTAS", true,
                40, "Error sintáctico {}: Falta el finalizador de sentencia \';\' [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION TOKEN_FIN_SENTENCIA", true,
                41, "Error sintáctico {}: Declaración de identificador incompleta, falta asignar un bloque de notas (ejemplo: [F.n, A.b, C.n]) [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR  TOKEN_ASIGNACION COMPAS_NOTAS", true,
                42, "Error sintáctico {}: Declaración de identificador incompleta, NO hay una palabra que pueda ser utilizada para asignación [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_IDENTIFICADOR TOKEN_ASIGNACION COMPAS_NOTAS", true,
                43, "Error sintáctico {}: Declaración de identificador incompleta, falta agregar la palabra reservada 'var' para decrarar el identificador [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION", true,
                44, "Error sintáctico {}: Declaración de identificador incompleta, falta asignar un bloque de notas y el finalizador de sentencias (ejemplo: [F.n, A.b, C.n];) [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR", true,
                45, "Error sintáctico {}: Declaración de identificador incompleta, (ejemplo: var id = [F.n, A.b, C.n];) [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR", true,
                46, "Error sintáctico {}: Declaración de identificador incompleta, no hay información del identificar [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA TOKEN_SELECION_CLAVE", true,
                47, "Error sintáctico {}: Declaración de clave incompleta, hace falta un valor de octava [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA TOKEN_DIGITO", true,
                48, "Error sintáctico {}: Declaración de clave incompleta, no un selector de clave '^' [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE TOKEN_DIGITO", true,
                49, "Error sintáctico {}: Declaración de clave incompleta, no hay una nota a la cual asignar una octava [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA", true,
                50, "Error sintáctico {}: Nota de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE", true,
                51, "Error sintáctico {}: Selección de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE CLAVE_IF_EXPRESION", true,
                52, "Error sintáctico {}: Hace falta un cierre de clave ')'[#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE CLAVE_IF_EXPRESION TOKEN_CIERRE_CLAVE", true,
                53, "Error sintáctico {}: Hace falta una apertura de clave '('[#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE TOKEN_CIERRE_CLAVE", true,
                54, "Error sintáctico {}: Hace falta una valor de clave dentro de la declaracion [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE (TOKEN_DIGITO | TOKEN_TEMPO | TOKEN_ASIGNACION | TOKEN_VAR | TOKEN_IDENTIFICADOR | ERROR_IDENTIFICADOR | COMPAS_ERROR) TOKEN_CIERRE_CLAVE", true,
                55, "Error sintáctico {}: Valor de clave invalido[#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_APERTURA_CLAVE CLAVE_IF_EXPRESION TOKEN_CIERRE_CLAVE", true,
                56, "Error sintáctico {}: Hace falta la palabra reservada 'clave' para la sentencia [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE", true,
                57, "Error sintáctico {}: Token de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_APERTURA_CLAVE", true,
                58, "Error sintáctico {}: Token de apertura de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CIERRE_CLAVE", true,
                59, "Error sintáctico {}: Token de cierre de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF", "CLAVE_IF_EXPRESION", true,
                60, "Error sintáctico {}: Expresion de asignacion de clave fuera de contexto [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_CIERRE_CLAVE", true,
                61, "Error sintáctico {}: Declaracion de clave invalida, no hay una apertura de clave '(' ni un valor de clave [#,%]");

        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE", true,
                62, "Error sintáctico {}: Declaracionde de clave invalida, no hay un cierre de clave ')' ni un valor de clave [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA_CLAVE TOKEN_SEPARACION_NOTA FIGURA )+", true,
                63, "Error sintáctico {}: Hace falta una separacion de figura nota ',' [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA_CLAVE FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                64, "Error sintáctico {}: Hace falta una entre figura y nota '.' [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA_CLAVE TOKEN_SEPARACION_NOTA TOKEN_SEPARACION_COMPAS? )+", true,
                65, "Error sintáctico {}: Hace falta un valor de figura [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                66, "Error sintáctico {}: Hace falta una nota (A=La, B=Si, C=Do, D=Re, E=Mi, F=Fa, G=Sol) [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA_CLAVE TOKEN_SEPARACION_COMPAS? )+", true,
                67, "Error sintáctico {}: No se encontro la separacion de figura y nota, ni un valor para de figura [#,%]");

        gramatica.group("FIGURA_NOTA", "( FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                68, "Error sintáctico {}: No se ha encontrado un token de nota, ni el token de separacion de nota y figura '.' [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_SEPARACION_COMPAS", true,
                69, "Error sintáctico {}: Separacion de compas fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "FIGURA", true,
                70, "Error sintáctico {}: Figura fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_SEPARACION_NOTA", true,
                71, "Error sintáctico {}: Token de separacion de nota fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_NOTA_CLAVE", true,
                72, "Error sintáctico {}: Token de nota fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_NOTA FIGURA )+", true,
                73, "Error sintáctico {}: Hace falta una separacion de figura nota ',' [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                74, "Error sintáctico {}: Hace falta una entre figura y nota '.' [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_NOTA TOKEN_SEPARACION_COMPAS? )+", true,
                75, "Error sintáctico {}: Hace falta un valor de figura [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                76, "Error sintáctico {}: Hace falta una nota (A=La, B=Si, C=Do, D=Re, E=Mi, F=Fa, G=Sol) [#,%]");

        gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_COMPAS? )+", true,
                77, "Error sintáctico {}: No se encontro la separacion de figura y nota, ni un valor para de figura [#,%]");

        gramatica.group("FIGURA_NOTA", "( FIGURA TOKEN_SEPARACION_COMPAS? )+", true,
                78, "Error sintáctico {}: No se ha encontrado un token de nota, ni el token de separacion de nota y figura '.' [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_SEPARACION_COMPAS", true,
                79, "Error sintáctico {}: Separacion de compas fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "FIGURA", true,
                80, "Error sintáctico {}: Figura fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_SEPARACION_NOTA", true,
                81, "Error sintáctico {}: Token de separacion de nota fuera de contexto [#,%]");

        gramatica.group("FIGURA_NOTA", "TOKEN_NOTA", true,
                82, "Error sintáctico {}: Token de nota fuera de contexto [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE TOKEN_DIGITO", true,
                83, "Error sintáctico {}: Hace falta un cierre de repeticion ')' [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE (TOKEN_DIGITO | TOKEN_TEMPO | TOKEN_ASIGNACION | TOKEN_VAR | TOKEN_IDENTIFICADOR | ERROR_IDENTIFICADOR | COMPAS_ERROR) TOKEN_CIERRE_CLAVE", true,
                84, "Error sintáctico {}: Declaración de repetición incompleta, falta el contenido (ejemplo: \\rep {...};) [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_DIGITO TOKEN_CIERRE_CLAVE", true,
                85, "Error sintáctico {}: Hace falta una apertura de selecion de repeticion '(' [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_APERTURA_CLAVE TOKEN_DIGITO TOKEN_CIERRE_CLAVE", true,
                86, "Error sintáctico {}: Hace falta la declaracion de repeticion [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE TOKEN_CIERRE_CLAVE", true,
                87, "Error sintáctico {}: No hay un valor asignado a la repeticion [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE", true,
                89, "Error sintáctico {}: Declaracion de repeticion incompleta, hace falta un valor de repeticion y un cierre de repeticion ')' [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_CIERRE_CLAVE", true,
                90, "Error sintáctico {}: Declaracion de repeticion incompleta, hace falta un valor de repeticion y una apertura de repeticion '(' [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP", true,
                91, "Error sintáctico {}: Token de repeticion fuera de contexto [#,%]");

        gramatica.group("SENTENCIAS", "TOKEN_IDENTIFICADOR", true,
                92, "Error sintáctico {}: NO hay un finalizador de sentencia ';'[#,%]");

        gramatica.group("DECLARACION_FUNCION", "TOKEN_FUNCION", true,
                93, "Error sintáctico {}: Declaracion de funcion sin nombre [#,%]");

        gramatica.group("DECLARACION_FUNCION", "TOKEN_FUNCION TOKEN_IDENTIFICADOR_FUNCION TOKEN_CIERRE_CLAVE", true,
                94, "Error sintáctico {}: Declaracion de funcion incompleta, hace falta un token de apertura '(' [#,%]");

        gramatica.group("DECLARACION_FUNCION", "TOKEN_FUNCION TOKEN_IDENTIFICADOR_FUNCION TOKEN_APERTURA_CLAVE", true,
                95, "Error sintáctico {}: Declaracion de funcion incompleta, hace falta un token de cierre ')' [#,%]");

        gramatica.group("DECLARACION_FUNCION", "TOKEN_FUNCION TOKEN_IDENTIFICADOR_FUNCION", true,
                96, "Error sintáctico {}: Declaracion de funcion incompleta, hacen falta '()' despues del nombre de la funcion [#,%]");

        gramatica.group("FUNCION_EJECUTAR", "TOKEN_IDENTIFICADOR_FUNCION TOKEN_CIERRE_CLAVE TOKEN_FIN_SENTENCIA", true,
                97, "Error sintáctico {}: Identificador de funcion incompleta, hace falta un token de apertura '(' [#,%]");

        gramatica.group("FUNCION_EJECUTAR", "TOKEN_IDENTIFICADOR_FUNCION TOKEN_APERTURA_CLAVE TOKEN_FIN_SENTENCIA", true,
                98, "Error sintáctico {}: Identificador de funcion incompleto, hace falta un token de cierre ')' [#,%]");

        gramatica.group("FUNCION_EJECUTAR", "TOKEN_IDENTIFICADOR_FUNCION TOKEN_FIN_SENTENCIA", true,
                99, "Error sintáctico {}: Declaracion de funcion incompleta, hacen falta '()' despues del nombre de la funcion [#,%]");

        gramatica.group("FUNCION_EJECUTAR", "TOKEN_IDENTIFICADOR_FUNCION TOKEN_APERTURA_CLAVE TOKEN_CIERRE_CLAVE", true,
                100, "Error sintáctico {}: Identificador de funcion incompleta, hace falta un fin de sentencia ';' [#,%]");

        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void verificarLlaves() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < jtpCode.getText().length(); i++) {
            char caracter = jtpCode.getText().charAt(i);
            if (caracter == '{') {
                stack.push(caracter);
            } else if (caracter == '}') {
                if (stack.isEmpty()) {
                    errors.add(new ErrorLSSL(111, "Error sintactico {}: Llave cerrada sin ser abierta en la posición ", new Token("{", "}", 1, 1)));
                    return;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            errors.add(new ErrorLSSL(112, "Error sintactico {}: Llave abierta sin ser cerrada", new Token("{", "}", 1, 1)));
            while (!stack.isEmpty()) {
                char llave = stack.pop();
                System.out.println("Llave sin cerrar en la pila: " + llave);
            }
        } else {
            System.out.println("Todas las llaves están balanceadas.");
        }
    }

    private void verificarTokens() {
        Stack<String> stack = new Stack<>();

        int posicion = 0;
        int linea = 1;

        while (posicion < jtpCode.getText().length()) {
            if (jtpCode.getText().startsWith("\\inicio", posicion)) {
                stack.push("\\inicio");
                posicion += "\\inicio".length();
            } else if (jtpCode.getText().startsWith("\\fin", posicion)) {
                if (stack.isEmpty() || !stack.pop().equals("\\inicio")) {
                    errors.add(new ErrorLSSL(113, "Error sintactico {}: Token \\fin sin el correspondiente \\inicio en la línea " + linea + ", posición " + posicion, new Token("\\inicio", "\\inicio", 1, 1)));
                    return;
                }
                posicion += "\\fin".length();
            } else if (jtpCode.getText().charAt(posicion) == '\n') {
                linea++;
                posicion++;
            } else {
                posicion++;
            }
        }

        if (!stack.isEmpty()) {
            errors.add(new ErrorLSSL(114, "Error sintactico {}: Token \\inicio sin el correspondiente \\fin", new Token("{", "}", 1, 1)));
            while (!stack.isEmpty()) {
                String token = stack.pop();
                System.out.println("Token \\inicio sin el correspondiente \\fin en la pila: " + token);
            }
        } else {
            System.out.println("Todos los tokens están balanceados.");
        }
    }

    private void analisisSemantico() {
        // Variables auxiliares
        int it = 0;
        double sum = 0;
        double valorCompas;
        String produccionesEvaluar = "";
        String[] elementosDentroCompas;
        Map<String, Double> diccionarioFiguraNota = analizadorSemantico.crearDiccionarioFiguraValor();

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de rango tempo
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);
            if (analizadorSemantico.validarTempo(produccionesEvaluar) && it == 1) // Error: Numero de notas menor al compas
            {
                errors.add(new ErrorLSSL(101, "Error semantico {}: El valor del tempo es invalido (no puede ser MENOR a 40 o MAYOR a 208) en la linea: " + id.getLine(), id, true));
            }
            it++;

        }// Analisis de rango tempo
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de valores compas
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar = id.lexemeRank(0, -1);
            if (analizadorSemantico.validarTamanoCompas(produccionesEvaluar)) // Error: Numero de notas menor al compas
            {
                errors.add(new ErrorLSSL(102, "Error semantico {}: El valor del compas es invalido (numerador o denominador > 10) en la linea: " + id.getLine(), id, true));
            }
        }// Analisis de valores compas
        produccionesEvaluar = "";
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de compases
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);

            // Variables de la evaluacion de notas
            diccionarioFiguraNota = analizadorSemantico.crearDiccionarioFiguraValor();
            elementosDentroCompas = analizadorSemantico.extraerElementosCorchetesCompas(produccionesEvaluar);
            valorCompas = analizadorSemantico.calcularCompas(produccionesEvaluar);

            // Extraer los valores a evaluar de las producciones
            for (String element : elementosDentroCompas) {
                sum = analizadorSemantico.sumaValoresCompas(element, diccionarioFiguraNota);
            }

            if (sum == 0) {
                continue;
            }
            if (valorCompas > sum) { // Error: Numero de notas menor al compas
                errors.add(new ErrorLSSL(103, "Error semantico {}: Las notas son menores al compas (" + valorCompas + " > " + sum + ") en la linea: " + id.getLine(), id, true));
            } else if (valorCompas < sum) { // Error: Numero de notas mayor al compas
                errors.add(new ErrorLSSL(104, "Error semantico {}: Las notas son mayores al compas (" + valorCompas + " < " + sum + ") en la linea: " + id.getLine(), id, true));
            }
        }// Analisis de compases
        produccionesEvaluar = "";
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Elementos mixtos
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);

            String[] elementosCorchetes = analizadorSemantico.extraerElementosCorchetesCompas(produccionesEvaluar);
            String[] elementosPuntoComa = analizadorSemantico.obtenerElementosPuntoComa(elementosCorchetes);

            if (analizadorSemantico.verificarElementosMixtos(elementosPuntoComa)) {
                //System.out.println("Error: Se encontraron elementos mixtos en el arreglo, linea: "+id.getLine());
            } else {
                //System.out.println("Todos los elementos comienzan con 'P' o ninguno lo hace, linea:"+id.getLine());
            }
        }// Elementos mixtos
        produccionesEvaluar = "";
        //--------------------------------------------------------------------------------------- ----------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Numero maximo de repeticiones
        analizadorSemantico.validarRepeticiones(jtpCode.getText(), errors);
        // Numero maximo de repeticiones
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis, siempre deben aparecer compas y tempo
        analizadorSemantico.verificarTexto(jtpCode.getText().toString(), errors);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Comprobar variables sin declarar
        Stack<ElementoPila> pila = analizadorSemantico.guardarElementosConDolarEnPila(jtpCode.getText());
        analizadorSemantico.contarRepetidos(pila, errors, jtpCode.getText());
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Comprobar numeros dentro de claves
        String[] claves = analizadorSemantico.buscarClavesEnTexto(jtpCode.getText());
        analizadorSemantico.validarClaves(claves, errors, jtpCode.getText());
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    private void colores() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void rellenarTablaTokens() {
        btnTablaSimbolos.setBackground(Color.green);
        limpiarTablaTokens();
        tokens.forEach(token -> {
            Object[] data = new Object[]{id(token.getLexicalComp()), token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(TablaDSimbolos.getTblTokens(), data);
        });
    }

    private void limpiarTablaTokens() {
        DefaultTableModel modelo = (DefaultTableModel) TablaDSimbolos.getTblTokens().getModel();

        // Limpiar todas las filas
        modelo.setRowCount(0);

        // Refrescar la vista de la tabla
        modelo.fireTableDataChanged();

        // Asignar modelo de tabla limpia
        TablaDSimbolos.getTblTokens().setModel(modelo);
    }

    public String id(String n) {
        switch (n) {
            case "TOKEN_DIGITO":
                return "1";
            case "TOKEN_NOTA":
                return "2";
            case "TOKEN_CLAVE":
                return "3";
            case "TOKEN_COMPAS":
                return "4";
            case "TOKEN_TEMPO":
                return "5";
            case "TOKEN_INICIO_PARTITURA":
                return "6";
            case "TOKEN_FINAL_PARTITURA":
                return "7";
            case "TOKEN_REDONDA":
                return "8";
            case "TOKEN_BLANCA":
                return "9";
            case "TOKEN_NEGRA":
                return "10";
            case "TOKEN_CORCHEA":
                return "11";
            case "TOKEN_SEMICORCHEA":
                return "12";
            case "TOKEN_FUSA":
                return "13";
            case "TOKEN_SEMIFUSA":
                return "14";
            case "TOKEN_SILENCIO_REDONDA":
                return "15";
            case "TOKEN_SILENCIO_BLANCA":
                return "16";
            case "TOKEN_SILENCIO_NEGRA":
                return "17";
            case "TOKEN_SILENCIO_CORCHEA":
                return "18";
            case "TOKEN_SILENCIO_SEMICORCHEA":
                return "19";
            case "TOKEN_SILENCIO_FUSA":
                return "20";
            case "TOKEN_SILENCIO_SEMIFUSA":
                return "21";
            case "TOKEN_PUNTILLO":
                return "22";
            case "TOKEN_SOSTENIDO":
                return "23";
            case "TOKEN_BEMOL":
                return "24";
            case "TOKEN_DIVISOR_TEMPO":
                return "25";
            case "TOKEN_DIVISOR_COMPAS":
                return "26";
            case "TOKEN_APERTURA_NOTAS":
                return "27";
            case "TOKEN_APERTURA_COMPAS":
                return "28";
            case "TOKEN_APERTURA_CLAVE":
                return "29";
            case "TOKEN_CIERRE_NOTAS":
                return "30";
            case "TOKEN_CIERRE_COMPAS":
                return "31";
            case "TOKEN_CIERRE_CLAVE":
                return "32";
            case "TOKEN_ASIGNACION":
                return "33";
            case "TOKEN_SEPARACION_COMPAS":
                return "34";
            case "TOKEN_DEFINE_CLAVE":
                return "35";
            case "TOKEN_FIN_SENTENCIA":
                return "36";
            case "TOKEN_SEPARACION_NOTA":
                return "37";
            case "TOKEN_IDENTIFICADOR":
                return "38";
            case "TOKEN_REDONDA_PIANO":
                return "39";
            case "TOKEN_BLANCA_PIANO":
                return "40";
            case "TOKEN_NEGRA_PIANO":
                return "41";
            case "TOKEN_CORCHEA_PIANO":
                return "42";
            case "TOKEN_SEMICORCHEA_PIANO":
                return "43";
            case "TOKEN_FUSA_PIANO":
                return "44";
            case "TOKEN_SEMIFUSA_PIANO":
                return "45";
            case "TOKEN_REDONDA_LED":
                return "46";
            case "TOKEN_BLANCA_LED":
                return "47";
            case "TOKEN_NEGRA_LED":
                return "48";
            case "TOKEN_CORCHEA_LED":
                return "49";
            case "TOKEN_SEMICORCHEA_LED":
                return "50";
            case "TOKEN_FUSA_LED":
                return "51";
            case "TOKEN_SEMIFUSA_LED":
                return "52";
            case "TOKEN_SELECION_CLAVE":
                return "53";
            case "TOKEN_PIANO_CONTROL":
                return "54";
            case "TOKEN_REP":
                return "55";
            case "TOKEN_NOTA_CLAVE":
                return "56";
            case "TOKEN_VAR":
                return "57";
            case "TOKEN_FUNCION":
                return "58";
            case "TOKEN_IDENTIFICADOR_FUNCION":
                return "59";
            default:
                return "";
        }
    }

    private void mostrarConsola() {
        // Contar numero de errores
        int sizeErrors = errors.size();

        // Crear tabla
        DefaultTableModel modeloTblErrores = (DefaultTableModel) tblErrores.getModel();

        // Acciones si hay errores en la tabla
        if (sizeErrors > 0) {
            codeHasBeenCompiled = false;
            // Action listener para abrir imagenes
            // Agregar un MouseListener a la segunda columna para abrir la imagen al hacer clic
            tblErrores.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tblErrores.rowAtPoint(e.getPoint());
                    int col = tblErrores.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        Object value = tblErrores.getValueAt(row, col);
                        if (value != null) {
                            mostrarImagen(value.toString());
                        }
                    }
                }
            });

            // Generar diccionario de errores
            generarDiccionarioErrores();

            // Agregrar filas a la tabla
            modeloTblErrores.setRowCount(sizeErrors);

            modeloTblErrores.fireTableDataChanged();

            // Cargar nuevo modelo en la tabla
            tblErrores.setModel(modeloTblErrores);

            // Ordenar por linea
            Functions.sortErrorsByLineAndColumn(errors);

            // Iniciarlizar variables auxiliares
            // Expresión regular para encontrar el número antes de ':'
            Pattern pattern = Pattern.compile("\\d+(?=:)");
            String imagenError = "";
            int i = 0;

            // Agregar errores a la tabla
            for (ErrorLSSL error : errors) {
                // String del error
                String strError = String.valueOf(error);

                // Encontrar el número en el texto1
                Matcher matcher = pattern.matcher(strError);
                if (matcher.find()) {
                    String numeroError = matcher.group();
                    imagenError = diccionarioErroresImagenes.get(Integer.parseInt(numeroError));
                }

                tblErrores.setValueAt(strError, i, 0);
                tblErrores.setValueAt(imagenError, i, 1);
                i++;
            }
        } else {
            JOptionPane.showMessageDialog(this, "El código compilo sin errores", "Compilación terminada", JOptionPane.INFORMATION_MESSAGE);
            codeHasBeenCompiled = true;
        }
    }

    private void mostrarImagen(String linkImagen) {
        try {
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                // Sistema operativo Windows
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", linkImagen);
                processBuilder.start();
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
                // Sistemas operativos Unix (Linux, macOS)
                ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", linkImagen);
                processBuilder.start();
            } else {
                System.out.println("Sistema operativo no soportado para abrir imágenes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generarDiccionarioErrores() {
        diccionarioErroresImagenes = new HashMap<>();

        // Agregar relaciones al diccionario (número de error -> ruta de la imagen)
        //diccionarioErroresImagenes.put(1, "ruta/a/imagen1.jpg");
        String base = "/home/kobayashi/Desktop/Semestre7/4-Automatas2/todoxd/Compilador-main/src/imagenes/ImgGramaticas/";
        diccionarioErroresImagenes.put(2, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(3, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(4, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(5, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(6, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(7, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(8, base + "Compas.jpeg");
        diccionarioErroresImagenes.put(9, base + "Compas.jpeg");
        diccionarioErroresImagenes.put(10, base + "Compas.jpeg");
        diccionarioErroresImagenes.put(11, base + "ClaveIfExpresion.jpeg");

        diccionarioErroresImagenes.put(12, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(13, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(14, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(15, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(16, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(17, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(18, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(19, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(20, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(21, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(22, base + "CompasNotas.jpeg");

        diccionarioErroresImagenes.put(23, base + "CompasNotas.jpeg");
        diccionarioErroresImagenes.put(24, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(25, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(26, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(27, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(28, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(29, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(30, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(31, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(32, base + "Declaracion compas.jpeg");
        diccionarioErroresImagenes.put(33, base + "DeclaracionTempo.jpeg");

        diccionarioErroresImagenes.put(34, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(35, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(36, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(37, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(38, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(39, base + "DeclaracionTempo.jpeg");
        diccionarioErroresImagenes.put(40, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(41, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(42, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(43, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(44, base + "DeclaracionIdentificador.jpeg");

        diccionarioErroresImagenes.put(45, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(46, base + "DeclaracionIdentificador.jpeg");
        diccionarioErroresImagenes.put(47, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(48, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(49, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(50, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(51, base + "ClaveIfExpresion.jpeg");
        diccionarioErroresImagenes.put(52, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(53, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(54, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(55, base + "ClaveIf.jpeg");

        diccionarioErroresImagenes.put(56, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(57, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(58, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(59, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(60, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(61, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(62, base + "ClaveIf.jpeg");
        diccionarioErroresImagenes.put(63, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(64, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(65, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(66, base + "FiguraNota.jpg");

        diccionarioErroresImagenes.put(67, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(68, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(69, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(70, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(71, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(72, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(73, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(74, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(75, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(76, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(77, base + "FiguraNota.jpg");

        diccionarioErroresImagenes.put(78, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(79, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(80, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(81, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(82, base + "FiguraNota.jpg");
        diccionarioErroresImagenes.put(83, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(84, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(85, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(86, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(87, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(88, base + "DeclaracionRep.jpeg");

        diccionarioErroresImagenes.put(89, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(90, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(91, base + "DeclaracionRep.jpeg");
        diccionarioErroresImagenes.put(92, base + "Sentencias.jpeg");
        diccionarioErroresImagenes.put(93, base + "DeclaracionFuncion.jpg");
        diccionarioErroresImagenes.put(94, base + "DeclaracionFuncion.jpg");
        diccionarioErroresImagenes.put(95, base + "DeclaracionFuncion.jpg");
        diccionarioErroresImagenes.put(96, base + "DeclaracionFuncion.jpg");
        diccionarioErroresImagenes.put(97, base + "FuncionEjecutar.jpg");
        diccionarioErroresImagenes.put(98, base + "FuncionEjecutar.jpg");
        diccionarioErroresImagenes.put(99, base + "FuncionEjecutar.jpg");

        diccionarioErroresImagenes.put(100, base + "FuncionEjecutar.jpg");
        diccionarioErroresImagenes.put(101, base + "ValidarTempo.jpeg");
        diccionarioErroresImagenes.put(102, base + "ValidarTamanoCompas.jpeg");
        diccionarioErroresImagenes.put(103, base + "SumaValoresCompas.jpeg");
        diccionarioErroresImagenes.put(104, base + "SumaValoresCompas.jpeg");
        diccionarioErroresImagenes.put(105, base + "VerificarTexto.jpeg");
        diccionarioErroresImagenes.put(106, base + "VerificarTexto.jpeg");
        diccionarioErroresImagenes.put(107, base + "VerificarTexto.jpeg");
        diccionarioErroresImagenes.put(108, base + "ContarRepetidos.jpeg");
        diccionarioErroresImagenes.put(109, base + "ValidarClaves.jpeg");
        diccionarioErroresImagenes.put(110, base + "ValidarRepeticiones.jpeg");

        diccionarioErroresImagenes.put(111, base + "EstructuraControlCompleta.jpeg");
        diccionarioErroresImagenes.put(112, base + "EstructuraControlCompleta.jpeg");
        diccionarioErroresImagenes.put(113, base + "BloqueInicioFin.jpeg");
        diccionarioErroresImagenes.put(114, base + "BloqueInicioFin.jpeg");
    }

    private void limpiarCampos() {
        Functions.clearDataInTable(tblErrores);
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
        notasxd = "";
        funcionesxd = "";
        nombreArchivo = "";
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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAnalizadores;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnGramaticas;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC1;
    private javax.swing.JButton btnIntermedio;
    private javax.swing.JButton btnIntermedioOptimizado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSubirArduino;
    private javax.swing.JButton btnTablaSimbolos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblErrores;
    // End of variables declaration//GEN-END:variables
}
