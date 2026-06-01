package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {

    private List<ReportDto> reports = new ArrayList<>();
    private long nextId = 1;

    public List<ReportDto> findAll() {
        return reports;
    }

    public ReportDto findById(Long id) {
        if (id == null) return null;

        for (ReportDto r : reports) {
            if (id.equals(r.getId())) {
                return r;
            }
        }
        return null;
    }

    public void create(ReportDto report) {
        report.setId(nextId++);
        reports.add(report);
    }

    public void update(Long id, String title, String detail) {

        for (ReportDto r : reports) {

            if (r.getId().equals(id)) {

                r.setTitle(title);
                r.setDetail(detail);

                return;
            }
        }
    }

    public void delete(Long id) {
        reports.removeIf(r -> r.getId().equals(id));
    }
}