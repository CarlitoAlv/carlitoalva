package clases;


import com.barcodelib.barcode.QRCode;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

public class Boleto {
    String fc="",hr="";
    int udm=0,resol=72,rot=0;
    float mi=0.000f, md=0.000f, ms=0.000f, min=0.000f,tam=15.00f;
   
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);     
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.UNDEFINED, 16, Font.BOLD);
    private static final String CEFQR = "C:\\Users\\luigi/boletoqr.gif";

public String idBoleto(){
String identificador = "";

/// Los caracteres de interés en un array de char.
char [] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

// Longitud del array de char.
int charsLength = chars.length;

// Instanciamos la clase Random
Random random = new Random();

// Un StringBuffer para componer la cadena aleatoria de forma eficiente
StringBuffer buffer = new StringBuffer();

// Bucle para elegir una cadena de 10 caracteres al azar
for (int i=0;i<9;i++){

   // Añadimos al buffer un caracter al azar del array
   buffer.append(chars[random.nextInt(charsLength)]);
}
// Y solo nos queda hacer algo con la cadena
System.out.println("Random String " + buffer.toString());
identificador=buffer.toString();
return identificador;
}

public void generarQR(String dato){
    try{
    QRCode c=new QRCode();
    c.setData(dato);
    c.setDataMode(QRCode.MODE_BYTE);
    c.setUOM(udm);
    c.setLeftMargin(mi);
    c.setRightMargin(md);
    c.setTopMargin(ms);
    c.setBottomMargin(min);
    c.setResolution(resol);
    c.setRotate(rot);
    c.setModuleSize(tam);
    String archivo=System.getProperty("user.home")+"/boletoqr.gif";
    c.renderBarcode(archivo);
    }catch(Exception e){
        System.out.println("Error de: "+e);
    }
}

public boolean insertarRepuesto(String cod, String est){
    String codrep="";
    Calendar c = Calendar.getInstance();
    String dia,mes,annio,minut;
    int hora,minutos;
    dia = Integer.toString(c.get(Calendar.DATE));
    mes = Integer.toString(c.get(Calendar.MONTH)+1);
    annio = Integer.toString(c.get(Calendar.YEAR));
   
    
    hora =c.get(Calendar.HOUR_OF_DAY);
    minutos=c.get(Calendar.MINUTE);
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
            
            ResultSet considrep = sentencia.executeQuery("select idrboleto,identificadorrepuesto from repuestos where rownum=1 order by idrboleto desc");
            
    while(considrep.next())
    {
    codrep=considrep.getString("IDENTIFICADORREPUESTO");
    }
    
    if(codrep.equals(cod)){
        return false;
    }
    else{
   
    //System.out.println("es la f"+fc);
   
    if(String.valueOf(minutos).length()==1){
    minut=0+String.valueOf(minutos);
    /*hr=hora+":"+min;
        System.out.println("h"+hr);*/
    sentencia.executeQuery("insert into REPUESTOS (estacionamiento,precio,estadoboleto,identificadorrepuesto,fec_entrada,hr_entrada,evento) \n" +
"values ('"+est+"',50,'NO AUTORIZADO','"+cod+"','"+dia+"-"+mes+"-"+annio+"','"+hora+":"+minut+"','Bandas ITD')");
    }
    else{
    //hr=hora+":"+minutos;
    //System.out.println("h"+hr);
    sentencia.executeQuery("insert into REPUESTOS (estacionamiento,precio,estadoboleto,identificadorrepuesto,fec_entrada,hr_entrada,evento) \n" +
"values ('"+est+"',50,'NO AUTORIZADO','"+cod+"','"+dia+"-"+mes+"-"+annio+"','"+hora+":"+minutos+"','Bandas ITD')");
    }

    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         return false;      
        }
       return true;
}
    
public boolean crearBoleto(File pdf, String c){
String fc="",hr="";
    Calendar cal = Calendar.getInstance();
    String dia,mes,annio,minut;
    int hora,minutos;
    dia = Integer.toString(cal.get(Calendar.DATE));
    mes = Integer.toString(cal.get(Calendar.MONTH)+1);
    annio = Integer.toString(cal.get(Calendar.YEAR));
    fc=dia+"/"+mes+"/"+annio;
    hora =cal.get(Calendar.HOUR_OF_DAY);
    minutos=cal.get(Calendar.MINUTE);
    if(String.valueOf(minutos).length()==1){
    minut=0+String.valueOf(minutos);
    hr=hora+":"+minut;
    }
    else{
    hr=hora+":"+minutos;
    }
    Boleto b= new Boleto();
b.generarQR(c);
    try {
        Rectangle pageSize = new Rectangle(400f, 900f); //ancho y alto

    Document document = new Document(pageSize);
    try {
        PdfWriter.getInstance(document, new FileOutputStream(pdf));
    } catch (FileNotFoundException fileNotFoundException) {
        System.out.println("No such file was found to generate the PDF "
                + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        return false;
    }
    document.open();
    document.addTitle("Boleto");
    document.addSubject("Luis Carlos Alvarado Martinez");
    document.addKeywords("Java, PDF, iText");
    document.addAuthor("CEF");
    document.addCreator("CEF");
    Paragraph evento=new Paragraph("\n\nEvento: BANDAS ITD");
    Paragraph codigo=new Paragraph(c,smallBold);
    Paragraph fyh=new Paragraph("Fecha: "+fc+"                          Hora: "+hr,smallBold);
  
    Chunk chunk = new Chunk("Dirección de Ferias Espectáculos y Paseos Turísticos de Durango", chapterFont);
   
// Let's create de first Chapter (Creemos el primer capítulo)
Chapter chapter = new Chapter(new Paragraph(chunk), 1);
chapter.setNumberDepth(0);
chapter.setIndentationLeft(50);
evento.setIndentationLeft(60);
chapter.add(evento);
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
evento.add(new Phrase(Chunk.NEWLINE));evento.add(new Phrase(Chunk.NEWLINE));
codigo.setIndentationLeft(65);
chapter.add(codigo);
codigo.add(new Phrase(Chunk.NEWLINE));codigo.add(new Phrase(Chunk.NEWLINE));
codigo.add(new Phrase(Chunk.NEWLINE));codigo.add(new Phrase(Chunk.NEWLINE));

fyh.setIndentationLeft(50);
chapter.add(fyh);
Image image;
try {
    image = Image.getInstance(CEFQR);  
    image.setAbsolutePosition(50, 300);
    chapter.add(image);
} catch (BadElementException ex) {
    System.out.println("Image BadElementException" +  ex);
} catch (IOException ex) {
    System.out.println("Image IOException " +  ex);
}

    document.add(chapter);
    document.close();
    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
} catch (DocumentException documentException) {
    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
}

 return true;
}

}


