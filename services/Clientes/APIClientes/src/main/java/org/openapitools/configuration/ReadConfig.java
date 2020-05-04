package org.openapitools.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "readEntityManagerFactory", transactionManagerRef = "readTransactionManager", basePackages = "com.touresbalon.api.repository.read")
public class ReadConfig {

	@Bean(name = "readDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.read")
	public DataSource ReadDatasource() {
		System.out.println("entro a metodo");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "readEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("readDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.touresbalon.api.repository.read").persistenceUnit("read")
				.build();
	}

	@Bean(name = "readTransactionManager")
	public PlatformTransactionManager readTransactionManager(
			@Qualifier("readEntityManagerFactory") EntityManagerFactory readEntityManagerFactory) {
		return new JpaTransactionManager(readEntityManagerFactory);
	}
}
