package pronostics;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import pronostics.service.GameService;

@Configuration
@ComponentScan
public class AppConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		ds.setUrl(System.getProperty("pronostics.mysql.database.url", "jdbc:mysql://localhost:3306/pronostics"));
		ds.setUsername(System.getProperty("pronostics.mysql.user", "root"));
		ds.setPassword(System.getProperty("pronostics.mysql.password", ""));
		return ds;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean(GameService.class).process();
	}
}