package oop.io.demo.loan.csvexporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/export")
public class CsvExporterController {
    @Autowired
    CsvExportService csvExportService;

    @GetMapping("/loans")
    public ResponseEntity getAllLoansInCsv() throws Exception {
        try{
            csvExportService.writeLoansToCsv();
            return ResponseEntity.ok("Loans CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("CSV not sent! :(");
        }
    }

}