package fr.diginamic.hello;

import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.repositories.DepartementRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableTransactionManagement
@SpringBootApplication
public class App implements CommandLineRunner {

    private final DepartementRepository departementRepository;

    public App(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        DepartementDTO[] departementsApi = restTemplate.getForObject("https://geo.api.gouv" +
                ".fr/departements", DepartementDTO[].class);

        if (departementsApi != null) {
            for (DepartementDTO dto : departementsApi) {
                departementRepository.findByCode(dto.code()).ifPresentOrElse(
                        p -> p.setNom(dto.nom()),
                        () -> departementRepository.save(dto.toEntity())
                );
            }
        }
    }
}
