package fundoonotesapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FundooNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooNotesApplication.class, args);
		System.out.println("Welcome to FundooNotes Application");
	}

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
