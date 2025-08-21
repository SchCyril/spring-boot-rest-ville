package fr.diginamic.hello.services;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    public DepartementService(DepartementRepository departementRepository, VilleRepository villeRepository) {
        this.departementRepository = departementRepository;
        this.villeRepository = villeRepository;
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementByCode(String code) {
        return departementRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec le code : " + code));
    }

    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Departement updateDepartement(String code, Departement departement) {
        Departement existingDepartement = departementRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec le code : " + code));

        existingDepartement.setNom(departement.getNom());
        existingDepartement.setCode(departement.getCode());

        return departementRepository.save(existingDepartement);
    }

    public void deleteDepartement(String code) {
        Departement departement = departementRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec le code : " + code));
        departementRepository.delete(departement);
    }

    /**
     * Récupère une liste de villes d'un département avec une limite sur le nombre de villes.
     *
     * @param departement le département dont on veut les villes
     * @param nombre      le nombre maximum de villes à récupérer
     * @return une liste de villes du département, limitée au nombre spécifié
     */

    public List<Ville> getVillesByDepartementAndNombre(Departement departement, int nombre) {
        Optional<Departement> dep = departementRepository.findByCode(departement.getCode());
        if (dep.isEmpty()) {
            throw new EntityNotFoundException("Département non trouvé avec le code : " + departement.getCode());
        }
        return villeRepository.findByDepartement(departement).stream().filter(p -> p.getPopulation() > nombre).collect(Collectors.toList());

    }


    public List<Ville> getVillesByDepartementAndPopulationRange(String departementCode, Integer populationMin, Integer populationMax) {
        Departement departement = departementRepository.findByCode(departementCode)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec le code : " + departementCode));

        return departementRepository.findByCodeAndPopulationBetween(departement.getCode(), populationMin, populationMax);

    }
}
