package com.doku.infopeserta.model.internal;

import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class ResponseModel {
    private Integer idhead;
    private Integer jmlkeluarga;
    private Integer kdasalpremi;
    private String kdbank;
    private String nama;
    private String namapic;
    private String nmbank;
    private String nmcabang;
    private String noka;
    private String nokapic;
    private String nokk;
    private String norekening;
    private String nova;
    private Integer premi;
    private Integer status_;
    private String tglInput;

    public void setData(
        Integer idhead,
        Integer jmlkeluarga, 
        Integer kdasalpremi, 
        String kdbank, 
        String nama,
        String namapic, 
        String nmbank, 
        String nmcabang, 
        String noka, 
        String nokapic, 
        String nokk, 
        String norekening,
        String nova, 
        Integer premi, 
        Integer status_, 
        String tglInput) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        this.idhead = idhead;
        this.jmlkeluarga = jmlkeluarga;
        this.kdasalpremi = kdasalpremi;
        this.kdbank = kdbank;
        this.nama = nama;
        this.namapic = namapic;
        this.nmbank = nmbank;
        this.nmcabang = nmcabang;
        this.noka = noka;
        this.nokapic = nokapic;
        this.nokk = nokk;
        this.norekening = norekening;
        this.nova = nova;
        this.premi = premi;
        this.status_ = status_;
        this.tglInput = formatter.format(tglInput);
    }
}