package spring.organizer.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.organizer.services.EventService;
import spring.organizer.services.ReportService;

import java.io.IOException;

/**
 * Created by radu on 29.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EventService eventService;

//    private JasperReportsPdfView jasperReportsPdfView;


    @RequestMapping(name = "/", method = RequestMethod.POST)
    public ResponseEntity<String> printPdf() throws IOException, DocumentException {
        reportService.generateReport(eventService.findAll());
        return new ResponseEntity<String>("bla", HttpStatus.OK);
    }
}
