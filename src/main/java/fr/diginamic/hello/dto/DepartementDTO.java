package fr.diginamic.hello.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public record DepartementDTO(int id,
                             String code,
                             String nom) {

    public DepartementDTO(int id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }
}
