/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cef;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author luigi
 */
public class CEFUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form CEFUsuarios
     */
    public CEFUsuarios() {
        initComponents();
        this.setResizable(false);
        this.setSize(350,515);
        this.setTitle("Registrar usuario");
        this.setIconImage(new ImageIcon(getClass().getResource("/img/p.png")).getImage());
         //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
        txtncj.setEnabled(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    void habilitarTxt(){
        switch(cmbusua.getSelectedItem().toString()){
            case "Cajero":
            txtncj.setEnabled(true);
            break;    
            case "Administrador":
            txtncj.setEnabled(false);
            break;
            case "Supervisor":
            txtncj.setEnabled(false);
            break;
        }
    
    }
    
    void esconderCon(){
    if(!mostCon.isSelected()){
      passTxt.setEchoChar('\u2022');
    }
    else{
    boolean a=true;
    char i=0;
             if (a) {  // a es una variable boolean en true
             passTxt.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
             a = false;
             } else {
             passTxt.setEchoChar(i); // i es el char
             a = true;
             }
        }
    }
    void insertarDat(){
    int idusuario=0;    
    String idus="",nomUs="";
    String nombres="";
    String apellidos="";
    String Cusuario="";
    String contrasena="";
    String sexo="";
    String tipoUsuario="";
    int numCaja=0;
    boolean ban=false;
    
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
            String usua="";
            String tipusua="";
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
       
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
           
            
    ResultSet consid = sentencia.executeQuery("select idusuario from usuarios");
    while(consid.next())
    {
    idus=consid.getString("IDUSUARIO");
    }
    
    ResultSet conus = sentencia.executeQuery("select usuario from usuarios where usuario='"+txtUsua.getText()+"'");
    while(conus.next())
    {
    nomUs=conus.getString("USUARIO");
    }
    
    
    
    System.out.println("esta devolviendo:"+nomUs);
    if(cmbusua.getSelectedItem().toString().equals("Cajero")){
    if(txtNom.getText().equals("") || txtApe.getText().equals("") || txtUsua.getText().equals("") || txtncj.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos correctamente.\n -No pueden ir espacios en blanco.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE);       
    }
    else{
    
    if(idus.equals(""))
    {
        JOptionPane.showMessageDialog(null,"Esta llegando nulo");
        idusuario=1;
        ban=true;
    }
    else{
    int idnum;    
    idnum=Integer.parseInt(idus);
    idnum++;
    idusuario=idnum;
    }
    
    if(ban==true){
    nombres=txtNom.getText();
    apellidos=txtApe.getText();
    Cusuario=txtUsua.getText();
     contrasena=passTxt.getText();
    sexo=cmbSex.getSelectedItem().toString();
    tipoUsuario=cmbusua.getSelectedItem().toString();
    numCaja=Integer.parseInt(txtncj.getText());
    
    System.out.println(idusuario+nombres+apellidos+Cusuario+contrasena+sexo+tipoUsuario+numCaja);
   sentencia.executeQuery("insert into usuarios (idusuario, usuario,contrasena,tipousuario,numcaja,nombres,apellidos,sexo)" +
"values ('"+idusuario+"','"+Cusuario+"','"+contrasena+"','"+tipoUsuario+"','"+numCaja+"','"+nombres+"','"+apellidos+"','"+sexo+"')"); 
    JOptionPane.showMessageDialog(null,"Se registro el nuevo usuario exitosamente");
    }
    else  if(!nomUs.equals(txtUsua.getText())){
   nombres=txtNom.getText();
    apellidos=txtApe.getText();
    Cusuario=txtUsua.getText();
     contrasena=passTxt.getText();
    sexo=cmbSex.getSelectedItem().toString();
    tipoUsuario=cmbusua.getSelectedItem().toString();
    numCaja=Integer.parseInt(txtncj.getText());
    
    System.out.println(idusuario+nombres+apellidos+Cusuario+contrasena+sexo+tipoUsuario);
 sentencia.executeQuery("insert into usuarios (idusuario,usuario,contrasena,tipousuario,numcaja,nombres,apellidos,sexo)" +
"values ('"+idusuario+"','"+Cusuario+"','"+contrasena+"','"+tipoUsuario+"','"+numCaja+"','"+nombres+"','"+apellidos+"','"+sexo+"')"); 
    JOptionPane.showMessageDialog(null,"Se registro el nuevo usuario exitosamente");
    }
    else{
      JOptionPane.showMessageDialog(null, "Ese usuario ya existe.\n -Porfavor registre otro.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE); 
    }
   
    }
    }
    else{
        if(txtNom.getText().equals("") || txtApe.getText().equals("") || txtUsua.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos correctamente.\n -No pueden ir espacios en blanco.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE);       
    }
    else{
    
    if(idus.equals(""))
    {
        JOptionPane.showMessageDialog(null,"Esta llegando nulo");
        idusuario=1;
        ban=true;
    }
    else{
    int idnum;    
    idnum=Integer.parseInt(idus);
    idnum++;
    idusuario=idnum;
    }
    //JOptionPane.showMessageDialog(null,"Este es el nuevo id despues:\n"+"IDUSUARIO:   \t"+idusuario);
 if(ban==true){
    nombres=txtNom.getText();
    apellidos=txtApe.getText();
    Cusuario=txtUsua.getText();
     contrasena=passTxt.getText();
    sexo=cmbSex.getSelectedItem().toString();
    tipoUsuario=cmbusua.getSelectedItem().toString();
    
    System.out.println(idusuario+nombres+apellidos+Cusuario+contrasena+sexo+tipoUsuario);
   sentencia.executeQuery("insert into usuarios (idusuario, usuario, contrasena,tipousuario,nombres,apellidos,sexo)" +
"values ('"+idusuario+"','"+Cusuario+"','"+contrasena+"','"+tipoUsuario+"','"+nombres+"','"+apellidos+"','"+sexo+"')"); 
    JOptionPane.showMessageDialog(null,"Se registro el nuevo usuario exitosamente");
    }
    else  if(!nomUs.equals(txtUsua.getText())){
   nombres=txtNom.getText();
    apellidos=txtApe.getText();
    Cusuario=txtUsua.getText();
     contrasena=passTxt.getText();
    sexo=cmbSex.getSelectedItem().toString();
    tipoUsuario=cmbusua.getSelectedItem().toString();
    
    
    System.out.println(idusuario+nombres+apellidos+Cusuario+contrasena+sexo+tipoUsuario);
    
 sentencia.executeQuery("insert into usuarios (idusuario, usuario, contrasena,tipousuario,nombres,apellidos,sexo)" +
"values ('"+idusuario+"','"+Cusuario+"','"+contrasena+"','"+tipoUsuario+"','"+nombres+"','"+apellidos+"','"+sexo+"')"); 
    JOptionPane.showMessageDialog(null,"Se registro el nuevo usuario exitosamente");
    }
    else{
      JOptionPane.showMessageDialog(null, "Ese usuario ya existe.\n -Porfavor registre otro.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE); 
    }
    }
    }
     } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el registro del usuario\n -Verifique los campos si son correctos", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);       
        }
    
    }
 
    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtncj = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtApe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbusua = new javax.swing.JComboBox<>();
        cmbSex = new javax.swing.JComboBox<>();
        mostCon = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(140, -10, 70, 110);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo de usuario:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(170, 300, 150, 30);
        jPanel1.add(txtUsua);
        txtUsua.setBounds(30, 200, 290, 24);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Numero de caja:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 370, 150, 40);
        jPanel1.add(txtncj);
        txtncj.setBounds(180, 380, 140, 24);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 120, 110, 30);
        jPanel1.add(txtApe);
        txtApe.setBounds(30, 150, 290, 24);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 300, 60, 30);
        jPanel1.add(passTxt);
        passTxt.setBounds(30, 250, 290, 22);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contraseña:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 220, 110, 30);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Usuario:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 170, 110, 30);

        cmbusua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Supervisor", "Cajero" }));
        cmbusua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbusuaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbusua);
        cmbusua.setBounds(170, 330, 150, 26);

        cmbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino\t", "Femenino" }));
        jPanel1.add(cmbSex);
        cmbSex.setBounds(30, 330, 110, 26);

        mostCon.setBackground(new java.awt.Color(255, 255, 255));
        mostCon.setForeground(new java.awt.Color(255, 255, 255));
        mostCon.setText("Mostrar contraseña");
        mostCon.setOpaque(false);
        mostCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostConActionPerformed(evt);
            }
        });
        jPanel1.add(mostCon);
        mostCon.setBounds(30, 270, 150, 24);

        jButton1.setText("Registrar Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(180, 440, 140, 30);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, 70, 80, 30);
        jPanel1.add(txtNom);
        txtNom.setBounds(30, 100, 290, 24);

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(30, 442, 77, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondous.png"))); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 350, 490);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbusuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbusuaActionPerformed
        habilitarTxt();
    }//GEN-LAST:event_cmbusuaActionPerformed

    private void mostConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostConActionPerformed
        esconderCon();
    }//GEN-LAST:event_mostConActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int resp=JOptionPane.showConfirmDialog(null,"¿Seguro que desea registrar un nuevo usuario?");
      if (JOptionPane.YES_OPTION == resp){
        insertarDat();
      }
      else if(JOptionPane.NO_OPTION==resp){}
      else if(JOptionPane.CANCEL_OPTION==resp){
      this.setVisible(false);
    }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      txtNom.setText("");
      txtApe.setText("");
      txtUsua.setText("");
      passTxt.setText("");
      txtncj.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CEFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CEFUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbSex;
    private javax.swing.JComboBox<String> cmbusua;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox mostCon;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtUsua;
    private javax.swing.JTextField txtncj;
    // End of variables declaration//GEN-END:variables
}
