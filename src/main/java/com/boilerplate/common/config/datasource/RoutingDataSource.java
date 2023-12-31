package com.boilerplate.common.config.datasource;

import jakarta.validation.constraints.NotNull;
import javax.sql.DataSource;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    public RoutingDataSource(@NotNull DataSource readerDataSource, @NotNull DataSource writerDataSource) {
        Map<Object, Object> dataSources = Map.of(Target.WRITER, writerDataSource, Target.READER, readerDataSource);
        setTargetDataSources(dataSources);
        setDefaultTargetDataSource(writerDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        Target target = readOnly ? Target.READER : Target.WRITER;
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            HikariDataSource dataSource = (HikariDataSource) getResolvedDataSources().get(target);
            log.debug("routing: {}", dataSource.getPoolName());
        }
        return target;
    }

    public enum Target {
        WRITER, READER
    }
}
