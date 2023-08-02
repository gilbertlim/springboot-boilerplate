package com.megazone.springbootboilerplate.config.datasource;

public class WriterDataSourceInfo extends AbstractDataSourceInfo {

    public WriterDataSourceInfo(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }
}
