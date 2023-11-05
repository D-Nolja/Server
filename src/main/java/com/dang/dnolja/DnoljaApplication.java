package com.dang.dnolja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DnoljaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnoljaApplication.class, args);
	}

}
