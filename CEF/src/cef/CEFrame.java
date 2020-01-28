package cef;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CEFrame extends javax.swing.JFrame {
    String id="",usua="";
    String tipusua="";
    
    public CEFrame() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/img/p.png")).getImage());
        this.setTitle("Inicio");
        this.setSize(335,435);
        this.setResizable(false);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
        fondthree.setBackground(new Color(0,0,0,180));
    } 
    public boolean autorizarUsuario(String claveAu){
        String codAut="";
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
            ResultSet coda = sentencia.executeQuery("Select codigoautorizacion from codigosautorizacion where codigoautorizacion like'"+claveAu+"'");
    
    while(coda.next())
    {
    codAut=coda.getString("CODIGOAUTORIZACION");
    }
        if(codAut.equals("")){return false;}
        else{return true;}
     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el tipo de dato en el codigo de autorización\n -Verifique que el codigo sea correcto", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondthree = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txfus = new javax.swing.JTextField();
        txfp = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cuentaNueva = new javax.swing.JLabel();
        fondtwo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ingresar");
        jButton1.setDefaultCapable(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Control de estacionamiento");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogoECF.png"))); // NOI18N

        cuentaNueva.setBackground(new java.awt.Color(0, 0, 255));
        cuentaNueva.setFont(new java.awt.Font("Leelawadee UI Semilight", 3, 12)); // NOI18N
        cuentaNueva.setForeground(new java.awt.Color(255, 255, 255));
        cuentaNueva.setText("Crear una cuenta");
        cuentaNueva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cuentaNueva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuentaNuevaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondthreeLayout = new javax.swing.GroupLayout(fondthree);
        fondthree.setLayout(fondthreeLayout);
        fondthreeLayout.setHorizontalGroup(
            fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondthreeLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fondthreeLayout.createSequentialGroup()
                        .addGroup(fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txfus, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fondthreeLayout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel4))
                            .addComponent(cuentaNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondthreeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        fondthreeLayout.setVerticalGroup(
            fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondthreeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(fondthreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(cuentaNueva)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(fondthree);
        fondthree.setBounds(20, 20, 290, 370);

        fondtwo.setIcon(new javax.swing.ImageIcon("C:\\Users\\luigi\\Documents\\NetBeansProjects\\CEF\\src\\img\\fondo2.jpg")); // NOI18N
        getContentPane().add(fondtwo);
        fondtwo.setBounds(0, 0, 330, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
               if(txfus.getText().equals("") || txfp.getText().equals(""))
                {
                JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos correctamente.\n -No pueden ir espacios en blanco.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                acceder(txfus.getText(), txfp.getText());
               
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cuentaNuevaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuentaNuevaMouseClicked
     CEFUsuarios fusu= new CEFUsuarios();
 
     if(autorizarUsuario(JOptionPane.showInputDialog(null,"Ingrese el codigo","", JOptionPane.PLAIN_MESSAGE))==true){
           fusu.setVisible(true);
        }
        else{JOptionPane.showMessageDialog(null, "No se pudo crear el usuario\n -Ingrese el codigo correcto porfavor", 
                        "¡Ops!", JOptionPane.WARNING_MESSAGE);}
     
    }//GEN-LAST:event_cuentaNuevaMouseClicked
   
    void acceder(String usu, String pass){
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
            ResultSet resultado = sentencia.executeQuery("SELECT * from usuarios where usuario='"+usu+"'and contrasena='"+pass+"'");
            //Se recorre el resultado obtenido
            while (resultado.next())
            {
                id=resultado.getString("idusuario");
                usua=resultado.getString("usuario");
                tipusua=resultado.getString("tipousuario");
            }
            
           
            if("".equals(usua)){
                JOptionPane.showMessageDialog(null,"Usuario inexistente o contraseña incorrecta");
                System.out.println("Usuario y tipo de usuario:\t"+usua+";\t"+tipusua);
            }else{
            CEFrameV vh= new CEFrameV(id,usua);
            CEFSuper sp= new CEFSuper(id,usua);
            CEFCaja cj= new CEFCaja(id,usua);
                if(tipusua.equals("Administrador")){
                JOptionPane.showMessageDialog(null,"\t\tBienvenido","",JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
                vh.setVisible(true);
                }
                else if(tipusua.equals("Supervisor")){
                JOptionPane.showMessageDialog(null,"\t\tBienvenido","",JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
                sp.setVisible(true);
                }
                else if(tipusua.equals("Cajero")){
                JOptionPane.showMessageDialog(null,"\t\tBienvenido","",JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
                cj.setVisible(true);
                }
                }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Datos erroneos");
        }
            //Cerramos la sentencia
            //sentencia.close();
      
    }
     
    
   
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
            java.util.logging.Logger.getLogger(CEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cuentaNueva;
    public javax.swing.JPanel fondthree;
    public javax.swing.JLabel fondtwo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txfp;
    private javax.swing.JTextField txfus;
    // End of variables declaration//GEN-END:variables
}
