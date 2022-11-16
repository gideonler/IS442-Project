/**
 * Contains endpoints for ADMIN to export JSON of the following
 * 1. Loans [GET] /export/loans
 * 2. Attractions [GET] /export/attractions
 * 3. Passes [GET] /export/passes
 * 4. Users [GET] /export/users
*/

package oop.io.demo.csvexporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.security.jwt.JwtUtils;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/export")
public class CsvExporterController {

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    CsvExportService csvExportService;

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
            return ResponseEntity.ok(csvExportService.writeAttractionsToJSON());
        } catch (Exception e){
            return ResponseEntity.ok("Attractions CSV not sent! :(");
        }
    }

    @GetMapping("/passes")
    public ResponseEntity getAllPassesInCsv() throws Exception {
        try{
            return ResponseEntity.ok(csvExportService.writePassesToJSON());
        } catch (Exception e){
            return ResponseEntity.ok("Passes CSV not sent! :(");
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsersInCsv() throws Exception {
        try{
            return ResponseEntity.ok(csvExportService.writeUsersToJSON());
        } catch (Exception e){
            return ResponseEntity.ok("Users CSV not sent! :(");
        }
    }

}