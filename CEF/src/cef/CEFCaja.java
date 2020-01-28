
package cef;

import clases.Boleto;
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CEFCaja extends javax.swing.JFrame {
    Boleto bt=new Boleto();
    Icon iconV = new ImageIcon(getClass().getResource("/img/true.png"));
    String nom="Boleto";
    String bidentificador="",ridentificador="";
    String[] boleto = new String[6];
    String[] repuesto = new String[6];
    String id,usua,tipbol,idventa;
 
    
    
    public CEFCaja(String id, String usua) {
        initComponents();
        panCj.setBackground(new Color(0, 150, 255,160));
        this.setSize(690, 580);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/img/p.png")).getImage());
         //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
        inhabilitar();
        txtBol.setHorizontalAlignment(JTextField.CENTER);
        this.id=id;
        this.usua=usua;
    }
    void inhabilitar(){
        txtMPago.setEnabled(false);
        txtEst.setEnabled(false);
        txtPark.setEnabled(false);
        txtFec.setEnabled(false);
        txtHor.setEnabled(false);
        txtPago.setEnabled(false);
        btnCanVenta.setEnabled(false);
        btnPagar.setEnabled(false);
    }
    void pagarBoleto(String idboleto){
        String idbol="",bol="",estbol="",precbol="";
        String idrep="",rep="",estrep="",precrep="";
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
           ResultSet conbol = sentencia.executeQuery("select idboleto,identificadorboleto,estadoboleto,precio from boletos where identificadorboleto='"+idboleto+"'");
           while(conbol.next()){
           idbol=conbol.getString("idboleto");
           bol=conbol.getString("identificadorboleto");
           estbol=conbol.getString("estadoboleto");
           precbol=conbol.getString("precio");
           }
           sentencia.close();
           sentencia = conexion.createStatement();
           ResultSet conrep = sentencia.executeQuery("select idrboleto,identificadorrepuesto,estadoboleto,precio from repuestos where identificadorrepuesto='"+idboleto+"'");
           while(conrep.next()){
           idrep=conrep.getString("idrboleto");
           rep=conrep.getString("identificadorrepuesto");
           estrep=conrep.getString("estadoboleto");
           precrep=conrep.getString("precio");
           }
           sentencia.close();
           if(bol.equals("") && rep.equals("")){
           JOptionPane.showMessageDialog(null, "Error en la busqueda del boleto.\n - El numero que intenta ingresar es incorrecto", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE); }
           
   else{
       if(txtPago.getText().equals("")){
      JOptionPane.showMessageDialog(null, "Porfavor ingrese la cantidad de pago.\n -No puede estar en blanco el espacio de pago.", 
                        "", JOptionPane.WARNING_MESSAGE);
       }
       else{    
       int cantidad,res = 0;
 
   if(bol.equals(idboleto) && estbol.equals("NO AUTORIZADO")){
       cantidad=Integer.parseInt(txtPago.getText());
       if(cantidad>=Integer.parseInt(precbol)){
       res=cantidad-Integer.parseInt(precbol);
       JOptionPane.showMessageDialog(null, "El cambio es de: "+res , 
                        "", JOptionPane.PLAIN_MESSAGE);
       sentencia = conexion.createStatement();
       sentencia.executeQuery("update boletos set estadoboleto='AUTORIZADO' where identificadorboleto='"+idboleto+"'");
       JOptionPane.showMessageDialog(null,"Se ha pagado el boleto","",JOptionPane.PLAIN_MESSAGE,iconV);
       txtEst.setText("AUTORIZADO");
       sentencia.close();
      
           Calendar c = Calendar.getInstance();
           String dia,mes,annio;
           dia = Integer.toString(c.get(Calendar.DATE));
           mes = Integer.toString(c.get(Calendar.MONTH)+1);
           annio = Integer.toString(c.get(Calendar.YEAR));
           sentencia = conexion.createStatement();
           sentencia.executeQuery("insert into VENTAS (idrboleto,idusuario,fecha) \n" +
           "values ('"+idbol+"', '"+id+"','"+dia+"-"+mes+"-"+annio+"'");
           sentencia.close();
  

       }
       else{
       JOptionPane.showMessageDialog(null, "Porfavor ingrese la cantidad debida para hacer el pago.", 
                        "", JOptionPane.WARNING_MESSAGE);
       }
   }
   else if (rep.equals(idboleto) && estrep.equals("NO AUTORIZADO")){
       cantidad=Integer.parseInt(txtPago.getText());
       if(cantidad>=Integer.parseInt(precrep)){
       res=cantidad-Integer.parseInt(precrep);
       JOptionPane.showMessageDialog(null, "El cambio es de: "+res , 
                        "", JOptionPane.PLAIN_MESSAGE);
       sentencia = conexion.createStatement();
       sentencia.execute("update repuestos set ESTADOBOLETO='AUTORIZADO' where IDENTIFICADORREPUESTO='"+idboleto+"'");
       JOptionPane.showMessageDialog(null,"Se ha pagado el boleto","",JOptionPane.PLAIN_MESSAGE,iconV);
       txtEst.setText("AUTORIZADO");
       sentencia.close();
       
           Calendar c = Calendar.getInstance();
           String dia,mes,annio;
           dia = Integer.toString(c.get(Calendar.DATE));
           mes = Integer.toString(c.get(Calendar.MONTH)+1);
           annio = Integer.toString(c.get(Calendar.YEAR));
           sentencia = conexion.createStatement();
           sentencia.executeQuery("insert into VENTAS (idrboleto,idusuario,fecha) \n" +
           "values ('"+idrep+"','"+id+"','"+dia+"-"+mes+"-"+annio+"')");
           sentencia.close();

       }
       else{
       JOptionPane.showMessageDialog(null, "Porfavor ingrese la cantidad debida para realizar el pago.", 
                        "", JOptionPane.WARNING_MESSAGE);
       }
   }
   else{
       JOptionPane.showMessageDialog(null, "Este boleto ya fue pagado.\n"+"Esta AUTORIZADO", 
                        "¡ESPERA!", JOptionPane.WARNING_MESSAGE);    
           }
       }
    }
        
    }catch(SQLException exql){
    System.out.println(exql.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error interno.\n"+"- Intentelo de nuevo porfavor.", 
                        "", JOptionPane.ERROR_MESSAGE);  
    }
    }
    void buscarBoleto(String id){
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
           
            
    ResultSet conbol = sentencia.executeQuery("select * from boletos where identificadorboleto='"+id+"'");
    while(conbol.next())
    {
    boleto[0]=conbol.getString("IDBOLETO");
    boleto[1]=conbol.getString("ESTACIONAMIENTO");
    boleto[2]=conbol.getString("PRECIO");
    boleto[3]=conbol.getString("ESTADOBOLETO");
    bidentificador=conbol.getString("IDENTIFICADORBOLETO");
    boleto[4]=conbol.getString("FEC_ENTRADA");
    boleto[5]=conbol.getString("HR_ENTRADA");
    }
   sentencia.close();
   sentencia = conexion.createStatement();
    ResultSet conrep = sentencia.executeQuery("select * from repuestos where identificadorrepuesto='"+id+"'");
    while(conrep.next())
    {
    repuesto[0]=conrep.getString("IDRBOLETO");
    repuesto[1]=conrep.getString("ESTACIONAMIENTO");
    repuesto[2]=conrep.getString("PRECIO");
    repuesto[3]=conrep.getString("ESTADOBOLETO");
    ridentificador=conrep.getString("IDENTIFICADORREPUESTO");
    repuesto[4]=conrep.getString("FEC_ENTRADA");
    repuesto[5]=conrep.getString("HR_ENTRADA");
    }    
   if(bidentificador.equals("") && ridentificador.equals("")){
           JOptionPane.showMessageDialog(null, "Error en la busqueda del boleto.\n - El numero que intenta ingresar es incorrecto", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE); }
      
   else{
   if(id.equals(bidentificador) && boleto[3].equals("NO AUTORIZADO")){
       txtMPago.setText(boleto[2]);
       txtEst.setText(boleto[3]);
       txtPark.setText(boleto[1]);
       String subFecha = boleto[4].substring(0,10);
       txtFec.setText(subFecha);
       txtHor.setText(boleto[5]);
       txtPago.setEnabled(true);
       btnCanVenta.setEnabled(true);
       btnPagar.setEnabled(true);
       }
   else if (id.equals(ridentificador) && repuesto[3].equals("NO AUTORIZADO")){
       txtMPago.setText(repuesto[2]);
       txtEst.setText(repuesto[3]);
       txtPark.setText(repuesto[1]);
       String subFecha = repuesto[4].substring(0,10);
       txtFec.setText(subFecha);
       txtHor.setText(repuesto[5]);
       txtPago.setEnabled(true);
       btnCanVenta.setEnabled(true);
       btnPagar.setEnabled(true);
   }
   else{
       JOptionPane.showMessageDialog(null, "Este boleto ya fue pagado.\n"+"Esta AUTORIZADO", 
                        "¡ESPERA!", JOptionPane.WARNING_MESSAGE);    
           }
       }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la busqueda del boleto.", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);       
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panCj = new javax.swing.JPanel();
        txtMPago = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPark = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFec = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBol = new javax.swing.JTextField();
        btnCanVenta = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtEst = new javax.swing.JTextField();
        txtPago = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHor = new javax.swing.JTextField();
        buscarBoleto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        generarBoleto = new javax.swing.JMenuItem();
        salirCaja = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMPago.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtMPago.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Monto a pagar:");

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estacionamiento:");

        txtPark.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPark.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pago:");

        txtFec.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtFec.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha:");

        txtBol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBolActionPerformed(evt);
            }
        });

        btnCanVenta.setText("Calcelar");
        btnCanVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanVentaActionPerformed(evt);
            }
        });

        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Estado:");

        txtEst.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEst.setForeground(new java.awt.Color(255, 255, 255));

        txtPago.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPago.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hora:");

        txtHor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtHor.setForeground(new java.awt.Color(255, 255, 255));

        buscarBoleto.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        buscarBoleto.setText("Buscar");
        buscarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBoletoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar por numero de boleto:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/d1.png"))); // NOI18N

        javax.swing.GroupLayout panCjLayout = new javax.swing.GroupLayout(panCj);
        panCj.setLayout(panCjLayout);
        panCjLayout.setHorizontalGroup(
            panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCjLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panCjLayout.createSequentialGroup()
                        .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panCjLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCanVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panCjLayout.createSequentialGroup()
                                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMPago)
                                    .addComponent(txtEst)
                                    .addComponent(txtPark, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panCjLayout.createSequentialGroup()
                                        .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBol, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7)
                                        .addComponent(txtFec, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(txtHor)
                                        .addComponent(txtPago)
                                        .addComponent(buscarBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))))
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(panCjLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panCjLayout.setVerticalGroup(
            panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCjLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMPago, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFec, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEst, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panCjLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPark, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panCjLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panCjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCanVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panCjLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        getContentPane().add(panCj, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 730));

        jMenu1.setText("Archivo");

        generarBoleto.setText("     Nuevo Boleto     ");
        generarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarBoletoActionPerformed(evt);
            }
        });
        jMenu1.add(generarBoleto);

        salirCaja.setText("     Salir");
        salirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirCajaActionPerformed(evt);
            }
        });
        jMenu1.add(salirCaja);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirCajaActionPerformed
        CEFrame log=new CEFrame();
        this.setVisible(false);
        log.setVisible(true);
    }//GEN-LAST:event_salirCajaActionPerformed

    private void btnCanVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanVentaActionPerformed
                  txtMPago.setText("");
                  txtEst.setText("");
                  txtPark.setText("");
                  txtFec.setText("");
                  txtHor.setText("");
                  txtPago.setText("");
                  inhabilitar();
    }//GEN-LAST:event_btnCanVentaActionPerformed

    private void generarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBoletoActionPerformed
      int resp=JOptionPane.showConfirmDialog(null,"¿Seguro que desea generar un nuevo boleto?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
      if (JOptionPane.YES_OPTION == resp){
        Object opc = JOptionPane.showInputDialog(new JDialog(),"Seleccione el Estacionamiento",
        "",  JOptionPane.PLAIN_MESSAGE, null,
        new Object[] { "Seleccione","Estacionamiento 1", "Estacionamiento 2", "Estacionamiento VIP" },JOptionPane.DEFAULT_OPTION);
        String est= String.valueOf(opc);
        if (opc==null || est.equals("Seleccione")){JOptionPane.showMessageDialog(null, "Porfavor ingrese un estacionamiento.\n- No puede dejarlo inespecífico", 
                        "¡UPS!", JOptionPane.ERROR_MESSAGE);
        }else{ 
        Boleto bt=new Boleto();
        String codbol="";
        codbol=bt.idBoleto();
        
        if(bt.crearBoleto(new File("C:\\Users\\luigi\\Documents\\Boletos\\"+nom+".pdf"),codbol)==true){
            if(bt.insertarRepuesto(codbol,est)==true){
        try {
            Desktop.getDesktop().open(new File("C:\\Users\\luigi\\Documents\\Boletos\\"+nom+".pdf"));
        } catch (IOException ex) {
           
            Logger.getLogger(CEFCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        else{JOptionPane.showMessageDialog(null, "Algo salio mal:\n - Porfavor intente de nuevo porfavor.\nNOTA: Cierre la ventana de impresion del boleto", 
                        "!UPS!", JOptionPane.WARNING_MESSAGE);}
      }
      }
      else if(JOptionPane.NO_OPTION==resp){}
      
    }//GEN-LAST:event_generarBoletoActionPerformed
    
    private void txtBolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBolActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        int resp=JOptionPane.showConfirmDialog(null,"         ¿Desea pagar el boleto?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
        if(JOptionPane.YES_OPTION==resp){
        pagarBoleto(txtBol.getText());       
        }
        else{}
    }//GEN-LAST:event_btnPagarActionPerformed

    private void buscarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBoletoActionPerformed
      if(txtBol.getText().equals(""))
                {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese el numero del boleto.", 
                        "¡ALTO!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                  buscarBoleto(txtBol.getText());
                }
    }//GEN-LAST:event_buscarBoletoActionPerformed

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
            java.util.logging.Logger.getLogger(CEFCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanVenta;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton buscarBoleto;
    private javax.swing.JMenuItem generarBoleto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panCj;
    private javax.swing.JMenuItem salirCaja;
    private javax.swing.JTextField txtBol;
    private javax.swing.JTextField txtEst;
    private javax.swing.JTextField txtFec;
    private javax.swing.JTextField txtHor;
    private javax.swing.JTextField txtMPago;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtPark;
    // End of variables declaration//GEN-END:variables
}
