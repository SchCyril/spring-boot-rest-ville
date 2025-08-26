package fr.diginamic.hello.services;

import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {
    /**
     * Service pour gérer les opérations liées aux villes.
     */
    private final VilleRepository villeRepository;
    private final DepartementRepository departementRepository;
    private final MessageSource messageSource;

    /**
     * Constructeur de la classe VilleService.
     *
     * @param villeRepository le repository pour accéder aux données des villes
     */
    public VilleService(VilleRepository villeRepository, DepartementRepository departementRepository, MessageSource messageSource) {
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
        this.messageSource = messageSource;
    }

    /**
     * Extrait la liste de toutes les villes.
     *
     * @return une liste de toutes les villes
     */
    public List<VilleDTO> extractVilles() {
        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();
    }

    public Page<VilleDTO> getVillesPagination(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        return villeRepository.findAll(pagination).map(VilleDTO::fromEntity);
    }

    /**
     * Extrait une ville par son identifiant.
     *
     * @param idVille l'identifiant de la ville
     * @return une Optional contenant la ville si elle existe, sinon une exception est levée
     */

    public VilleDTO extractVille(int idVille) throws ExceptionFonctionnelle {
        return villeRepository.findById((long) idVille).map(VilleDTO::fromEntity).orElseThrow(() -> new ExceptionFonctionnelle(messageSource.getMessage("ville.notfound", null, LocaleContextHolder.getLocale())));
    }

    /**
     * Extrait une ville par son nom.
     *
     * @param nom le nom de la ville
     * @return une Optional contenant le nom de la ville si elle existe, sinon une exception est levée
     */
    public VilleDTO extractVille(String nom) {
        return villeRepository.findByNom(nom)
                .map(VilleDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas avec le nom : " + nom));
    }

    /**
     * Insère une nouvelle ville dans la base de données.
     *
     * @param villeDto la ville à insérer
     * @return une liste de toutes les villes après l'insertion
     */
    public List<VilleDTO> insertVille(VilleDTO villeDto) throws ExceptionFonctionnelle {
        if (villeDto.nom() == null) {
            throw new ExceptionFonctionnelle("Le nom ne peut pas être nul");
        }
        if (villeDto.population() == null) {
            throw new ExceptionFonctionnelle("La population ne peut pas être nulle");
        }
        if (villeDto.departementCode() == null) {
            throw new ExceptionFonctionnelle("Le code du département ne peut pas être nul");
        }



        Departement departement = departementRepository.findByCode(villeDto.departementCode())
                .orElseThrow(() -> new ExceptionFonctionnelle(
                        "Département introuvable avec le code : " + villeDto.departementCode()));

        if(villeRepository.existsByNomAndDepartement(villeDto.nom(), departement)) {
            throw new ExceptionFonctionnelle("La ville " + villeDto.nom() + " existe déjà dans le département " + departement.getNom());
        }

        Ville ville = new Ville();
        ville.setNom(villeDto.nom());
        ville.setPopulation(villeDto.population());
        ville.setDepartement(departement);

        villeRepository.save(ville);

        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();
    }


    public List<VilleDTO> updateVille(int idVille, VilleDTO villeModifiee) throws ExceptionFonctionnelle {
        // Récupérer la ville existante
        Ville villeExistante = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new ExceptionFonctionnelle("Ville n'existe pas avec l'ID : " + idVille));

        // Nom
        if (villeModifiee.nom() != null) {
            villeExistante.setNom(villeModifiee.nom());
        }

        // Population
        if (villeModifiee.population() != null) {
            villeExistante.setPopulation(villeModifiee.population());
        }

        // Département
        if (villeModifiee.departementCode() != null) {
            Departement departement = departementRepository.findByCode(villeModifiee.departementCode())
                    .orElseThrow(() -> new ExceptionFonctionnelle("Département introuvable avec le code : " + villeModifiee.departementCode()));
            villeExistante.setDepartement(departement);
        }

        // Sauvegarde
        villeRepository.save(villeExistante);

        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();
    }

    public List<VilleDTO> deleteVille(int idVille) throws ExceptionFonctionnelle {
        Ville ville = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new ExceptionFonctionnelle("Ville n'existe pas avec l'ID : " + idVille));
        villeRepository.delete(ville);
        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();

    }

    /**
     * Récupère une liste de villes dont le nom contient la chaîne spécifiée.
     *
     * @param nom la chaîne à rechercher dans les noms des villes
     * @return une liste de villes dont le nom contient la chaîne spécifiée
     * @throws EntityNotFoundException si aucune ville n'est trouvée
     */

    public List<VilleDTO> getVillesByNomContaining(String nom) throws ExceptionFonctionnelle {

        List<Ville> villes = villeRepository.findByNomContaining(nom);

        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée contenant : " + nom);
        }
        return villes.stream()
                .map(VilleDTO::fromEntity)
                .toList();

    }

    /**
     * Récupère une liste de villes avec une population supérieure à la valeur spécifiée,
     * triée par population décroissante.
     *
     * @param population la population minimale
     * @return une liste de villes avec une population supérieure à la valeur spécifiée
     * @throws EntityNotFoundException si aucune ville n'est trouvée
     */


    public List<VilleDTO> getVillesByPopulationGreaterThan(int population) throws ExceptionFonctionnelle {

        List<Ville> villes = villeRepository.findByPopulationGreaterThanOrderByPopulationDesc(population);
        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée avec une population supérieure à : " + population);
        }
        return villes.stream().map(VilleDTO::fromEntity).toList();
    }


    /**
     * Récupère une liste de villes avec une population comprise entre les valeurs spécifiées,
     * triée par population décroissante.
     *
     * @param min la population minimale
     * @param max la population maximale
     * @return une liste de villes avec une population comprise entre les valeurs spécifiées
     * @throws EntityNotFoundException si aucune ville n'est trouvée
     */
    public List<VilleDTO> getVillesByPopulationBetweenAndOrderByPopulationDesc(int min, int max) throws ExceptionFonctionnelle {
        List<Ville> villes = villeRepository.findByPopulationBetweenOrderByPopulationDesc(min, max);
        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée avec une population entre : " + min + " et " + max);
        }
        return villes.stream().map(VilleDTO::fromEntity).toList();
    }

    public List<VilleDTO> getVillesByDepartementCodeAndPopulationGreaterThanOrderByPopulationDesc(String code,
                                                                                                  int population) throws ExceptionFonctionnelle {
        List<Ville> villes = villeRepository.findByDepartementCodeAndPopulationGreaterThanOrderByPopulationDesc(code,
                population);
        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée dans le département " + code + " avec une population supérieure à : " + population);
        }
        return villes.stream().map(VilleDTO::fromEntity).toList();
    }

    public List<VilleDTO> getVilleByDepartementCodeAndPopulationBetweenOrderByPopulationDesc(String code, Integer min
            , Integer max) throws ExceptionFonctionnelle{
        List<Ville> villes = villeRepository.findByDepartementCodeAndPopulationBetweenOrderByPopulationDesc(code,
                min, max);
        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée dans le département " + code + " avec une population entre : " + min + " et " + max);
        }
        return villes.stream().map(VilleDTO::fromEntity).toList();
    }

    public List<VilleDTO> getTopNVillesByPopulation() throws  ExceptionFonctionnelle {
        List<Ville> villes = villeRepository.findTop10ByOrderByPopulationDesc();
        if (villes.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville trouvée.");
        }
        return villes.stream().map(VilleDTO::fromEntity).toList();
    }


}




