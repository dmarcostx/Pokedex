package br.gov.sp.fatec.marcos.teixeira13.Pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mariadb://localhost:3306/pokedex", "pokedex", "pokedex");
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		return dataSource;
	}
}
