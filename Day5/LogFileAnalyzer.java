package Day5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LogFileAnalyzer {
        public static void main(String[] args) {
            int infoCount=0;
            int warnCount=0;
            int errorCount=0;
            int total=0;
            List<String> messages = new ArrayList<>();
            List<LocalDateTime> ts = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Server.log"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Results.txt"));

            String line;
            List <String> data = new ArrayList<>();
            Map <String,Integer> logger = new HashMap<>();
            
            while((line=br.readLine())!=null){
                if(!line.contains("[")){
                    throw new MalformedLogEntryException(line);
                }
                // System.out.println("CURRENT LINE: "+line);
                if(line.contains("INFO")){
                    infoCount++;
                    total++;
                    logger.put("INFO", infoCount);
                    // System.out.println("ADDED INFO");
                }else if(line.contains("ERROR")){
                    errorCount++;
                    total++;
                    logger.put("ERROR", errorCount);
                    //System.out.println("ADDED ERROR");
                }else if(line.contains("WARN")){
                    warnCount++;
                    total++;
                    logger.put("WARN", warnCount);
                    //System.out.println("ADDED WARN");
                }else{
                    System.out.println("NO INFO");
                }
                data.add(line);
                
            }
            br.close();
            String[]words;
            String[]date;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (String  i: data) {
                words = i.split(":");
                
                // System.out.println(words[3]);
                messages.add(words[3]);
                //  
                
                ts.add(LocalDateTime.parse(i.substring(1,20),formatter));
            }
            
            //PRINTING RESULTS
            System.out.println("Log Summary Report");
            bw.write("Log Summary Report");
            bw.newLine();
            System.out.println("-------------------");
            bw.write("-------------------");
            bw.newLine();
            System.out.println("Total Entries: "+total);
            bw.write("Total Entries: "+total);
            bw.newLine();
            for (Map.Entry<String,Integer> entry : logger.entrySet()){
                System.out.println(entry.getKey()+": "+entry.getValue());
                bw.write(entry.getKey()+": "+entry.getValue());
                bw.newLine();
            }
            System.out.println("\nError messages:");
            bw.write("\nError messages:");
            bw.newLine();
            for (String i : messages) {
                System.out.println(i);
                bw.write(i);
                bw.newLine();
            }
            // for (LocalDateTime elem : ts) {
            //     System.out.println(elem.format(formatter));
            //     // System.out.println(least(elem, elem));
            // }

            LocalDateTime minDate = Collections.min(ts);
            LocalDateTime maxDate = Collections.max(ts);
            System.out.print("\nEarliest Timestamp: ");
            bw.write("\nEarliest Timestamp: ");
            System.out.println(minDate.format(formatter));
            bw.write(minDate.format(formatter));
            bw.newLine();

            
            System.out.print("Latest Timestamp: ");
            bw.write("Latest Timestamp: ");
            System.out.println(maxDate.format(formatter));
            bw.write(maxDate.format(formatter));
            bw.newLine();

            bw.close();
            
                
            } catch (FileNotFoundException e) {
                System.out.println("Error file not found");
                
            }catch (IOException e){
                System.out.println("Error");
            }
            catch (MalformedLogEntryException e){
                System.out.println(e.toString());
            }
    
}
    

//     public static LocalDateTime least(LocalDateTime a, LocalDateTime b) {
//     return a == null ? b : (b == null ? a : (a.isBefore(b) ? a : b));
// }
}
