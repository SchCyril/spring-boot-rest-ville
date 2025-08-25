package fr.diginamic.hello.dto;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;

public record VilleDTO(String nom, Integer population, String departementCode) {

    // Mapper Entité -> DTO
    public static VilleDTO fromEntity(Ville ville) {
        return new VilleDTO(ville.getNom(), ville.getPopulation(),
                ville.getDepartement() != null ? ville.getDepartement().getCode() : null);
    }

    // Mapper DTO -> Entité (on passe le Département existant depuis le service)
    public Ville toEntity(Departement departement) {
        Ville v = new Ville();
        v.setNom(this.nom);
        v.setPopulation(this.population);
        v.setDepartement(departement); // on associe le vrai département de la DB
        return v;
    }
}
