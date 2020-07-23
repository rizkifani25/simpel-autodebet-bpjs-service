package com.doku.infopeserta.model.pesertabpjs;

import com.doku.infopeserta.model.MetadataModel;

import lombok.Data;

@Data
public class GetInfoPesertaBPJSResponse<T> {
    private MetadataModel metadata;
    private T response;

    public void setData(MetadataModel metadata, T response) {
        this.metadata = metadata;
        this.response = response;
    }
}