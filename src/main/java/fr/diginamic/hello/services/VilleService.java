package fr.diginamic.hello.services;

import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {
    /**
     * Service pour gérer les opérations liées aux villes.
     */
    private final VilleRepository villeRepository;
    private final DepartementRepository departementRepository;

    /**
     * Constructeur de la classe VilleService.
     *
     * @param villeRepository le repository pour accéder aux données des villes
     */
    public VilleService(VilleRepository villeRepository, DepartementRepository departementRepository) {
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
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

    /**
     * Extrait une ville par son identifiant.
     *
     * @param idVille l'identifiant de la ville
     * @return une Optional contenant la ville si elle existe, sinon une exception est levée
     */

    public VilleDTO extractVille(int idVille) {
        return villeRepository.findById((long) idVille)
                .map(VilleDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas avec l'ID : " + idVille));
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
    public List<VilleDTO> insertVille(VilleDTO villeDto) {
        if (villeDto.nom() == null || villeDto.population() == null || villeDto.departement_code() == null) {
            throw new IllegalArgumentException("Le nom, la population et le département de la ville ne peuvent pas être nuls.");
        }

        // On mappe le DTO -> entité de base
        Ville ville = villeDto.toEntity();

        // On récupère le département depuis la base
        Departement departement = departementRepository.findByCode(villeDto.departement_code())
                .orElseThrow(() -> new EntityNotFoundException("Département introuvable avec le code : " + villeDto.departement_code()));

        ville.setDepartement(departement);

        // Sauvegarde
        villeRepository.save(ville);

        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();
    }

    public List<VilleDTO> updateVille(int idVille, VilleDTO villeModifiee) {
        // Récupérer la ville existante
        Ville villeExistante = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas avec l'ID : " + idVille));

        // Nom
        if (villeModifiee.nom() != null) {
            villeExistante.setNom(villeModifiee.nom());
        }

        // Population
        if (villeModifiee.population() != null) {
            villeExistante.setPopulation(villeModifiee.population());
        }

        // Département
        if (villeModifiee.departement_code() != null) {
            Departement departement = departementRepository.findByCode(villeModifiee.departement_code())
                    .orElseThrow(() -> new EntityNotFoundException("Département introuvable avec le code : " + villeModifiee.departement_code()));
            villeExistante.setDepartement(departement);
        }

        // Sauvegarde
        villeRepository.save(villeExistante);

        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();
    }

    public List<VilleDTO> deleteVille(int idVille) {
        Ville ville = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas avec l'ID : " + idVille));
        villeRepository.delete(ville);
        return villeRepository.findAll().stream()
                .map(VilleDTO::fromEntity)
                .toList();

    }


}




