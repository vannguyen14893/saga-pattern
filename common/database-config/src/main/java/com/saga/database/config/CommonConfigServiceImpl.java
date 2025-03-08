package com.saga.database.config;

import com.saga.database.dto.DatabaseConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@EnableConfigurationProperties({DatabaseConfigProperties.class})
@RequiredArgsConstructor
public class CommonConfigServiceImpl implements ConfigCommonService {
    @Bean
    public DatabaseConfigProperties databaseConfigProperties() {
        return new DatabaseConfigProperties();
    }

    @Bean
    @ConditionalOnBean(DatabaseConfigProperties.class)
    @ConditionalOnExpression("'${database.enable}'=='true'")
    public DataSource dataSource() {
        return hikariDataSource(databaseConfigProperties());
    }

}
