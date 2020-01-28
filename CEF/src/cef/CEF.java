
package cef;
public class CEF {

    public static void main(String[] args) { 
                //Se crea un ejecutable
                Runnable ejecutable = new Runnable() {
                public void run() {
 
               //Se instancia la ventana
               CEFCarga carga = new CEFCarga();
 
               //Se muestra la ventana
               carga.setVisible(true);
 
                try {
                //Se espera el número definido de segundos
                Thread.sleep(3000);
                } catch (Exception e) {
                  e.printStackTrace();
                }
                     carga.setVisible(false);
                     CEFrame  login=new CEFrame();
                     login.setVisible(true);
                    
            }
        };
        //Se corre la tarea
        Thread tarea = new Thread(ejecutable);
        tarea.start();
    }
    
}
