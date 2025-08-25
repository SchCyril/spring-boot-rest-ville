package fr.diginamic.hello;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.List;

@SpringBootApplication
@EnableSpringDataWebSupport
public class VilleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VilleApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner start(VilleRepository villeRepository) {
//        return args -> {
//
//
//            Ville v1 = new Ville();
//            v1.setNom("Paris");
//            v1.setPopulation(2140526);
//
//            Ville v2 = new Ville("Lyon", 515695);
//
//
//            Ville v3 = Ville.builder()
//                    .nom("Marseille")
//                    .population(861635)
//                    .build();
//
//            villeRepository.save(v1);
//            villeRepository.save(v2);
//            villeRepository.save(v3);
//
//
//            List<Ville> villeList = villeRepository.findAll();
//            villeList.forEach(p-> System.out.println(p.toString()));
//        };
//
//    }

}
