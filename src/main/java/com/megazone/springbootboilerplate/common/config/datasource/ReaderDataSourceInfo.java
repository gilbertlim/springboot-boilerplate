package com.megazone.springbootboilerplate.common.config.datasource;

public class ReaderDataSourceInfo extends AbstractDataSourceInfo {

    public ReaderDataSourceInfo(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
