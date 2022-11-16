package oop.io.demo.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    AnalysisService analysisService;

    //Total Loans/Month by Year -->both must be "yyyy-MM" format
    @GetMapping("/totalloans")
    public ResponseEntity getTotalLoans() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.allLoans());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Total loans not shown! :(");
        }
    }

    //Average Loans/Month by Year
    @GetMapping("/averageloans")
    public ResponseEntity getAverageLoans() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.averageLoans());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Average loans not shown! :(");
        }
    }

    //Total Loans/Month by Attraction, Year
    @GetMapping("/totalattractions")
    public ResponseEntity getTotalLoansAttractions() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.allAttractionLoans());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Attractions not shown! :(");
        }
    }

    //Total Unique Employees by Year
    @GetMapping("/totalemployees")
    public ResponseEntity getTotalEmployees() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.allEmployees());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Total employees not shown! :(");
        }
    }

    //Total Unique Loans by Year
    @GetMapping("/totalyearloans")
    public ResponseEntity getTotalYearLoans() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.allYearLoans());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Total year loans not shown! :(");
        }
    }

    //Total Unique Employees by Year
    @GetMapping("/totalyearemployees")
    public ResponseEntity getTotalYearEmployees(){
        try{
            return ResponseEntity.ok(analysisService.allYearEmployees());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Total year employees not shown! :(");
        }
    }

    //Summarised Yearly Stats
    @GetMapping("/yearstats")
    public ResponseEntity getYearSummary() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.yearSummary());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Year Summary not shown! :(");
        }
    }

/* 
    //No. of Available Passes Left to Loan
    @GetMapping("/loans")
    public ResponseEntity getAllLoansInCsv() throws Exception {
        try{
            analysisService.writeLoansToCsv();
            return ResponseEntity.ok("Loans CSV exported!");
        } catch (Exception e){
            return ResponseEntity.ok("Loans CSV not sent! :(");
        }
    }
*/

    
}

/*
@GetMapping("/totalloans")
    public ResponseEntity getTotalLoans(@RequestParam("year") String year, @RequestParam(value = "month", defaultValue = "0") String month) throws Exception {
        try{
            int output = analysisService.allLoans(year, month);
            return ResponseEntity.ok(output);
        } catch (Exception e){
            return ResponseEntity.ok("Loans not shown! :(");
        }
    }
 */