package fr.diginamic.hello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "departement", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nom"}),
        @UniqueConstraint(columnNames = {"code"})
})
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le code du département doit être renseigné")
    @Size(min = 2, max = 3, message = "LE code département doit comporter 2 caractères")
    private String code;

    @NotBlank
    @Size(min = 3, message = "Le nom du département doit comporter au moins 3 lettres")
    private String nom;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Ville> villes = new ArrayList<>();
}
