package oop.io.demo.csvexporter;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import java.io.*;
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

    //Export Loans CSV
    public void writeLoansToCsv() throws Exception{
        try{
            String emailTemplateUploadDir = "src/main/resources/CsvExports/Loan.csv";

            File file = new File(emailTemplateUploadDir);
    
            FileWriter outputfile = new FileWriter(file);
    
            List<Loan> loans = loanRepository.findAll();

            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"LoanId", "LoanDate", "AttractionName", "UserEmail", "ContactNo", "PassId", "Status"};
            writer.writeNext(header);
            for (Loan loan : loans) {
                String[] data = {loan.getLoanID(), loan.getLoanDate().toString(), loan.getAttractionName(), loan.getUserEmail(), loan.getContactNo(), loan.getPassId(), loan.getStatus().toString()};
                writer.writeNext(data);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Export Attractions CSV
    public void writeAttractionsToCsv() throws Exception{
        try{
            String emailTemplateUploadDir = "src/main/resources/CsvExports/Attractions.csv";

            File file = new File(emailTemplateUploadDir);
    
            FileWriter outputfile = new FileWriter(file);
    
            List<Attraction> attractions = attractionRepository.findAll();

            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"AttractionName", "ReplacementFee", "PassType", "isActive", "Template", "Image", "Attachment"};
            writer.writeNext(header);
            for (Attraction attraction : attractions) {
                String[] data = {attraction.getAttractionName(), String.valueOf(attraction.getReplacementFee()), attraction.getPassType().toString(), String.valueOf(attraction.isActive()), attraction.getTemplateFilename(), attraction.getImageFilename(), attraction.getAttachmentPDFFilename()};
                writer.writeNext(data);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Export Pass CSV
    public void writePassesToCsv() throws Exception{
        try{
            String emailTemplateUploadDir = "src/main/resources/CsvExports/Pass.csv";

            File file = new File(emailTemplateUploadDir);
    
            FileWriter outputfile = new FileWriter(file);
    
            List<Pass> passes = passRepository.findAll();

            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"PassId", "PassNo", "AttractionName", "PassStatus"};
            writer.writeNext(header);
            for (Pass pass : passes) {
                String[] data = {pass.getPassId(),pass.getPassNo(), pass.getAttractionName(), pass.getPassStatus().toString()};
                writer.writeNext(data);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Export Users CSV
    public void writeUsersToCsv() throws Exception{
        try{
            String emailTemplateUploadDir = "src/main/resources/CsvExports/Users.csv";

            File file = new File(emailTemplateUploadDir);
    
            FileWriter outputfile = new FileWriter(file);
    
            List<User> users = userRepository.findAll();

            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"Email", "Name", "ContactNo", "UserType", "isVerified", "OutstandingFees"};
            writer.writeNext(header);
            for (User user : users) {
                String[] data = {user.getEmail(), user.getName(), user.getContactNo(), user.getUserType().toString(),String.valueOf(user.isVerified()),String.valueOf(user.getOutstandingFees())};
                writer.writeNext(data);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}