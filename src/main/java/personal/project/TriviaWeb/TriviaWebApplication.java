package personal.project.TriviaWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import personal.project.TriviaWeb.controller.TriviaAPIController;

@SpringBootApplication
public class TriviaWebApplication{

	@Autowired
	TriviaAPIController api;

	public static void main(String[] args) {
		SpringApplication.run(TriviaWebApplication.class, args);
	}
}
