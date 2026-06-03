package org.eclipse.jakarta.infrastracture.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {

    private final String URL = "jdbc:postgresql://localhost:5432/Day9";
    private final String USER = "Ken";
    private final String PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<ReportDto> findAll() {
        List<ReportDto> reports = new ArrayList<>();

        String sql = "SELECT id, title, details FROM reports";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReportDto report = new ReportDto(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("details") 
                );
                reports.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }


    public ReportDto findById(Long id) {
        String sql = "SELECT id, title, detail FROM public.reports WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ReportDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("detail")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(ReportDto report) {
        String sql = "INSERT INTO reports (title, details) VALUES (?, ?)"; 

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, report.getTitle());
            ps.setString(2, report.getDetail());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB ERROR: " + e.getMessage());
        }
    }


    public void update(Long id, String title, String detail) {
        String sql = "UPDATE reports SET title = ?, details = ? WHERE id = ?"; 

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, detail);
            ps.setLong(3, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM public.reports WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
