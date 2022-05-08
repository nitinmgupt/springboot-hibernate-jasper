package com.reports.jaspersoft.jasperreports.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reports.jaspersoft.jasperreports.model.ByFlowData;

public interface ByFlowDataRepository extends JpaRepository<ByFlowData,Long> {
    List<ByFlowData> findAllByTransId(Long transId);
    List<ByFlowData> findAllByFileId(Long FileId);
    List<ByFlowData> findAllByLoadDatetime(Timestamp loadDatetime);
    List<ByFlowData> findAllByTransDate(Timestamp transDate);
    
    @Query(value = "SELECT DISTINCT TRUNC(transdate) FROM RPT_BY_FLOW_DATA WHERE file_id = ?1", nativeQuery = true)
    List<String> findAllTransDateByFileId(String fileId);
}
