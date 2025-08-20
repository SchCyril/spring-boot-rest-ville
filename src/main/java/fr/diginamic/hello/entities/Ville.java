package fr.diginamic.hello.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull (message = "Le nom de la ville ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom de la ville doit contenir entre 2 et 50 caractères")
    private String nom;
    @Min(value = 1, message = "La population doit être supérieure à 0")
    private Integer population;
}
