package com.doku.infopeserta.model.external;

import lombok.Data;

@Data
public class ResponseModel {
    private Integer jmlkeluarga;
    private String nama;
    private String nmcabang;
    private String noka;
    private String nova;

    public void setData(Integer jmlkeluarga, String nama, String nmcabang, String noka, String nova) {
        this.jmlkeluarga = jmlkeluarga;
        this.nama = nama;
        this.nmcabang = nmcabang;
        this.noka = noka;
        this.nova = nova;
    }
}