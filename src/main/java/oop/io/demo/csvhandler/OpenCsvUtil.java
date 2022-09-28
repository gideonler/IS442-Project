package oop.io.demo.csvhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import oop.io.demo.user.User;

/**
 * Copyright by https://loizenai.com
 * 
 * @author loizenai.com
 */
public class OpenCsvUtil {

	public static List<User> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
		Reader fileReader = null;
		CsvToBean<User> csvToBean = null;
	
		List<User> users = new ArrayList<User>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<User> mappingStrategy = new ColumnPositionMappingStrategy<User>();
	
			mappingStrategy.setType(User.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<User>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
	
			users = csvToBean.parse();
			
			return users;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return users;
	}

	public static void customersToCsv(Writer writer, List<User> users) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
	    
	    StatefulBeanToCsv<User> beanToCsv = null;
	 
	    try {
	      // write List of Objects
	      ColumnPositionMappingStrategy<User> mappingStrategy = 
	                new ColumnPositionMappingStrategy<User>();
	      
	      mappingStrategy.setType(User.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<User>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(users);
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    }
	}
}
