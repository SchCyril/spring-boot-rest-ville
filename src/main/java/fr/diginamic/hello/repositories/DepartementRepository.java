package fr.diginamic.hello.repositories;

import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Optional<Departement> findByCode(String code);

    @Query("SELECT v FROM Ville v WHERE v.departement.code = :code AND v.population BETWEEN :min AND :max")
    List<Ville> findByCodeAndPopulationBetween(String code, Integer min, Integer max);

}
