package kz.yermek.NeoProject.repositories;

import kz.yermek.NeoProject.dto.ReviewDto;
import kz.yermek.NeoProject.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByTourId(Long tourId, Pageable pageable);
}
