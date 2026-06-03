package org.eclipse.jakarta.infrastracture.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

public class Server{
	private String url = "jdbc:postgresql://localhost:5432/reports";
    private String user = "Ken";
    private String password = "password";
    Connection con = null;
 
    
    public Server() {
    	try {
   	     
 	       con = DriverManager.getConnection(url, user, password);
 	   
 	       
 	    } catch (Exception e) {
 	       e.printStackTrace();
 	    }
    }
    public List<ReportDto> getList() {
    	String sql = "SELECT * FROM reports";
    	List<ReportDto> reports = null;
        try {
            ReportDto report;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            	Long id = rs.getLong(0);
            	String title = rs.getString(1);
            	String detail = rs.getString(2);
            	report = new ReportDto(id,title,detail);
                
                
                reports.add(report);
                
            }
            
        } catch (SQLException e) {
            System.out.println("No record has been saved");
        }
        return reports;
    }
    
    void addData(long id,String title,String details) {
    	
    	try {
            String addQuery = "INSERT INTO reports (id,title,email) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(addQuery);
            ps.setLong(0, id);
            ps.setString(1, title);
            ps.setString(2,details);
            ps.executeUpdate();
           
         } catch (SQLException e) {
            System.out.println("Error: email is already in use");
         }
    }
    
}
