package com.doku.infopeserta.service;

import com.doku.infopeserta.model.MetadataModel;
import com.doku.infopeserta.model.external.GetInfoPesertaBPJSResponse;
import com.doku.infopeserta.model.internal.GetInfoPesertaAutodebetResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("rawtypes")
public class InfoPesertaBPJSService {

    private final RestTemplate restTemplate;
    private final Logger LOG = LoggerFactory.getLogger(getClass()); 

    public InfoPesertaBPJSService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public GetInfoPesertaAutodebetResponse getInfoPesertaBPJS(String noka) {
        GetInfoPesertaBPJSResponse pesertaBPJS = new GetInfoPesertaBPJSResponse();
        GetInfoPesertaAutodebetResponse pesertaAutodebet = new GetInfoPesertaAutodebetResponse();
        MetadataModel metadata = new MetadataModel();
        // BPJS InfoPesertaBPJS
        String urlExternal = "http://localhost:9090/InfoPesertaBPJS/{noka}";
        // BPJS TambahDataAutoDebet
        String urlInsertData = "http://localhost:9090/TambahDataAutoDebet";
        // WS GetInfoPesertaAutoDebet
        String urlInternal = "http://localhost:9090/InfoPesertaAutoDebet/{noka}";
        String errorcode;
        
        // Hit to BPJS InfoPesertaBPJS
        LOG.info("========== START Hit to BPJS InfoPesertaBPJS ==========");
        pesertaBPJS = this.restTemplate.getForObject(urlExternal, GetInfoPesertaBPJSResponse.class, noka);
        LOG.info(pesertaBPJS.toString());
        LOG.info("========== END Hit to BPJS InfoPesertaBPJS ==========");

        metadata = pesertaBPJS.getMetadata();
        errorcode = metadata.getErrorcode();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<String>("OK", headers);
        
        // HIT WS GetInfoPesertaAutoDebet depends on the response
        LOG.info("========== START Hit WS GetInfoPesertaAutodebet ==========");
        pesertaAutodebet = this.restTemplate.postForObject(urlInternal, request, GetInfoPesertaAutodebetResponse.class, noka);
        
        if (errorcode.equals("1")) {
            pesertaAutodebet.setData(pesertaAutodebet.getMetadata(), pesertaAutodebet.getResponse());
        }else{
            pesertaAutodebet.setData(pesertaAutodebet.getMetadata(), pesertaAutodebet.getResponse());
        }

        LOG.info(pesertaAutodebet.toString());
        LOG.info("========== END Hit WS GetInfoPesertaAutodebet ==========");
        return pesertaAutodebet;
    }
}