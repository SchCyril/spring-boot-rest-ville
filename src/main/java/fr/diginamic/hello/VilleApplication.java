package fr.diginamic.hello;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VilleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VilleApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(VilleRepository villeRepository) {
        return args -> {

            Ville v1 = new Ville();
            v1.setNom("Paris");
            v1.setPopulation(2140526);

            Ville v2 = new Ville();
            v2.setNom("Lyon");
            v2.setPopulation(  1475600 );

            villeRepository.save(v1);
            villeRepository.save(v2);


            List<Ville> villeList = villeRepository.findAll();
            villeList.forEach(p-> System.out.println(p.toString()));
        };

    }

}
