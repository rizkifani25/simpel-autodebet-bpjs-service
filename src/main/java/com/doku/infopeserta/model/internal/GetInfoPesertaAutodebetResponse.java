package com.doku.infopeserta.model.internal;

import com.doku.infopeserta.model.MetadataModel;

import lombok.Data;

@Data
public class GetInfoPesertaAutodebetResponse<T> {
    private MetadataModel metadata;
    private T response;

    public void setData(MetadataModel metadata, T response) {
        this.metadata = metadata;
        this.response = response;
    }
}