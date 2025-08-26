package fr.diginamic.hello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit contenir au moins 3 lettres")
    private String nom;
    @Column(name= "nb_habs")

    @NotNull(message = "La population est obligatoire")
    @Min(value = 10, message = "La ville doit contenir au moins 10 habitants")
    private Integer population;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Ville(String nom, Integer population, Departement departement) {
        this.nom = nom;
        this.population = population;
        this.departement = departement;
    }
}
