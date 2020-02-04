package sample.ClassBusiness;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportController {

    public static void exportPDFRating() throws DocumentException, IOException, SQLException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("pdfOrdonatRating.pdf"));
        document.open();
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph paragraph = null;
        for (int i = 4; i <= 14; i++) {
            ResultSet rs = DBController.getGenresFromId(i);
            while (rs.next()) {
                paragraph = new Paragraph(rs.getString("name")+"\n\n", font1);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
            }
            ResultSet resultSet = DBController.getMovieFromGenreOrderedRating(i);
            PdfPTable table = new PdfPTable(8);
            table.addCell("title");
            table.addCell("year");
            table.addCell("certificate");
            table.addCell("runtime");
            table.addCell("IMDB");
            table.addCell("metascore");
            table.addCell("votes");
            table.addCell("gross($)");
            while (resultSet.next()) {
                table.addCell(resultSet.getString("title"));
                table.addCell(resultSet.getString("year"));
                table.addCell(resultSet.getString("certificate"));
                table.addCell(resultSet.getString("runtime"));
                table.addCell(resultSet.getString("imdb_rating"));
                table.addCell(resultSet.getString("metascore"));
                table.addCell(resultSet.getString("votes"));
                table.addCell(resultSet.getString("gross"));
            }
            document.add(table);
        }
        document.close();
    }
    public static void exportPDFNume() throws DocumentException, IOException, SQLException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("pdfOrdonatNume.pdf"));
        document.open();
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph paragraph = null;
        for (int i = 4; i <= 14; i++) {
            ResultSet rs = DBController.getGenresFromId(i);
            while (rs.next()) {
                paragraph = new Paragraph(rs.getString("name")+"\n\n", font1);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
            }
            ResultSet resultSet = DBController.getMovieFromGenreOrderedNume(i);
            PdfPTable table = new PdfPTable(8);
            table.addCell("title");
            table.addCell("year");
            table.addCell("certificate");
            table.addCell("runtime");
            table.addCell("IMDB");
            table.addCell("metascore");
            table.addCell("votes");
            table.addCell("gross($)");
            while (resultSet.next()) {
                table.addCell(resultSet.getString("title"));
                table.addCell(resultSet.getString("year"));
                table.addCell(resultSet.getString("certificate"));
                table.addCell(resultSet.getString("runtime"));
                table.addCell(resultSet.getString("imdb_rating"));
                table.addCell(resultSet.getString("metascore"));
                table.addCell(resultSet.getString("votes"));
                table.addCell(resultSet.getString("gross"));
            }
            document.add(table);
        }
        document.close();
    }
    public static void exportHTMLRating() throws DocumentException, SQLException, IOException, ParserConfigurationException {
        exportPDFRating();
        PDDocument pdf = PDDocument.load(new File("pdfOrdonatRating.pdf"));
        Writer output = new PrintWriter("htmlOrdonatRating.html", StandardCharsets.UTF_8);
        new PDFDomTree().writeText(pdf, output);
        output.close();
    }
    public static void exportHTMLNume() throws DocumentException, SQLException, IOException, ParserConfigurationException {
        exportPDFNume();
        PDDocument pdf = PDDocument.load(new File("pdfOrdonatNume.pdf"));
        Writer output = new PrintWriter("htmlOrdonatNume.html", StandardCharsets.UTF_8);
        new PDFDomTree().writeText(pdf, output);
        output.close();
    }
}
