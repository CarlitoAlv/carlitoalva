package cef;

import clases.Reporte;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class CEFSuper extends javax.swing.JFrame {
     String id, usr; 
     Icon iconcant = new ImageIcon(getClass().getResource("/img/b.png"));
      
    public CEFSuper(String id , String usr) {
        initComponents();
        this.setSize(1000, 500);
         this.setIconImage(new ImageIcon(getClass().getResource("/img/p.png")).getImage());
         //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
        mostrarUsuarios();
        this.usr=usr;
        this.id=id;
        mostrarCierres();
       tbUs.getTableHeader().setBackground(new Color(0, 150, 255));
      
       tbCier.getTableHeader().setBackground(new Color(0, 150, 255));
     
    }
   
    
    void mostrarUsuarios(){ 
        DefaultTableModel model=new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;}};
        
        model.addColumn("USUARIOS");
        model.addColumn("NUMERO DE CAJA");
        tbUs.setModel(model);
        
       DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tbUs.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tbUs.getColumnModel().getColumn(1).setCellRenderer(tcr);
        String[] users =new String[2];
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
            ResultSet conUs = sentencia.executeQuery("select usuario,numcaja from usuarios where tipousuario='Cajero' order by usuario ASC");
            
            while(conUs.next()){    
            users[0]=conUs.getString("USUARIO");
            users[1]=conUs.getString("NUMCAJA");
            model.addRow(users);
            }

            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
        }
    void mostrarCierres(){
        Calendar c = Calendar.getInstance();
           String dia,mes,annio;
           dia = Integer.toString(c.get(Calendar.DATE));
           mes = Integer.toString(c.get(Calendar.MONTH)+1);
           annio = Integer.toString(c.get(Calendar.YEAR));
        DefaultTableModel model=new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;}};
        
        model.addColumn("IDCIERRE");
        model.addColumn("SUPERVISOR");
        model.addColumn("CAJERO");
        model.addColumn("NUMCAJA");
        model.addColumn("PRECIO");
        model.addColumn("CANT VENTAS");
        model.addColumn("CANT TOTAL");
        model.addColumn("FECHA");
        model.addColumn("HORA");
        tbCier.setModel(model);
        
       DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.CENTER);
       tbCier.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tbCier.getColumnModel().getColumn(8).setCellRenderer(tcr);
        String[] cierres =new String[9];
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
            ResultSet conUs = sentencia.executeQuery("select * from cierres where fecha='"+dia+"-"+mes+"-"+annio+"'order by idcierre ASC");
            
            while(conUs.next()){    
  
           cierres[0]=conUs.getString("IDCIERRE");
           cierres[1]=conUs.getString("USUARIO_SUP");
           cierres[2]=conUs.getString("USUARIO_CAJ");
           cierres[3]=conUs.getString("NUMCAJA");
           cierres[4]=conUs.getString("PRECIO");
           cierres[5]=conUs.getString("CANT_VENTAS");
           cierres[6]=conUs.getString("CANT_PTOTAL");
           cierres[7]=conUs.getString("FECHA").substring(0,10);
           cierres[8]=conUs.getString("HORA");
           model.addRow(cierres);
            }

            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }
    public void obtenerTVentas(){
        int cantV=0,canT=0,pre=0;
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
            ResultSet canTV = sentencia.executeQuery("select COUNT(*) from ventas where ventas.fecha='"+dia+"-"+mes+"-"+annio+"' order by idventa ASC");
            
            while(canTV.next()){    
            cantV=canTV.getInt("COUNT(*)");
            }
            sentencia.close();
            sentencia = conexion.createStatement();
            ResultSet conPre = sentencia.executeQuery("select precio from repuestos where fec_entrada='"+dia+"-"+mes+"-"+annio+"' and rownum=1 order by idrboleto ASC");
            while(conPre.next()){
            pre=conPre.getInt("precio");
            }
            canT=cantV*pre;
            JOptionPane.showMessageDialog(null,"El total de ventas con la fecha "+dia+"/"+mes+"/"+annio+" son: "+cantV+" y la cantidad total de dinero acumulado es de: $"+canT+".00", 
                        "", JOptionPane.PLAIN_MESSAGE,iconcant);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la cantidad y el total de ventas.", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
         
        }
    }    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnS = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUs = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCier = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        actualizarUsuarios = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        obtenerCT = new javax.swing.JMenuItem();
        corteGeneral = new javax.swing.JMenuItem();
        salirSuper = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        reporteCierreGeneral = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnS.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 255)));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tbUs.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbUs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbUsMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbUs);

        jTabbedPane1.addTab("Usuarios", jScrollPane2);

        tbCier.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbCier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbCier);

        jTabbedPane1.addTab("Cierres", jScrollPane1);

        javax.swing.GroupLayout pnSLayout = new javax.swing.GroupLayout(pnS);
        pnS.setLayout(pnSLayout);
        pnSLayout.setHorizontalGroup(
            pnSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        pnSLayout.setVerticalGroup(
            pnSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jMenu1.setText("Inicio");

        actualizarUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK));
        actualizarUsuarios.setText("     Actualizar");
        actualizarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(actualizarUsuarios);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("     Cierre de venta general");
        jMenu1.add(jMenuItem1);

        obtenerCT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        obtenerCT.setText("     Consultar Cantitdad y Total de Ventas     ");
        obtenerCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obtenerCTActionPerformed(evt);
            }
        });
        jMenu1.add(obtenerCT);

        corteGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        corteGeneral.setText("     Corte general ");
        corteGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corteGeneralActionPerformed(evt);
            }
        });
        jMenu1.add(corteGeneral);

        salirSuper.setText("     Salir");
        salirSuper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirSuperActionPerformed(evt);
            }
        });
        jMenu1.add(salirSuper);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Archivo");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setText("     Exportar Reporte General     ");
        jMenu2.add(jMenuItem4);

        reporteCierreGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        reporteCierreGeneral.setText("     Exportar Reporte de Cierre General    ");
        reporteCierreGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteCierreGeneralActionPerformed(evt);
            }
        });
        jMenu2.add(reporteCierreGeneral);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void tbUsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsMousePressed
        if(evt.getClickCount()==2){
        int filaSeleccionada=tbUs.getSelectedRow(); 
       String dato;
       try{
       dato=tbUs.getValueAt(filaSeleccionada, 0).toString();
       
       CEFVentas vt=new CEFVentas();
       vt.datosSup(this.id, this.usr);
       vt.setVisible(true);
       vt.mostrarVentas(dato);
      
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, "    El usuario no ha realizado ninguna venta.\n", 
                        "", JOptionPane.PLAIN_MESSAGE);   
       }
       }
    }//GEN-LAST:event_tbUsMousePressed

    private void salirSuperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirSuperActionPerformed
        CEFrame log=new CEFrame();
        this.setVisible(false);
        log.setVisible(true);        
    }//GEN-LAST:event_salirSuperActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void corteGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corteGeneralActionPerformed
 
    }//GEN-LAST:event_corteGeneralActionPerformed

    private void actualizarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarUsuariosActionPerformed
       try{
       mostrarUsuarios();
       mostrarCierres();}
       catch (Exception e){
        JOptionPane.showMessageDialog(null, "    El usuario no ha realizado ninguna venta.\n- Por lo que no hay nada que actualizar", 
                        "", JOptionPane.PLAIN_MESSAGE);   
       }
    }//GEN-LAST:event_actualizarUsuariosActionPerformed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
      mostrarCierres();
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void reporteCierreGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteCierreGeneralActionPerformed
       Reporte rp=new Reporte();
      rp.generarReporteCierre(new File("C:\\Users\\luigi\\Documents\\reporteCierre.pdf"));
      try {
            Desktop.getDesktop().open(new File("C:\\Users\\luigi\\Documents\\reporteCierre.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(CEFCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteCierreGeneralActionPerformed

    private void obtenerCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obtenerCTActionPerformed
    obtenerTVentas();
    }//GEN-LAST:event_obtenerCTActionPerformed

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
            java.util.logging.Logger.getLogger(CEFSuper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFSuper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFSuper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFSuper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem actualizarUsuarios;
    private javax.swing.JMenuItem corteGeneral;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem obtenerCT;
    private javax.swing.JPanel pnS;
    private javax.swing.JMenuItem reporteCierreGeneral;
    private javax.swing.JMenuItem salirSuper;
    private javax.swing.JTable tbCier;
    private javax.swing.JTable tbUs;
    // End of variables declaration//GEN-END:variables
}
