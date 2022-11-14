package oop.io.demo.analysis;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.analysis.payload.AnalysisRequest;

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
            return ResponseEntity.ok("Loans not shown! :(");
        }
    }

    //Average Loans/Month by Year
    @GetMapping("/averageloans")
    public ResponseEntity getAverageLoans() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.averageLoans());
        } catch (Exception e){
            return ResponseEntity.ok("Loans CSV not sent! :(");
        }
    }

    //Total Loans/Month by Attraction, Year
    @GetMapping("/totalattractions")
    public ResponseEntity getTotalLoansAttractions() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.allAttractionLoans());
        } catch (Exception e){
            return ResponseEntity.ok("Loans CSV not sent! :(");
        }
    }

/* 
    //Summarised Yearly Stats
    @GetMapping("/year")
    public ResponseEntity getYearSummary() throws Exception {
        try{
            return ResponseEntity.ok(analysisService.yearSummary());
        } catch (Exception e){
            return ResponseEntity.ok("Loans CSV not sent! :(");
        }
    }


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