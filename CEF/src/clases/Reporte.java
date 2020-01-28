
package clases;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reporte {
     private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
      private static final String CONTENT = "El usuario ";
public void generarReporteCierre(File pdf){
     
 
    try {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(pdf));
    } catch (FileNotFoundException fileNotFoundException) {
        System.out.println("No such file was found to generate the PDF "
                + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);

    }
    document.open();
    document.addTitle("Reporte");
    document.addSubject("Luis Carlos Alvarado Martinez");
    document.addKeywords("Java, PDF, iText");
    document.addAuthor("CEF");
    document.addCreator("CEF");
    document.setMargins(30, 25, 30, 25);
    Chunk chunk = new Chunk(Reporte.CONTENT ,chapterFont);

Paragraph para1 = new Paragraph(chunk);
            para1.setAlignment(Paragraph.ALIGN_JUSTIFIED_ALL);
            para1.setSpacingAfter(150);
            document.add(para1);


            document.close();
    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
} catch (DocumentException documentException) {
    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
}
}
}