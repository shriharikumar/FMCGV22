package com.bizsoft.fmcgv2.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by GopiKing on 27-12-2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ledger {


            String AccountName;
            String ACType;
            Double OPBal;

    public Long getAccountGroupId() {
        return AccountGroupId;
    }

    public void setAccountGroupId(Long accountGroupId) {
        AccountGroupId = accountGroupId;
    }

    Long AccountGroupId;

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getACType() {
        return ACType;
    }

    public void setACType(String ACType) {
        this.ACType = ACType;
    }

    public Double getOPBal() {
        return OPBal;
    }

    public void setOPBal(Double OPBal) {
        this.OPBal = OPBal;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLedgerName() {
        return LedgerName;
    }

    public void setLedgerName(String ledgerName) {
        LedgerName = ledgerName;
    }

    Long Id;
           String LedgerName;

}
