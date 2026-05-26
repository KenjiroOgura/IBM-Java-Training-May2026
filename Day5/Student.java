


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Student {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("student.csv"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write("{");
            bw.newLine();
            String line;
            String[] id = br.readLine().split(",");
            String[] data;
            for (int i=0;i<id.length;i++) {
                id[i]=id[i].replace(" ", "");
            }
            while((line=br.readLine())!=null){
                bw.write("\t{");
                bw.newLine();
                for (int i = 0; i < id.length; i++) {
                    data= line.split(",");
                    data[i]=data[i].replace("\"", "");
                    bw.write("\t\t"+id[i]+":\""+data[i]+"\"");
                    System.out.println(id[i]+":\""+data[i]+"\"");
                    bw.newLine();
                    
                }
                System.out.println();
                bw.write("\t}");
                bw.newLine();
            }
           
            bw.write("}");
            br.close();
            bw.close();
        }catch(IOException e){
            System.out.println("Error");
        }
    }
}
