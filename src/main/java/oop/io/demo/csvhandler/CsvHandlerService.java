package oop.io.demo.csvhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oop.io.demo.csvhandler.ApacheCommonsCsvUtil;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

/**
 * 
 * Copyright https://loizenai.com
 * @author loizenai.com
 */
@Service
public class CsvHandlerService {
    @Autowired
	UserRepository userRepo;

	// Store Csv File's data to database
	public void store(InputStream file) {
		try {
			// Using ApacheCommons Csv Utils to parse CSV file
			List<User> userList = ApacheCommonsCsvUtil.parseCsvFile(file);
			
			// Using OpenCSV Utils to parse CSV file
			// List<Users> usersList = OpenCsvUtil.parseCsvFile(file);
			
			// Save users to database
			userRepo.saveAll(userList);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	// Load Data to CSV File
    public void loadFile(Writer writer) throws IOException {
    	try {
        	List<User> users = (List<User>) userRepo.findAll();
        	
        	// Using ApacheCommons Csv Utils to write User List objects to a Writer
             ApacheCommonsCsvUtil.usersToCsv(writer, users);
        	
        	// Using Open CSV Utils to write User List objects to a Writer
        	// OpenCsvUtil.usersToCsv(writer, users);    		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
}
