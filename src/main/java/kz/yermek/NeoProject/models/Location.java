package kz.yermek.NeoProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "place")
    private String place;

    @Column(name = "country")
    private String country;

    @Column(name = "continent")
    private String continent;

    @OneToMany(mappedBy = "location")
    private List<Tour> tours;

    public Location(String place, String country, String continent) {
        this.place = place;
        this.country = country;
        this.continent = continent;
    }
}
