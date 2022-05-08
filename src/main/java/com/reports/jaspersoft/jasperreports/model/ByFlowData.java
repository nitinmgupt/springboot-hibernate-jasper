package com.reports.jaspersoft.jasperreports.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// This is for composite key
//@IdClass(TransId.class)
@Table(name = "RPT_BY_FLOW_DATA", schema = "IBM_AT")
@Data
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ByFlowData {

    @Id
    @Column(name = "TRANSID", columnDefinition = "NUMBER", unique=true, nullable=false)
    private Long transId;

    @Column(name = "FILE_ID", columnDefinition = "NUMBER")
    private Long fileId;

    @Column(name = "TRANSDATE")
    private Timestamp transDate;

    @Column(name = "SRCMDN")
    private Long srcMdn;

    @Column(name = "DESTMDN")
    private Long destMdn;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "REGION")
    private String region;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "SMS_LOCATION")
    private String smsLocation;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "TRAFFIC")
    private String traffic;

    @Column(name = "LOAD_DATETIME")
    private Timestamp loadDatetime;


}
