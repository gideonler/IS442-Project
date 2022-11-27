/**
 * Contains methods called by LoanController
 * 1. get passID by attraction extractPassId(String attractionName,LocalDate loanDate)
 * 2. get loan user contact number by userEmail extractContactNo(userEmail)
 * 3. add a booking using addBooking(String userEmail, LocalDate loanDate, String attractionName, String loanId)
 * 4. change to cancel status by cancelLoan(String loanID, LOANSTATUS loanStatus) 
 * 5. change loan status by changeLoanStatus(String loanId, LOANSTATUS loanstatus)
 * 6. delete all loan bookings by deleteBooking(String loanId, LocalDate loanDate)
 * 7. change all loan statuses once user has collected passes by GO based on userEmail LoanCollect(String userEmail)
 * 8. get loan bookings by userEmail and check if there is an active loan by checkLoan(String userEmail) 
 * 9. check if there is a booking available for the attraction on that date using checkAvail(LocalDate loanDate, String attractionName)
 * 10. return user info of the particular loan based on loanDate and attractionName by getUserInfo(LocalDate loanDate, String attractionName)
 * 11. change status to LOSS if pass is lost based on the loanID ReportLoss(String loanID, LOANSTATUS loanStatus)
 * 12. cancels all loans made based on date and passID by cancelAllLoans(String passId, LocalDate date)
 * 13. get passes that are unavailable returning all unavailable passes by checkUnavailPasses()
 */
package oop.io.demo.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import oop.io.demo.attraction.Attraction;
import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;
import oop.io.demo.attraction.AttractionRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service

public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;
    private final PassRepository passRepository;
    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;

    Pass p = new Pass();
    User u = new User();

    public LoanService(LoanRepository loanRepository, PassRepository PassRepository, UserRepository UserRepository, AttractionRepository attractionRepository) {
        this.loanRepository = loanRepository;
        this.passRepository = PassRepository;
        this.userRepository = UserRepository;
        this.attractionRepository = attractionRepository;
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
        }
        for (Pass pass : passList.get()){
            String checkPassId = pass.getPassId();
            if (!loanedPasses.contains(checkPassId)){
                passId = checkPassId;
                break;
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
        loanRepository.delete(loan);
        return "Booking to " + loan.getAttractionName() + " made by " + loan.getUserEmail() + " has been deleted.";
    }

    // Method for GO to update the status of the loanpass once user has collected
    public String LoanCollect(String userEmail) {
        LocalDate todayDate = LocalDate.now();
        String checkID = userEmail + todayDate;
        Loan loan = loanRepository.findByLoanId(checkID);
        loan.setStatus(LOANSTATUS.ACTIVE);
        // trigger email here
        return "Loan has been collected by the user";

    }


    //Method to check if loan is active
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


    public ResponseEntity ReportLoss(String loanID, LOANSTATUS loanStatus){
        Loan l = loanRepository.findByLoanId(loanID);
        String attractionName = l.getAttractionName();
        String userEmail = l.getUserEmail();
        Attraction a = attractionRepository.findByAttractionName(attractionName).get();
        User u = userRepository.findByEmail(userEmail).get();
        l.setStatus(LOANSTATUS.LOST);
        Double fee = a.getReplacementFee();
        u.setOutstandingFees(fee);
        Double new_fee = u.getOutstandingFees();
        userRepository.save(u);
        return ResponseEntity.ok("Card changed to lost: " + loanStatus.toString()+ ", please pay a fee of " + new_fee);
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
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate loanDate = LocalDate.parse(loan.getDueDate(), dateFormat);
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