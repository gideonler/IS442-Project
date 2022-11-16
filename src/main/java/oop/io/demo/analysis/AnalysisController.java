/**
 * Contains endpoints for ADMIN to
 * 1. Get total loans/month per year [GET] /analysis/totalloans
 * 2. Get average loans/month per year [GET] /analysis/averageloans
 * 3. Get total loans/attraction/month per year [GET] /analysis/totalattractions
 * 4. Get total unique employee/month per year [GET] /analysis/totalemployees
 * 5. Get total borrowers & total loans & avg loans per year [GET] /analysis/yearstats
*/

package oop.io.demo.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.security.jwt.JwtUtils;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    AnalysisService analysisService;

    //Total Loans/Month by Year
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
}