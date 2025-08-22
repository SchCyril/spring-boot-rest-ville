package fr.diginamic.hello.dto;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;

public record VilleDTO(String nom, Integer population, String departement_code) {

    // Mapper entité -> DTO
    public static VilleDTO fromEntity(Ville ville) {
        String departementCode = ville.getDepartement() != null ? ville.getDepartement().getCode() : null;
        return new VilleDTO(ville.getNom(), ville.getPopulation(), departementCode);
    }

    // Mapper DTO -> entité
    public Ville toEntity() {
        Ville v = new Ville();
        v.setNom(this.nom);
        v.setPopulation(this.population);

        if (this.departement_code != null) {
            Departement d = new Departement();
            d.setCode(this.departement_code);
            v.setDepartement(d);
        }

        return v;
    }
}
