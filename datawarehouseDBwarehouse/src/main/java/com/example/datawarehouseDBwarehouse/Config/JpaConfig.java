package com.example.datawarehouseDBwarehouse.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    // Cấu hình EntityManagerFactory cho DBWarehouse
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dbWarehouseDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.datawarehouseDBwarehouse.Model") // Đường dẫn đến các Entity của DBWarehouse
                .persistenceUnit("DBwarehouse")
                .build();
    }

    // Cấu hình PlatformTransactionManager cho DBWarehouse
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // Cấu hình DataSource cho DBWarehouse
    @Primary
    @Bean(name = "dbWarehouseDataSource")
    public DataSource dataSource(
            @Value("${db.warehouse.url}") String url,
            @Value("${db.warehouse.username}") String username,
            @Value("${db.warehouse.password}") String password) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
