package pl.damianradowiecki.transactionaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TransactionaltestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionaltestApplication.class, args);
	}

}
