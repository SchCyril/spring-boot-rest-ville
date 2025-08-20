package fr.diginamic.hello.repositories;

import fr.diginamic.hello.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    Optional<Ville> findByNom(String nom);
}
