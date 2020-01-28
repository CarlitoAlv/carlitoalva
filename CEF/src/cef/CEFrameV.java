
package cef;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class CEFrameV extends javax.swing.JFrame {
        String id, usr;
    public CEFrameV(){}   
    public CEFrameV(String id , String usr) {
        initComponents();
         setExtendedState(MAXIMIZED_BOTH);
         this.setIconImage(new ImageIcon(getClass().getResource("/img/p2.png")).getImage());
         this.setSize(820,700);
         panVeh.setBackground(new Color(205, 120, 50));
        this.usr=usr;
        this.id=id;
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
      
       
       txtBus.setHorizontalAlignment(javax.swing.JLabel.LEFT);
       btnBus.setHorizontalAlignment(javax.swing.JLabel.LEFT);
     
      
       consultarTbGeneral();  
       consultarTbUsuarios();
       consultarEst1();
       consultarEst2();
       consultarVIP();
       conG();
       conCier();
       conCodV();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panVeh = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panBusV = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtT = new javax.swing.JLabel();
        txtVin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSal2 = new javax.swing.JLabel();
        chG = new javax.swing.JCheckBox();
        chbxFec = new javax.swing.JCheckBox();
        txtBus = new javax.swing.JTextField();
        btnBus = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGen = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUs = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableEst1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableEst2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableVip = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableCor = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableCier = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableCodV = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cefnar.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panVehLayout = new javax.swing.GroupLayout(panVeh);
        panVeh.setLayout(panVehLayout);
        panVehLayout.setHorizontalGroup(
            panVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panVehLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        panVehLayout.setVerticalGroup(
            panVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Vehiculos ingresados:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Total de vehiculos:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Vehiculos que han salido:");

        chG.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chG.setText("Todo en general");
        chG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chGActionPerformed(evt);
            }
        });

        chbxFec.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chbxFec.setText("Por fecha");

        txtBus.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnBus.setText("Buscar");
        btnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panBusVLayout = new javax.swing.GroupLayout(panBusV);
        panBusV.setLayout(panBusVLayout);
        panBusVLayout.setHorizontalGroup(
            panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panBusVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVin, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSal2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(chbxFec)
                .addGap(83, 83, 83)
                .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBus)
                .addContainerGap())
        );
        panBusVLayout.setVerticalGroup(
            panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBusVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBusVLayout.createSequentialGroup()
                        .addGroup(panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chG)
                                .addComponent(chbxFec))
                            .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(panBusVLayout.createSequentialGroup()
                        .addGroup(panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(txtSal2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panBusVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtVin, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tableGen.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableGen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableGen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableGen);

        jTabbedPane1.addTab("General", jScrollPane1);

        tableUs.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableUs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableUs);

        jTabbedPane1.addTab("Usuarios", jScrollPane2);

        tableEst1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableEst1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableEst1);

        jTabbedPane1.addTab("Estacionamiento 1", jScrollPane3);

        tableEst2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableEst2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tableEst2);

        jTabbedPane1.addTab("Estacionamiento 2", jScrollPane4);

        tableVip.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableVip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tableVip);

        jTabbedPane1.addTab("Estacionamiento VIP", jScrollPane5);

        tableCor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tableCor);

        jTabbedPane1.addTab("Cortes", jScrollPane6);

        tableCier.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableCier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tableCier);

        jTabbedPane1.addTab("Cierres", jScrollPane7);
        jTabbedPane1.addTab("Repuestos", jScrollPane8);

        tableCodV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tableCodV);

        jTabbedPane1.addTab("Codigos de verificación", jScrollPane9);

        jMenu1.setText("Inicio");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setText("     Actualizar      ");
        jMenu1.add(jMenuItem4);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem1.setText("     Insertar Boleto Modelo     ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        itemSalir.setText("     Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVeh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addComponent(panBusV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panBusV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void consultarTbGeneral(){
    DefaultTableModel model=new DefaultTableModel();
    model.addColumn("IDBOLETO");
    model.addColumn("ESTACIONAMIENTO");
    model.addColumn("PRECIO");
    model.addColumn("ESTADOBOLETO");
    model.addColumn("IDREPUESTO");
    model.addColumn("FEC_ENTRADA");
    model.addColumn("HR_ENTRADA");
    model.addColumn("FEC_SALIDA");
    model.addColumn("HR_SALIDA");
    tableGen.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableGen.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableGen.getColumnModel().getColumn(8).setCellRenderer(tcr);
    tableGen.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosGen =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conGen = sentencia.executeQuery("select * from repuestos order by idrboleto ASC");
            
            while(conGen.next())
            {
            datosGen[0]=conGen.getString("IDRBOLETO");
            datosGen[1]=conGen.getString("ESTACIONAMIENTO");
            datosGen[2]=conGen.getString("PRECIO");
            datosGen[3]=conGen.getString("ESTADOBOLETO");
            datosGen[4]=conGen.getString("IDENTIFICADORREPUESTO");
            datosGen[5]=conGen.getString("FEC_ENTRADA").substring(0,10);
            datosGen[6]=conGen.getString("HR_ENTRADA");
            datosGen[7]=conGen.getString("FEC_SALIDA");
            datosGen[8]=conGen.getString("HR_SALIDA");
            model.addRow(datosGen);
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    
    void consultarTbUsuarios(){
    DefaultTableModel model=new DefaultTableModel();
    model.addColumn("IDUSUARIO");
    model.addColumn("USUARIO");
    model.addColumn("CONTRASENA");
    model.addColumn("TIPOUSUARIO");
    model.addColumn("NOMBRES");
    model.addColumn("APELLIDOS");
    model.addColumn("SEXO");
    model.addColumn("NUMCAJA");
    tableUs.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableUs.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableUs.getColumnModel().getColumn(7).setCellRenderer(tcr);
   tableUs.getTableHeader().setBackground(new Color(255, 200, 70));
    String[] datosUs =new String[8];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
     
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conUs = sentencia.executeQuery("select * from usuarios order by idusuario ASC");
            
            while(conUs.next())
            {
            datosUs[0]=conUs.getString("IDUSUARIO");
            datosUs[1]=conUs.getString("USUARIO");
            datosUs[2]=conUs.getString("CONTRASENA");
            datosUs[3]=conUs.getString("TIPOUSUARIO");
            datosUs[4]=conUs.getString("NOMBRES");
            datosUs[5]=conUs.getString("APELLIDOS");
            datosUs[6]=conUs.getString("SEXO");
            datosUs[7]=conUs.getString("NUMCAJA");
            model.addRow(datosUs);
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    void consultarEst1(){
    DefaultTableModel model=new DefaultTableModel();
    model.addColumn("IDBOLETO");
    model.addColumn("ESTACIONAMIENTO");
    model.addColumn("PRECIO");
    model.addColumn("ESTADOBOLETO");
    model.addColumn("IDREPUESTO");
    model.addColumn("FEC_ENTRADA");
    model.addColumn("HR_ENTRADA");
    model.addColumn("FEC_SALIDA");
    model.addColumn("HR_SALIDA");
    tableEst1.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableEst1.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableEst1.getColumnModel().getColumn(8).setCellRenderer(tcr);
    tableEst1.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosEst1 =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conEst1 = sentencia.executeQuery("select * from repuestos where estacionamiento = 'Estacionamiento 1' order by idrboleto ASC");
            
            while(conEst1.next())
            {
            datosEst1[0]=conEst1.getString("IDRBOLETO");
            datosEst1[1]=conEst1.getString("ESTACIONAMIENTO");
            datosEst1[2]=conEst1.getString("PRECIO");
            datosEst1[3]=conEst1.getString("ESTADOBOLETO");
            datosEst1[4]=conEst1.getString("IDENTIFICADORREPUESTO");
            datosEst1[5]=conEst1.getString("FEC_ENTRADA").substring(0,10);
            datosEst1[6]=conEst1.getString("HR_ENTRADA");
            datosEst1[7]=conEst1.getString("FEC_SALIDA");
            datosEst1[8]=conEst1.getString("HR_SALIDA");
            model.addRow(datosEst1);
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    void consultarEst2(){
    DefaultTableModel model=new DefaultTableModel();
    model.addColumn("IDBOLETO");
    model.addColumn("ESTACIONAMIENTO");
    model.addColumn("PRECIO");
    model.addColumn("ESTADOBOLETO");
    model.addColumn("IDREPUESTO");
    model.addColumn("FEC_ENTRADA");
    model.addColumn("HR_ENTRADA");
    model.addColumn("FEC_SALIDA");
    model.addColumn("HR_SALIDA");
    tableEst2.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableEst2.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableEst2.getColumnModel().getColumn(8).setCellRenderer(tcr);
    tableEst2.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosEst2 =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conEst2 = sentencia.executeQuery("select * from repuestos where estacionamiento = 'Estacionamiento 2' order by idrboleto ASC");
            
            while(conEst2.next())
            {
            datosEst2[0]=conEst2.getString("IDRBOLETO");
            datosEst2[1]=conEst2.getString("ESTACIONAMIENTO");
            datosEst2[2]=conEst2.getString("PRECIO");
            datosEst2[3]=conEst2.getString("ESTADOBOLETO");
            datosEst2[4]=conEst2.getString("IDENTIFICADORREPUESTO");
            datosEst2[5]=conEst2.getString("FEC_ENTRADA").substring(0,10);
            datosEst2[6]=conEst2.getString("HR_ENTRADA");
            datosEst2[7]=conEst2.getString("FEC_SALIDA");
            datosEst2[8]=conEst2.getString("HR_SALIDA");
            model.addRow(datosEst2);
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    void consultarVIP(){
    DefaultTableModel model=new DefaultTableModel();
    model.addColumn("IDBOLETO");
    model.addColumn("ESTACIONAMIENTO");
    model.addColumn("PRECIO");
    model.addColumn("ESTADOBOLETO");
    model.addColumn("IDREPUESTO");
    model.addColumn("FEC_ENTRADA");
    model.addColumn("HR_ENTRADA");
    model.addColumn("FEC_SALIDA");
    model.addColumn("HR_SALIDA");
    tableVip.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableVip.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableVip.getColumnModel().getColumn(8).setCellRenderer(tcr);
    tableVip.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosVip =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conVip = sentencia.executeQuery("select * from repuestos where estacionamiento = 'Estacionamiento VIP' order by idrboleto ASC");
            
            while(conVip.next())
            {
            datosVip[0]=conVip.getString("IDRBOLETO");
            datosVip[1]=conVip.getString("ESTACIONAMIENTO");
            datosVip[2]=conVip.getString("PRECIO");
            datosVip[3]=conVip.getString("ESTADOBOLETO");
            datosVip[4]=conVip.getString("IDENTIFICADORREPUESTO");
            datosVip[5]=conVip.getString("FEC_ENTRADA").substring(0,10);
            datosVip[6]=conVip.getString("HR_ENTRADA");
            datosVip[7]=conVip.getString("FEC_SALIDA");
            datosVip[8]=conVip.getString("HR_SALIDA");
            model.addRow(datosVip);
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    void conG(){
        int total = 0,tIng=0,tSal=3;
         Calendar c = Calendar.getInstance();
           String dia,mes,annio;
           dia = Integer.toString(c.get(Calendar.DATE));
           mes = Integer.toString(c.get(Calendar.MONTH)+1);
           annio = Integer.toString(c.get(Calendar.YEAR));
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conTot = sentencia.executeQuery("select COUNT(*) from repuestos where fec_entrada = '"+dia+"-"+mes+"-"+annio+"'");
            
            while(conTot.next())
            {
            total=conTot.getInt("COUNT(*)");
            }
            txtT.setText(String.valueOf(total));
            sentencia.close();
            
            sentencia = conexion.createStatement();
            ResultSet conIng = sentencia.executeQuery("select COUNT(*) from repuestos where estadoboleto = 'NO AUTORIZADO' AND fec_entrada = '"+dia+"-"+mes+"-"+annio+"'");
            while(conIng.next())
            {
            tIng=conIng.getInt("COUNT(*)");
            }
            txtVin.setText(String.valueOf(tIng));
            sentencia.close();
            /*
            sentencia = conexion.createStatement();
            ResultSet conSa = sentencia.executeQuery("select COUNT(*) from repuestos where fec_salida <> '' AND fec_entrada = '"+dia+"-"+mes+"-"+annio+"'");
            while(conSa.next())
            {
            tSal=conSa.getInt("COUNT(*)");
            }
            */
            txtSal2.setText(String.valueOf(tSal));
  
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar el total de boletos del dia\n -Vuelva a iniciar la ventana de administrador porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
        }
        
    }
    void conAll(){
          int total = 0,tIng=0,tSal=3;
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conTot = sentencia.executeQuery("select COUNT(*) from repuestos");
            
            while(conTot.next())
            {
            total=conTot.getInt("COUNT(*)");
            }
            txtT.setText(String.valueOf(total));
            sentencia.close();
            
            sentencia = conexion.createStatement();
            ResultSet conIng = sentencia.executeQuery("select COUNT(*) from repuestos where estadoboleto = 'NO AUTORIZADO'");
            while(conIng.next())
            {
            tIng=conIng.getInt("COUNT(*)");
            }
            txtVin.setText(String.valueOf(tIng));
            sentencia.close();
            
            
            System.out.println("PIUTPTPP");
  
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar el total de todos los boletos en general\n -Vuelva a iniciar la ventana de administrador porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
        }
    txtSal2.setText(String.valueOf(tSal));
    }
    void conCier(){
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("NO.CIERRE");
    model.addColumn("SUPERVISOR");
    model.addColumn("CAJERO");
    model.addColumn("NUMCAJA");
    model.addColumn("PRECIO");
    model.addColumn("CANT_VENTAS");
    model.addColumn("CANT_TOTAL");
    model.addColumn("FECHA");
    model.addColumn("HORA");
    tableCier.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableCier.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableCier.getColumnModel().getColumn(8).setCellRenderer(tcr);
    tableCier.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosCier =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conCier = sentencia.executeQuery("select * from cierres");
            
            while(conCier.next())
            {
            datosCier[0]=conCier.getString("IDCIERRE");
            datosCier[1]=conCier.getString("USUARIO_SUP");
            datosCier[2]=conCier.getString("USUARIO_CAJ");
            datosCier[3]=conCier.getString("NUMCAJA");
            datosCier[4]=conCier.getString("PRECIO");
            datosCier[5]=conCier.getString("CANT_VENTAS");
            datosCier[6]=conCier.getString("CANT_PTOTAL");
            datosCier[7]=conCier.getString("FECHA").substring(0,10);
            datosCier[8]=conCier.getString("HORA");
             model.addRow(datosCier);
            }
            
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla de cierres\n -Vuelva a iniciar la ventana de administrador porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
        }
    }
    void conCodV(){
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Codigos de autorización");
    
    tableCodV.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tableCodV.getColumnModel().getColumn(0).setCellRenderer(tcr);
     
    tableCodV.getTableHeader().setBackground(new Color(255, 200, 70));

    String[] datosCodV =new String[9];
    try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "orcl";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "C##FENADU";
            String password = "admin";
        
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet conCoV = sentencia.executeQuery("select * from codigosautorizacion");
            
            while(conCoV.next())
            {
            datosCodV[0]=conCoV.getString("CODIGOAUTORIZACION");
          
             model.addRow(datosCodV);
            }
            
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla de cierres\n -Vuelva a iniciar la ventana de administrador porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
        }
    
    }
    
    void btnBusc(){
    String valor = txtBus.getText();
        for (int i = 0; i < tableGen.getRowCount(); i++) {
            if(tableGen.getValueAt(i, 0).equals(valor)){
            tableGen.changeSelection(i, 0, false, false);
            }
            else if(chbxFec.isSelected() && tableGen.getValueAt(i, 5).equals(valor)){
            tableGen.changeSelection(i, 5, false, false);
            }
            else if(tableGen.getValueAt(i, 4).equals(valor)){
            tableGen.changeSelection(i, 4, false, false);
            }
            
            }
        
            for (int j = 0; j < tableUs.getRowCount(); j++) {
            if(tableUs.getValueAt(j, 0).equals(valor)){
            tableUs.changeSelection(j, 0, false, false);
            }
            else if(tableUs.getValueAt(j, 1).equals(valor)){
            tableUs.changeSelection(j, 1, false, false);
            }
            else if(tableUs.getValueAt(j, 4).equals(valor)){
            tableUs.changeSelection(j, 4, false, false);
            }
            else if(tableUs.getValueAt(j, 5).equals(valor)){
            tableUs.changeSelection(j, 5, false, false);
            }
            }
            
            for (int k = 0; k < tableEst1.getRowCount(); k++) {
            if(tableEst1.getValueAt(k, 0).equals(valor)){
            tableEst1.changeSelection(k, 0, false, false);
            }
            else if(chbxFec.isSelected() && tableEst1.getValueAt(k, 5).equals(valor)){
            tableEst1.changeSelection(k, 5, false, false);
            }
            }
            
            for (int l = 0; l < tableEst2.getRowCount(); l++) {
            if(tableEst2.getValueAt(l, 0).equals(valor)){
            tableEst2.changeSelection(l, 0, false, false);
            }
            else if(chbxFec.isSelected() && tableEst2.getValueAt(l, 5).equals(valor)){
            tableEst2.changeSelection(l, 5, false, false);
            }
            }
            
            for (int m = 0; m < tableVip.getRowCount(); m++) {
            if(tableVip.getValueAt(m, 0).equals(valor)){
            tableVip.changeSelection(m, 0, false, false);
            }
            else if(chbxFec.isSelected() && tableEst1.getValueAt(m, 5).equals(valor)){
            tableEst1.changeSelection(m, 5, false, false);
            }
            }
            
            for (int n = 0; n < tableCier.getRowCount(); n++) {
            if(tableCier.getValueAt(n, 0).equals(valor)){
            tableCier.changeSelection(n, 0, false, false);
            }
            else if(chbxFec.isSelected() && tableCier.getValueAt(n, 7).equals(valor)){
            tableCier.changeSelection(n, 7, false, false);
            }
            else if(tableCier.getValueAt(n, 1).equals(valor)){
            tableCier.changeSelection(n, 1, false, false);
            }
            else if(tableCier.getValueAt(n, 2).equals(valor)){
            tableCier.changeSelection(n, 2, false, false);
            }
            }
            
            for (int p = 0; p < tableCodV.getRowCount(); p++) {
            if(tableCodV.getValueAt(p, 0).equals(valor)){
            tableCodV.changeSelection(p, 0, false, false);
            }
            }
            
    }
          
   
    
    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        CEFrame log=new CEFrame();
        this.setVisible(false);
        log.setVisible(true);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void chGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chGActionPerformed
        if(chG.isSelected()){conAll();}
        else{conG();}
    }//GEN-LAST:event_chGActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       consultarTbGeneral();  
       consultarTbUsuarios();
       consultarEst1();
       consultarEst2();
       consultarVIP();
       conG();
       conCier();
       conCodV();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusActionPerformed
     btnBusc();     
    }//GEN-LAST:event_btnBusActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
       
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tableGenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGenMouseClicked
      if(evt.getClickCount()==2){
      if(evt.getButton()==MouseEvent.BUTTON2){
      System.out.println("click izquierdo");  
      
      }
      }
    }//GEN-LAST:event_tableGenMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       String nombre="";
       int prec;
       boolean ban=false;
       while(ban == false){ 
       try{
   
       nombre=JOptionPane.showInputDialog(null, "El nombre del evento es:\n- Maximo 25 caracteres");
       
       if (nombre.length()>25){
       JOptionPane.showMessageDialog(null, "Porfavor ingrese un nombre con el tamaño especifico o menor.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE);
       } 
       else { 
              ban = true;
              prec=Integer.parseInt(JOptionPane.showInputDialog(null,"El precio del boleto será:"));
              System.out.println("El nombre"+nombre+"y el precio será:"+prec);
           }
       } catch(Exception e){
          
           JOptionPane.showMessageDialog(null, "Porfavor ingrese un nombre y precio.\n- No puede dejarlo inespecífico", 
                        "¡UPS!", JOptionPane.ERROR_MESSAGE);
       }
       }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(CEFrameV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFrameV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFrameV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFrameV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBus;
    private javax.swing.JCheckBox chG;
    private javax.swing.JCheckBox chbxFec;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panBusV;
    private javax.swing.JPanel panVeh;
    private javax.swing.JTable tableCier;
    private javax.swing.JTable tableCodV;
    private javax.swing.JTable tableCor;
    private javax.swing.JTable tableEst1;
    private javax.swing.JTable tableEst2;
    private javax.swing.JTable tableGen;
    private javax.swing.JTable tableUs;
    private javax.swing.JTable tableVip;
    private javax.swing.JTextField txtBus;
    private javax.swing.JLabel txtSal2;
    private javax.swing.JLabel txtT;
    private javax.swing.JLabel txtVin;
    // End of variables declaration//GEN-END:variables
}
