package com.megazone.springbootboilerplate.config.datasource;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutingDataSource extends AbstractRoutingDataSource {

    public enum Target {
        WRITER, READER
    }

    public RoutingDataSource(@NotNull DataSource readerDataSource,@NotNull DataSource writerDataSource) {
        Map<Object, Object> dataSources = Map.of(Target.WRITER, writerDataSource, Target.READER, readerDataSource);
        setTargetDataSources(dataSources);
        setDefaultTargetDataSource(writerDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.debug("{}", Target.READER);
            return Target.READER;
        }

        log.debug("{}", Target.WRITER);
        return Target.WRITER;
    }

}
