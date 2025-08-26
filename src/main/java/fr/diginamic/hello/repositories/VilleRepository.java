package fr.diginamic.hello.repositories;

import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {


    Optional<Ville> findByNom(String nom);
    List<Ville> findByDepartementCode(String code);

    boolean existsByNomAndDepartement(String nom, Departement departement);

    List<Ville> findByDepartementCodeAndPopulationBetween(String code, Integer min, Integer max);

    List<Ville> findByNomContaining(String nom);

    List<Ville> findByPopulationGreaterThanOrderByPopulationDesc(Integer population);

    List<Ville> findByPopulationBetweenOrderByPopulationDesc(Integer min, Integer max);

    List<Ville> findByDepartementCodeAndPopulationGreaterThanOrderByPopulationDesc(String code, Integer population);

    List<Ville> findByDepartementCodeAndPopulationBetweenOrderByPopulationDesc(String code, Integer min, Integer max);

    List<Ville> findTop10ByOrderByPopulationDesc();



}
