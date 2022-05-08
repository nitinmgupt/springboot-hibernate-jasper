package com.reports.jaspersoft.jasperreports.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    String generateReport(LocalDate transDate, String fileId, String fileFormat) throws JRException, IOException;
    List<String> findAllTransDate();
}
