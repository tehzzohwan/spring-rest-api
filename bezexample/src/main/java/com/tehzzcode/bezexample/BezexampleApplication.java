package com.tehzzcode.bezexample;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.entities.Role;
import com.tehzzcode.bezexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.tehzzcode.bezexample")
public class BezexampleApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(BezexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer adminAccount = customerRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			Customer customer = new Customer();

			customer.setAge(21);
			customer.setEmail("admin@gmail.com");
			customer.setGender("female");
			customer.setName("admin");
			customer.setRole(Role.ADMIN);
			customer.setPassword(new BCryptPasswordEncoder().encode("admin"));

			customerRepository.save(customer);
		}
	}
}
