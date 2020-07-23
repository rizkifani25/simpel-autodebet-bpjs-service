package com.doku.infopeserta.repository;

import com.doku.infopeserta.model.dataautodebet.TambahDataAutoDebetModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DataAutoDebetRepository extends JpaRepository<TambahDataAutoDebetModel, String>{
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO data_autodebet(noka, haridebet, norekening, namaonrekening, nohp, alamatemail, status_) VALUES (:noka, :haridebet, :norekening, :namaonrekening, :nohp, :alamatemail, :status_);", nativeQuery = true)
    void insertNewDataAutoDebet(String noka, Integer haridebet, String norekening, String namaonrekening, String nohp, String alamatemail, Integer status_);

}