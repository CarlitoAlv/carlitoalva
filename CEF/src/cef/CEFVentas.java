
package cef;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class CEFVentas extends javax.swing.JFrame {
    String sId,sUs,usVen;
    String us="";
    String[] ventas =new String[8];
    String id="",count="",total="";
    Calendar c = Calendar.getInstance();
    String dia,mes,annio;
   
    String fec;
    int hora, minutos;
    Date d=new Date(); 
 
    public CEFVentas() {
        initComponents();
         this.setSize(1100,410);
          this.setIconImage(new ImageIcon(getClass().getResource("/img/p.png")).getImage());
         //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = this.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        setLocation((pantalla.width - ventana.width)/2 , (pantalla.height- ventana.height)/2);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        tbVent.getTableHeader().setBackground(new Color(0, 150, 255));
    }
    void datosSup(String id, String us){
        this.sId=id;
        this.sUs=us;
    }
    
    void mostrarVentas(String usu){
    us=usu;
    this.setTitle("Usuario:  "+usu);
    DefaultTableModel model=new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}};
    model.addColumn("IDVENTA");
    model.addColumn("USUARIO");
    model.addColumn("NUMCAJA");
    model.addColumn("NOMBRES");
    model.addColumn("APELLIDOS");
    model.addColumn("SEXO");
    model.addColumn("FECHA");
    model.addColumn("PRECIO");
    tbVent.setModel(model);
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.CENTER);
    tbVent.getColumnModel().getColumn(0).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(1).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(2).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(3).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(4).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(5).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(6).setCellRenderer(tcr);
    tbVent.getColumnModel().getColumn(7).setCellRenderer(tcr);
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
            ResultSet conId = sentencia.executeQuery("select idusuario from usuarios where usuario='"+usu+"'");
            while(conId.next()){
            id=conId.getString("IDUSUARIO");
            }
            
            ResultSet conVent = sentencia.executeQuery("select distinct ventas.idventa, usuarios.usuario, usuarios.numcaja ,\n" +
"usuarios.nombres, usuarios.apellidos, usuarios.sexo, ventas.fecha, repuestos.precio\n" +
"from ventas, usuarios, repuestos \n" +
"where ventas.idusuario='"+id+"' and usuarios.idusuario='"+id+"' and ventas.fecha='"+dia+"-"+mes+"-"+annio+"' order by idventa ASC");
            {}
            while(conVent.next()){
            ventas[0]=conVent.getString("IDVENTA");
            ventas[1]=conVent.getString("USUARIO");
            ventas[2]=conVent.getString("NUMCAJA");
            ventas[3]=conVent.getString("NOMBRES");
            ventas[4]=conVent.getString("APELLIDOS");
            ventas[5]=conVent.getString("SEXO");
            ventas[6]=conVent.getString("FECHA").substring(0,10);
            ventas[7]=conVent.getString("PRECIO");
            model.addRow(ventas);
            }
            
            fec=ventas[6].substring(0,10);
            ResultSet vt= sentencia.executeQuery("select COUNT(*), sum(precio) from (select distinct ventas.idventa, usuarios.usuario, usuarios.numcaja ,\n" +
"usuarios.nombres, usuarios.apellidos, usuarios.sexo, ventas.fecha, repuestos.precio\n" +
"from ventas, usuarios, repuestos\n" +
"where ventas.idusuario='"+id+"' and usuarios.idusuario='"+id+"' and ventas.fecha='"+dia+"-"+mes+"-"+annio+"'  order by idventa ASC)");
            
            while(vt.next())
            {
            count=vt.getString("COUNT(*)");
            total=vt.getString("SUM(PRECIO)");
            }
            sentencia.close();
            
            numVent.setText(count);
            tot.setText(total);
           
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en mostrar la tabla\n -Vuelva a actualizar porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);     
        }
    }
    void corteCajaActual(){
         dia = Integer.toString(c.get(Calendar.DATE));
         mes = Integer.toString(c.get(Calendar.MONTH)+1);
         annio = Integer.toString(c.get(Calendar.YEAR));
         hora =c.get(Calendar.HOUR_OF_DAY);
         minutos = c.get(Calendar.MINUTE);
         String actfec=annio+"-"+mes+"-"+dia;
         if(ventas[1]==null){
            JOptionPane.showMessageDialog(null, "Error en hacer el corte de caja actual\n -El usuario no a realizado ventas", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE); 
         }
         else{
         
         try
        {
            int cantVent=0;
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
            
            Connection conexion = DriverManager.getConnection( url, usuario, password );
             Statement sentencia = conexion.createStatement();
            ResultSet conCierr = sentencia.executeQuery("select cant_ventas from (select * from cierres where usuario_caj='"+ventas[1]+"'\n" +
"order by idcierre desc ) where rownum=1");
             while(conCierr.next()){
             cantVent=Integer.parseInt(conCierr.getString("cant_ventas"));
             }
             sentencia.close();
             System.out.println("La fecha en la consulta"+fec+"La fecha actual"+actfec);
             System.out.println("Va "+count+"CantVent"+cantVent);
             if(fec.equals(actfec) || Integer.parseInt(count)>cantVent){
          JOptionPane.showMessageDialog(null,"El usuario "+this.sUs+" quien es supervisor, hizo el corte del "+"usuario:\n"+ventas[1]+","+
               " "+ventas[3]+" "+ventas[4]+", en la caja: #"+ventas[2]+"\n"+
               "donde el costo del boleto fue de: "+"$"+ventas[7]+".00"+
               " y realizo "+count+" ventas\ncon una cantidad total de: "+"$"+total+".00"+
               ", el "+dia+"/"+mes+"/"+annio+" a las: "+hora+":"+minutos+".","",JOptionPane.PLAIN_MESSAGE);
            sentencia = conexion.createStatement();
            sentencia.executeQuery("insert into CIERRES (USUARIO_SUP,USUARIO_CAJ,NUMCAJA,PRECIO,CANT_VENTAS,CANT_PTOTAL,FECHA,HORA)"
                    + "values ('"+this.sUs+"','"+ventas[1]+"',"+ventas[2]+","+ventas[7]+","+count+","+total+",'"+dia+"-"+mes+"-"+annio+"','"+hora+":"+minutos+"')");
             }
             
             else{
             JOptionPane.showMessageDialog(null, "Existe un cierre con esta cantidad.\n- Puede exportar el reporte del cierre de esta caja.", 
                        "", JOptionPane.WARNING_MESSAGE);
             }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en hacer el corte de caja\n -Vuelva a realizarlo porfavor", 
                        "¡Ocurrio un error!", JOptionPane.ERROR_MESSAGE);   
         }
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVent = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numVent = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        actualizarVentas = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbVent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbVent);

        jLabel1.setText("Numero de ventas:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setText("Total:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        numVent.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        tot.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numVent, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addComponent(numVent, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Inicio");

        actualizarVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        actualizarVentas.setText("     Actualizar");
        actualizarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarVentasActionPerformed(evt);
            }
        });
        jMenu1.add(actualizarVentas);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("     Corte de caja actual");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("     Exportar reporte de caja actual        ");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    corteCajaActual();
    
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void actualizarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarVentasActionPerformed
     try{
        mostrarVentas(us);
     }
     catch (Exception e){
        JOptionPane.showMessageDialog(null, "El usuario no ha realizado ninguna venta.\n  - Por lo que no hay nada que actualizar", 
                        "", JOptionPane.PLAIN_MESSAGE);   
       }
    }//GEN-LAST:event_actualizarVentasActionPerformed

 
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
            java.util.logging.Logger.getLogger(CEFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem actualizarVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numVent;
    private javax.swing.JTable tbVent;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
