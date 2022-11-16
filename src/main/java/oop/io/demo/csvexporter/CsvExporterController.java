package oop.io.demo.csvexporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/export")
public class CsvExporterController {
    @Autowired
    CsvExportService csvExportService;

    /* 
    @GetMapping("/loans")
    public ResponseEntity getAllLoansInCsv() throws Exception {
        try{
            csvExportService.writeLoansToCsv();
            return ResponseEntity.ok("Loans CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("Loans CSV not sent! :(");
        }
    }
*/
    @GetMapping("/loans")
    public ResponseEntity getAllLoansInCsv() throws Exception {
        try{
            return ResponseEntity.ok(csvExportService.writeLoansToJSON());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Loans CSV not sent! :(");
        }
    }

    @GetMapping("/attractions")
    public ResponseEntity getAllAttractionsInCsv() throws Exception {
        try{
            csvExportService.writeAttractionsToCsv();
            return ResponseEntity.ok("Attractions CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("Attractions CSV not sent! :(");
        }
    }

    @GetMapping("/passes")
    public ResponseEntity getAllPassesInCsv() throws Exception {
        try{
            csvExportService.writePassesToCsv();
            return ResponseEntity.ok("Passes CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("Passes CSV not sent! :(");
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsersInCsv() throws Exception {
        try{
            csvExportService.writeUsersToCsv();
            return ResponseEntity.ok("Users CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("Users CSV not sent! :(");
        }
    }

}