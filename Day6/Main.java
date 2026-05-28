package Day6;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static int idNum=0;
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/test1";
        String user = "Ken";
        String password = "password";
        Connection con = null;
        String fQuery = "SELECT studentid FROM student ORDER BY studentid DESC LIMIT 1";
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
            PreparedStatement ps = con.prepareStatement(fQuery);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idNum = rs.getInt("studentid");
           
            System.out.println(idNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        
        boolean quit = false;
        do { 
        System.out.println("===MENU===");
        System.out.println("[A]dd");
        System.out.println("[V]iew");
        System.out.println("[U]pdate password");
        System.out.println("[D]elete");
        System.out.println("[Q]uit\n");
        System.out.print("Enter choice:");
        String choice = sc.nextLine().toUpperCase();

        switch(choice){
            case "A":
                System.out.println("Adding");
                addUsers(con,idNum,sc);
                idNum++;
                break;
            case "V":
                System.out.println("Viewing");
                viewData(con);
                break;
            
            case "U":
                System.out.println("Updating");
                updatePassword(con, sc);
                break;
            case "D":
                System.out.println("Deleting");
                delete(con, sc);
                break;
            case "Q":
                System.out.println("Exiting Program");
                quit = true;
                sc.close();
                if (!con.isClosed()) {
                    con.close();
                }
                break;
            
            default:
                System.out.println("Error: use the available choices");
                
        }


            
        } while (!quit);
        // String url = "jdbc:postgresql://localhost:5432/test1";
        // String user = "Ken";
        // String password = "password";

        // try {
        //     Class.forName("org.postgresql.Driver");
        //     System.out.println("Driver loaded");
        //     Connection con = DriverManager.getConnection(url, user, password);
        //     System.out.println("Connected!");

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    public static void addUsers(Connection con,int idNum, Scanner sc){
        String password ="";
        String fname="";
        String lname="";
        System.out.println("Enter your email:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        password= sc.nextLine();
        System.out.println("Enter your first name:");
        fname = sc.nextLine();
        System.out.println("Enter your last name:");
        lname = sc.nextLine();
        try {
            String addQuery = "INSERT INTO student (studentid,email,password,firstname,lastname,dateadded,dateupdated) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(addQuery);
            ps.setInt(1,idNum+1 );
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, fname);
            ps.setString(5, lname);
            // LocalDateTime now = LocalDateTime.now();
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String formattedDate = now.format(formatter);
            // new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            
            ps.executeUpdate();
            System.out.println("Added user into database");
            System.out.println();
            
        } catch (SQLException e) {
                System.out.println("Error: email is already in use");
        }
    }

    public static void viewData(Connection con){
        String sql = "SELECT * FROM student";
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("Id: "+rs.getInt(1));
                System.out.println("Email: "+rs.getString(2));
                System.out.println("First name: "+ rs.getString(4));
                System.out.println("Last name: "+ rs.getString(5));
                System.out.println();
            }
            
        } catch (SQLException e) {
            System.out.println("No record has been saved");
        }

    }
    public static void updatePassword(Connection con,Scanner sc){
        int id=0;
        String newPass="";
        System.out.println("Enter Id:");
        
        id = sc.nextInt();
        System.out.println("Enter new password:");
        sc.nextLine();
        newPass = sc.nextLine();
        
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String query = "UPDATE student SET password= (?) WHERE studentid = "+id;
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newPass);
          
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error: id not found");
        }
    }

    public static void delete(Connection con,Scanner sc){
        int id=0;
        System.out.println("Enter Id:");
        
        id = sc.nextInt();
        sc.nextLine();
        try {
            String sql = "DELETE FROM student WHERE studentid = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error id not found");
        }
    }
}
