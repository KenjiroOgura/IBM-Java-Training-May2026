package org.eclipse.jakarta.backingbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import org.eclipse.jakarta.infrastracture.repository.Server;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportListBean {
	@SuppressWarnings("unused")
	private Server server = new Server();
    private List<ReportDto> reports;
    
    @Inject
    private ReportRepository reportRepository;


@PostConstruct
public void init() {
    reports = reportRepository.findAll();
}


    public List<ReportDto> getReports() {
        return reports;
    }

    public String delete(Long id) {
        reportRepository.delete(id);
        return "/reportList.xhtml?faces-redirect=true";
    }
}