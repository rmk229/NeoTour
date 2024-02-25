package kz.yermek.NeoProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tour_name")
    private String tourName;

    @Column(name = "description", length = 750)
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "recommended_seasons")
    private int recommendedSeasons;

    @OneToMany
    @JoinTable(
            name = "tour_image",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;

    @OneToMany(mappedBy = "tour")
    private List<Review> reviews;

    @Column(name = "is_special")
    private boolean isSpecial;

    @Column(name = "booking_count")
    private int bookingCount;

    @Column(name = "view_count")
    private int viewCount;

    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }
}
