package com.api.tarefas.apitarefa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApitarefaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApitarefaApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Hello word";
	}

}
