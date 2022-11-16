/**
 * Contains methods called by AnalysisController to
 * 1. get total loans in a month/year allLoans()
 * 2. get average loans in a month/year averageLoans()
 * 3. get loans/attraction in a month/year allAttractionLoans()
 * 4. get total loans in a year allYearLoans()
 * 5. get total unique employees in a month/year allEmployees()
 * 6. get total employees in a year allYearEmployees()
 * 7. get summary statistics per year yearSummary()
 */

package oop.io.demo.analysis;

import org.springframework.stereotype.Service;

import java.util.*;

import oop.io.demo.loan.Loan;
import oop.io.demo.loan.LoanRepository;
import oop.io.demo.user.UserRepository;

@Service
public class AnalysisService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public AnalysisService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    //Get Total Loans in a Month/Year
    public Map<String,Map<String,Integer>> allLoans() throws Exception{
        Map<String,Map<String,Integer>> output = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String strYear = ""+loan.getLoanDate().getYear();
                String strMonth = ""+loan.getLoanDate().getMonth().getValue();
                if (output.containsKey(strYear)){
                    if (output.get(strYear).containsKey(strMonth)){
                        output.get(strYear).put(strMonth, output.get(strYear).get(strMonth) + 1);
                    } else {
                        output.get(strYear).put(strMonth,1);
                    }
                } else {
                    Map<String,Integer> add = new TreeMap<>();
                    add.put(strMonth, 1);
                    output.put(strYear,add);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Average Loans in a Year
    public Map<String,Map<String,Double>> averageLoans() throws Exception{
        Map<String,Map<String,Integer>> allLoans = allLoans();
        Double totalEmployees = Double.valueOf(userRepository.findAll().size());
        Map<String,Map<String,Double>> output =  new TreeMap<>();

        try{
            for (Map.Entry<String,Map<String,Integer>> entry : allLoans.entrySet()){
                String year = entry.getKey();
                for (Map.Entry<String,Integer> entry1 : allLoans.get(year).entrySet()){
                    String month = entry1.getKey();
                    Double avg = Double.valueOf((allLoans.get(year).get(month)))/totalEmployees;
                    Map<String,Double> add = new TreeMap<>();
                    add.put(month,avg);
                    if(output.containsKey(year)){
                        output.get(year).put(month,avg);
                    }else {
                        output.put(year, add);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Attraction Loans in a Month/Year
    public Map<String,Map<String,Map<String,Integer>>> allAttractionLoans() throws Exception{
        Map<String,Map<String,Map<String,Integer>>> output = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String strYear = ""+ loan.getLoanDate().getYear();
                String strMonth = ""+ loan.getLoanDate().getMonth().getValue();
                String attraction = loan.getAttractionName();
                if (output.containsKey(strYear)){
                    if (output.get(strYear).containsKey(strMonth)){
                        if (output.get(strYear).get(strMonth).containsKey(attraction)){
                            output.get(strYear).get(strMonth).put(attraction,output.get(strYear).get(strMonth).get(attraction) + 1);
                        }
                        else {
                            output.get(strYear).get(strMonth).put(attraction,1);
                        }
                    } else {
                        Map<String,Integer> add1 = new TreeMap<>();
                        add1.put(attraction, 1);
                        output.get(strYear).put(strMonth, add1);
                    }
                } else {
                    Map<String,Integer> add1 = new TreeMap<>();
                    add1.put(attraction, 1);
                    Map<String,Map<String,Integer>> add2 = new TreeMap<>();
                    add2.put(strMonth,add1);
                    output.put(strYear,add2);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Total Loans in a Year
    public Map<String,Integer> allYearLoans() throws Exception{
        Map<String,Integer> output = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String strYear = ""+loan.getLoanDate().getYear();

                if (output.containsKey(strYear)){
                    output.put(strYear, output.get(strYear) + 1);
                } else {
                    output.put(strYear,1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Total Unique Employees in a Month/Year
    public Map<String,Map<String,Integer>> allEmployees() throws Exception{
        Map<String,Map<String,Integer>> output = new TreeMap<>();
        Map<String,ArrayList> employeeMap = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String empEmail = loan.getUserEmail();
                String strYear = ""+ loan.getLoanDate().getYear();
                String strMonth = ""+ loan.getLoanDate().getMonth();
                if (output.containsKey(strYear)){
                    if (output.get(strYear).containsKey(strMonth)){
                        if(!(employeeMap.get(strYear+strMonth).contains(empEmail))){
                            output.get(strYear).put(strMonth, output.get(strYear).get(strMonth) + 1);
                            employeeMap.get(strYear+strMonth).add(empEmail);
                        }
                    } else {
                        output.get(strYear).put(strMonth,1);
                        ArrayList empList = new ArrayList<>();
                        empList.add(empEmail);
                        employeeMap.put(strYear+strMonth, empList);
                    }
                } else {
                    Map<String,Integer> add = new TreeMap<>();
                    add.put(strMonth, 1);
                    output.put(strYear,add);
                    ArrayList empList = new ArrayList<>();
                    empList.add(empEmail);
                    employeeMap.put(strYear+strMonth, empList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Total Unique Employees in a Year
     public Map<String,Integer> allYearEmployees() throws Exception{
        Map<String,Integer> output = new TreeMap<>();
        Map<String,ArrayList> employeeMap = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String empEmail = loan.getUserEmail();
                String strYear = ""+loan.getLoanDate().getYear();

                if (output.containsKey(strYear)){
                    if(!(employeeMap.get(strYear).contains(empEmail))){
                        output.put(strYear, output.get(strYear) + 1);
                        employeeMap.get(strYear).add(empEmail);
                    }
                } else {
                    output.put(strYear,1);
                    ArrayList empList = new ArrayList<>();
                    empList.add(empEmail);
                    employeeMap.put(strYear, empList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //Get Yearly Summary
    public Map<String,Map<String,Integer>> yearSummary() throws Exception{
        Map<String,Integer> allYearEmployees = allYearEmployees();
        Map<String,Integer> allYearLoans = allYearLoans();
        Map<String,Map<String,Integer>> output = new TreeMap<>();

        for (Map.Entry<String,Integer> entry : allYearLoans.entrySet()){
            String year = entry.getKey();
            int totalloans = allYearLoans.get(year);
            int totalemployees = allYearEmployees.get(year);
            int avgloans = totalloans/totalemployees;
            Map<String,Integer> temp = new TreeMap();
            temp.put("total_loans", totalloans);
            temp.put("total_borrowers", totalemployees);
            temp.put("avg_loans", avgloans);

            output.put(year, temp);
        }
        return output;
    }
}