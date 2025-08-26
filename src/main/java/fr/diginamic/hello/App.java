package fr.diginamic.hello;

import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.repositories.DepartementRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final DepartementRepository departementRepository;

    public App(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(VilleApplication.class, args);
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
