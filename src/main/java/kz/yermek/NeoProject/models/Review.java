package kz.yermek.NeoProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "author_nickname")
    private String authorNickname;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image imageProfile;

    @Column(name = "text", length = 300)
    private String text;
}
