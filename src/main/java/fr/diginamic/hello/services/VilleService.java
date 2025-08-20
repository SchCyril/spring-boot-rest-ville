package fr.diginamic.hello.services;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    /**
     * Service pour gérer les opérations liées aux villes.
     */
    private final VilleRepository villeRepository;

    /**
     * Constructeur de la classe VilleService.
     *
     * @param villeRepository le repository pour accéder aux données des villes
     */
    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    /**
     * Extrait la liste de toutes les villes.
     *
     * @return une liste de toutes les villes
     */
    public List<Ville> extractVilles() {
        return villeRepository.findAll();
    }

    /**
     * Extrait une ville par son identifiant.
     *
     * @param idVille l'identifiant de la ville
     * @return une Optional contenant la ville si elle existe, sinon une exception est levée
     */

    public Ville extractVille(int idVille) {
        return villeRepository.findById((long) idVille).orElseThrow(() -> new EntityNotFoundException("Ville n'existe" +
                " pas"));
    }

    /**
     * Extrait une ville par son nom.
     *
     * @param nom le nom de la ville
     * @return une Optional contenant le nom de la ville si elle existe, sinon une exception est levée
     */
    public Ville extractVille(String nom) {
        return villeRepository.findByNom(nom).orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas avec le nom : " + nom));
    }

    /**
     * Insère une nouvelle ville dans la base de données.
     *
     * @param ville la ville à insérer
     * @return une liste de toutes les villes après l'insertion
     */
    public List<Ville> insertVille(Ville ville) {
        if (villeRepository.findByNom(ville.getNom()).isPresent()) {
            throw new IllegalArgumentException("La ville " + ville.getNom() + " existe déjà.");
        }
        villeRepository.save(ville);
        return villeRepository.findAll();
    }

    /**
     * Modifie une ville existante.
     *
     * @param idVille       l'identifiant de la ville à modifier
     * @param villeModifiee les nouvelles informations de la ville
     * @return une Optional contenant la ville modifiée si elle existe, sinon une exception est levée
     */

    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville villeExistante = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas"));

        if (villeModifiee.getNom() != null) {
            villeExistante.setNom(villeModifiee.getNom());
        }
        if (villeModifiee.getPopulation() != null) {
            villeExistante.setPopulation(villeModifiee.getPopulation());
        }

        villeRepository.save(villeExistante);
        return villeRepository.findAll();
    }

    /**
     * Supprime une ville par son identifiant.
     *
     * @param idVille l'identifiant de la ville à supprimer
     * @return une Optional contenant la ville supprimée si elle existe, sinon une exception est levée
     */
    public List<Ville> supprimerVille(int idVille) {
        Ville villeExistante = villeRepository.findById((long) idVille)
                .orElseThrow(() -> new EntityNotFoundException("Ville n'existe pas"));

        villeRepository.delete(villeExistante);
        return villeRepository.findAll();

    }
}
