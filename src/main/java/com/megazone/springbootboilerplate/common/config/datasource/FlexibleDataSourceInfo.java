package com.megazone.springbootboilerplate.common.config.datasource;

import java.util.Optional;

public record FlexibleDataSourceInfo(
    ReaderDataSourceInfo reader,
    WriterDataSourceInfo writer
) {
    public boolean isMultiple() {
        return reader != null && writer != null;
    }

    public AbstractDataSourceInfo getOne() {
        if (Optional.ofNullable(writer).isPresent()) {
            return writer;
        }

        return reader;
    }
}
