package com.doku.infopeserta.service;

import com.doku.infopeserta.model.BundleModel;
import com.doku.infopeserta.model.MetadataModel;
import com.doku.infopeserta.model.dataautodebet.*;
import com.doku.infopeserta.model.pesertabpjs.*;
import com.doku.infopeserta.model.pesertaautodebet.*;
import com.doku.infopeserta.repository.DataAutoDebetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("rawtypes")
public class InfoPesertaBPJSService {

    @Autowired
    DataAutoDebetRepository dataAutoDebetRepository;

    private final RestTemplate restTemplate;
    private final Logger LOG = LoggerFactory.getLogger(getClass()); 

    public InfoPesertaBPJSService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BundleModel getInfoPesertaBPJS(String noka) {

        GetInfoPesertaBPJSResponse pesertaBPJS = new GetInfoPesertaBPJSResponse();
        GetInfoPesertaAutodebetResponse pesertaAutodebet = new GetInfoPesertaAutodebetResponse();
        TambahDataAutoDebetResponse insertNewDataResponse = new TambahDataAutoDebetResponse();
        MetadataModel metadata = new MetadataModel();
        BundleModel bundleResponse = new BundleModel();
        
        // BPJS InfoPesertaBPJS
        String urlExternal = "http://localhost:9090/InfoPesertaBPJS/{noka}";
        // BPJS TambahDataAutoDebet
        String urlInsertData = "http://localhost:9090/TambahDataAutoDebet";
        // WS GetInfoPesertaAutoDebet
        String urlInternal = "http://localhost:9090/InfoPesertaAutoDebet/{noka}";
        String errorcode;
        
        // HIT to BPJS InfoPesertaBPJS
        LOG.info("===== START Hit to BPJS InfoPesertaBPJS =====");
        pesertaBPJS = this.restTemplate.getForObject(urlExternal, GetInfoPesertaBPJSResponse.class, noka);
        LOG.info(pesertaBPJS.toString());
        LOG.info("===== END Hit to BPJS InfoPesertaBPJS =====");

        metadata = pesertaBPJS.getMetadata();
        errorcode = metadata.getErrorcode();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> infoPesertaAutoDebetRequest = new HttpEntity<String>("OK", headers);
        
        // HIT to WS GetInfoPesertaAutoDebet depends on the response
        LOG.info("===== START Hit WS GetInfoPesertaAutodebet =====");
        pesertaAutodebet = this.restTemplate.postForObject(urlInternal, infoPesertaAutoDebetRequest, GetInfoPesertaAutodebetResponse.class, noka);
        
        if (errorcode.equals("1")) {
            bundleResponse.setData(pesertaAutodebet.getMetadata(), pesertaAutodebet.getResponse());
            LOG.info(bundleResponse.toString());
        }else{
            bundleResponse.setData(pesertaAutodebet.getMetadata(), pesertaAutodebet.getResponse());
            LOG.info(bundleResponse.toString());
            LOG.info("===== END Hit WS GetInfoPesertaAutodebet =====");
            return bundleResponse;
        }

        LOG.info("===== END Hit WS GetInfoPesertaAutodebet =====");
        
        // HIT to WS InsertAutoDebet depends on the response
        LOG.info("===== START Hit WS InsertAutodebet =====");
        TambahDataAutoDebetModel insertNewData = new TambahDataAutoDebetModel();
        insertNewData.setData(noka, 1, "1111112222", "Rizki Saja", "0888888888898", "rizki.saja@gmail.com", 0);

        HttpEntity<TambahDataAutoDebetModel> tambahDataAutoDebetRequest = new HttpEntity<TambahDataAutoDebetModel>(insertNewData, headers);

        insertNewDataResponse = this.restTemplate.postForObject(urlInsertData, tambahDataAutoDebetRequest, TambahDataAutoDebetResponse.class, noka);
        metadata = insertNewDataResponse.getMetadata();
        errorcode = metadata.getErrorcode();
        
        if (errorcode.equals("1")) {
            dataAutoDebetRepository.insertNewDataAutoDebet(noka, 1, "1111112222", "Rizki Saja", "0888888888898", "rizki.saja@gmail.com", 0);
            bundleResponse.setData(insertNewDataResponse.getMetadata(), insertNewDataResponse.getResponse());
            LOG.info(bundleResponse.toString());
        } else {
            bundleResponse.setData(insertNewDataResponse.getMetadata(), insertNewDataResponse.getResponse());
            LOG.info(bundleResponse.toString());
            LOG.info("===== END Hit WS InsertAutodebet =====");
            return bundleResponse;
        }

        LOG.info("===== END Hit WS InsertAutodebet =====");
        return bundleResponse;
    }
}