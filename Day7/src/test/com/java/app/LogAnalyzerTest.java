package com.java;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.day6.*;

import main.LogAnalyzer;

import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.FileWriter;
import java.io.IOException;



class LogAnalyzerTest extends LogAnalyzer {

	

	@BeforeEach
	void setUp() throws Exception {
		LogAnalyzer log = new LogAnalyzer();
		
	}

	
	@Test
    void testMssgMissing() throws Exception {
        String[] args = {"resources/Test2.log"};
 
		   PrintStream originalOut = System.out;


	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	       
	        try {
	            LogAnalyzer.main(args);

	            String output = outputStream.toString();
	            assertEquals("Skipping malformed line: [2024-05-10 09:00:06] \r\n"
	            		+ "Analysis complete. Summary written to summary.txt", output);

	        } finally {
	            System.setOut(originalOut);
	        }
    }

	@Test
    void testMainThrowsCustomException() throws Exception {
        String[] args = {"resources/Test1.log"};
 
		   PrintStream originalOut = System.out;


	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	       
	        try {
	            LogAnalyzer.main(args);

	            String output = outputStream.toString();
	            assertEquals("Skipping malformed line: 2024-05-10 09:00:06] INFO: Database connection established\r\n"
	            		+ "Skipping malformed line: [2024-05-10 09:00:21 INFO: Backup completed successfully\r\n"
	            		+ "Analysis complete. Summary written to summary.txt", output);

	        } finally {
	            System.setOut(originalOut);
	        }
    }

	@Test
    void testTypeMissing() throws Exception {
        String[] args = {"resources/Test3.log"};
 
		   PrintStream originalOut = System.out;


	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	       
	        try {
	            LogAnalyzer.main(args);

	            String output = outputStream.toString();
	            assertEquals("Skipping malformed line: [2024-05-10 09:00:18] : Scheduled backup started\r\n"
	            		+ "Analysis complete. Summary written to summary.txt", output);

	        } finally {
	            System.setOut(originalOut);
	        }
    }
	
	@Test
    void testFileMissing() throws Exception {
        String[] args = {"resources/Test-1.log"};
 
		   PrintStream originalOut = System.out;


	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	       
	        try {
	            LogAnalyzer.main(args);

	            String output = outputStream.toString();
	            assertEquals("Log file not found.\r\n", output);

	        } finally {
	            System.setOut(originalOut);
	        }
    }
	

	@Test
    void testMain() {

        PrintStream originalOut = System.out;


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String args[] = {"resources/server.log"};
        try {
            LogAnalyzer.main(args);

            String output = outputStream.toString();

            assertEquals("Analysis complete. Summary written to summary.txt", output);

        } finally {
            System.setOut(originalOut);
        }

	}
	
	

	@Test
    void shouldCreateFile() throws IOException {
		
		String args[] = {"resources/Server.log"};
		LogAnalyzer.main(args);
		Path path = Paths.get("resources/summary.txt");
	    assertTrue(Files.exists(path), "File should exist");
        
        
    }
	
	@Test
	void testFileContent() throws Exception {
	    Path filePath = Path.of("resources/summary.txt");
	    String content = Files.readString(filePath);
	    String expected = "Log Summary Report\r\n------------------\r\n"
	    		+ "Total Entries: 85\r\n"
	    		+ "INFO: 66\r\n"
	    		+ "WARN: 10\r\n"
	    		+ "ERROR: 9\r\n"
	    		+ "\r\n"
	    		+ "Error Messages:\r\n"
	    		+ "- Failed to connect to external API\r\n"
	    		+ "- NullPointerException in UserService.java line 87\r\n"
	    		+ "- Timeout while reading data from database\r\n"
	    		+ "- FileNotFoundException in ConfigLoader.java line 45\r\n"
	    		+ "- Unauthorized access attempt detected\r\n"
	    		+ "- Assertion failed in TestCase #45\r\n"
	    		+ "- Unable to parse JSON response from API\r\n"
	    		+ "- SMTP server not responding\r\n"
	    		+ "- Audit log write failure\r\n"
	    		+ "\r\n"
	    		+ "Earliest Timestamp: 2024-05-10T09:00\r\n"
	    		+ "Latest Timestamp: 2024-05-10T09:04:18";
	    
		expected = expected.replace("\r\n", "\n");
		content = content.replace("\r\n", "\n");
		
		assertEquals(expected.trim(), content.trim());

	    assertEquals(expected, content.trim());
	}

	@Test
	void testIO() throws IOException {

        PrintStream originalOut = System.out;
        String args[] = {"resources/test5.log"};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
		try (FileOutputStream fos = new FileOutputStream("resources/test5.log");
				FileChannel channel = fos.getChannel()) {
	           
	           FileLock lock = channel.lock();
	           
	           
	           LogAnalyzer.main(args);

	            String output = outputStream.toString();

	            assertEquals("Error reading file.", output.trim());

	         
	           lock.release();
	           System.out.println("File lock released.");
	           
	       } finally {
	    	   System.setOut(originalOut);
	       }
	}
	
	@Test
	void testWrite() throws FileNotFoundException, IOException {
		 
		      File myObj = new File("resources/summary.txt");
		      myObj.createNewFile();           
		        
			 	PrintStream originalOut = System.out;
		        String args[] = {"resources/server.log"};
	
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        System.setOut(new PrintStream(outputStream));
			try (FileOutputStream fos = new FileOutputStream("resources/summary.txt");
					FileChannel channel = fos.getChannel()) {
		           
		           FileLock lock = channel.lock();
		           
		           
		           LogAnalyzer.main(args);

		            String output = outputStream.toString();
		            String expected = "Error writing summary file.\nAnalysis complete. Summary written to summary.txt";
		            expected = expected.replace("\r\n", "\n");
		    		output = output.replace("\r\n", "\n");
		            assertEquals(expected.trim(), output.trim());

		         
		           lock.release();
		           System.out.println("File lock released.");
		           
		       } finally {
		    	   System.setOut(originalOut);
		       }
		 
	}
	

	
}
