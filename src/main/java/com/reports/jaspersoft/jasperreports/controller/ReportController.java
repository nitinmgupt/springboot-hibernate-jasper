package com.reports.jaspersoft.jasperreports.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reports.jaspersoft.jasperreports.service.ReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/")
    public String showLandingPage(Model model){
        model.addAttribute("transdates",reportService.findAllTransDate());
        return "byflowdata";
    }
    
    @PostMapping("/report")
    public String generateReport(@RequestParam("transDate") String date,
                                 @RequestParam("fileId") String fileId, @RequestParam("fileFormat") String fileFormat) throws JRException, IOException {
        LocalDate localDate = LocalDate.parse(date);
        String fileLink = reportService.generateReport(localDate, fileId, fileFormat);
        return "redirect:/"+fileLink;
    }

}
