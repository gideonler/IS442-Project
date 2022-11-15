/**
 * TODO: remove uncommented code to allow for email validation
 * 
 * This class contains a method: Store that takes in a file and uses ApacheCommonCsvUtil's parseCsv
 * method to get a list of Sign Up requests whereby it will call sign up each user
 * using AuthService's signUpOneUser method
 */

package oop.io.demo.user.csvhandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oop.io.demo.auth.AuthService;
import oop.io.demo.auth.confirmationToken.ConfirmationTokenRepository;
import oop.io.demo.auth.payload.request.SignupRequest;
import oop.io.demo.user.UserRepository;

@Service
public class CsvHandlerService {
    @Autowired
	UserRepository repository;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	// Store Csv File's data to database
	public ArrayList<String> store(InputStream file) {
		ArrayList<String> messages = new ArrayList<>();
		try {
			// Using ApacheCommons Csv Utils to parse CSV file
			List<SignupRequest> signupRequest = ApacheCommonsCsvUtil.parseCsvFile(file);
			
			int successfulSignUps = 0;
			// Save users to database
			AuthService authService = new AuthService(repository, confirmationTokenRepository);
			for(SignupRequest request: signupRequest) {
				if (repository.existsByEmail(request.getEmail())) {
					messages.add(request.getEmail()+" already exists");
					continue;
				} //else if(!(request.getEmail().matches("[a-z0-9]+@sportsschool.edu.sg")) && !(request.getEmail().matches("[a-z0-9]+@nysi.org.sg"))) {
				// 	messages.add(request.getEmail()+" isn't a Singapore Sports School email.");
				// 	continue;
				// }
				authService.signUpOneUser(request);
				successfulSignUps+=1;
			}
			messages.add(""+successfulSignUps+ " users successfully added to database.");
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}

		return messages;
	}
	
}
