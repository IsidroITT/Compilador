
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import compilerTools.Directory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
//import jflex.performance.Timer;

/**
 *
 * @author codex_404
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    private float compasEvaluar;
    private HashMap<String, Integer> dicNotas;
    private HashMap<String, Integer> dicSilencios;
    private AnalisisSemantico elementosCompas;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        jPanel1.setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
    }

    private void init() {
        title = "Codex_Music";// Titulo de la ventana
        setLocationRelativeTo(null);
        setTitle(title);
        btnEjecutar.setVisible(false);
        // algun nombre de extension???
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
        btnEjecutar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        lblASin = new javax.swing.JLabel();
        lblALex = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(188, 255, 237));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 720, -1, -1));

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
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 50));

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
        jPanel1.add(btnAbrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 110, 50));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 120, 50));

        btnGuardarC.setFont(new java.awt.Font("Open Sans Semibold", 1, 15)); // NOI18N
        btnGuardarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32743 (1).png"))); // NOI18N
        btnGuardarC.setText("Guardar como");
        btnGuardarC.setToolTipText("");
        btnGuardarC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        btnGuardarC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 170, 50));

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
        jPanel1.add(btnCompilar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 530, 130, 50));

        jLabel1.setFont(new java.awt.Font("Open Sans", 2, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Codex-Mus c (1).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 351, 78));

        jtpCode.setBackground(new java.awt.Color(102, 0, 102));
        jtpCode.setFont(new java.awt.Font("JetBrainsMono Nerd Font", 0, 24)); // NOI18N
        jtpCode.setForeground(new java.awt.Color(0, 102, 102));
        jtpCode.setCaretColor(new java.awt.Color(255, 255, 255));
        jtpCode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jtpCode);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 840, 430));

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(229, 251, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 710, 199));

        lblASin.setFont(new java.awt.Font("Open Sans Semibold", 0, 18)); // NOI18N
        lblASin.setForeground(new java.awt.Color(52, 73, 94));
        lblASin.setText("Analizador Sintactico");
        jPanel1.add(lblASin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        lblALex.setFont(new java.awt.Font("Open Sans Semibold", 0, 18)); // NOI18N
        lblALex.setForeground(new java.awt.Color(52, 73, 94));
        lblALex.setText("Analizador Lexico");
        jPanel1.add(lblALex, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("-");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 530, 40, 20));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("+");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 40, 20));

        tblTokens.setBackground(new java.awt.Color(229, 251, 255));
        tblTokens.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(52, 73, 94), 2, true));
        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 500, 550));

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel2.setText("Espacio para boton de abrir nuevas ventanas de ayuda xdxdxd");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 660, -1));

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colores();
            limpiarCampos();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            limpiarCampos();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            limpiarCampos();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compilar();
            }
        } else {
            compilar();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);
            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aumentarFuente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        disminuirFuente();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void aumentarFuente() {
        Font font = jtpCode.getFont();
        float size = font.getSize() + 2; // Incremento de tamaño de la fuente
        jtpCode.setFont(font.deriveFont(size));
    }

    private void disminuirFuente() {
        Font font = jtpCode.getFont();
        float size = font.getSize() - 2; // Decremento de tamaño de la fuente
        jtpCode.setFont(font.deriveFont(size));
    }

    private void compilar() {
        limpiarCampos();
        analisisLexico();
        rellenarTablaTokens();
        analisisSintactico();
        analisisSemantico();
        mostrarConsola();
        codeHasBeenCompiled = true;
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
        /* ERRORES */
        gramatica.delete(new String[]{"ERROR", "ERROR_RESERVADA"}, 1);

        /* GRUPOS */
        gramatica.group("FIGURA", "(TOKEN_REDONDA|TOKEN_BLANCA|TOKEN_NEGRA|TOKEN_CORCHEA|TOKEN_SEMICORCHEA|TOKEN_FUSA|TOKEN_SEMIFUSA|"
                + "TOKEN_SILENCIO_REDONDA|TOKEN_SILENCIO_BLANCA|TOKEN_SILENCIO_NEGRA|TOKEN_SILENCIO_CORCHEA|TOKEN_SILENCIO_SEMICORCHEA|TOKEN_SILENCIO_FUSA|TOKEN_SILENCIO_SEMIFUSA|"
                + "TOKEN_REDONDA_PIANO|TOKEN_BLANCA_PIANO|TOKEN_NEGRA_PIANO|TOKEN_CORCHEA_PIANO|TOKEN_SEMICORCHEA_PIANO|TOKEN_FUSA_PIANO|TOKEN_SEMIFUSA_PIANO|"
                + "TOKEN_REDONDA_LED|TOKEN_BLANCA_LED|TOKEN_NEGRA_LED|TOKEN_CORCHEA_LED|TOKEN_SEMICORCHEA_LED|TOKEN_FUSA_LED|TOKEN_SEMIFUSA_LED)", true);

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_SELECION_CLAVE TOKEN_DIGITO", true);

        /* DECLARACION FIGURA CON NOTA ----------------------------------------- */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("FIGURA_NOTA", "(TOKEN_NOTA TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS)* TOKEN_NOTA TOKEN_SEPARACION_NOTA FIGURA");
        });
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("FIGURA_NOTA_CLAVE", "(TOKEN_NOTA_CLAVE TOKEN_SEPARACION_NOTA FIGURA TOKEN_SEPARACION_COMPAS)* TOKEN_NOTA_CLAVE TOKEN_SEPARACION_NOTA FIGURA");
        });
        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS", true);
        gramatica.group("COMPAS", "TOKEN_DIGITO TOKEN_DIVISOR_TEMPO TOKEN_DIGITO");
        //gramatica.group("COMPAS_ERROR", "TOKEN_DIGITO TOKEN_DIGITO | TOKEN_DIGITO");

        /* DECLARACIÓN COMPAS */
        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION COMPAS TOKEN_FIN_SENTENCIA", true, identProd);

        gramatica.initialLineColumn();
        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION COMPAS_ERROR TOKEN_FIN_SENTENCIA", true,
                6, "Error sintáctico {}: Falta declarar unidad de tiempo [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_DIVISOR_TEMPO TOKEN_DIGITO TOKEN_FIN_SENTENCIA", true,
                7, "Error sintáctico {}: Falta declarar unidad de compás [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_DIGITO TOKEN_DIVISOR_TEMPO TOKEN_DIGITO TOKEN_FIN_SENTENCIA", true,
                8, "Error sintáctico {}: Falta declarar asignación (=) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_FIN_SENTENCIA", true,
                9, "Error sintáctico {}: Falta divisor (/) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_FIN_SENTENCIA", true,
                10, "Error sintáctico {}: No hay un compás asignado [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_DIGITO TOKEN_DIVISOR_TEMPO", true,
                11, "Error sintáctico {}: Se esperaba un fin de sentencia (;) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION TOKEN_DIVISOR_TEMPO", true,
                12, "Error sintáctico {}: Se esperaba un número después del divisor (/) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_ASIGNACION", true,
                13, "Error sintáctico {}: Declaración de compás incompleta, falta especificar el compás (ejemplo: 3/4) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS TOKEN_DIGITO", true,
                14, "Error sintáctico {}: Declaración de compás incompleta, falta especificar el divisor (/) [#,%]");

        gramatica.group("DECLARACION_COMPAS", "TOKEN_COMPAS", true,
                15, "Error sintáctico {}: Declaración de compás incompleta, falta asignar un valor (ejemplo: compas = 3/4;) [#,%]");

        /* DECLARACIÓN TEMPO */
        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_ASIGNACION TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true, identProd);

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_ASIGNACION", true,
                16, "Error sintáctico {}: Declaración de tempo incompleta, falta especificar el valor del tempo [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO TOKEN_DIGITO+ TOKEN_FIN_SENTENCIA", true,
                17, "Error sintáctico {}: Declaración de tempo incompleta, falta asignar un valor (ejemplo: tempo = 120;) [#,%]");

        gramatica.group("DECLARACION_TEMPO", "TOKEN_TEMPO", true,
                18, "Error sintáctico {}: Declaración de tempo incompleta, falta asignar un valor (ejemplo: tempo = 120;) [#,%]");

        /* DECLARACIÓN IDENTIFICADOR */
        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION COMPAS_NOTAS TOKEN_FIN_SENTENCIA", true, identProd);

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR TOKEN_ASIGNACION", true,
                19, "Error sintáctico {}: Declaración de identificador incompleta, falta especificar las notas [#,%]");

        gramatica.group("DECLARACION_IDENTIFICADOR", "TOKEN_VAR TOKEN_IDENTIFICADOR COMPAS_NOTAS TOKEN_FIN_SENTENCIA", true,
                20, "Error sintáctico {}: Declaración de identificador incompleta, falta asignar un valor (ejemplo: id = {...};) [#,%]");

        /* DECLARACIÓN FIGURA CON NOTA */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_NOTAS_TOCAR", "(COMPAS_NOTAS|TOKEN_IDENTIFICADOR TOKEN_SEPARACION_COMPAS)* COMPAS_NOTAS|TOKEN_IDENTIFICADOR", identProd);
        });

        gramatica.group("COMPAS_NOTAS", "TOKEN_APERTURA_COMPAS (FIGURA_NOTA|FIGURA_NOTA_CLAVE)+ TOKEN_CIERRE_COMPAS", true);

        gramatica.initialLineColumn();
        gramatica.group("BLOQUE_NOTAS_TOCAR", "COMPAS_NOTAS|TOKEN_IDENTIFICADOR TOKEN_SEPARACION_COMPAS", true,
                22, "Error sintáctico {}: Declaración de notas incompleta, falta especificar las notas o finalizar con un punto y coma (;) [#,%]");

        gramatica.group("BLOQUE_NOTAS_TOCAR", "(COMPAS_NOTAS|TOKEN_IDENTIFICADOR TOKEN_SEPARACION_COMPAS)+", true,
                23, "Error sintáctico {}: Declaración de notas incompleta, falta especificar las notas o finalizar con un punto y coma (;) [#,%]");

        /* DECLARACION CLAVE */
        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA TOKEN_SELECION_CLAVE TOKEN_DIGITO", true);
        gramatica.group("CLAVE_IF", "TOKEN_CLAVE TOKEN_APERTURA_CLAVE CLAVE_IF_EXPRESION TOKEN_CIERRE_CLAVE", true);

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE TOKEN_SELECION_CLAVE", true,
                25, "Error sintáctico {}: Declaración de clave incompleta, falta asignar un valor (ejemplo: clave = G2;) [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_NOTA_CLAVE", true,
                26, "Error sintáctico {}: Declaración de clave incompleta, falta asignar un valor (ejemplo: clave = G2;) [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE TOKEN_DIGITO", true,
                27, "Error sintáctico {}: Declaración de clave incompleta, falta la palabra reservada \"\\clave\" antes de la selección de clave (ejemplo: \\clave G2;) [#,%]");

        gramatica.group("CLAVE_IF_EXPRESION", "TOKEN_SELECION_CLAVE", true,
                28, "Error sintáctico {}: Declaración de clave incompleta, falta la palabra reservada \"\\clave\" antes de la selección de clave (ejemplo: \\clave G2;) [#,%]");

        /* DECLARACION REP */
        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE COMPAS_ERROR+ TOKEN_CIERRE_CLAVE", true);

        gramatica.initialLineColumn();
        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE COMPAS_ERROR* TOKEN_CIERRE_CLAVE", true,
                29, "Error sintáctico {}: Declaración de repetición incompleta, falta especificar las repeticiones o el contenido (ejemplo: \\rep {...};) [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE COMPAS_ERROR+ TOKEN_CIERRE_CLAVE", true,
                30, "Error sintáctico {}: Declaración de repetición incompleta, falta el contenido (ejemplo: \\rep {...};) [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE COMPAS_ERROR+ TOKEN_CIERRE_CLAVE", true,
                31, "Error sintáctico {}: Declaración de repetición incompleta, falta especificar las repeticiones (ejemplo: \\rep 3 {...};) [#,%]");

        gramatica.group("DECLARACION_REP", "TOKEN_REP TOKEN_APERTURA_CLAVE COMPAS_ERROR* TOKEN_CIERRE_CLAVE", true,
                32, "Error sintáctico {}: Declaración de repetición incompleta, falta especificar las repeticiones (ejemplo: \\rep 3 {...};) [#,%]");

        /* DECLARACION SENTENCIAS */
        gramatica.initialLineColumn();
        gramatica.group("SENTENCIAS", "(BLOQUE_NOTAS_TOCAR | TOKEN_SEPARACION_COMPAS | TOKEN_IDENTIFICADOR)+", true);

        gramatica.initialLineColumn();
        /* ESTRUCTURAS DE CONTROL */
        gramatica.group("ESTRUCTURAS_CONTROL", "(CLAVE_IF | DECLARACION_REP)", true, identProd);

        /* FUNCIONES SALIDA FISICA */
        gramatica.group("FUNCIONES", "(TOKEN_PIANO_CONTROL | TOKEN_LEDS)", true);

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("ESTRUCTURA_CONTROL_COMPLETA", "ESTRUCTURAS_CONTROL TOKEN_APERTURA_NOTAS (SENTENCIAS)? TOKEN_CIERRE_NOTAS", true);
        });

        /* DECLARACION INICIO-FIN */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_INICIO_FIN", "TOKEN_INICIO_PARTITURA (SENTENCIAS | ESTRUCTURA_CONTROL_COMPLETA)+ TOKEN_FINAL_PARTITURA", true);
        });

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("BLOQUE_SALIDA_FISICA", "FUNCIONES TOKEN_APERTURA_NOTAS (ESTRUCTURA_CONTROL_COMPLETA | SENTENCIAS)+ TOKEN_CIERRE_NOTAS", true);
        });

        gramatica.finalLineColumn();
        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void analisisSemantico() {
        // Variables auxiliares
        int it = 0;
        double sum = 0;
        double valorCompas;
        String produccionesEvaluar = "";
        String[] elementosDentroCompas;
        Map<String, Double> diccionarioFiguraNota = elementosCompas.crearDiccionarioFiguraValor();

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de rango tempo
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);

            if (elementosCompas.validarTempo(produccionesEvaluar) && it == 1) // Error: Numero de notas menor al compas
            {
                errors.add(new ErrorLSSL(51, "Error: El valor del tempo es invalido (no puede ser MENOR a 40 o MAYOR a 208) en la linea: " + id.getLine(), id, true));
            }
            it++;

        }// Analisis de rango tempo

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de valores compas
        it = 0;
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);

            if (elementosCompas.validarTamanoCompas(produccionesEvaluar) && it == 1) // Error: Numero de notas menor al compas
            {
                errors.add(new ErrorLSSL(50, "Error: El valor del compas es invalido (numerador o denominador > 10) en la linea: " + id.getLine(), id, true));
            }
            it++;
            
        }// Analisis de valores compas
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Analisis de compases
        for (Production id : identProd) {
            // Obtener la produccion a evaluar
            produccionesEvaluar += id.lexemeRank(0, -1);
            System.out.println(produccionesEvaluar);
            // Variables de la evaluacion de notas
            diccionarioFiguraNota = elementosCompas.crearDiccionarioFiguraValor();
            elementosDentroCompas = elementosCompas.extraerElementosCorchetesCompas(produccionesEvaluar);
            valorCompas = elementosCompas.calculateCompas(produccionesEvaluar);
            // Extraer los valores a evaluar de las producciones
            for (String element : elementosDentroCompas) {
                sum = elementosCompas.sumaValoresCompas(element, diccionarioFiguraNota);
            }

            if (valorCompas > sum) { // Error: Numero de notas menor al compas
                errors.add(new ErrorLSSL(52, "Error: Las notas son menores al compas (" + valorCompas + " > " + sum + ") en la linea: " + id.getLine(), id, true));
            } else if (valorCompas < sum) { // Error: Numero de notas mayor al compas
                errors.add(new ErrorLSSL(52, "Error: Las notas son mayores al compas (" + valorCompas + " < " + sum + ") en la linea: " + id.getLine(), id, true));
            }
        }// Analisis de compases
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
        tokens.forEach(token -> {
            Object[] data = new Object[]{id(token.getLexicalComp()), token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
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
            default:
                return "";
        }
    }

    private void mostrarConsola() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada... \n NO hubo errores");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void limpiarCampos() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
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
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JLabel lblALex;
    private javax.swing.JLabel lblASin;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
