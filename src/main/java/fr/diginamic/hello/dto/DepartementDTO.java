package fr.diginamic.hello.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.diginamic.hello.entities.Departement;
import jakarta.validation.constraints.NotBlank;

public record DepartementDTO(
        @NotBlank(message = "Le code département est obligatoire")
        String code,
        @NotBlank(message = "Le nom du département est obligatoire")
        String nom) {

    // Mapper entité -> DTO
    public static DepartementDTO fromEntity(Departement departement) {
        return new DepartementDTO(departement.getCode(), departement.getNom());
    }

    // Mapper DTO -> entité
    public Departement toEntity() {
        Departement d = new Departement();
        d.setCode(this.code);
        d.setNom(this.nom);
        return d;
    }
}
