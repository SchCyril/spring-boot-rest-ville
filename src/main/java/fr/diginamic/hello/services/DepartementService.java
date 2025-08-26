package fr.diginamic.hello.services;

import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    public DepartementService(DepartementRepository departementRepository, VilleRepository villeRepository) {
        this.departementRepository = departementRepository;
        this.villeRepository = villeRepository;
    }

    /**
     * Récupère une liste de tous les départements.
     *
     * @return une liste de tous les départements
     */

    public List<DepartementDTO> getAllDepartements() {
        return departementRepository.findAll().stream()
                .map(DepartementDTO::fromEntity)
                .toList();
    }

    /**
     * Récupère un département par son code.
     *
     * @param code le code du département
     * @return le département correspondant au code
     * @throws EntityNotFoundException si le département n'est pas trouvé
     */

    public DepartementDTO getDepartementByCode(String code) throws  ExceptionFonctionnelle {
        return departementRepository.findByCode(code)
                .map(DepartementDTO::fromEntity)
                .orElseThrow(() -> new ExceptionFonctionnelle("Département non trouvé avec le code : " + code));
    }

    /**
     * Ajoute un nouveau département.
     *
     * @param departementDto le département à ajouter
     * @return une liste mise à jour de tous les départements après l'ajout
     * @throws IllegalArgumentException si le code du département est nul
     */

    public List<DepartementDTO> addDepartement(DepartementDTO departementDto) throws ExceptionFonctionnelle {


       if(departementRepository.existsByCode(departementDto.code())) {
           throw new ExceptionFonctionnelle("Le code du département " + departementDto.code() + " existe déja");
       }

       if (departementRepository.existsByNom(departementDto.nom()))  {
           throw new ExceptionFonctionnelle("Le nom du département " + departementDto.nom() + " existe déjà");
       }

       departementRepository.save(departementDto.toEntity());
         return departementRepository.findAll().stream()
                .map(DepartementDTO::fromEntity)
                .toList();
    }

    /**
     * Met à jour un département existant.
     *
     * @param code        le code du département à mettre à jour
     * @param departement les nouvelles informations du département
     * @return une liste mise à jour de tous les départements après la mise à jour
     * @throws EntityNotFoundException si le département n'est pas trouvé
     */

    public List<DepartementDTO> updateDepartement(String code, DepartementDTO departement) throws  ExceptionFonctionnelle {
        Departement departementExistant = departementRepository.findByCode(code)
                .orElseThrow(() -> new ExceptionFonctionnelle("Département non trouvé avec le code : " + code));

        if(departement.code() != null) {
            departementExistant.setCode(departement.code());
        }


        departementRepository.save(departementExistant);
        return departementRepository.findAll().stream()
                .map(DepartementDTO::fromEntity)
                .toList();
    }

    /**
     * Supprime un département par son code.
     *
     * @param code le code du département à supprimer
     * @return une liste mise à jour de tous les départements après la suppression
     */

    public List<DepartementDTO> deleteDepartement(String code) throws   ExceptionFonctionnelle {
        Departement departement = departementRepository.findByCode(code)
                .orElseThrow(() -> new ExceptionFonctionnelle("Département non trouvé avec le code : " + code));
        departementRepository.delete(departement);
        return departementRepository.findAll().stream()
                .map(DepartementDTO::fromEntity)
                .toList();
    }

    /**
     * Récupère une liste de villes d'un département avec une limite sur le nombre de villes.
     *
     * @param code        le code du département
     * @param nombre      le nombre maximum de villes à récupérer
     * @return une liste de villes du département, limitée au nombre spécifié
     */

    public List<VilleDTO> getVillesByDepartementAndNombre(String code, int nombre) throws  ExceptionFonctionnelle {
        // Vérifie que le département existe
        Departement departement = departementRepository.findByCode(code)
                .orElseThrow(() -> new ExceptionFonctionnelle(
                        "Département non trouvé avec le code : " + code));

        // Récupère les villes associées à ce département
        List<Ville> villes = villeRepository.findByDepartementCode(code);

        // Filtre les villes nulles ou sans département, limite le nombre, et map en DTO
        return villes.stream()
                .filter(v -> v.getDepartement() != null)  // évite le NPE
                .limit(nombre)
                .map(VilleDTO::fromEntity)
                .toList();
    }


    /**
     * Récupère une liste de villes d'un département dont la population est comprise dans une plage donnée.
     *
     * @param code          le code du département
     * @param populationMin la population minimale
     * @param populationMax la population maximale
     * @return une liste de villes du département dont la population est dans la plage spécifiée
     * @throws EntityNotFoundException si le département n'est pas trouvé
     */

    public List<VilleDTO> getVillesByDepartementAndPopulationRange(String code, Integer populationMin,
                                                            Integer populationMax) throws ExceptionFonctionnelle{

        List<Ville> villes = villeRepository.findByDepartementCodeAndPopulationBetween(code, populationMin, populationMax);
        if (code == null) {
            throw new ExceptionFonctionnelle("Département non trouvé avec le code : " + code);
        }
        return villes.stream()
                .map(VilleDTO::fromEntity)
                .toList();

    }
}
