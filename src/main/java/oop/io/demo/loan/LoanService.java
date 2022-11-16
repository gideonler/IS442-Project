package oop.io.demo.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
// Service component is used to annotate class at service layer, build business
// logic
public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;
    private final PassRepository passRepository;
    private final UserRepository userRepository;

    Pass p = new Pass();
    User u = new User();

    public LoanService(LoanRepository loanRepository, PassRepository PassRepository, UserRepository UserRepository) {
        this.loanRepository = loanRepository;
        this.passRepository = PassRepository;
        this.userRepository = UserRepository;
    }


    public String extractPassId(String attractionName, LocalDate loanDate){
        String passId="";
        List<String> loanedPasses = new ArrayList<String>();
        Optional<List<Pass>> passList = passRepository.findByAttractionName(attractionName);
        ArrayList<Loan> loanList = loanRepository.findAllByAttractionName(attractionName);
        for (Loan loan: loanList){
            String checkAttraction = loan.getAttractionName();
            LocalDate checkDate = loan.getLoanDate();
            if ((checkAttraction.equals(attractionName)) && (loanDate.equals(checkDate))){
                loanedPasses.add(loan.getPassId());
            }
            for (Pass pass : passList.get()){
                String checkPassId = pass.getPassId();
                if (!loanedPasses.contains(checkPassId)){
                    passId = checkPassId;
                    break;
                }
            }
        }


        return passId;
    }

    public String extractContactNo(String userEmail){
        String contactNo = "";
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()){
            contactNo = user.get().getContactNo();
        }
        return contactNo;
    }



    // LoanRepository lr=new LoanRepository();
    // loan service should:
    // have method be able to change pass status (when pass is made available)

    // method to allow user to make booking

    // method to allow user to cancel booking

    public Loan addBooking(String userEmail, LocalDate loanDate, String attractionName, String loanId) {
    
        String passId = extractPassId(attractionName, loanDate);
        if (passId != ""){
            String contactNo = extractContactNo(userEmail);
            Loan loan = new Loan(userEmail, loanDate, attractionName, loanId);
            loan.setPassId(passId);
            loan.setLoanId();
            loan.setStatus(LOANSTATUS.CONFIRMED);
            loan.setContactNo(contactNo);
            loanRepository.save(loan);
            return loan;
        }

        // if (checkAvail(loanDate,attractionName)){
        // repository.save(loan);
        // return "Booking to " + loan.getAttractionName() + " made by " +
        // loan.getUserEmail() + " has been added.";
        // }
        // getUserInfo(loanDate,attractionName);
        return null;
    }

    public ResponseEntity cancelLoan(String loanID, LOANSTATUS loanStatus) {
        Loan l = loanRepository.findByLoanId(loanID);
        l.setStatus(LOANSTATUS.CANCELLED);
        return ResponseEntity.ok("Loan cancelled: " + loanStatus.toString());
    }




    public ResponseEntity changeLoanStatus(String loanId, LOANSTATUS loanstatus){
        Optional<Loan> l = loanRepository.findById(loanId);
        if(!l.isPresent()) {
            return ResponseEntity.badRequest().body("Loan does not exist");
        }
        Loan loan = l.get();
        loan.setStatus(loanstatus);
        loanRepository.save(loan);
        return ResponseEntity.ok("Changed status of loan successfully to: " +loanstatus.toString());
    }

    public String deleteBooking(String loanId, LocalDate loanDate) {
        Loan loan = loanRepository.findByLoanId(loanId);
        // ArrayList<Loan>loan=loanRepository.findByUserEmail(userEmail);
        loanRepository.delete(loan);
        return "Booking to " + loan.getAttractionName() + " made by " + loan.getUserEmail() + " has been deleted.";
    }

    // Method for GO to update the status of the loanpass once user has collected
    public String LoanCollect(String userEmail) {
        Calendar cal = Calendar.getInstance();
        LocalDate todayDate = LocalDate.now();
        String checkID = userEmail + todayDate;
        Loan loan = loanRepository.findByLoanId(checkID);
        // ArrayList<Loan>loan=loanRepository.findByLoanId(checkID);
        loan.setStatus(LOANSTATUS.ACTIVE);
        // trigger email here
        return "Loan has been collected by the user";

    }
    
    public String checkLoan(String userEmail) {
        ArrayList<Loan> loanList = loanRepository.findAllByUserEmail(userEmail);
        LocalDate todayDate = LocalDate.now();
        for (int i = 0; i < loanList.size(); i++) {
            Loan loan = loanList.get(i);
            LocalDate checkDate = loan.getLoanDate();
            if (todayDate.equals(checkDate)) {
                return "Booking exists on " + todayDate;
            } else {
                return "Booking does not exist on " + todayDate;
            }
        }
        return "";

    }

    // The system checks whether a booking for an attraction on a date is available
    // or not.
    public boolean checkAvail(LocalDate loanDate, String attractionName) {
        ArrayList<Loan> loans = loanRepository.findAllByAttractionName(attractionName);
        Iterator<Loan> iter = loans.iterator();

        while (iter.hasNext()) {
            Loan value = iter.next();
            if (value.equals(loanDate)) {
                return false;

            }
        }
        return true;

    }



    // Method for getting userinfo of a loan
    public String getUserInfo(LocalDate loanDate, String attractionName) {
        ArrayList<Loan> loans = loanRepository.findAllByAttractionName(attractionName);
        String userEmail = u.getEmail();
        String toReturn = "";

        for (Loan loan : loans) {
            LocalDate checkDate = loan.getLoanDate();
            if (checkDate == loanDate) {
                String passId = p.getPassId();
                String uEmail = loan.getUserEmail();
                Loan booked_user = loanRepository.findByUserEmail(userEmail).get();
                String userName = booked_user.getName();
                String contactNo = booked_user.getContactNo();

                toReturn = "The booking for pass " + passId + " is already loaned by " + userName + " (Contact No: "
                        + contactNo + ")";
            } else {
                toReturn = "Not found!";
            }

        }
        return toReturn;

    }

    // Method for the user to report loss of cards

    public ResponseEntity ReportLoss(String loanID, LOANSTATUS loanStatus) {
        Loan l = loanRepository.findByLoanId(loanID);
        l.setStatus(LOANSTATUS.LOST);
        return ResponseEntity.ok("Card changed to lost: " + loanStatus.toString());
    }


    // Method to cancel all loans
    public String cancelAllLoans(String passId, LocalDate date) {
        ArrayList<Loan> loans = loanRepository.findAllByPassId(passId);
        if (!loans.isEmpty()) {
            for (Loan loan : loans) {
                LocalDate checkDate = loan.getLoanDate();
                // to check whether the loan is made for later dates
                if (date.isBefore(checkDate)) {
                    loan.setStatus(LOANSTATUS.LOST);
                }
            }
        }
        return "All changes have been made";
    }

    public Map<String,Map<String,Integer>> checkUnavailPasses() {
        Map<String,Map<String,Integer>> output = new TreeMap<>();

        try{
            List<Loan> loans = loanRepository.findAll();

            for (Loan loan: loans){
                String attraction = loan.getAttractionName();
                LocalDate loanDate = loan.getDueDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(loanDate);
                int availPass = passRepository.findByAttractionName(attraction).get().size();
                if (output.containsKey(attraction)){
                    if (output.get(attraction).containsKey(strDate)){
                        output.get(attraction).put(strDate, output.get(attraction).get(strDate) - 1);
                    } else {
                        output.get(attraction).put(strDate,availPass - 1);
                    }
                } else {
                    Map<String,Integer> add = new TreeMap<>();
                    add.put(strDate, availPass-1);
                    output.put(attraction,add);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

} 