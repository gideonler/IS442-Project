package oop.io.demo.csvhandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import oop.io.demo.csvhandler.Message;
import oop.io.demo.csvhandler.Response;
import oop.io.demo.csvhandler.CsvHandlerService;
import oop.io.demo.csvhandler.ApacheCommonsCsvUtil;

/**
 * 
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */

@RestController
@RequestMapping("/api/upload/csv")
public class UploadCsvController {
    @Autowired
	CsvHandlerService csvHandlerService;

	@PostMapping("/users")
	public Response uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {
	
		Response response = new Response();
	
		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file to upload! Please select a File", "fail"));
	
			return response;
		}
	
		// checking the upload file's type is CSV or NOT
		
		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file! Please check your file type.", "fail")); 
	        return response; 
		}
		  
		
		try {
			// save file data to database
			csvHandlerService.store(csvfile.getInputStream());
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}
}
