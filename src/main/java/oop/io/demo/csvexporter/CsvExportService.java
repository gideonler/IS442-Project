/**
 * Contains methods called by CsvExporterController to export the following as Maps:
 * 1. Loans writeLoansToJSON()
 * 2. Attractions writeAttractionsToJSON()
 * 3. Passes writePassesToJSON()
 * 4. Users writeUsersToJSON()
 */

package oop.io.demo.csvexporter;

import org.springframework.stereotype.Service;

import java.util.*;

import oop.io.demo.loan.Loan;
import oop.io.demo.loan.LoanRepository;
import oop.io.demo.attraction.Attraction;
import oop.io.demo.attraction.AttractionRepository;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;
import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;

@Service
public class CsvExportService {

    private final LoanRepository loanRepository;
    private final AttractionRepository attractionRepository;
    private final UserRepository userRepository;
    private final PassRepository passRepository;

    public CsvExportService(LoanRepository loanRepository, AttractionRepository attractionRepository, UserRepository userRepository, PassRepository passRepository) {
        this.loanRepository = loanRepository;
        this.attractionRepository = attractionRepository;
        this.userRepository = userRepository;
        this.passRepository = passRepository;
    }

    //Export Loans as Map
    public Map<Integer,Map<String,String>> writeLoansToJSON() throws Exception{
        Map<Integer,Map<String,String>> output = new TreeMap<>();
        try{    
            List<Loan> loans = loanRepository.findAll();

            int counter = 0;
            for (Loan loan : loans) {
                Map<String,String> temp = new TreeMap();
                temp.put("LoanId", loan.getLoanID());
                temp.put("LoanDate",loan.getLoanDate().toString());
                temp.put("AttractionName", loan.getAttractionName());
                temp.put("UserEmail", loan.getUserEmail());
                temp.put("ContactNo", loan.getContactNo());
                temp.put("PassNo", loan.getPassId());
                temp.put("Status", loan.getStatus().toString());
                output.put(counter, temp);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Export Attractions as Map
    public Map<Integer,Map<String,String>> writeAttractionsToJSON() throws Exception{
        Map<Integer,Map<String,String>> output = new TreeMap<>();
        try{    
            List<Attraction> attractions = attractionRepository.findAll();

            int counter = 0;
            for (Attraction attraction : attractions) {
                Map<String,String> temp = new TreeMap();
                temp.put("AttractionName", attraction.getAttractionName());
                temp.put("ReplacementFee",String.valueOf(attraction.getReplacementFee()));
                temp.put("PassType", attraction.getPassType().toString());
                temp.put("isActive", String.valueOf(attraction.isActive()));
                temp.put("Template", attraction.getTemplateFilename());
                temp.put("Image", attraction.getImageFilename());
                temp.put("Attachment", attraction.getAttachmentPDFFilename());
                output.put(counter, temp);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
            
    //Export Passes as Map
    public Map<Integer,Map<String,String>> writePassesToJSON() throws Exception{
        Map<Integer,Map<String,String>> output = new TreeMap<>();
        try{    
            List<Pass> passes = passRepository.findAll();

            int counter = 0;
            for (Pass pass : passes) {
                Map<String,String> temp = new TreeMap();
                temp.put("PassId", pass.getPassId());
                temp.put("PassNo",pass.getPassNo());
                temp.put("AttractionName", pass.getAttractionName());
                temp.put("PassStatus", pass.getPassStatus().toString());
                output.put(counter, temp);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Export Users as Map
    public Map<Integer,Map<String,String>> writeUsersToJSON() throws Exception{
        Map<Integer,Map<String,String>> output = new TreeMap<>();
        try{    
            List<User> users = userRepository.findAll();

            int counter = 0;
            for (User user : users) {
                Map<String,String> temp = new TreeMap();
                temp.put("Email", user.getEmail());
                temp.put("Name",user.getName());
                temp.put("ContactNo", user.getContactNo());
                temp.put("UserType", user.getUserType().toString());
                temp.put("isVerified", String.valueOf(user.isVerified()));
                temp.put("OutstandingFees", String.valueOf(user.getOutstandingFees()));
                output.put(counter, temp);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}