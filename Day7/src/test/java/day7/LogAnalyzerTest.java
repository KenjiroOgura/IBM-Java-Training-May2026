package day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Day7.LogAnalyzer;



class LogAnalyzerTest extends LogAnalyzer {

	

	@BeforeEach
	void setUp() throws Exception {
		LogAnalyzer log = new LogAnalyzer();
		File myObj = new File("src/main/resources/summary.txt");
		myObj.delete();
	}

	
	@Test
    void testMssgMissing() throws Exception {
        
	        
	        PrintStream originalOut = System.out;

	        String expected = Files.readString(Path.of("src/test/resources/Exec003/missingSummary.txt"));
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	        String args[] = {"src/test/resources/Exec003/Test2.log"};
	        try {
	            LogAnalyzer.main(args);
	            String actual = Files.readString(Path.of("src/main/resources/summary.txt"));
	            String output = outputStream.toString();
	            assertEquals("Skipping malformed line: [2024-05-10 09:00:06] \r\n"
	            		+ "Analysis complete. Summary written to summary.txt\r\n", output);
	            assertEquals(expected, actual);
	        } finally {
	            System.setOut(originalOut);
	        }
    }

	@Test
    void testMainThrowsMalformedException() throws Exception {
        PrintStream originalOut = System.out;

        String expected = Files.readString(Path.of("src/test/resources/Exec002/malformed_summary.txt"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String args[] = {"src/test/resources/Exec002/Test1.log"};
        try {
            LogAnalyzer.main(args);
            String actual = Files.readString(Path.of("src/main/resources/summary.txt"));
            String output = outputStream.toString();
        
            assertEquals("Skipping malformed line: 2024-05-10 09:00:06] INFO: Database connection established\r\n"
          		+ "Skipping malformed line: [2024-05-10 09:00:21 INFO: Backup completed successfully\r\n"
          		+ "Analysis complete. Summary written to summary.txt\r\n", output);
            assertEquals(expected, actual);
	    }finally {
	        System.setOut(originalOut);
	    }
	}
	@Test
    void testTypeMissing() throws Exception {
		PrintStream originalOut = System.out;

        String expected = Files.readString(Path.of("src/test/resources/Exec004/summary_type.txt"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String args[] = {"src/test/resources/Exec004/Test3.log"};
        try {
            LogAnalyzer.main(args);
            String actual = Files.readString(Path.of("src/main/resources/summary.txt"));
            String output = outputStream.toString();
            assertEquals("Skipping malformed line: [2024-05-10 09:00:24] : Failed to connect to external API\r\n"
            		+ "Analysis complete. Summary written to summary.txt\r\n", output);
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }
	
	@Test
    void testFileMissing() throws Exception {
        String[] args = {"src/resources/Test-1.log"};
 
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
    void testMain() throws IOException {

        PrintStream originalOut = System.out;

        String expected = Files.readString(Path.of("src/test/resources/Exec001/summary_testMain.txt"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String args[] = {"src/main/resources/server.log"};
        try {
            LogAnalyzer.main(args);
            String actual = Files.readString(Path.of("src/main/resources/summary.txt"));
            String output = outputStream.toString();

            assertEquals("Analysis complete. Summary written to summary.txt\r\n", output);
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }

	}
	
	

	@Test
    void shouldCreateFile() throws IOException {
		
		String args[] = {"src/main/resources/Server.log"};
		LogAnalyzer.main(args);
		Path path = Paths.get("src/main/resources/summary.txt");
	    assertTrue(Files.exists(path), "File should exist");
        
   
    }
	

	@Test
	void testIO() throws IOException {

        PrintStream originalOut = System.out;
        String args[] = {"src/test/resources/test5.log"};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
		try (FileOutputStream fos = new FileOutputStream("src/test/resources/test5.log");
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
		 
		      File myObj = new File("src/main/resources/summary.txt");
		      myObj.createNewFile();           
		        
			 	PrintStream originalOut = System.out;
		        String args[] = {"src/main/resources/server.log"};
	
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        System.setOut(new PrintStream(outputStream));
			try (FileOutputStream fos = new FileOutputStream("src/main/resources/summary.txt");
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
