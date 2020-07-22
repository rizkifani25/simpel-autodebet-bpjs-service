package com.doku.infopeserta.model;

import lombok.Data;

@Data
public class MetadataModel {
    private String errorcode;
    private String errormessage;

    public void setData(String errorcode, String errormessage) {
        this.errorcode = errorcode;
        this.errormessage = errormessage;
    }
}