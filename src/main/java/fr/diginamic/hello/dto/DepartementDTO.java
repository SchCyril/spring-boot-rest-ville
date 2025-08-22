package fr.diginamic.hello.dto;


import fr.diginamic.hello.entities.Departement;

public record DepartementDTO(String code) {

    // Mapper entité -> DTO
    public static DepartementDTO fromEntity(Departement departement) {
        return new DepartementDTO(departement.getCode());
    }

    // Mapper DTO -> entité
    public Departement toEntity() {
        Departement d = new Departement();
        d.setCode(this.code);
        return d;
    }
}
