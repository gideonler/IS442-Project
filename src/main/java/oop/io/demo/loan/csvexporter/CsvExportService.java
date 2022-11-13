package oop.io.demo.loan.csvexporter;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

import oop.io.demo.loan.Loan;
import oop.io.demo.loan.LoanRepository;

@Service
public class CsvExportService {

    private final LoanRepository loanRepository;

    public CsvExportService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void writeLoansToCsv() throws Exception{
        try{
            String emailTemplateUploadDir = "src/main/resources/CsvExports/Loan.csv";

            File file = new File(emailTemplateUploadDir);
    
            FileWriter outputfile = new FileWriter(file);
    
            List<Loan> loans = loanRepository.findAll();

            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"LoanID", "LoanDate", "AttractionName", "UserEmail", "ContactNo", "PassNo", "Status"};
            writer.writeNext(header);
            for (Loan loan : loans) {
                String[] data = {loan.getLoanID(), loan.getLoanDate().toString(), loan.getAttractionName(), loan.getUserEmail(), loan.getContactNo(), loan.getPassNo(), loan.getStatus().toString()};
                writer.writeNext(data);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}