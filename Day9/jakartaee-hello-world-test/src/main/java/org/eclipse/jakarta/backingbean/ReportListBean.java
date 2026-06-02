package org.eclipse.jakarta.backingbean;

import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportListBean {

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