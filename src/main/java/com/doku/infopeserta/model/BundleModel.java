package com.doku.infopeserta.model;

import lombok.Data;

@Data
public class BundleModel<T> {
    private MetadataModel metadata;
    private T response;

    public void setData(MetadataModel metadata, T response) {
        this.metadata = metadata;
        this.response = response;
    }
}