package br.com.ulisses.igti.gerenciador;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import br.com.ulisses.igti.gerenciador.qualifier.GerenciadorDataSource;

/**
 * Configuração Spring do módulo core da aplicação.
 */
@Configuration
@EnableScheduling
@EnableTransactionManagement
@ComponentScan
@PropertySource({ "classpath:database.properties" })
public class GerenciadorCoreConfiguration {

	private Environment env;

	@Inject
	public GerenciadorCoreConfiguration(Environment env) {
		this.env = env;
	}

	@Bean
	@GerenciadorDataSource
	public PlatformTransactionManager gerenciadorTransactionManager() throws PropertyVetoException {
		return new DataSourceTransactionManager(gerenciadorDataSource());
	}

	@Bean
	@GerenciadorDataSource
	public NamedParameterJdbcTemplate gerenciadorJdbcTemplate() throws PropertyVetoException {
		return new NamedParameterJdbcTemplate(gerenciadorDataSource());
	}

	@Bean
	@GerenciadorDataSource
	public DataSource gerenciadorDataSource() throws PropertyVetoException {
		return createDataSource(env.getProperty("gerenciador.datasource.driver"),
                env.getProperty("gerenciador.datasource.url"),
                env.getProperty("gerenciador.datasource.username"),
                env.getProperty("gerenciador.datasource.password"),
                env.getProperty("gerenciador.datasource.maxPoolSize", Integer.class),
                env.getProperty("gerenciador.datasource.minPoolSize", Integer.class),
                env.getProperty("gerenciador.datasource.maxStatements", Integer.class));
	}

	private DataSource createDataSource(String driverClass, String url, String username, String password,
			Integer maxPoolSize, Integer minPoolSize, Integer maxStatements) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setMaxStatements(maxStatements);

		return dataSource;
	}

}
