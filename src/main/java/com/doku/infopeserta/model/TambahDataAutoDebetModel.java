package com.doku.infopeserta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TambahDataAutoDebetModel {
    @Id
    @Column(name = "noka")
    private String noka;

    @Column(name = "haridebet")
    private Integer haridebet;

    @Column(name = "norekening")
    private String norekening;

    @Column(name = "namaonrekening")
    private String namaonrekening;

    @Column(name = "nohp")
    private String nohp;

    @Column(name = "alamatemail")
    private String alamatemail;

    @Column(name = "status_")
    private Integer status_;
    
    public void setData(String noka, Integer haridebet, String norekening, String namaonrekening, String nohp, String alamatemail, Integer status_) {
        this.noka = noka;
        this.haridebet = haridebet;
        this.norekening = norekening;
        this.namaonrekening = namaonrekening;
        this.nohp = nohp;
        this.alamatemail = alamatemail;
        this.status_ = status_;
    }
}