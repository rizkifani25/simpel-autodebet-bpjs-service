package com.doku.infopeserta.controller;

import com.doku.infopeserta.model.internal.GetInfoPesertaAutodebetResponse;
import com.doku.infopeserta.service.InfoPesertaBPJSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping(value = "/GetInfoPesertaBPJS/{noka}")
public class GetInfoPesertaController {

    @Autowired
    InfoPesertaBPJSService infoPesertaBPJSService;

    @GetMapping
    public ResponseEntity<GetInfoPesertaAutodebetResponse> getController(@PathVariable("noka") String noka) {
        return new ResponseEntity<>(infoPesertaBPJSService.getInfoPesertaBPJS(noka), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postController() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}