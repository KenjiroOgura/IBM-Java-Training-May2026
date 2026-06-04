package org.eclipse.jakarta.backingbean;

import java.io.Serializable;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ReportUpdateBean implements Serializable {

    private Long id;
    private String title;
    private String detail;

    @Inject
    private ReportRepository reportRepository;

    @PostConstruct
    public void init() {

        String param = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (param != null) {

            id = Long.valueOf(param);

            ReportDto report = reportRepository.findById(id);

            if (report != null) {
                title = report.getTitle();
                detail = report.getDetail();
            }
        }
    }


public String update() {
    System.out.println("UPDATE CALLED: id=" + id + ", title=" + title + ", detail=" + detail);

    reportRepository.update(id, title, detail);

    return "/reportList.xhtml?faces-redirect=true";
}


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDetail() {
    return detail;
}

public void setDetail(String detail) {
    this.detail = detail;
}


}