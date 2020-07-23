package com.doku.infopeserta.model.dataautodebet;

import com.doku.infopeserta.model.MetadataModel;

import lombok.Data;

@Data
public class TambahDataAutoDebetResponse<T> {
    private MetadataModel metadata;
    private T response;

    public void setData(MetadataModel metadata, T response) {
        this.metadata = metadata;
        this.response = response;
    }
}