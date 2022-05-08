package com.reports.jaspersoft.jasperreports.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.reports.jaspersoft.jasperreports.model.ByFlowData;
import com.reports.jaspersoft.jasperreports.repository.ByFlowDataRepository;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

	private final ByFlowDataRepository byFlowDataRepository;    
    
	@Override
    public List<String> findAllTransDate() {
    	return byFlowDataRepository.findAllTransDateByFileId("6400417443592");
    }

	@Override
    public String generateReport(LocalDate transDate, String fileId, String fileFormat) throws JRException, IOException {
        List<ByFlowData> dataCollection = byFlowDataRepository.findAllByFileId(Long.parseLong(fileId));
        //load the file and compile it
        String resourceLocation = "classpath:byFlowData.jrxml";
        JasperPrint jasperPrint = getJasperPrintByFlowData(dataCollection, resourceLocation);
        //create a folder to store the report
        String fileName = "/"+"byFlowData.pdf";
        Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);
        //create a private method that returns the link to the specific pdf file

        return getPdfFileLink4ByFlowData(uploadPath.toString());
    }

    private JasperPrint getJasperPrintByFlowData(List<ByFlowData> dataCollection, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataCollection);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","Bharat");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        return jasperPrint;
    }

    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-reports");
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        //generate the report and save it in the just created folder
        if (fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, uploadPath+fileName
            );
        }
        return uploadPath;
    }
    
    private String getPdfFileLink4ByFlowData(String uploadPath){
        return uploadPath+"/"+"byFlowData.pdf";
    }
    

}
