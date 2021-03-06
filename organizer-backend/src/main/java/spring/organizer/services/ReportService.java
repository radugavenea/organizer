package spring.organizer.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.entities.Event;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * Created by radu on 29.06.2017.
 */


/**
 * Created by radu on 16.04.2017.
 */
@Service
public class ReportService {


//    @Autowired
//    ServletContext ctx;

    private static Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void generateReport(java.util.List<Event> events) throws IOException, DocumentException {

//        String path = ctx.getRealPath("/reports/" + LocalTime.now().toString()  + ".pdf");
//        File file = new File(path);
//        if (!file.getParentFile().exists())
//            file.getParentFile().mkdirs();
//        if (!file.exists())
//            file.createNewFile();
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(path));
//
//        document.open();
//        addMetaData(document);
//        addContent(document, events);
//        document.close();
    }


    private void addMetaData(Document document) {
        document.addTitle("Report");
        document.addAuthor("Gigel");
    }


    private void addContent(Document document, List<Event> events) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Events report or something", font));
        addEmptyLine(preface, 1);;
        preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") +
                ", " + new Date(), smallFont));
        addEmptyLine(preface, 1);
        document.add(preface);

        Anchor anchor = new Anchor("First Chapter", font);
        anchor.setName("First Chapter");
        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor),1);

        Paragraph subPara = new Paragraph("Events",smallFont);
        Section subCatPart = catPart.addSection(subPara);
        // add a table
        addEmptyLine(subCatPart, 1);

        createTable(subCatPart, events);
        document.add(subCatPart);
    }

    private static void createTable(Section section, List<Event> events){
        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Event name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Starts at"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ends at"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Note"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for(int i=0; i<events.size(); i++){
            table.addCell(events.get(i).getName());
            table.addCell(events.get(i).getStartDate().format(formatter));
            table.addCell(events.get(i).getEndDate().format(formatter));
            table.addCell(events.get(i).getNote());

        }

        section.add(table);
    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    private static void addEmptyLine(Section section, int number) {
        for (int i = 0; i < number; i++) {
            section.add(new Paragraph(" "));
        }
    }

}